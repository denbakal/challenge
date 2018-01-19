package ua.challenge.hibernate.examples.performance.stored.procedure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.junit.Test;
import ua.challenge.hibernate.examples.jpa.JPAUnitTestCase;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.*;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StoredProcedureTest extends JPAUnitTestCase {

    /*
    * CREATE OR REPLACE FUNCTION count_clients(IN clientId bigint, OUT clientCount bigint)
        RETURNS bigint AS
        $BODY$
        BEGIN
            SELECT COUNT(*) INTO clientCount FROM clients;
        END;
        $BODY$
        LANGUAGE plpgsql;
    * */

    /*
    * CREATE OR REPLACE FUNCTION tmp_ids_insert(IN ids bigint[])
        RETURNS void AS
        $BODY$
            DECLARE
                i bigint;
            BEGIN
                i=1;
                while i<= array_length(ids, 1)
                    LOOP
                    INSERT INTO temp_ids VALUES (ids[i]);
                    i=i+1;
                END LOOP;
            END;
        $BODY$
        LANGUAGE plpgsql;
    * */

    @Test
    public void startFunction() {
        doInJPA(entityManager -> {
            Client client = new Client();
            client.setFirstName("Alex");
            client.setLastName("Felix");
            client.setJob("Manager");
            client.setCompany("TRK");
            client.setAge(34);

            entityManager.persist(client);

            client = new Client();
            client.setFirstName("Alex");
            client.setLastName("Felix");
            client.setJob("Manager");
            client.setCompany("TRK");
            client.setAge(34);

            entityManager.persist(client);
            entityManager.flush();

            StoredProcedureQuery query = entityManager
                    .createStoredProcedureQuery("count_clients")
                    .registerStoredProcedureParameter("clientId", Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("clientCount", Long.class, ParameterMode.OUT)
                    .setParameter("clientId", client.getId());

            query.execute();

            Long clientCount = (Long) query.getOutputParameterValue("clientCount");
            System.out.println("clientCount = " + clientCount);
        });
    }

    @Test
    public void createTempTable() {
        doInJPA(entityManager -> {
            entityManager
                    .createNativeQuery("CREATE TEMPORARY TABLE temp_ids (id bigint) ON COMMIT DROP;")
                    .executeUpdate();
        });
    }

    @Test
    public void createTempTableAndInsertData() {
        doInJPA(entityManager -> {
            entityManager
                    .createNativeQuery("CREATE TEMPORARY TABLE temp_ids (id bigint) ON COMMIT DROP")
                    .executeUpdate();

            entityManager
                    .createNativeQuery("INSERT INTO temp_ids(id) VALUES (111)")
                    .executeUpdate();

            List<Long> ids = entityManager
                    .createNativeQuery("SELECT * FROM temp_ids")
                    .getResultList();


            assertThat(ids.size()).isEqualTo(1);
        });
    }

    @Test(expected = Exception.class)
    public void insertIds() {
        doInJPA(entityManager -> {
            entityManager
                    .createNativeQuery("CREATE TEMPORARY TABLE temp_ids (id bigint) ON COMMIT DROP")
                    .executeUpdate();

            StoredProcedureQuery query = entityManager
                    .createStoredProcedureQuery("tmp_ids_insert")
                    .registerStoredProcedureParameter("ids", Long[].class, ParameterMode.IN)
                    .setParameter("ids", new Long[] {1L,10L, 89823L});

            query.execute();
        });
    }

    @Test
    public void insertIdsViaSession() {
        doInJPA(entityManager -> {
            Session session = entityManager.unwrap( Session.class );
            session.doWork( connection -> {
                try (Statement statement = connection.createStatement()) {
                    statement.execute("CREATE TEMPORARY TABLE temp_ids (id bigint) ON COMMIT DROP");
                }

                Array inArray = connection.createArrayOf("bigint", new Long[] {1L,10L, 89823L});

                try (CallableStatement function = connection.prepareCall(
                        "{ call tmp_ids_insert(?) }" )) {
                    function.setArray( 1, inArray);
                    function.execute();
                }

                try (Statement statement = connection.createStatement()) {
                    ResultSet resultSet = statement.executeQuery("select id from temp_ids");
                    while (resultSet.next()) {
                        int res = resultSet.getInt(1);
                        System.out.println("Id: " + res);
                    }
                }
            } );
        });
    }

    @Test
    public void insertIdsCombined() {
        doInJPA(entityManager -> {
            entityManager
                    .createNativeQuery("CREATE TEMPORARY TABLE temp_ids (id bigint) ON COMMIT DROP")
                    .executeUpdate();

            Session session = entityManager.unwrap( Session.class );
            session.doWork( connection -> {
                Array inArray = connection.createArrayOf("bigint", new Long[] {1L,10L, 89823L});

                try (CallableStatement function = connection.prepareCall(
                        "{ call tmp_ids_insert(?) }" )) {
                    function.setArray( 1, inArray);
                    function.execute();
                }
            } );

            List<BigInteger> result = entityManager
                    .createNativeQuery("SELECT * FROM temp_ids")
                    .getResultList();

            result.forEach(System.out::println);
        });
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Client.class
        };
    }

    @Entity
    @Table(name = "clients")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Client {
        @Id
        @GeneratedValue
        private Long id;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String job;
        private int age;
        private String company;
    }
}

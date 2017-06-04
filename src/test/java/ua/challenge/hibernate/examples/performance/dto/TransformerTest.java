package ua.challenge.hibernate.examples.performance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import ua.challenge.hibernate.examples.jpa.JPAUnitTestCase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TransformerTest extends JPAUnitTestCase{
    @Test
    public void transformSQL() {
        doInJPA(entityManager -> {
            Client client = new Client();
            client.setFirstName("Alex");
            client.setLastName("Felix");
            client.setJob("Manager");
            client.setCompany("TRK");
            client.setAge(34);

            entityManager.persist(client);

            List clientDTOList = entityManager.unwrap(Session.class)
                    .createNativeQuery("select firstName, lastName from clients")
                    .addScalar("firstName")
                    .addScalar("lastName")
                    .setResultTransformer(Transformers.aliasToBean(ClientDTO.class))
                    .list();

            ClientDTO clientDTO = (ClientDTO) clientDTOList.get(0);
            assertThat(clientDTO.getFirstName()).isEqualTo("Alex");
        });
    }

    @Test
    public void transformJPQL() {
        doInJPA(entityManager -> {
            Client client = new Client();
            client.setFirstName("Alex");
            client.setLastName("Felix");
            client.setJob("Manager");
            client.setCompany("TRK");
            client.setAge(34);

            entityManager.persist(client);

            List<ClientDTO> clientDTOS = entityManager.createQuery("select" +
                    " new ua.challenge.hibernate.examples.performance.dto.ClientDTO(" +
                    "c.firstName, c.lastName" +
                    ")" +
                    "from ua.challenge.hibernate.examples.performance.dto.TransformerTest$Client c", ClientDTO.class)
                    .getResultList();

            assertThat(clientDTOS.get(0).getFirstName()).isEqualTo("Alex");
        });
    }

    @Test
    public void transformResultSet() {
        doInJPA(entityManager -> {
            Client client = new Client();
            client.setFirstName("Alex");
            client.setLastName("Felix");
            client.setJob("Manager");
            client.setCompany("TRK");
            client.setAge(34);

            entityManager.persist(client);

            List<ClientDTO> clientDTOS = entityManager
                    .createQuery("select c.firstName, c.lastName from ua.challenge.hibernate.examples.performance.dto.TransformerTest$Client c")
                    .unwrap(org.hibernate.query.Query.class)
                    .setResultTransformer(
                            new ResultTransformer() {
                                @Override
                                public Object transformTuple(Object[] objects, String[] strings) {
                                    return new ClientDTO((String) objects[0], (String) objects[1]);
                                }

                                @Override
                                public List transformList(List list) {
                                    return list;
                                }
                            }
                    )
                    .getResultList();

            assertThat(clientDTOS.get(0).getFirstName()).isEqualTo("Alex");
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

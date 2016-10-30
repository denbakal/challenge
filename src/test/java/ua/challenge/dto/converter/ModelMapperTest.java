package ua.challenge.dto.converter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import ua.challenge.db.entity.Address;
import ua.challenge.db.entity.City;
import ua.challenge.db.entity.User;
import ua.challenge.dto.AddressDTO;
import ua.challenge.dto.UserDTO;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by d.bakal on 30.10.2016.
 * Tests for DTO projection
 */
public class ModelMapperTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testMapping() {
        City city = new City();
        city.setId(2L);
        city.setName("Kiev");

        Address address = new Address();
        address.setId(1L);
        address.setCity(city);
        address.setStreet("Main street");

        User user = new User();
        user.setId(4L);
        user.setFirstName("Bruno");
        user.setLastName("Mario");
        user.setBirthDay(new Date());
        user.setAddress(address);

        ModelMapper mapper = new ModelMapper();

        UserDTO userDTO = mapper.map(user, UserDTO.class);
        System.out.println("userDTO = " + userDTO);
        assertThat(userDTO.getFirstName()).isEqualTo(user.getFirstName());

        User userNew = mapper.map(userDTO, User.class);
        System.out.println("userNew = " + userNew);
        assertThat(userNew.getLastName()).isEqualTo(userDTO.getLastName());
    }

    @Test
    public void testSimpleMapping() {
        User user = new User();
        user.setId(4L);
        user.setFirstName("Bruno");
        user.setLastName("Mario");
        user.setBirthDay(new Date());

        ModelMapper mapper = new ModelMapper();

        UserDTO userDTO = mapper.map(user, UserDTO.class);
        System.out.println("userDTO = " + userDTO);
        assertThat(userDTO.getFirstName()).isEqualTo(user.getFirstName());

        User userNew = mapper.map(userDTO, User.class);
        System.out.println("userNew = " + userNew);
        assertThat(userNew.getLastName()).isEqualTo(userDTO.getLastName());
    }

    @Test
    public void testExplicitMapping() throws Exception {
        PropertyMap<User, UserDTO> userPropertyMap = new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                map().setFirstName(source.getLastName());
                // map(source.getLastName(), destination.getFirstName());
                skip(skip().getId());
                skip(skip().getBirthDay());
            }
        };

        User user = new User();
        user.setId(4L);
        user.setFirstName("Bruno");
        user.setLastName("Mario");
        user.setBirthDay(new Date());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        mapper.addMappings(userPropertyMap);

        UserDTO userDTO = mapper.map(user, UserDTO.class);
        System.out.println("user = " + user);
        System.out.println("userDTO = " + userDTO);
    }

    @Test
    public void testConverterMapping() throws Exception {
        Converter<String, String> toUpperCase = new AbstractConverter<String, String>() {
            @Override
            protected String convert(String source) {
                return source == null ? null : "Mr./Ms. " + source.toUpperCase();
            }
        };

        PropertyMap<User, UserDTO> propertyMap = new PropertyMap<User, UserDTO>() {
            @Override
            protected void configure() {
                using(toUpperCase).map().setFirstName(source.getFirstName());
            }
        };

        User user = new User();
        user.setId(4L);
        user.setFirstName("Bruno");
        user.setLastName("Mario");
        user.setBirthDay(new Date());

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(propertyMap);
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        System.out.println("userDTO = " + userDTO);
    }

    @Test
    public void testProviderMapping() {
        Provider<AddressDTO> provider = new AbstractProvider<AddressDTO>() {
            @Override
            protected AddressDTO get() {
                return null;
            }
        };

        City city = new City();
        city.setId(2L);
        city.setName("Kiev");

        Address address = new Address();
        address.setId(1L);
        address.setCity(city);
        address.setStreet("Main street");

        User user = new User();
        user.setId(4L);
        user.setFirstName("Bruno");
        user.setLastName("Mario");
        user.setBirthDay(new Date());
        user.setAddress(address);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
                .setProvider(provider);

        UserDTO userDTO = mapper.map(user, UserDTO.class);
        System.out.println("userDTO = " + userDTO);
        assertThat(userDTO.getFirstName()).isEqualTo(user.getFirstName());

        User userNew = mapper.map(userDTO, User.class);
        System.out.println("userNew = " + userNew);
        assertThat(userNew.getLastName()).isEqualTo(userDTO.getLastName());
    }

}
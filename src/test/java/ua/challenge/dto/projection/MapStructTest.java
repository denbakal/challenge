package ua.challenge.dto.projection;

import org.junit.Test;
import ua.challenge.dto.CityDTO;
import ua.challenge.dto.mapper.CityMapper;
import ua.challenge.hibernate.examples.audit.entity.City;

/**
 * Created by d.bakal on 21.04.2017.
 */
public class MapStructTest {
    @Test
    public void mapperTest() {
        // given
        City city = new City();
        city.setId(1L);
        city.setName("Kiev");

        //when
        CityDTO cityDTO = CityMapper.INSTANCE.toCityDTO(city);

        System.out.println("cityDTO = " + cityDTO);
    }
}

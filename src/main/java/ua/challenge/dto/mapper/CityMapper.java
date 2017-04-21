package ua.challenge.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.challenge.dto.CityDTO;
import ua.challenge.hibernate.examples.audit.entity.City;

/**
 * Created by d.bakal on 21.04.2017.
 */
@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDTO toCityDTO(City city);
}

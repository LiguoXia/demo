package com.liguo.demo.core.pojo.covert;

import com.liguo.demo.core.pojo.dos.CarDO;
import com.liguo.demo.core.pojo.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarCovert {
    CarCovert INSTANCE = Mappers.getMapper(CarCovert.class);

    @Mappings({
            @Mapping(source = "id1", target = "id2", defaultValue = "12"),
            @Mapping(target ="brand", ignore = true),
            @Mapping(source = "name", target = "carName")
    })
    CarDTO carDO2DTO(CarDO carDO);
}

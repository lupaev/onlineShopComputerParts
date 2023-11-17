package com.example.onlineshopcomputerparts.mapper;

import com.example.onlineshopcomputerparts.dto.HddDTO;
import com.example.onlineshopcomputerparts.entity.Hdd;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Маппер для Жесткого диска
 */


@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HddMapper {

  Hdd toEntity(HddDTO hddDTO);

  HddDTO toDTO(Hdd hdd);

  void updateHddFromDto(HddDTO hddDTO, @MappingTarget Hdd hdd);

  Collection<HddDTO> toDTOList(Collection<Hdd> list);

}

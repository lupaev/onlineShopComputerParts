package com.example.onlineshopcomputerparts.Mapper;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import com.example.onlineshopcomputerparts.Entity.Hdd;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HddMapper {

  Hdd toEntity(HddDTO hddDTO);
  HddDTO toDTO(Hdd hdd);
  void updateHddFromDto(HddDTO hddDTO, @MappingTarget Hdd hdd);

  Collection<HddDTO> toDTOList(Collection<Hdd> list);

}

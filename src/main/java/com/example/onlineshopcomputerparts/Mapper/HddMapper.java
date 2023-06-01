package com.example.onlineshopcomputerparts.Mapper;

import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.Entity.Hdd;
import org.mapstruct.Mapper;

@Mapper
public interface HddMapper {

  Hdd toEntity(HddDTO hddDTO);
  HddDTO toDTO(Hdd hdd);

}

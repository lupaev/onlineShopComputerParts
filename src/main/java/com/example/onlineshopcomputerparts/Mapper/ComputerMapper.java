package com.example.onlineshopcomputerparts.Mapper;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import org.mapstruct.Mapper;

@Mapper
public interface ComputerMapper {

  Computer toEntity(ComputerDTO computerDTO);
  ComputerDTO toDTO(Computer computer);

}

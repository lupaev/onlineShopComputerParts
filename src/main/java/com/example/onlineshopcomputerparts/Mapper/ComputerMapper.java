package com.example.onlineshopcomputerparts.Mapper;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Маппер для Компьютера
 */

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ComputerMapper {

  Computer toEntity(ComputerDTO computerDTO);

  ComputerDTO toDTO(Computer computer);

  void updateComputerFromDto(ComputerDTO computerDTO, @MappingTarget Computer computer);

  Collection<ComputerDTO> toDTOList(Collection<Computer> list);

}

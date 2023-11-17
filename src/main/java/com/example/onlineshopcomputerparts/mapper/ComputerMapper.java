package com.example.onlineshopcomputerparts.mapper;

import com.example.onlineshopcomputerparts.dto.ComputerDTO;
import com.example.onlineshopcomputerparts.entity.Computer;
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

package com.example.onlineshopcomputerparts.mapper;

import com.example.onlineshopcomputerparts.dto.MonitorDTO;
import com.example.onlineshopcomputerparts.entity.Monitor;
import java.util.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Маппер для Монитора
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MonitorMapper {

  Monitor toEntity(MonitorDTO monitorDTO);

  MonitorDTO toDTO(Monitor monitor);

  void updateMonitorFromDto(MonitorDTO monitorDTO, @MappingTarget Monitor monitor);

  Collection<MonitorDTO> toDTOList(Collection<Monitor> list);

}

package com.example.onlineshopcomputerparts.Mapper;

import com.example.onlineshopcomputerparts.DTO.MonitorDTO;
import com.example.onlineshopcomputerparts.Entity.Monitor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MonitorMapper {

  Monitor toEntity(MonitorDTO monitorDTO);
  MonitorDTO toDTO(Monitor monitor);
  void updateMonitorFromDto(MonitorDTO monitorDTO, @MappingTarget Monitor monitor);

}

package com.example.onlineshopcomputerparts.Mapper;

import com.example.onlineshopcomputerparts.DTO.MonitorDTO;
import com.example.onlineshopcomputerparts.Entity.Monitor;
import org.mapstruct.Mapper;

@Mapper
public interface MonitorMapper {

  Monitor toEntity(MonitorDTO monitorDTO);
  MonitorDTO toDTO(Monitor monitor);

}

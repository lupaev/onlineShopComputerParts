package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.DTO.MonitorDTO;
import java.util.Collection;

public interface MonitorService {

  MonitorDTO add(MonitorDTO monitorDTO);
  MonitorDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);
  Collection<MonitorDTO> findAll();

  MonitorDTO findById(Long id);
}

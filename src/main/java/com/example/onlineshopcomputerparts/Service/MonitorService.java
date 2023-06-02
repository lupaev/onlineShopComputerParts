package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.MonitorDTO;

public interface MonitorService {

  MonitorDTO add(MonitorDTO monitorDTO);
  MonitorDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);
}

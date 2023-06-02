package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.LaptopDTO;
import com.example.onlineshopcomputerparts.DTO.MonitorDTO;
import org.springframework.stereotype.Service;


public interface MonitorService {

  MonitorDTO add(MonitorDTO monitorDTO);

  MonitorDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);
}

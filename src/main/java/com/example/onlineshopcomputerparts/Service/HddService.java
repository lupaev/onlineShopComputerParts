package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.DTO.HddDTO;
import java.util.Collection;

public interface HddService {

  HddDTO add(HddDTO hddDTO);
  HddDTO patch(Long id, Integer VolumeGb, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);
  Collection<HddDTO> findAll();

}

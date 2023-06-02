package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.HddDTO;

public interface HddService {

  HddDTO add(HddDTO hddDTO);
  HddDTO patch(Long id, Integer VolumeGb, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);

}

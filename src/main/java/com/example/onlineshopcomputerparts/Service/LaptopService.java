package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.LaptopDTO;
import java.util.Collection;

public interface LaptopService {

  LaptopDTO add(LaptopDTO laptopDTO);

  LaptopDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);

  Collection<LaptopDTO> findAll();

  LaptopDTO findById(Long id);
}

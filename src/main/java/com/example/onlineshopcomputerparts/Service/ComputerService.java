package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import java.util.Collection;

public interface ComputerService {

  ComputerDTO add(ComputerDTO computerDTO);

  ComputerDTO patch(Long id, String form, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);

  Collection<ComputerDTO> findAll();

  ComputerDTO findById(Long id);
}

package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import org.springframework.stereotype.Service;


public interface ComputerService {

  ComputerDTO add(ComputerDTO computer);

}

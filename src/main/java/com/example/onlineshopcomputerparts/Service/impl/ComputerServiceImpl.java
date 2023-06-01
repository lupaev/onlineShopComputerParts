package com.example.onlineshopcomputerparts.Service.impl;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import com.example.onlineshopcomputerparts.Mapper.ComputerMapper;
import com.example.onlineshopcomputerparts.Repository.ComputerRepository;
import com.example.onlineshopcomputerparts.Service.ComputerService;
import org.springframework.stereotype.Service;

@Service
public class ComputerServiceImpl implements ComputerService {

  private final ComputerRepository computerRepository;

  private final ComputerMapper computerMapper;

  public ComputerServiceImpl(ComputerRepository computerRepository, ComputerMapper computerMapper) {
    this.computerRepository = computerRepository;
    this.computerMapper = computerMapper;
  }

  @Override
  public ComputerDTO add(ComputerDTO computerDTO) {
//    Computer computer = new Computer();
//    computer.setForm(computerDTO.getForm());
//    computer.setPrice(computerDTO.getPrice());
//    computer.setQuantity(computerDTO.getQuantity());
//    computer.setManufacturer(computerDTO.getManufacturer());
//    computer.setSerialNumber(computerDTO.getSerialNumber());
//    System.out.println(computer.toString());
    computerRepository.save(computerMapper.toEntity(computerDTO));
    return computerDTO;
  }
}

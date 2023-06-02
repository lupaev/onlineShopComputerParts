package com.example.onlineshopcomputerparts.Service.impl;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import com.example.onlineshopcomputerparts.Exception.ElemNotFound;
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
    computerRepository.save(computerMapper.toEntity(computerDTO));
    return computerDTO;
  }

//  @Override
//  public ComputerDTO change(Long id, String form, int serialNumber, String manufacturer,
//      double price, int quantity) {
//    Computer computer = computerRepository.findById(id)
//        .orElseThrow(() -> new ElemNotFound("Product not found on :: "+ id));
//    computer.setForm(form);
//    computer.setQuantity(quantity);
//    computer.setSerialNumber(serialNumber);
//    computer.setManufacturer(manufacturer);
//    computer.setPrice(price);
//    computerRepository.save(computer);
//    return computerMapper.toDTO(computer);
//  }


}

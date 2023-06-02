package com.example.onlineshopcomputerparts.Service.impl;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import com.example.onlineshopcomputerparts.Exception.ElemNotFound;
import com.example.onlineshopcomputerparts.Mapper.ComputerMapper;
import com.example.onlineshopcomputerparts.Repository.ComputerRepository;
import com.example.onlineshopcomputerparts.Service.ComputerService;
import java.util.ArrayList;
import java.util.Collection;
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



  @Override
  public ComputerDTO patch(Long id, String form, Integer serialNumber, String manufacturer,
      Double price, Integer quantity) {
    ComputerDTO computerDTO = new ComputerDTO(id, serialNumber, manufacturer, price, quantity, form);
    Computer computer = computerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: "+ id));
    computerMapper.updateComputerFromDto(computerDTO,computer);
    computerRepository.save(computer);
    return computerMapper.toDTO(computer);
  }

  @Override
  public Collection<ComputerDTO> findAll() {
    Collection<Computer> collection = computerRepository.findAll();
    return new ArrayList<>(computerMapper.toDTOList(collection));
  }

  @Override
  public ComputerDTO findById(Long id) {
    Computer computer = computerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: "+ id));
    return computerMapper.toDTO(computer);

  }


}

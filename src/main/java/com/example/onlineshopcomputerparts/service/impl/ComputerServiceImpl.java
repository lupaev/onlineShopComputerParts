package com.example.onlineshopcomputerparts.service.impl;

import com.example.onlineshopcomputerparts.dto.ComputerDTO;
import com.example.onlineshopcomputerparts.entity.Computer;
import com.example.onlineshopcomputerparts.exception.ElemNotFound;
import com.example.onlineshopcomputerparts.logger.FormLogInfo;
import com.example.onlineshopcomputerparts.mapper.ComputerMapper;
import com.example.onlineshopcomputerparts.repository.ComputerRepository;
import com.example.onlineshopcomputerparts.service.ComputerService;
import java.util.ArrayList;
import java.util.Collection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComputerServiceImpl implements ComputerService {

  private final ComputerRepository computerRepository;
  private final ComputerMapper computerMapper;

  @Override
  public ComputerDTO add(ComputerDTO computerDTO) {
    log.info(FormLogInfo.getInfo());
    computerRepository.save(computerMapper.toEntity(computerDTO));
    return computerDTO;
  }

  @Override
  public ComputerDTO patch(Long id, String form, Integer serialNumber, String manufacturer,
      Double price, Integer quantity) {
    log.info(FormLogInfo.getInfo());
    ComputerDTO computerDTO = new ComputerDTO(id, serialNumber, manufacturer, price, quantity,
        form);
    Computer computer = computerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    computerMapper.updateComputerFromDto(computerDTO, computer);
    computerRepository.save(computer);
    return computerMapper.toDTO(computer);
  }

  @Override
  public Collection<ComputerDTO> findAll() {
    log.info(FormLogInfo.getInfo());
    Collection<Computer> collection = computerRepository.findAll();
    return new ArrayList<>(computerMapper.toDTOList(collection));
  }

  @Override
  public ComputerDTO findById(Long id) {
    log.info(FormLogInfo.getInfo());
    Computer computer = computerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    return computerMapper.toDTO(computer);
  }
}

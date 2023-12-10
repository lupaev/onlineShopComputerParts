package com.example.onlineshopcomputerparts.service.impl;

import com.example.onlineshopcomputerparts.dto.ComputerDTO;
import com.example.onlineshopcomputerparts.dto.ComputerFullDTO;
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
  public ComputerFullDTO add(ComputerDTO computerDTO) {
    log.info(FormLogInfo.getInfo());
    Computer computer = computerRepository.save(computerMapper.toEntity(computerDTO));
    return computerMapper.toDTO(computer);
  }

  @Override
  public ComputerFullDTO patch(Long id, ComputerDTO computerDTO) {
    log.info(FormLogInfo.getInfo());
    Computer computer = computerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    computerMapper.updateComputerFromDto(computerDTO, computer);
    Computer updComputer = computerRepository.save(computer);
    return computerMapper.toDTO(updComputer);
  }

  @Override
  public Collection<ComputerFullDTO> findAll() {
    log.info(FormLogInfo.getInfo());
    Collection<Computer> collection = computerRepository.findAll();
    return new ArrayList<>(computerMapper.toDTOList(collection));
  }

  @Override
  public ComputerFullDTO findById(Long id) {
    log.info(FormLogInfo.getInfo());
    Computer computer = computerRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    return computerMapper.toDTO(computer);
  }
}

package com.example.onlineshopcomputerparts.service.impl;

import com.example.onlineshopcomputerparts.dto.LaptopDTO;
import com.example.onlineshopcomputerparts.dto.LaptopFullDTO;
import com.example.onlineshopcomputerparts.entity.Laptop;
import com.example.onlineshopcomputerparts.exception.ElemNotFound;
import com.example.onlineshopcomputerparts.logger.FormLogInfo;
import com.example.onlineshopcomputerparts.mapper.LaptopMapper;
import com.example.onlineshopcomputerparts.repository.LaptopRepository;
import com.example.onlineshopcomputerparts.service.LaptopService;
import java.util.ArrayList;
import java.util.Collection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LaptopServiceImpl implements LaptopService {

  private final LaptopRepository laptopRepository;
  private final LaptopMapper laptopMapper;

  @Override
  public LaptopFullDTO add(LaptopDTO laptopDTO) {
    log.info(FormLogInfo.getInfo());
    Laptop laptop = laptopRepository.save(laptopMapper.toEntity(laptopDTO));
    return laptopMapper.toDTO(laptop);
  }

  @Override
  public LaptopFullDTO patch(Long id, LaptopDTO laptopDTO) {
    log.info(FormLogInfo.getInfo());
    Laptop laptop = laptopRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    laptopMapper.updateLaptopFromDto(laptopDTO, laptop);
    Laptop updLaptop = laptopRepository.save(laptop);
    return laptopMapper.toDTO(updLaptop);
  }

  @Override
  public Collection<LaptopFullDTO> findAll() {
    log.info(FormLogInfo.getInfo());
    Collection<Laptop> collection = laptopRepository.findAll();
    return new ArrayList<>(laptopMapper.toDTOList(collection));
  }

  @Override
  public LaptopFullDTO findById(Long id) {
    log.info(FormLogInfo.getInfo());
    Laptop laptop = laptopRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    return laptopMapper.toDTO(laptop);
  }
}

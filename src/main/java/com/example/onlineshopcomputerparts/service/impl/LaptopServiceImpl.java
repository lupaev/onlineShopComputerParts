package com.example.onlineshopcomputerparts.service.impl;

import com.example.onlineshopcomputerparts.dto.LaptopDTO;
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
  public LaptopDTO add(LaptopDTO laptopDTO) {
    log.info(FormLogInfo.getInfo());
    laptopRepository.save(laptopMapper.toEntity(laptopDTO));
    return laptopDTO;
  }

  @Override
  public LaptopDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity) {
    log.info(FormLogInfo.getInfo());
    LaptopDTO laptopDTO = new LaptopDTO(id, serialNumber, manufacturer, price, quantity, diagonal);
    Laptop laptop = laptopRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    laptopMapper.updateLaptopFromDto(laptopDTO, laptop);
    laptopRepository.save(laptop);
    return laptopMapper.toDTO(laptop);
  }

  @Override
  public Collection<LaptopDTO> findAll() {
    log.info(FormLogInfo.getInfo());
    Collection<Laptop> collection = laptopRepository.findAll();
    return new ArrayList<>(laptopMapper.toDTOList(collection));
  }

  @Override
  public LaptopDTO findById(Long id) {
    log.info(FormLogInfo.getInfo());
    Laptop laptop = laptopRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    return laptopMapper.toDTO(laptop);
  }
}

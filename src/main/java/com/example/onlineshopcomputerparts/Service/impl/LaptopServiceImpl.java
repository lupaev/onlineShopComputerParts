package com.example.onlineshopcomputerparts.Service.impl;

import com.example.onlineshopcomputerparts.DTO.LaptopDTO;
import com.example.onlineshopcomputerparts.Mapper.LaptopMapper;
import com.example.onlineshopcomputerparts.Repository.LaptopRepository;
import com.example.onlineshopcomputerparts.Service.LaptopService;
import org.springframework.stereotype.Service;

@Service
public class LaptopServiceImpl implements LaptopService {

  private final LaptopRepository laptopRepository;

  private final LaptopMapper laptopMapper;

  public LaptopServiceImpl(LaptopRepository laptopRepository, LaptopMapper laptopMapper) {
    this.laptopRepository = laptopRepository;
    this.laptopMapper = laptopMapper;
  }

  @Override
  public LaptopDTO add(LaptopDTO laptopDTO) {
    laptopRepository.save(laptopMapper.toEntity(laptopDTO));
    return laptopDTO;
  }
}

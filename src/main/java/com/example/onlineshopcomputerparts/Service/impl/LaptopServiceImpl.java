package com.example.onlineshopcomputerparts.Service.impl;

import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.DTO.LaptopDTO;
import com.example.onlineshopcomputerparts.Entity.Hdd;
import com.example.onlineshopcomputerparts.Entity.Laptop;
import com.example.onlineshopcomputerparts.Exception.ElemNotFound;
import com.example.onlineshopcomputerparts.Mapper.LaptopMapper;
import com.example.onlineshopcomputerparts.Repository.LaptopRepository;
import com.example.onlineshopcomputerparts.Service.LaptopService;
import java.util.ArrayList;
import java.util.Collection;
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

  @Override
  public LaptopDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity) {
    LaptopDTO laptopDTO = new LaptopDTO(id, serialNumber, manufacturer, price, quantity, diagonal);
    Laptop laptop = laptopRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: "+ id));
    laptopMapper.updateLaptopFromDto(laptopDTO, laptop);
    laptopRepository.save(laptop);
    return laptopMapper.toDTO(laptop);
  }

  @Override
  public Collection<LaptopDTO> findAll() {
    Collection<Laptop> collection = laptopRepository.findAll();
    return new ArrayList<>(laptopMapper.toDTOList(collection));
  }
}

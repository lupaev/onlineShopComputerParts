package com.example.onlineshopcomputerparts.Service.impl;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import com.example.onlineshopcomputerparts.Entity.Hdd;
import com.example.onlineshopcomputerparts.Exception.ElemNotFound;
import com.example.onlineshopcomputerparts.Mapper.HddMapper;
import com.example.onlineshopcomputerparts.Repository.HddRepository;
import com.example.onlineshopcomputerparts.Service.HddService;
import org.springframework.stereotype.Service;

@Service
public class HddServiceImpl implements HddService {

  private final HddMapper hddMapper;

  private final HddRepository hddRepository;

  public HddServiceImpl(HddMapper hddMapper, HddRepository hddRepository) {
    this.hddMapper = hddMapper;
    this.hddRepository = hddRepository;
  }

  @Override
  public HddDTO add(HddDTO hddDTO) {
    hddRepository.save(hddMapper.toEntity(hddDTO));
    return hddDTO;
  }

  @Override
  public HddDTO patch(Long id, Integer volumeGb, Integer serialNumber, String manufacturer,
      Double price, Integer quantity) {
    HddDTO hddDTO = new HddDTO(id, serialNumber, manufacturer, price, quantity, volumeGb);
    Hdd hdd = hddRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: "+ id));
    hddMapper.updateHddFromDto(hddDTO, hdd);
    hddRepository.save(hdd);
    return hddMapper.toDTO(hdd);
  }
}

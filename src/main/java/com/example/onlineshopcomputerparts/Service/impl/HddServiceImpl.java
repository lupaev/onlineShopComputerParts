package com.example.onlineshopcomputerparts.Service.impl;

import com.example.onlineshopcomputerparts.DTO.HddDTO;
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
}

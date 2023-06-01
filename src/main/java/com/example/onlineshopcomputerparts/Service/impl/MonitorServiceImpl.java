package com.example.onlineshopcomputerparts.Service.impl;

import com.example.onlineshopcomputerparts.DTO.MonitorDTO;
import com.example.onlineshopcomputerparts.Mapper.MonitorMapper;
import com.example.onlineshopcomputerparts.Repository.MonitorRepository;
import com.example.onlineshopcomputerparts.Service.MonitorService;
import org.springframework.stereotype.Service;

@Service
public class MonitorServiceImpl implements MonitorService {

  private final MonitorMapper monitorMapper;

  private final MonitorRepository monitorRepository;

  public MonitorServiceImpl(MonitorMapper monitorMapper, MonitorRepository monitorRepository) {
    this.monitorMapper = monitorMapper;
    this.monitorRepository = monitorRepository;
  }

  @Override
  public MonitorDTO add(MonitorDTO monitorDTO) {
    monitorRepository.save(monitorMapper.toEntity(monitorDTO));
    return monitorDTO;
  }
}

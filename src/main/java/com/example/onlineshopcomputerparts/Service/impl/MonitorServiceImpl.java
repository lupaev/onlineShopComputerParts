package com.example.onlineshopcomputerparts.Service.impl;

import com.example.onlineshopcomputerparts.DTO.MonitorDTO;
import com.example.onlineshopcomputerparts.Entity.Monitor;
import com.example.onlineshopcomputerparts.Exception.ElemNotFound;
import com.example.onlineshopcomputerparts.Mapper.MonitorMapper;
import com.example.onlineshopcomputerparts.Repository.MonitorRepository;
import com.example.onlineshopcomputerparts.Service.MonitorService;
import java.util.ArrayList;
import java.util.Collection;
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

  public MonitorDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity) {
    MonitorDTO monitorDTO = new MonitorDTO(id, serialNumber, manufacturer, price, quantity,
        diagonal);
    Monitor monitor = monitorRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    monitorMapper.updateMonitorFromDto(monitorDTO, monitor);
    monitorRepository.save(monitor);
    return monitorMapper.toDTO(monitor);
  }

  @Override
  public Collection<MonitorDTO> findAll() {
    Collection<Monitor> collection = monitorRepository.findAll();
    return new ArrayList<>(monitorMapper.toDTOList(collection));
  }

  @Override
  public MonitorDTO findById(Long id) {
    Monitor monitor = monitorRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    return monitorMapper.toDTO(monitor);
  }
}

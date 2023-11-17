package com.example.onlineshopcomputerparts.service.impl;

import com.example.onlineshopcomputerparts.dto.MonitorDTO;
import com.example.onlineshopcomputerparts.entity.Monitor;
import com.example.onlineshopcomputerparts.exception.ElemNotFound;
import com.example.onlineshopcomputerparts.logger.FormLogInfo;
import com.example.onlineshopcomputerparts.mapper.MonitorMapper;
import com.example.onlineshopcomputerparts.repository.MonitorRepository;
import com.example.onlineshopcomputerparts.service.MonitorService;
import java.util.ArrayList;
import java.util.Collection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MonitorServiceImpl implements MonitorService {

  private final MonitorMapper monitorMapper;
  private final MonitorRepository monitorRepository;


  @Override
  public MonitorDTO add(MonitorDTO monitorDTO) {
    monitorRepository.save(monitorMapper.toEntity(monitorDTO));
    return monitorDTO;
  }

  public MonitorDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity) {
    log.info(FormLogInfo.getInfo());
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
    log.info(FormLogInfo.getInfo());
    Collection<Monitor> collection = monitorRepository.findAll();
    return new ArrayList<>(monitorMapper.toDTOList(collection));
  }

  @Override
  public MonitorDTO findById(Long id) {
    log.info(FormLogInfo.getInfo());
    Monitor monitor = monitorRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    return monitorMapper.toDTO(monitor);
  }
}

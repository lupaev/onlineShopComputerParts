package com.example.onlineshopcomputerparts.service.impl;

import com.example.onlineshopcomputerparts.dto.HddDTO;
import com.example.onlineshopcomputerparts.entity.Hdd;
import com.example.onlineshopcomputerparts.exception.ElemNotFound;
import com.example.onlineshopcomputerparts.logger.FormLogInfo;
import com.example.onlineshopcomputerparts.mapper.HddMapper;
import com.example.onlineshopcomputerparts.repository.HddRepository;
import com.example.onlineshopcomputerparts.service.HddService;
import java.util.ArrayList;
import java.util.Collection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HddServiceImpl implements HddService {

  private final HddMapper hddMapper;
  private final HddRepository hddRepository;

  @Override
  public HddDTO add(HddDTO hddDTO) {
    log.info(FormLogInfo.getInfo());
    hddRepository.save(hddMapper.toEntity(hddDTO));
    return hddDTO;
  }

  @Override
  public HddDTO patch(Long id, Integer volumeGb, Integer serialNumber, String manufacturer,
      Double price, Integer quantity) {
    log.info(FormLogInfo.getInfo());
    HddDTO hddDTO = new HddDTO(id, serialNumber, manufacturer, price, quantity, volumeGb);
    Hdd hdd = hddRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    hddMapper.updateHddFromDto(hddDTO, hdd);
    hddRepository.save(hdd);
    return hddMapper.toDTO(hdd);
  }

  @Override
  public Collection<HddDTO> findAll() {
    log.info(FormLogInfo.getInfo());
    Collection<Hdd> collection = hddRepository.findAll();
    return new ArrayList<>(hddMapper.toDTOList(collection));
  }

  @Override
  public HddDTO findById(Long id) {
    log.info(FormLogInfo.getInfo());
    Hdd hdd = hddRepository.findById(id)
        .orElseThrow(() -> new ElemNotFound("Product not found on :: " + id));
    return hddMapper.toDTO(hdd);
  }
}

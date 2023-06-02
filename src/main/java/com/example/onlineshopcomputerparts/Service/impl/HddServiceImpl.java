package com.example.onlineshopcomputerparts.Service.impl;

import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.Entity.Hdd;
import com.example.onlineshopcomputerparts.Exception.ElemNotFound;
import com.example.onlineshopcomputerparts.Loger.FormLogInfo;
import com.example.onlineshopcomputerparts.Mapper.HddMapper;
import com.example.onlineshopcomputerparts.Repository.HddRepository;
import com.example.onlineshopcomputerparts.Service.HddService;
import java.util.ArrayList;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HddServiceImpl implements HddService {

  private final HddMapper hddMapper;

  private final HddRepository hddRepository;

  public HddServiceImpl(HddMapper hddMapper, HddRepository hddRepository) {
    this.hddMapper = hddMapper;
    this.hddRepository = hddRepository;
  }

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

package com.example.onlineshopcomputerparts.mapper;

import com.example.onlineshopcomputerparts.dto.MonitorDTO;
import com.example.onlineshopcomputerparts.dto.MonitorFullDTO;
import com.example.onlineshopcomputerparts.entity.Monitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MonitorMapperTest {

  private final MonitorMapper monitorMapper = new MonitorMapperImpl();

  private MonitorDTO monitorDTO;

  private Monitor monitor;

  @BeforeEach
  void setUp() {
    monitorDTO = new MonitorDTO(1111, "Test1", 12601.98,
        11, 17);
    monitor= new Monitor();
    monitor.setId(1L);
    monitor.setPrice(12600.98);
    monitor.setManufacturer("Test");
    monitor.setDiagonal(24);
    monitor.setQuantity(1);
    monitor.setSerialNumber(111);
  }

  @AfterEach
  void afterEach() {
    monitor = null;
    monitorDTO = null;
  }

  @Test
  void toDTOPositive() {
    assertNotNull(monitor);
    assertNotNull(monitorDTO);
    MonitorFullDTO dto = monitorMapper.toDTO(monitor);
    assertNotNull(dto);
    assertThat(dto.getId()).isEqualTo(monitor.getId());
    assertThat(dto.getPrice()).isEqualTo(monitor.getPrice());
    assertThat(dto.getManufacturer()).isEqualTo(monitor.getManufacturer());
    assertThat(dto.getDiagonal()).isEqualTo(monitor.getDiagonal());
    assertThat(dto.getQuantity()).isEqualTo(monitor.getQuantity());
    assertThat(dto.getSerialNumber()).isEqualTo(monitor.getSerialNumber());
  }

  @Test
  void toListDTOPositive() {
    assertNotNull(monitor);
    Collection<MonitorFullDTO> dto = monitorMapper.toDTOList(List.of(monitor));
    assertNotNull(dto);
    assertTrue(dto.contains(monitorMapper.toDTO(monitor)));
  }

  @Test
  void toListDTONegative() {
    assertNotNull(monitor);
    Collection<MonitorFullDTO> dto = monitorMapper.toDTOList(List.of(monitor));
    assertNotNull(dto);
    assertTrue(dto.contains(monitorMapper.toDTO(monitor)));
  }

  @Test
  void updateComputerFromDto() {
    assertNotNull(monitorDTO);
    assertNotNull(monitor);
    monitorMapper.updateMonitorFromDto(monitorDTO, monitor);
    assertEquals(monitor.getSerialNumber(), monitorDTO.getSerialNumber());
  }
}
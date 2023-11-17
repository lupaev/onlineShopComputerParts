package com.example.onlineshopcomputerparts.mapper;

import com.example.onlineshopcomputerparts.dto.MonitorDTO;
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
    monitorDTO = new MonitorDTO(2L, 1111, "Test1", 12601.98,
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
    MonitorDTO dto = monitorMapper.toDTO(monitor);
    assertNotNull(dto);
    assertThat(dto.getId()).isEqualTo(monitor.getId());
    assertThat(dto.getPrice()).isEqualTo(monitor.getPrice());
    assertThat(dto.getManufacturer()).isEqualTo(monitor.getManufacturer());
    assertThat(dto.getDiagonal()).isEqualTo(monitor.getDiagonal());
    assertThat(dto.getQuantity()).isEqualTo(monitor.getQuantity());
    assertThat(dto.getSerialNumber()).isEqualTo(monitor.getSerialNumber());
  }

  @Test
  void toDTONegative() {
    assertNotNull(monitor);
    assertNotNull(monitorDTO);
    MonitorDTO dto = monitorMapper.toDTO(monitor);
    assertNotNull(dto);
    assertNotEquals(dto.getId(), monitorDTO.getId());
    assertNotEquals(dto.getPrice(), monitorDTO.getPrice());
    assertNotEquals(dto.getManufacturer(), monitorDTO.getManufacturer());
    assertNotEquals(dto.getDiagonal(), monitorDTO.getDiagonal());
    assertNotEquals(dto.getQuantity(), monitorDTO.getQuantity());
    assertNotEquals(dto.getSerialNumber(), monitorDTO.getSerialNumber());
  }

  @Test
  void toEntityPositive() {
    assertNotNull(monitor);
    assertNotNull(monitorDTO);
    Monitor l = monitorMapper.toEntity(monitorDTO);
    assertNotNull(l);
    assertThat(l.getId()).isEqualTo(monitorDTO.getId());
    assertThat(l.getPrice()).isEqualTo(monitorDTO.getPrice());
    assertThat(l.getManufacturer()).isEqualTo(monitorDTO.getManufacturer());
    assertThat(l.getDiagonal()).isEqualTo(monitorDTO.getDiagonal());
    assertThat(l.getQuantity()).isEqualTo(monitorDTO.getQuantity());
    assertThat(l.getSerialNumber()).isEqualTo(monitorDTO.getSerialNumber());
  }

  @Test
  void toEntityNegative() {
    assertNotNull(monitor);
    assertNotNull(monitorDTO);
    Monitor l = monitorMapper.toEntity(monitorDTO);
    assertNotNull(l);
    assertNotEquals(l.getId(), monitor.getId());
    assertNotEquals(l.getPrice(), monitor.getPrice());
    assertNotEquals(l.getManufacturer(), monitor.getManufacturer());
    assertNotEquals(l.getDiagonal(), monitor.getDiagonal());
    assertNotEquals(l.getQuantity(), monitor.getQuantity());
    assertNotEquals(l.getSerialNumber(), monitor.getSerialNumber());
  }

  @Test
  void toListDTOPositive() {
    assertNotNull(monitor);
    Collection<MonitorDTO> dto = monitorMapper.toDTOList(List.of(monitor));
    assertNotNull(dto);
    assertTrue(dto.contains(monitorMapper.toDTO(monitor)));
  }

  @Test
  void toListDTONegative() {
    assertNotNull(monitor);
    Collection<MonitorDTO> dto = monitorMapper.toDTOList(List.of(monitor));
    assertNotNull(dto);
    assertTrue(dto.contains(monitorMapper.toDTO(monitor)));
  }

  @Test
  void updateComputerFromDto() {
    assertNotNull(monitorDTO);
    assertNotNull(monitor);
    monitorMapper.updateMonitorFromDto(monitorDTO, monitor);
    assertEquals(monitor, monitorMapper.toEntity(monitorDTO));
    Monitor c = new Monitor();
    c.setId(1L);
    c.setPrice(12600.98);
    c.setManufacturer("Test");
    c.setDiagonal(6);
    c.setQuantity(1);
    c.setSerialNumber(111);
    assertNotEquals(monitor, c);
  }
}
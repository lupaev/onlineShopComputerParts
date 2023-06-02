package com.example.onlineshopcomputerparts.Mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import com.example.onlineshopcomputerparts.Entity.Hdd;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HddMapperTest {

  private HddMapper hddMapper = new HddMapperImpl();

  private HddDTO hddDTO;

  private Hdd hdd;

  @BeforeEach
  void setUp() {
    hddDTO = new HddDTO(2L, 1111, "Test1", 12601.98,
        11, 10);
    hdd = new Hdd();
    hdd.setId(1L);
    hdd.setPrice(12600.98);
    hdd.setManufacturer("Test");
    hdd.setVolumeGb(12);
    hdd.setQuantity(1);
    hdd.setSerialNumber(111);
  }

  @AfterEach
  void afterEach() {
    hdd = null;
    hddDTO = null;
  }

  @Test
  void toDTOPositiv() {
    assertNotNull(hdd);
    assertNotNull(hddDTO);
    HddDTO dto = hddMapper.toDTO(hdd);
    assertNotNull(dto);
    assertThat(dto.getId()).isEqualTo(hdd.getId());
    assertThat(dto.getPrice()).isEqualTo(hdd.getPrice());
    assertThat(dto.getManufacturer()).isEqualTo(hdd.getManufacturer());
    assertThat(dto.getVolumeGb()).isEqualTo(hdd.getVolumeGb());
    assertThat(dto.getQuantity()).isEqualTo(hdd.getQuantity());
    assertThat(dto.getSerialNumber()).isEqualTo(hdd.getSerialNumber());
  }

  @Test
  void toDTONegativ() {
    assertNotNull(hdd);
    assertNotNull(hddDTO);
    HddDTO dto = hddMapper.toDTO(hdd);
    assertNotNull(dto);
    assertNotEquals(dto.getId(), hddDTO.getId());
    assertNotEquals(dto.getPrice(), hddDTO.getPrice());
    assertNotEquals(dto.getManufacturer(), hddDTO.getManufacturer());
    assertNotEquals(dto.getVolumeGb(), hddDTO.getVolumeGb());
    assertNotEquals(dto.getQuantity(), hddDTO.getQuantity());
    assertNotEquals(dto.getSerialNumber(), hddDTO.getSerialNumber());
  }

  @Test
  void toEntityPositiv() {
    assertNotNull(hdd);
    assertNotNull(hddDTO);
    Hdd hdd1 = hddMapper.toEntity(hddDTO);
    assertNotNull(hdd1);
    assertThat(hdd1.getId()).isEqualTo(hddDTO.getId());
    assertThat(hdd1.getPrice()).isEqualTo(hddDTO.getPrice());
    assertThat(hdd1.getManufacturer()).isEqualTo(hddDTO.getManufacturer());
    assertThat(hdd1.getVolumeGb()).isEqualTo(hddDTO.getVolumeGb());
    assertThat(hdd1.getQuantity()).isEqualTo(hddDTO.getQuantity());
    assertThat(hdd1.getSerialNumber()).isEqualTo(hddDTO.getSerialNumber());
  }

  @Test
  void toEntityNegativ() {
    assertNotNull(hdd);
    assertNotNull(hddDTO);
    Hdd hdd1 = hddMapper.toEntity(hddDTO);
    assertNotNull(hdd1);
    assertNotEquals(hdd1.getId(), hdd.getId());
    assertNotEquals(hdd1.getPrice(), hdd.getPrice());
    assertNotEquals(hdd1.getManufacturer(), hdd.getManufacturer());
    assertNotEquals(hdd1.getVolumeGb(), hdd.getVolumeGb());
    assertNotEquals(hdd1.getQuantity(), hdd.getQuantity());
    assertNotEquals(hdd1.getSerialNumber(), hdd.getSerialNumber());
  }

  @Test
  void toListDTOPositiv() {
    assertNotNull(hdd);
    Collection<HddDTO> dto = hddMapper.toDTOList(List.of(hdd));
    assertNotNull(dto);
    assertTrue(dto.contains(hddMapper.toDTO(hdd)));
  }

  @Test
  void toListDTONegativ() {
    assertNotNull(hdd);
    Collection<HddDTO> dto = hddMapper.toDTOList(List.of(hdd));
    assertNotNull(dto);
    assertTrue(dto.contains(hddMapper.toDTO(hdd)));
  }

  @Test
  void updateComputerFromDto() {
    assertNotNull(hddDTO);
    assertNotNull(hdd);
    hddMapper.updateHddFromDto(hddDTO, hdd);
    assertEquals(hdd, hddMapper.toEntity(hddDTO));
    Hdd c = new Hdd();
    c.setId(1L);
    c.setPrice(12600.98);
    c.setManufacturer("Test");
    c.setVolumeGb(6);
    c.setQuantity(1);
    c.setSerialNumber(111);
    assertNotEquals(hdd, c);
  }
}
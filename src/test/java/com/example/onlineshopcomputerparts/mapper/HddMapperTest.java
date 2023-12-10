package com.example.onlineshopcomputerparts.mapper;

import com.example.onlineshopcomputerparts.dto.HddDTO;
import com.example.onlineshopcomputerparts.dto.HddFullDTO;
import com.example.onlineshopcomputerparts.entity.Hdd;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HddMapperTest {

  private final HddMapper hddMapper = new HddMapperImpl();

  private HddDTO hddDTO;

  private Hdd hdd;

  @BeforeEach
  void setUp() {
    hddDTO = new HddDTO(1111, "Test1", 12601.98,
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
  void toDTOPositive() {
    assertNotNull(hdd);
    assertNotNull(hddDTO);
    HddFullDTO dto = hddMapper.toDTO(hdd);
    assertNotNull(dto);
    assertThat(dto.getId()).isEqualTo(hdd.getId());
    assertThat(dto.getPrice()).isEqualTo(hdd.getPrice());
    assertThat(dto.getManufacturer()).isEqualTo(hdd.getManufacturer());
    assertThat(dto.getVolumeGb()).isEqualTo(hdd.getVolumeGb());
    assertThat(dto.getQuantity()).isEqualTo(hdd.getQuantity());
    assertThat(dto.getSerialNumber()).isEqualTo(hdd.getSerialNumber());
  }

  @Test
  void toEntityPositive() {
    assertNotNull(hdd);
    assertNotNull(hddDTO);
    Hdd hdd1 = hddMapper.toEntity(hddDTO);
    assertNotNull(hdd1);
    assertThat(hdd1.getPrice()).isEqualTo(hddDTO.getPrice());
    assertThat(hdd1.getManufacturer()).isEqualTo(hddDTO.getManufacturer());
    assertThat(hdd1.getVolumeGb()).isEqualTo(hddDTO.getVolumeGb());
    assertThat(hdd1.getQuantity()).isEqualTo(hddDTO.getQuantity());
    assertThat(hdd1.getSerialNumber()).isEqualTo(hddDTO.getSerialNumber());
  }

  @Test
  void toEntityNegative() {
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
  void toListDTOPositive() {
    assertNotNull(hdd);
    Collection<HddFullDTO> dto = hddMapper.toDTOList(List.of(hdd));
    assertNotNull(dto);
    assertTrue(dto.contains(hddMapper.toDTO(hdd)));
  }

  @Test
  void toListDTONegative() {
    assertNotNull(hdd);
    Collection<HddFullDTO> dto = hddMapper.toDTOList(List.of(hdd));
    assertNotNull(dto);
    assertTrue(dto.contains(hddMapper.toDTO(hdd)));
  }

  @Test
  void updateComputerFromDto() {
    assertNotNull(hddDTO);
    assertNotNull(hdd);
    hddMapper.updateHddFromDto(hddDTO, hdd);
    assertEquals(hdd.getSerialNumber(), hddDTO.getSerialNumber());
  }
}
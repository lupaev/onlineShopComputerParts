package com.example.onlineshopcomputerparts.Mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.DTO.LaptopDTO;
import com.example.onlineshopcomputerparts.Entity.Hdd;
import com.example.onlineshopcomputerparts.Entity.Laptop;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LaptopMapperTest {

  private LaptopMapper laptopMapper = new LaptopMapperImpl();

  private LaptopDTO laptopDTO;

  private Laptop laptop;

  @BeforeEach
  void setUp() {
    laptopDTO = new LaptopDTO(2L, 1111, "Test1", 12601.98,
        11, 17);
    laptop= new Laptop();
    laptop.setId(1L);
    laptop.setPrice(12600.98);
    laptop.setManufacturer("Test");
    laptop.setDiagonal(24);
    laptop.setQuantity(1);
    laptop.setSerialNumber(111);
  }

  @AfterEach
  void afterEach() {
    laptop = null;
    laptopDTO = null;
  }

  @Test
  void toDTOPositive() {
    assertNotNull(laptop);
    assertNotNull(laptopDTO);
    LaptopDTO dto = laptopMapper.toDTO(laptop);
    assertNotNull(dto);
    assertThat(dto.getId()).isEqualTo(laptop.getId());
    assertThat(dto.getPrice()).isEqualTo(laptop.getPrice());
    assertThat(dto.getManufacturer()).isEqualTo(laptop.getManufacturer());
    assertThat(dto.getDiagonal()).isEqualTo(laptop.getDiagonal());
    assertThat(dto.getQuantity()).isEqualTo(laptop.getQuantity());
    assertThat(dto.getSerialNumber()).isEqualTo(laptop.getSerialNumber());
  }

  @Test
  void toDTONegative() {
    assertNotNull(laptop);
    assertNotNull(laptopDTO);
    LaptopDTO dto = laptopMapper.toDTO(laptop);
    assertNotNull(dto);
    assertNotEquals(dto.getId(), laptopDTO.getId());
    assertNotEquals(dto.getPrice(), laptopDTO.getPrice());
    assertNotEquals(dto.getManufacturer(), laptopDTO.getManufacturer());
    assertNotEquals(dto.getDiagonal(), laptopDTO.getDiagonal());
    assertNotEquals(dto.getQuantity(), laptopDTO.getQuantity());
    assertNotEquals(dto.getSerialNumber(), laptopDTO.getSerialNumber());
  }

  @Test
  void toEntityPositive() {
    assertNotNull(laptop);
    assertNotNull(laptopDTO);
    Laptop l = laptopMapper.toEntity(laptopDTO);
    assertNotNull(l);
    assertThat(l.getId()).isEqualTo(laptopDTO.getId());
    assertThat(l.getPrice()).isEqualTo(laptopDTO.getPrice());
    assertThat(l.getManufacturer()).isEqualTo(laptopDTO.getManufacturer());
    assertThat(l.getDiagonal()).isEqualTo(laptopDTO.getDiagonal());
    assertThat(l.getQuantity()).isEqualTo(laptopDTO.getQuantity());
    assertThat(l.getSerialNumber()).isEqualTo(laptopDTO.getSerialNumber());
  }

  @Test
  void toEntityNegative() {
    assertNotNull(laptop);
    assertNotNull(laptopDTO);
    Laptop l = laptopMapper.toEntity(laptopDTO);
    assertNotNull(l);
    assertNotEquals(l.getId(), laptop.getId());
    assertNotEquals(l.getPrice(), laptop.getPrice());
    assertNotEquals(l.getManufacturer(), laptop.getManufacturer());
    assertNotEquals(l.getDiagonal(), laptop.getDiagonal());
    assertNotEquals(l.getQuantity(), laptop.getQuantity());
    assertNotEquals(l.getSerialNumber(), laptop.getSerialNumber());
  }

  @Test
  void toListDTOPositive() {
    assertNotNull(laptop);
    Collection<LaptopDTO> dto = laptopMapper.toDTOList(List.of(laptop));
    assertNotNull(dto);
    assertTrue(dto.contains(laptopMapper.toDTO(laptop)));
  }

  @Test
  void toListDTONegative() {
    assertNotNull(laptop);
    Collection<LaptopDTO> dto = laptopMapper.toDTOList(List.of(laptop));
    assertNotNull(dto);
    assertTrue(dto.contains(laptopMapper.toDTO(laptop)));
  }

  @Test
  void updateComputerFromDto() {
    assertNotNull(laptopDTO);
    assertNotNull(laptop);
    laptopMapper.updateLaptopFromDto(laptopDTO, laptop);
    assertEquals(laptop, laptopMapper.toEntity(laptopDTO));
    Laptop c = new Laptop();
    c.setId(1L);
    c.setPrice(12600.98);
    c.setManufacturer("Test");
    c.setDiagonal(6);
    c.setQuantity(1);
    c.setSerialNumber(111);
    assertNotEquals(laptop, c);
  }
}
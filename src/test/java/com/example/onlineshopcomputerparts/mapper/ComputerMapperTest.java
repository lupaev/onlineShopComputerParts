package com.example.onlineshopcomputerparts.mapper;

import com.example.onlineshopcomputerparts.dto.ComputerDTO;
import com.example.onlineshopcomputerparts.dto.ComputerFullDTO;
import com.example.onlineshopcomputerparts.entity.Computer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ComputerMapperTest {

  private final ComputerMapper computerMapper = new ComputerMapperImpl();
  private ComputerDTO computerDTO;
  private Computer computer;
  private ComputerFullDTO computerFullDTO;

  @BeforeEach
  void setUp() {
    computerDTO = new ComputerDTO(1111, "Test1", 12601.98,
        11, "mini1");
    computerFullDTO = new ComputerFullDTO(1L, 1111, "Test1", 12601.98,
            11, "mini1");

    computer = new Computer();
    computer.setId(1L);
    computer.setPrice(12600.98);
    computer.setManufacturer("Test");
    computer.setForm("mini");
    computer.setQuantity(1);
    computer.setSerialNumber(111);

  }

  @AfterEach
  void afterEach() {
    computer = null;
    computerDTO = null;
    computerFullDTO = null;
  }

  @Test
  void toDTOPositive() {
    assertNotNull(computer);
    assertNotNull(computerDTO);
    assertNotNull(computerFullDTO);
    ComputerFullDTO dto = computerMapper.toDTO(computer);
    assertNotNull(dto);
    assertThat(dto.getPrice()).isEqualTo(computer.getPrice());
    assertThat(dto.getManufacturer()).isEqualTo(computer.getManufacturer());
    assertThat(dto.getForm()).isEqualTo(computer.getForm());
    assertThat(dto.getQuantity()).isEqualTo(computer.getQuantity());
    assertThat(dto.getSerialNumber()).isEqualTo(computer.getSerialNumber());
  }


  @Test
  void toEntityPositive() {
    assertNotNull(computer);
    assertNotNull(computerDTO);
    Computer comp = computerMapper.toEntity(computerDTO);
    assertNotNull(comp);
    assertThat(comp.getPrice()).isEqualTo(computerDTO.getPrice());
    assertThat(comp.getManufacturer()).isEqualTo(computerDTO.getManufacturer());
    assertThat(comp.getForm()).isEqualTo(computerDTO.getForm());
    assertThat(comp.getQuantity()).isEqualTo(computerDTO.getQuantity());
    assertThat(comp.getSerialNumber()).isEqualTo(computerDTO.getSerialNumber());
  }

  @Test
  void toEntityNegative() {
    assertNotNull(computer);
    assertNotNull(computerDTO);
    Computer comp = computerMapper.toEntity(computerDTO);
    assertNotNull(comp);
    assertNotEquals(comp.getId(), computer.getId());
    assertNotEquals(comp.getPrice(), computer.getPrice());
    assertNotEquals(comp.getManufacturer(), computer.getManufacturer());
    assertNotEquals(comp.getForm(), computer.getForm());
    assertNotEquals(comp.getQuantity(), computer.getQuantity());
    assertNotEquals(comp.getSerialNumber(), computer.getSerialNumber());
  }

  @Test
  void toListDTOPositive() {
    assertNotNull(computer);
    Collection<ComputerFullDTO> dto = computerMapper.toDTOList(List.of(computer));
    assertNotNull(dto);
    assertTrue(dto.contains(computerMapper.toDTO(computer)));
  }

  @Test
  void toListDTONegative() {
    assertNotNull(computer);
    Collection<ComputerFullDTO> dto = computerMapper.toDTOList(List.of(computer));
    assertNotNull(dto);
    assertTrue(dto.contains(computerMapper.toDTO(computer)));
  }

  @Test
  void updateComputerFromDto() {
    assertNotNull(computerDTO);
    assertNotNull(computer);
    computerMapper.updateComputerFromDto(computerDTO, computer);
    assertEquals(computerDTO.getSerialNumber(), computer.getSerialNumber());
   }

}
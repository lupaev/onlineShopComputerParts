package com.example.onlineshopcomputerparts.Mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComputerMapperTest {

  private ComputerMapper computerMapper = new ComputerMapperImpl();

  private ComputerDTO computerDTO;

  private Computer computer;

  @BeforeEach
  void setUp() {
    computerDTO = new ComputerDTO(2L, 1111, "Test1", 12601.98,
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
  }

  @Test
  void toDTOPositive() {
    assertNotNull(computer);
    assertNotNull(computerDTO);
    ComputerDTO dto = computerMapper.toDTO(computer);
    assertNotNull(dto);
    assertThat(dto.getId()).isEqualTo(computer.getId());
    assertThat(dto.getPrice()).isEqualTo(computer.getPrice());
    assertThat(dto.getManufacturer()).isEqualTo(computer.getManufacturer());
    assertThat(dto.getForm()).isEqualTo(computer.getForm());
    assertThat(dto.getQuantity()).isEqualTo(computer.getQuantity());
    assertThat(dto.getSerialNumber()).isEqualTo(computer.getSerialNumber());
  }

  @Test
  void toDTONegative() {
    assertNotNull(computer);
    assertNotNull(computerDTO);
    ComputerDTO dto = computerMapper.toDTO(computer);
    assertNotNull(dto);
    assertNotEquals(dto.getId(), computerDTO.getId());
    assertNotEquals(dto.getPrice(), computerDTO.getPrice());
    assertNotEquals(dto.getManufacturer(), computerDTO.getManufacturer());
    assertNotEquals(dto.getForm(), computerDTO.getForm());
    assertNotEquals(dto.getQuantity(), computerDTO.getQuantity());
    assertNotEquals(dto.getSerialNumber(), computerDTO.getSerialNumber());
  }

  @Test
  void toEntityPositive() {
    assertNotNull(computer);
    assertNotNull(computerDTO);
    Computer comp = computerMapper.toEntity(computerDTO);
    assertNotNull(comp);
    assertThat(comp.getId()).isEqualTo(computerDTO.getId());
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
    Collection<ComputerDTO> dto = computerMapper.toDTOList(List.of(computer));
    assertNotNull(dto);
    assertTrue(dto.contains(computerMapper.toDTO(computer)));
  }

  @Test
  void toListDTONegative() {
    assertNotNull(computer);
    Collection<ComputerDTO> dto = computerMapper.toDTOList(List.of(computer));
    assertNotNull(dto);
    assertTrue(dto.contains(computerMapper.toDTO(computer)));
  }

  @Test
  void updateComputerFromDto() {
    assertNotNull(computerDTO);
    assertNotNull(computer);
    computerMapper.updateComputerFromDto(computerDTO, computer);
    assertEquals(computer, computerMapper.toEntity(computerDTO));
    Computer c = new Computer();
    c.setId(1L);
    c.setPrice(12600.98);
    c.setManufacturer("Test");
    c.setForm("mini");
    c.setQuantity(1);
    c.setSerialNumber(111);
    assertNotEquals(computer, c);
   }

}
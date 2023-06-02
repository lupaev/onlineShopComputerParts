package com.example.onlineshopcomputerparts.Mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import org.junit.jupiter.api.Test;

class ComputerMapperTest {

  private ComputerMapper computerMapper = new ComputerMapperImpl();

  @Test
  void given_customer_entity_mapper_should_map_all_fields_to_customer_dto() {
    Computer computer = new Computer();
    computer.setId(1L);
    computer.setPrice(10);
    computer.setManufacturer("ivi");
    computer.setForm("d");
    computer.setQuantity(100);
    computer.setSerialNumber(101);
    ComputerDTO computerDTO = computerMapper.toDTO(computer);
    assertThat(computerDTO.getId()).isEqualTo(1L);
    assertThat(computerDTO.getPrice()).isEqualTo(10);
    assertThat(computerDTO.getManufacturer()).isEqualTo("ivi");
    assertThat(computerDTO.getForm()).isEqualTo("d");
    assertThat(computerDTO.getQuantity()).isEqualTo(100);
    assertThat(computerDTO.getSerialNumber()).isEqualTo(101);

  }

  @Test
  void given_customer_dto_mapper_should_map_all_fields_to_customer_entity() {
    ComputerDTO computerDTO = new ComputerDTO();
    computerDTO.setId(1L);
    computerDTO.setPrice(10);
    computerDTO.setManufacturer("ivi");
    computerDTO.setForm("d");
    computerDTO.setQuantity(100);
    computerDTO.setSerialNumber(101);
    Computer computer = computerMapper.toEntity(computerDTO);
    assertThat(computer.getId()).isEqualTo(1L);
    assertThat(computer.getPrice()).isEqualTo(10);
    assertThat(computer.getManufacturer()).isEqualTo("ivi");
    assertThat(computer.getForm()).isEqualTo("d");
    assertThat(computer.getQuantity()).isEqualTo(100);
    assertThat(computer.getSerialNumber()).isEqualTo(101);
  }


}
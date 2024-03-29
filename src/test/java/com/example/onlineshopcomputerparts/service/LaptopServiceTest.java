package com.example.onlineshopcomputerparts.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.onlineshopcomputerparts.dto.LaptopDTO;
import com.example.onlineshopcomputerparts.dto.LaptopFullDTO;
import com.example.onlineshopcomputerparts.entity.Laptop;
import com.example.onlineshopcomputerparts.exception.ElemNotFound;
import com.example.onlineshopcomputerparts.mapper.LaptopMapper;
import com.example.onlineshopcomputerparts.repository.LaptopRepository;
import com.example.onlineshopcomputerparts.service.impl.LaptopServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LaptopServiceTest {

  @InjectMocks
  private LaptopServiceImpl service;
  @Mock
  private LaptopRepository repository;
  @Spy
  private LaptopMapper mapper;

  private LaptopDTO dto;
  private Laptop laptop;
  private LaptopFullDTO laptopFullDTO;

  @BeforeEach
  void setUp() {
    dto = new LaptopDTO(1111, "Test1", 12601.98,
        11, 10);
    laptopFullDTO = new LaptopFullDTO(2L, 1111, "Test1", 12601.98,
            11, 10);
    laptop = new Laptop();
    laptop.setId(laptopFullDTO.getId());
    laptop.setPrice(laptopFullDTO.getPrice());
    laptop.setManufacturer(laptopFullDTO.getManufacturer());
    laptop.setDiagonal(laptopFullDTO.getDiagonal());
    laptop.setQuantity(laptopFullDTO.getQuantity());
    laptop.setSerialNumber(laptopFullDTO.getSerialNumber());
  }

  @AfterEach
  void clearTestData() {
    dto = null;
    laptop = null;
    laptopFullDTO = null;
  }

  @Test
  void addPositiveTest() {
    LaptopServiceImpl service = mock(LaptopServiceImpl.class);
    LaptopMapper mapper = mock(LaptopMapper.class);

    when(mapper.toEntity(dto)).thenReturn(laptop);
    when(service.add(dto)).thenReturn(laptopFullDTO);
    when(repository.save(any(Laptop.class))).thenReturn(laptop);
    assertThat(service.add(dto)).isNotNull().isEqualTo(laptopFullDTO).isExactlyInstanceOf(LaptopFullDTO.class);
    assertThat(mapper.toEntity(dto)).isNotNull().isEqualTo(laptop)
        .isExactlyInstanceOf(Laptop.class);
    assertThat(repository.save(laptop)).isNotNull().isExactlyInstanceOf(Laptop.class);

    verify(repository, times(1)).save(laptop);
    verify(service, times(1)).add(dto);
    verify(mapper, times(1)).toEntity(dto);
  }

  @Test
  void addNegativeTest() {
    LaptopServiceImpl service = mock(LaptopServiceImpl.class);
    LaptopMapper mapper = mock(LaptopMapper.class);

    when(mapper.toEntity(any())).thenThrow(NullPointerException.class);
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.add(any())).thenThrow(RuntimeException.class);
    assertThrows(NullPointerException.class, () -> mapper.toEntity(any()));
    assertThrows(RuntimeException.class, () -> repository.save(any(Laptop.class)));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> service.add(dto));

    verify(repository, times(1)).save(any());
    verify(service, times(1)).add(any());
    verify(mapper, times(1)).toEntity(any());
  }

  @Test
  void patchPositiveTest() {
    LaptopServiceImpl service = mock(LaptopServiceImpl.class);
    LaptopMapper mapper = mock(LaptopMapper.class);
    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(laptop));
    when(mapper.toDTO(laptop)).thenReturn(laptopFullDTO);
    doNothing().when(mapper).updateLaptopFromDto(dto, laptop);
    when(repository.save(any(Laptop.class))).thenReturn(laptop);
    when(service.patch(2L, dto)).thenReturn(laptopFullDTO);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(laptop)).isNotNull().isEqualTo(laptopFullDTO)
        .isExactlyInstanceOf(LaptopFullDTO.class);
    assertDoesNotThrow(
        () -> mapper.updateLaptopFromDto(dto, laptop));
    assertThat(repository.save(laptop)).isNotNull().isExactlyInstanceOf(Laptop.class);

    assertThat(
        service.patch(2L, dto)).isNotNull().isEqualTo(laptopFullDTO)
        .isExactlyInstanceOf(LaptopFullDTO.class);

    verify(repository, times(1)).save(laptop);
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Laptop.class));
    verify(mapper, times(1)).updateLaptopFromDto(any(LaptopDTO.class), any(Laptop.class));

    verify(service, times(1)).patch(2L, dto);

  }

  @Test
  void patchNegativeTest() {
    LaptopServiceImpl service = mock(LaptopServiceImpl.class);
    LaptopMapper mapper = mock(LaptopMapper.class);

    when(repository.findById(anyLong())).thenThrow(ElemNotFound.class);
    when(mapper.toDTO(any())).thenThrow(NullPointerException.class);
    doThrow(NullPointerException.class).when(mapper).updateLaptopFromDto(any(), any());
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.patch(2L, dto)).thenThrow(RuntimeException.class);
    assertThrows(ElemNotFound.class, () -> repository.findById(anyLong()));
    assertThrows(NullPointerException.class, () -> mapper.toDTO(any()));
    assertThrows(NullPointerException.class, () -> mapper.updateLaptopFromDto(any(), any()));
    assertThrows(RuntimeException.class, () -> repository.save(any()));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
        () -> service.patch(2L, dto));

    verify(repository, times(1)).save(any());
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any());
    verify(mapper, times(1)).updateLaptopFromDto(any(), any());

    verify(service, times(1)).patch(2L, dto);

  }

  @Test
  void findAllPositiveTest() {
    LaptopServiceImpl service = mock(LaptopServiceImpl.class);
    LaptopMapper mapper = mock(LaptopMapper.class);

    when(repository.findAll()).thenReturn(List.of(laptop));
    when(mapper.toDTOList(List.of(laptop))).thenReturn(List.of(laptopFullDTO));
    when(service.findAll()).thenReturn(List.of(laptopFullDTO));

    assertThat(repository.findAll()).isNotNull().isEqualTo(List.of(laptop));
    assertThat(mapper.toDTOList(List.of(laptop))).isNotNull().isEqualTo(List.of(laptopFullDTO));
    assertThat(service.findAll()).isNotNull().isEqualTo(List.of(laptopFullDTO));

    verify(repository, times(1)).findAll();
    verify(service, times(1)).findAll();
    verify(mapper, times(1)).toDTOList(List.of(laptop));
  }

  @Test
  void findAllNegativeTest() {
    LaptopServiceImpl service = mock(LaptopServiceImpl.class);

    when(repository.findAll()).thenThrow(ElemNotFound.class);
    when(service.findAll()).thenThrow(RuntimeException.class);

    assertThrows(ElemNotFound.class, () -> repository.findAll());
    assertThrows(RuntimeException.class, service::findAll);

    verify(repository, times(1)).findAll();
    verify(service, times(1)).findAll();
  }

  @Test
  void findByIdPositiveTest() {
    LaptopServiceImpl service = mock(LaptopServiceImpl.class);
    LaptopMapper mapper = mock(LaptopMapper.class);

    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(laptop));
    when(mapper.toDTO(laptop)).thenReturn(laptopFullDTO);
    when(service.findById(anyLong())).thenReturn(laptopFullDTO);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(laptop)).isNotNull().isEqualTo(laptopFullDTO)
        .isExactlyInstanceOf(LaptopFullDTO.class);
    assertThat(service.findById(anyLong())).isNotNull().isEqualTo(laptopFullDTO)
        .isExactlyInstanceOf(LaptopFullDTO.class);

    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Laptop.class));
    verify(service, times(1)).findById(anyLong());
  }

  @Test
  void findByIdNegativeTest() {
    LaptopServiceImpl service = mock(LaptopServiceImpl.class);
    LaptopMapper mapper = mock(LaptopMapper.class);

    when(repository.findById(anyLong())).thenThrow(ElemNotFound.class);
    when(mapper.toDTO(any())).thenThrow(NullPointerException.class);
    when(service.findById(anyLong())).thenThrow(ElemNotFound.class);

    assertThrows(ElemNotFound.class, () -> repository.findById(anyLong()));
    assertThrows(NullPointerException.class, () -> mapper.toDTO(any()));
    assertThrows(ElemNotFound.class, () -> service.findById(anyLong()));

    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any());
    verify(service, times(1)).findById(anyLong());
  }

}
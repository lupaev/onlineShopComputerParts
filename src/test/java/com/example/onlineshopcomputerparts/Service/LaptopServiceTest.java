package com.example.onlineshopcomputerparts.Service;

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

import com.example.onlineshopcomputerparts.DTO.LaptopDTO;
import com.example.onlineshopcomputerparts.Entity.Laptop;
import com.example.onlineshopcomputerparts.Exception.ElemNotFound;
import com.example.onlineshopcomputerparts.Mapper.LaptopMapper;
import com.example.onlineshopcomputerparts.Repository.LaptopRepository;
import com.example.onlineshopcomputerparts.Service.impl.LaptopServiceImpl;
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

  @BeforeEach
  void setUp() {
    dto = new LaptopDTO(2L, 1111, "Test1", 12601.98,
        11, 10);
    laptop = new Laptop();
    laptop.setId(dto.getId());
    laptop.setPrice(dto.getPrice());
    laptop.setManufacturer(dto.getManufacturer());
    laptop.setDiagonal(dto.getDiagonal());
    laptop.setQuantity(dto.getQuantity());
    laptop.setSerialNumber(dto.getSerialNumber());
  }

  @AfterEach
  void clearTestData() {
    dto = null;
    laptop = null;
  }

  @Test
  void addPositiveTest() {
    LaptopServiceImpl service = mock(LaptopServiceImpl.class);
    LaptopMapper mapper = mock(LaptopMapper.class);

    when(mapper.toEntity(dto)).thenReturn(laptop);
    when(service.add(dto)).thenReturn(dto);
    when(repository.save(any(Laptop.class))).thenReturn(laptop);
    assertThat(service.add(dto)).isNotNull().isEqualTo(dto).isExactlyInstanceOf(LaptopDTO.class);
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
    when(mapper.toDTO(laptop)).thenReturn(dto);
    doNothing().when(mapper).updateLaptopFromDto(dto, laptop);
    when(repository.save(any(Laptop.class))).thenReturn(laptop);
    when(service.patch(anyLong(), anyInt(), anyInt(),
        anyString(), anyDouble(), anyInt())).thenReturn(dto);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(laptop)).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(LaptopDTO.class);
    assertDoesNotThrow(
        () -> mapper.updateLaptopFromDto(dto, laptop));
    assertThat(repository.save(laptop)).isNotNull().isExactlyInstanceOf(Laptop.class);

    assertThat(
        service.patch(anyLong(), anyInt(), anyInt(),
            anyString(), anyDouble(), anyInt())).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(LaptopDTO.class);

    verify(repository, times(1)).save(laptop);
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Laptop.class));
    verify(mapper, times(1)).updateLaptopFromDto(any(LaptopDTO.class), any(Laptop.class));

    verify(service, times(1)).patch(anyLong(), anyInt(), anyInt(),
        anyString(), anyDouble(), anyInt());

  }

  @Test
  void patchNegativeTest() {
    LaptopServiceImpl service = mock(LaptopServiceImpl.class);
    LaptopMapper mapper = mock(LaptopMapper.class);

    when(repository.findById(anyLong())).thenThrow(ElemNotFound.class);
    when(mapper.toDTO(any())).thenThrow(NullPointerException.class);
    doThrow(NullPointerException.class).when(mapper).updateLaptopFromDto(any(), any());
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.patch(anyLong(), anyInt(), anyInt(),
        anyString(), anyDouble(), anyInt())).thenThrow(RuntimeException.class);
    assertThrows(ElemNotFound.class, () -> repository.findById(anyLong()));
    assertThrows(NullPointerException.class, () -> mapper.toDTO(any()));
    assertThrows(NullPointerException.class, () -> mapper.updateLaptopFromDto(any(), any()));
    assertThrows(RuntimeException.class, () -> repository.save(any()));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
        () -> service.patch(dto.getId(), dto.getDiagonal(), dto.getSerialNumber(),
            dto.getManufacturer(), dto.getPrice(), dto.getQuantity()));

    verify(repository, times(1)).save(any());
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any());
    verify(mapper, times(1)).updateLaptopFromDto(any(), any());

    verify(service, times(1)).patch(anyLong(), anyInt(), anyInt(),
        anyString(), anyDouble(), anyInt());

  }

  @Test
  void findAllPositiveTest() {
    LaptopServiceImpl service = mock(LaptopServiceImpl.class);
    LaptopMapper mapper = mock(LaptopMapper.class);

    when(repository.findAll()).thenReturn(List.of(laptop));
    when(mapper.toDTOList(List.of(laptop))).thenReturn(List.of(dto));
    when(service.findAll()).thenReturn(List.of(dto));

    assertThat(repository.findAll()).isNotNull().isEqualTo(List.of(laptop));
    assertThat(mapper.toDTOList(List.of(laptop))).isNotNull().isEqualTo(List.of(dto));
    assertThat(service.findAll()).isNotNull().isEqualTo(List.of(dto));

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
    when(mapper.toDTO(laptop)).thenReturn(dto);
    when(service.findById(anyLong())).thenReturn(dto);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(laptop)).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(LaptopDTO.class);
    assertThat(service.findById(anyLong())).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(LaptopDTO.class);

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
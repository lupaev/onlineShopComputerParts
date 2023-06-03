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

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import com.example.onlineshopcomputerparts.Exception.ElemNotFound;
import com.example.onlineshopcomputerparts.Mapper.ComputerMapper;
import com.example.onlineshopcomputerparts.Repository.ComputerRepository;
import com.example.onlineshopcomputerparts.Service.impl.ComputerServiceImpl;
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
class ComputerServiceTest {

  @InjectMocks
  private ComputerServiceImpl service;
  @Mock
  private ComputerRepository repository;
  @Spy
  private ComputerMapper mapper;

  private ComputerDTO dto;
  private Computer computer;

  @BeforeEach
  void setUp() {
    dto = new ComputerDTO(2L, 1111, "Test1", 12601.98,
        11, "mini1");
    computer = new Computer();
    computer.setId(dto.getId());
    computer.setPrice(dto.getPrice());
    computer.setManufacturer(dto.getManufacturer());
    computer.setForm(dto.getForm());
    computer.setQuantity(dto.getQuantity());
    computer.setSerialNumber(dto.getSerialNumber());
  }

  @AfterEach
  void clearTestData() {
    dto = null;
    computer = null;
  }

  @Test
  void addPositiveTest() {
    ComputerServiceImpl service = mock(ComputerServiceImpl.class);
    ComputerMapper mapper = mock(ComputerMapper.class);

    when(mapper.toEntity(dto)).thenReturn(computer);
    when(service.add(dto)).thenReturn(dto);
    when(repository.save(any(Computer.class))).thenReturn(computer);
    assertThat(service.add(dto)).isNotNull().isEqualTo(dto).isExactlyInstanceOf(ComputerDTO.class);
    assertThat(mapper.toEntity(dto)).isNotNull().isEqualTo(computer)
        .isExactlyInstanceOf(Computer.class);
    assertThat(repository.save(computer)).isNotNull().isExactlyInstanceOf(Computer.class);

    verify(repository, times(1)).save(computer);
    verify(service, times(1)).add(dto);
    verify(mapper, times(1)).toEntity(dto);
  }

  @Test
  void addNegativeTest() {
    ComputerServiceImpl service = mock(ComputerServiceImpl.class);
    ComputerMapper mapper = mock(ComputerMapper.class);

    when(mapper.toEntity(any())).thenThrow(NullPointerException.class);
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.add(any())).thenThrow(RuntimeException.class);
    assertThrows(NullPointerException.class, () -> mapper.toEntity(any()));
    assertThrows(RuntimeException.class, () -> repository.save(any(Computer.class)));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> service.add(dto));

    verify(repository, times(1)).save(any());
    verify(service, times(1)).add(any());
    verify(mapper, times(1)).toEntity(any());
  }

  @Test
  void patchPositiveTest() {
    ComputerServiceImpl service = mock(ComputerServiceImpl.class);
    ComputerMapper mapper = mock(ComputerMapper.class);
    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(computer));
    when(mapper.toDTO(computer)).thenReturn(dto);
    doNothing().when(mapper).updateComputerFromDto(dto, computer);
    when(repository.save(any(Computer.class))).thenReturn(computer);
    when(service.patch(dto.getId(), dto.getForm(), dto.getSerialNumber(), dto.getManufacturer(),
        dto.getPrice(), dto.getQuantity())).thenReturn(dto);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(computer)).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(ComputerDTO.class);
    assertDoesNotThrow(
        () -> mapper.updateComputerFromDto(dto, computer));
    assertThat(repository.save(computer)).isNotNull().isExactlyInstanceOf(Computer.class);

    assertThat(
        service.patch(dto.getId(), dto.getForm(), dto.getSerialNumber(), dto.getManufacturer(),
            dto.getPrice(), dto.getQuantity())).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(ComputerDTO.class);

    verify(repository, times(1)).save(computer);
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Computer.class));
    verify(mapper, times(1)).updateComputerFromDto(any(ComputerDTO.class), any(Computer.class));

    verify(service, times(1)).patch(anyLong(), anyString(), anyInt(),
        anyString(), anyDouble(), anyInt());

  }

  @Test
  void patchNegativeTest() {
    ComputerServiceImpl service = mock(ComputerServiceImpl.class);
    ComputerMapper mapper = mock(ComputerMapper.class);

    when(repository.findById(anyLong())).thenThrow(ElemNotFound.class);
    when(mapper.toDTO(any())).thenThrow(NullPointerException.class);
    doThrow(NullPointerException.class).when(mapper).updateComputerFromDto(any(), any());
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.patch(dto.getId(), dto.getForm(), dto.getSerialNumber(), dto.getManufacturer(),
        dto.getPrice(), dto.getQuantity())).thenThrow(RuntimeException.class);
    assertThrows(ElemNotFound.class, () -> repository.findById(anyLong()));
    assertThrows(NullPointerException.class, () -> mapper.toDTO(any()));
    assertThrows(NullPointerException.class, () -> mapper.updateComputerFromDto(any(), any()));
    assertThrows(RuntimeException.class, () -> repository.save(any()));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
        () -> service.patch(dto.getId(), dto.getForm(), dto.getSerialNumber(),
            dto.getManufacturer(), dto.getPrice(), dto.getQuantity()));

    verify(repository, times(1)).save(any());
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any());
    verify(mapper, times(1)).updateComputerFromDto(any(), any());

    verify(service, times(1)).patch(anyLong(), anyString(), anyInt(), anyString(), anyDouble(),
        anyInt());

  }

  @Test
  void findAllPositiveTest() {
    ComputerServiceImpl service = mock(ComputerServiceImpl.class);
    ComputerMapper mapper = mock(ComputerMapper.class);

    when(repository.findAll()).thenReturn(List.of(computer));
    when(mapper.toDTOList(List.of(computer))).thenReturn(List.of(dto));
    when(service.findAll()).thenReturn(List.of(dto));

    assertThat(repository.findAll()).isNotNull().isEqualTo(List.of(computer));
    assertThat(mapper.toDTOList(List.of(computer))).isNotNull().isEqualTo(List.of(dto));
    assertThat(service.findAll()).isNotNull().isEqualTo(List.of(dto));

    verify(repository, times(1)).findAll();
    verify(service, times(1)).findAll();
    verify(mapper, times(1)).toDTOList(List.of(computer));
  }

  @Test
  void findAllNegativeTest() {
    ComputerServiceImpl service = mock(ComputerServiceImpl.class);

    when(repository.findAll()).thenThrow(ElemNotFound.class);
    when(service.findAll()).thenThrow(RuntimeException.class);

    assertThrows(ElemNotFound.class, () -> repository.findAll());
    assertThrows(RuntimeException.class, service::findAll);

    verify(repository, times(1)).findAll();
    verify(service, times(1)).findAll();
  }

  @Test
  void findByIdPositiveTest() {
    ComputerServiceImpl service = mock(ComputerServiceImpl.class);
    ComputerMapper mapper = mock(ComputerMapper.class);

    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(computer));
    when(mapper.toDTO(computer)).thenReturn(dto);
    when(service.findById(anyLong())).thenReturn(dto);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(computer)).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(ComputerDTO.class);
    assertThat(service.findById(anyLong())).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(ComputerDTO.class);

    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Computer.class));
    verify(service, times(1)).findById(anyLong());
  }

  @Test
  void findByIdNegativeTest() {
    ComputerServiceImpl service = mock(ComputerServiceImpl.class);
    ComputerMapper mapper = mock(ComputerMapper.class);

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
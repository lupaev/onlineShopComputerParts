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
import com.example.onlineshopcomputerparts.DTO.MonitorDTO;
import com.example.onlineshopcomputerparts.Entity.Monitor;
import com.example.onlineshopcomputerparts.Exception.ElemNotFound;
import com.example.onlineshopcomputerparts.Mapper.MonitorMapper;
import com.example.onlineshopcomputerparts.Repository.MonitorRepository;
import com.example.onlineshopcomputerparts.Service.impl.MonitorServiceImpl;
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
class MonitorServiceTest {

  @InjectMocks
  private MonitorServiceImpl service;
  @Mock
  private MonitorRepository repository;
  @Spy
  private MonitorMapper mapper;

  private MonitorDTO dto;
  private Monitor monitor;

  @BeforeEach
  void setUp() {
    dto = new MonitorDTO(2L, 1111, "Test1", 12601.98,
        11, 10);
    monitor = new Monitor();
    monitor.setId(dto.getId());
    monitor.setPrice(dto.getPrice());
    monitor.setManufacturer(dto.getManufacturer());
    monitor.setDiagonal(dto.getDiagonal());
    monitor.setQuantity(dto.getQuantity());
    monitor.setSerialNumber(dto.getSerialNumber());
  }

  @AfterEach
  void clearTestData() {
    dto = null;
    monitor = null;
  }

  @Test
  void addPositiveTest() {
    MonitorServiceImpl service = mock(MonitorServiceImpl.class);
    MonitorMapper mapper = mock(MonitorMapper.class);

    when(mapper.toEntity(dto)).thenReturn(monitor);
    when(service.add(dto)).thenReturn(dto);
    when(repository.save(any(Monitor.class))).thenReturn(monitor);
    assertThat(service.add(dto)).isNotNull().isEqualTo(dto).isExactlyInstanceOf(MonitorDTO.class);
    assertThat(mapper.toEntity(dto)).isNotNull().isEqualTo(monitor)
        .isExactlyInstanceOf(Monitor.class);
    assertThat(repository.save(monitor)).isNotNull().isExactlyInstanceOf(Monitor.class);

    verify(repository, times(1)).save(monitor);
    verify(service, times(1)).add(dto);
    verify(mapper, times(1)).toEntity(dto);
  }

  @Test
  void addNegativeTest() {
    MonitorServiceImpl service = mock(MonitorServiceImpl.class);
    MonitorMapper mapper = mock(MonitorMapper.class);

    when(mapper.toEntity(any())).thenThrow(NullPointerException.class);
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.add(any())).thenThrow(RuntimeException.class);
    assertThrows(NullPointerException.class, () -> mapper.toEntity(any()));
    assertThrows(RuntimeException.class, () -> repository.save(any(Monitor.class)));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> service.add(dto));

    verify(repository, times(1)).save(any());
    verify(service, times(1)).add(any());
    verify(mapper, times(1)).toEntity(any());
  }

  @Test
  void patchPositiveTest() {
    MonitorServiceImpl service = mock(MonitorServiceImpl.class);
    MonitorMapper mapper = mock(MonitorMapper.class);
    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(monitor));
    when(mapper.toDTO(monitor)).thenReturn(dto);
    doNothing().when(mapper).updateMonitorFromDto(dto, monitor);
    when(repository.save(any(Monitor.class))).thenReturn(monitor);
    when(service.patch(anyLong(), anyInt(), anyInt(),
        anyString(), anyDouble(), anyInt())).thenReturn(dto);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(monitor)).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(MonitorDTO.class);
    assertDoesNotThrow(
        () -> mapper.updateMonitorFromDto(dto, monitor));
    assertThat(repository.save(monitor)).isNotNull().isExactlyInstanceOf(Monitor.class);

    assertThat(
        service.patch(anyLong(), anyInt(), anyInt(),
            anyString(), anyDouble(), anyInt())).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(MonitorDTO.class);

    verify(repository, times(1)).save(monitor);
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Monitor.class));
    verify(mapper, times(1)).updateMonitorFromDto(any(MonitorDTO.class), any(Monitor.class));

    verify(service, times(1)).patch(anyLong(), anyInt(), anyInt(),
        anyString(), anyDouble(), anyInt());

  }

  @Test
  void patchNegativeTest() {
    MonitorServiceImpl service = mock(MonitorServiceImpl.class);
    MonitorMapper mapper = mock(MonitorMapper.class);

    when(repository.findById(anyLong())).thenThrow(ElemNotFound.class);
    when(mapper.toDTO(any())).thenThrow(NullPointerException.class);
    doThrow(NullPointerException.class).when(mapper).updateMonitorFromDto(any(), any());
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.patch(anyLong(), anyInt(), anyInt(),
        anyString(), anyDouble(), anyInt())).thenThrow(RuntimeException.class);
    assertThrows(ElemNotFound.class, () -> repository.findById(anyLong()));
    assertThrows(NullPointerException.class, () -> mapper.toDTO(any()));
    assertThrows(NullPointerException.class, () -> mapper.updateMonitorFromDto(any(), any()));
    assertThrows(RuntimeException.class, () -> repository.save(any()));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
        () -> service.patch(dto.getId(), dto.getDiagonal(), dto.getSerialNumber(),
            dto.getManufacturer(), dto.getPrice(), dto.getQuantity()));

    verify(repository, times(1)).save(any());
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any());
    verify(mapper, times(1)).updateMonitorFromDto(any(), any());

    verify(service, times(1)).patch(anyLong(), anyInt(), anyInt(),
        anyString(), anyDouble(), anyInt());

  }

  @Test
  void findAllPositiveTest() {
    MonitorServiceImpl service = mock(MonitorServiceImpl.class);
    MonitorMapper mapper = mock(MonitorMapper.class);

    when(repository.findAll()).thenReturn(List.of(monitor));
    when(mapper.toDTOList(List.of(monitor))).thenReturn(List.of(dto));
    when(service.findAll()).thenReturn(List.of(dto));

    assertThat(repository.findAll()).isNotNull().isEqualTo(List.of(monitor));
    assertThat(mapper.toDTOList(List.of(monitor))).isNotNull().isEqualTo(List.of(dto));
    assertThat(service.findAll()).isNotNull().isEqualTo(List.of(dto));

    verify(repository, times(1)).findAll();
    verify(service, times(1)).findAll();
    verify(mapper, times(1)).toDTOList(List.of(monitor));
  }

  @Test
  void findAllNegativeTest() {
    MonitorServiceImpl service = mock(MonitorServiceImpl.class);

    when(repository.findAll()).thenThrow(ElemNotFound.class);
    when(service.findAll()).thenThrow(RuntimeException.class);

    assertThrows(ElemNotFound.class, () -> repository.findAll());
    assertThrows(RuntimeException.class, service::findAll);

    verify(repository, times(1)).findAll();
    verify(service, times(1)).findAll();
  }

  @Test
  void findByIdPositiveTest() {
    MonitorServiceImpl service = mock(MonitorServiceImpl.class);
    MonitorMapper mapper = mock(MonitorMapper.class);

    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(monitor));
    when(mapper.toDTO(monitor)).thenReturn(dto);
    when(service.findById(anyLong())).thenReturn(dto);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(monitor)).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(MonitorDTO.class);
    assertThat(service.findById(anyLong())).isNotNull().isEqualTo(dto)
        .isExactlyInstanceOf(MonitorDTO.class);

    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Monitor.class));
    verify(service, times(1)).findById(anyLong());
  }

  @Test
  void findByIdNegativeTest() {
    MonitorServiceImpl service = mock(MonitorServiceImpl.class);
    MonitorMapper mapper = mock(MonitorMapper.class);

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
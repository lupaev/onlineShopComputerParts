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

import com.example.onlineshopcomputerparts.dto.HddDTO;
import com.example.onlineshopcomputerparts.dto.HddFullDTO;
import com.example.onlineshopcomputerparts.entity.Hdd;
import com.example.onlineshopcomputerparts.exception.ElemNotFound;
import com.example.onlineshopcomputerparts.mapper.HddMapper;
import com.example.onlineshopcomputerparts.repository.HddRepository;
import com.example.onlineshopcomputerparts.service.impl.HddServiceImpl;
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
class HddServiceTest {

  @InjectMocks
  private HddServiceImpl service;
  @Mock
  private HddRepository repository;
  @Spy
  private HddMapper mapper;

  private HddDTO dto;
  private Hdd hdd;
  private HddFullDTO hddFullDTO;

  @BeforeEach
  void setUp() {
    dto = new HddDTO(1111, "Test1", 12601.98,
        11, 10);
    hddFullDTO = new HddFullDTO(2L, 1111, "Test1", 12601.98,
            11, 10);
    hdd = new Hdd();
    hdd.setId(hddFullDTO.getId());
    hdd.setPrice(hddFullDTO.getPrice());
    hdd.setManufacturer(hddFullDTO.getManufacturer());
    hdd.setVolumeGb(hddFullDTO.getVolumeGb());
    hdd.setQuantity(hddFullDTO.getQuantity());
    hdd.setSerialNumber(hddFullDTO.getSerialNumber());
  }

  @AfterEach
  void clearTestData() {
    dto = null;
    hdd = null;
    hddFullDTO = null;
  }

  @Test
  void addPositiveTest() {
    HddServiceImpl service = mock(HddServiceImpl.class);
    HddMapper mapper = mock(HddMapper.class);

    when(mapper.toEntity(dto)).thenReturn(hdd);
    when(service.add(dto)).thenReturn(hddFullDTO);
    when(repository.save(any(Hdd.class))).thenReturn(hdd);
    assertThat(service.add(dto)).isNotNull().isEqualTo(hddFullDTO).isExactlyInstanceOf(HddFullDTO.class);
    assertThat(mapper.toEntity(dto)).isNotNull().isEqualTo(hdd)
        .isExactlyInstanceOf(Hdd.class);
    assertThat(repository.save(hdd)).isNotNull().isExactlyInstanceOf(Hdd.class);

    verify(repository, times(1)).save(hdd);
    verify(service, times(1)).add(dto);
    verify(mapper, times(1)).toEntity(dto);
  }

  @Test
  void addNegativeTest() {
    HddServiceImpl service = mock(HddServiceImpl.class);
    HddMapper mapper = mock(HddMapper.class);

    when(mapper.toEntity(any())).thenThrow(NullPointerException.class);
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.add(any())).thenThrow(RuntimeException.class);
    assertThrows(NullPointerException.class, () -> mapper.toEntity(any()));
    assertThrows(RuntimeException.class, () -> repository.save(any(Hdd.class)));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> service.add(dto));

    verify(repository, times(1)).save(any());
    verify(service, times(1)).add(any());
    verify(mapper, times(1)).toEntity(any());
  }

  @Test
  void patchPositiveTest() {
    HddServiceImpl service = mock(HddServiceImpl.class);
    HddMapper mapper = mock(HddMapper.class);
    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(hdd));
    when(mapper.toDTO(hdd)).thenReturn(hddFullDTO);
    doNothing().when(mapper).updateHddFromDto(dto, hdd);
    when(repository.save(any(Hdd.class))).thenReturn(hdd);
    when(service.patch(2L, dto)).thenReturn(hddFullDTO);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(hdd)).isNotNull().isEqualTo(hddFullDTO)
        .isExactlyInstanceOf(HddFullDTO.class);
    assertDoesNotThrow(
        () -> mapper.updateHddFromDto(dto, hdd));
    assertThat(repository.save(hdd)).isNotNull().isExactlyInstanceOf(Hdd.class);

    assertThat(
        service.patch(2L, dto)).isNotNull().isEqualTo(hddFullDTO)
        .isExactlyInstanceOf(HddFullDTO.class);

    verify(repository, times(1)).save(hdd);
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Hdd.class));
    verify(mapper, times(1)).updateHddFromDto(any(HddDTO.class), any(Hdd.class));

    verify(service, times(1)).patch(2L, dto);

  }

  @Test
  void patchNegativeTest() {
    HddServiceImpl service = mock(HddServiceImpl.class);
    HddMapper mapper = mock(HddMapper.class);

    when(repository.findById(anyLong())).thenThrow(ElemNotFound.class);
    when(mapper.toDTO(any())).thenThrow(NullPointerException.class);
    doThrow(NullPointerException.class).when(mapper).updateHddFromDto(any(), any());
    when(repository.save(any())).thenThrow(RuntimeException.class);
    when(service.patch(2L, dto)).thenThrow(RuntimeException.class);
    assertThrows(ElemNotFound.class, () -> repository.findById(anyLong()));
    assertThrows(NullPointerException.class, () -> mapper.toDTO(any()));
    assertThrows(NullPointerException.class, () -> mapper.updateHddFromDto(any(), any()));
    assertThrows(RuntimeException.class, () -> repository.save(any()));
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(
        () -> service.patch(2L, dto));

    verify(repository, times(1)).save(any());
    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any());
    verify(mapper, times(1)).updateHddFromDto(any(), any());

    verify(service, times(1)).patch(2L, dto);

  }

  @Test
  void findAllPositiveTest() {
    HddServiceImpl service = mock(HddServiceImpl.class);
    HddMapper mapper = mock(HddMapper.class);

    when(repository.findAll()).thenReturn(List.of(hdd));
    when(mapper.toDTOList(List.of(hdd))).thenReturn(List.of(hddFullDTO));
    when(service.findAll()).thenReturn(List.of(hddFullDTO));

    assertThat(repository.findAll()).isNotNull().isEqualTo(List.of(hdd));
    assertThat(mapper.toDTOList(List.of(hdd))).isNotNull().isEqualTo(List.of(hddFullDTO));
    assertThat(service.findAll()).isNotNull().isEqualTo(List.of(hddFullDTO));

    verify(repository, times(1)).findAll();
    verify(service, times(1)).findAll();
    verify(mapper, times(1)).toDTOList(List.of(hdd));
  }

  @Test
  void findAllNegativeTest() {
    HddServiceImpl service = mock(HddServiceImpl.class);

    when(repository.findAll()).thenThrow(ElemNotFound.class);
    when(service.findAll()).thenThrow(RuntimeException.class);

    assertThrows(ElemNotFound.class, () -> repository.findAll());
    assertThrows(RuntimeException.class, service::findAll);

    verify(repository, times(1)).findAll();
    verify(service, times(1)).findAll();
  }

  @Test
  void findByIdPositiveTest() {
    HddServiceImpl service = mock(HddServiceImpl.class);
    HddMapper mapper = mock(HddMapper.class);

    when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(hdd));
    when(mapper.toDTO(hdd)).thenReturn(hddFullDTO);
    when(service.findById(anyLong())).thenReturn(hddFullDTO);

    assertThat(repository.findById(anyLong())).isNotNull();
    assertThat(mapper.toDTO(hdd)).isNotNull().isEqualTo(hddFullDTO)
        .isExactlyInstanceOf(HddFullDTO.class);
    assertThat(service.findById(anyLong())).isNotNull().isEqualTo(hddFullDTO)
        .isExactlyInstanceOf(HddFullDTO.class);

    verify(repository, times(1)).findById(anyLong());
    verify(mapper, times(1)).toDTO(any(Hdd.class));
    verify(service, times(1)).findById(anyLong());
  }

  @Test
  void findByIdNegativeTest() {
    HddServiceImpl service = mock(HddServiceImpl.class);
    HddMapper mapper = mock(HddMapper.class);

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
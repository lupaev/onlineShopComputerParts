package com.example.onlineshopcomputerparts.Controller;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import com.example.onlineshopcomputerparts.Entity.Hdd;
import com.example.onlineshopcomputerparts.Repository.ComputerRepository;
import com.example.onlineshopcomputerparts.Repository.HddRepository;
import com.example.onlineshopcomputerparts.Service.ComputerService;
import com.example.onlineshopcomputerparts.Service.HddService;
import java.util.List;
import java.util.Optional;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(HddController.class)
class HddControllerTest {

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private MockMvc mockMvc;
  @InjectMocks
  private HddController hddController;
  @MockBean
  private HddService hddService;
  @MockBean
  private HddRepository hddRepository;


  @Test
  public void contextLoads() {
    assertNotNull(hddController);
  }


  @Test
  void add() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url = "/hdd/add";
    HddDTO hddDTO = new HddDTO(2L, 1111, "Test1", 12601.98,
        11, 5);
    Hdd hdd = new Hdd();
    hdd.setId(2L);
    hdd.setSerialNumber(1111);
    hdd.setManufacturer("Test1");
    hdd.setPrice(12601.98);
    hdd.setQuantity(11);
    hdd.setVolumeGb(5);
    JSONObject object = new JSONObject();
    object.put("id", hddDTO.getId());
    object.put("serialNumber", hddDTO.getSerialNumber());
    object.put("manufacturer", hddDTO.getManufacturer());
    object.put("price", hddDTO.getPrice());
    object.put("quantity", hddDTO.getQuantity());
    object.put("volumeGb", hddDTO.getVolumeGb());

    when(hddService.add(hddDTO)).thenReturn(hddDTO);
    when(hddRepository.save(hdd)).thenReturn(hdd);
    mockMvc.perform(post(url)
            .content(String.valueOf(object))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void findAll() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url = "/hdd/all";
    HddDTO hddDTO = new HddDTO(2L, 1111, "Test1", 12601.98,
        11, 5);

    when(hddService.findAll()).thenReturn(List.of(hddDTO));
    mockMvc.perform(get(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void findById() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url = "/hdd/2";
    HddDTO hddDTO = new HddDTO(2L, 1111, "Test1", 12601.98,
        11, 5);

    when(hddService.findById(anyLong())).thenReturn(hddDTO);
    mockMvc.perform(get(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void patch() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url1 = "/hdd/patch/{id}";
    Long id = 2L;
    HddDTO hddDTO = new HddDTO(2L, 1111, "Test1", 12601.98,
        11, 5);
    Hdd hdd = new Hdd();
    hdd.setId(2L);
    hdd.setSerialNumber(1111);
    hdd.setManufacturer("Test1");
    hdd.setPrice(12601.98);
    hdd.setQuantity(11);
    hdd.setVolumeGb(5);
    when(hddRepository.findById(any())).thenReturn(Optional.of(hdd));
    when(hddService.patch(anyLong(), anyInt(), anyInt(), anyString(), anyDouble(),
        anyInt())).thenReturn(hddDTO);
    when(hddRepository.save(hdd)).thenReturn(hdd);
    mockMvc.perform(MockMvcRequestBuilders
            .patch(url1, id)
            .param("serialNumber", "12135")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());

  }

}
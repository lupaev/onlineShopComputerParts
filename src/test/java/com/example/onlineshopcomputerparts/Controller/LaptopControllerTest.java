package com.example.onlineshopcomputerparts.Controller;

import static org.junit.jupiter.api.Assertions.*;
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

import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.DTO.LaptopDTO;
import com.example.onlineshopcomputerparts.Entity.Hdd;
import com.example.onlineshopcomputerparts.Entity.Laptop;
import com.example.onlineshopcomputerparts.Repository.HddRepository;
import com.example.onlineshopcomputerparts.Repository.LaptopRepository;
import com.example.onlineshopcomputerparts.Service.HddService;
import com.example.onlineshopcomputerparts.Service.LaptopService;
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

@WebMvcTest(LaptopController.class)
class LaptopControllerTest {


  @Autowired
  private WebApplicationContext context;

  @Autowired
  private MockMvc mockMvc;
  @InjectMocks
  private LaptopController laptopController;
  @MockBean
  private LaptopService laptopService;
  @MockBean
  private LaptopRepository laptopRepository;


  @Test
  public void contextLoads() {
    assertNotNull(laptopController);
  }


  @Test
  void add() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url = "/laptop/add";
    LaptopDTO laptopDTO = new LaptopDTO(2L, 1111, "Test1", 12601.98,
        11, 5);
    Laptop laptop = new Laptop();
    laptop.setId(2L);
    laptop.setSerialNumber(1111);
    laptop.setManufacturer("Test1");
    laptop.setPrice(12601.98);
    laptop.setQuantity(11);
    laptop.setDiagonal(5);
    JSONObject object = new JSONObject();
    object.put("id", laptopDTO.getId());
    object.put("serialNumber", laptopDTO.getSerialNumber());
    object.put("manufacturer", laptopDTO.getManufacturer());
    object.put("price", laptopDTO.getPrice());
    object.put("quantity", laptopDTO.getQuantity());
    object.put("volumeGb", laptopDTO.getDiagonal());

    when(laptopService.add(laptopDTO)).thenReturn(laptopDTO);
    when(laptopRepository.save(laptop)).thenReturn(laptop);
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
    String url = "/laptop/all";
    LaptopDTO laptopDTO = new LaptopDTO(2L, 1111, "Test1", 12601.98,
        11, 5);

    when(laptopService.findAll()).thenReturn(List.of(laptopDTO));
    mockMvc.perform(get(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void findById() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url = "/laptop/2";
    LaptopDTO laptopDTO = new LaptopDTO(2L, 1111, "Test1", 12601.98,
        11, 5);

    when(laptopService.findById(anyLong())).thenReturn(laptopDTO);
    mockMvc.perform(get(url)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void patch() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    String url1 = "/laptop/patch/{id}";
    Long id = 2L;
    LaptopDTO laptopDTO = new LaptopDTO(2L, 1111, "Test1", 12601.98,
        11, 5);
    Laptop laptop = new Laptop();
    laptop.setId(2L);
    laptop.setSerialNumber(1111);
    laptop.setManufacturer("Test1");
    laptop.setPrice(12601.98);
    laptop.setQuantity(11);
    laptop.setDiagonal(5);
    when(laptopRepository.findById(any())).thenReturn(Optional.of(laptop));
    when(laptopService.patch(anyLong(), anyInt(), anyInt(), anyString(), anyDouble(),
        anyInt())).thenReturn(laptopDTO);
    when(laptopRepository.save(laptop)).thenReturn(laptop);
    mockMvc.perform(MockMvcRequestBuilders
            .patch(url1, id)
            .param("diagonal", "10")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());

  }


}
package com.example.onlineshopcomputerparts.controller;


import com.example.onlineshopcomputerparts.dto.HddDTO;
import com.example.onlineshopcomputerparts.dto.HddFullDTO;
import com.example.onlineshopcomputerparts.entity.Hdd;
import com.example.onlineshopcomputerparts.repository.HddRepository;
import com.example.onlineshopcomputerparts.service.HddService;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void contextLoads() {
        assertNotNull(hddController);
    }


    @Test
    void add() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String url = "/hdd/add";
        HddDTO hddDTO = new HddDTO(1111, "Test1", 12601.98, 11, 5);
        HddFullDTO hddFullDTO = new HddFullDTO(2L, 1111, "Test1", 12601.98, 11, 5);
        Hdd hdd = new Hdd();
        hdd.setId(2L);
        hdd.setSerialNumber(1111);
        hdd.setManufacturer("Test1");
        hdd.setPrice(12601.98);
        hdd.setQuantity(11);
        hdd.setVolumeGb(5);
        JSONObject object = new JSONObject();
        object.put("serialNumber", hddDTO.getSerialNumber());
        object.put("manufacturer", hddDTO.getManufacturer());
        object.put("price", hddDTO.getPrice());
        object.put("quantity", hddDTO.getQuantity());
        object.put("volumeGb", hddDTO.getVolumeGb());

        when(hddService.add(hddDTO)).thenReturn(hddFullDTO);
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
        HddDTO hddDTO = new HddDTO(1111, "Test1", 12601.98, 11, 5);
        HddFullDTO hddFullDTO = new HddFullDTO(2L, 1111, "Test1", 12601.98, 11, 5);

        when(hddService.findAll()).thenReturn(List.of(hddFullDTO));
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
        HddDTO hddDTO = new HddDTO(1111, "Test1", 12601.98, 11, 5);
        HddFullDTO hddFullDTO = new HddFullDTO(2L, 1111, "Test1", 12601.98, 11, 5);

        when(hddService.findById(anyLong())).thenReturn(hddFullDTO);
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
        HddDTO hddDTO = new HddDTO(1111, "Test1", 12601.98, 11, 5);
        HddFullDTO hddFullDTO = new HddFullDTO(2L, 1111, "Test1", 12601.98, 11, 5);

        JSONObject object = new JSONObject();
        object.put("serialNumber", hddDTO.getSerialNumber());

        Hdd hdd = new Hdd();
        hdd.setId(2L);
        hdd.setSerialNumber(1111);
        hdd.setManufacturer("Test1");
        hdd.setPrice(12601.98);
        hdd.setQuantity(11);
        hdd.setVolumeGb(5);

        when(hddRepository.findById(any())).thenReturn(Optional.of(hdd));
        when(hddService.patch(id, hddDTO)).thenReturn(hddFullDTO);
        when(hddRepository.save(hdd)).thenReturn(hdd);

        mockMvc.perform(MockMvcRequestBuilders
                        .patch(url1, id)
                        .content(String.valueOf(object))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
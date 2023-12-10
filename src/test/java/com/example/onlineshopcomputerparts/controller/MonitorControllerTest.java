package com.example.onlineshopcomputerparts.controller;

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

import com.example.onlineshopcomputerparts.dto.MonitorDTO;
import com.example.onlineshopcomputerparts.dto.MonitorFullDTO;
import com.example.onlineshopcomputerparts.entity.Monitor;
import com.example.onlineshopcomputerparts.repository.MonitorRepository;
import com.example.onlineshopcomputerparts.service.MonitorService;

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

@WebMvcTest(MonitorController.class)
class MonitorControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private MonitorController monitorController;
    @MockBean
    private MonitorService monitorService;
    @MockBean
    private MonitorRepository monitorRepository;

    @Test
    void contextLoads() {
        assertNotNull(monitorController);
    }

    @Test
    void add() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String url = "/monitor/add";
        MonitorDTO monitorDTO = new MonitorDTO(1111, "Test1", 12601.98, 11, 5);
        MonitorFullDTO monitorFullDTO = new MonitorFullDTO(2L, 1111, "Test1", 12601.98, 11, 5);
        Monitor monitor = new Monitor();
        monitor.setId(2L);
        monitor.setSerialNumber(1111);
        monitor.setManufacturer("Test1");
        monitor.setPrice(12601.98);
        monitor.setQuantity(11);
        monitor.setDiagonal(5);
        JSONObject object = new JSONObject();
        object.put("serialNumber", monitorDTO.getSerialNumber());
        object.put("manufacturer", monitorDTO.getManufacturer());
        object.put("price", monitorDTO.getPrice());
        object.put("quantity", monitorDTO.getQuantity());
        object.put("volumeGb", monitorDTO.getDiagonal());

        when(monitorService.add(monitorDTO)).thenReturn(monitorFullDTO);
        when(monitorRepository.save(monitor)).thenReturn(monitor);
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
        String url = "/monitor/all";
        MonitorDTO monitorDTO = new MonitorDTO(1111, "Test1", 12601.98, 11, 5);
        MonitorFullDTO monitorFullDTO = new MonitorFullDTO(2L, 1111, "Test1", 12601.98, 11, 5);

        when(monitorService.findAll()).thenReturn(List.of(monitorFullDTO));
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String url = "/monitor/2";
        MonitorDTO monitorDTO = new MonitorDTO(1111, "Test1", 12601.98, 11, 5);
        MonitorFullDTO monitorFullDTO = new MonitorFullDTO(2L, 1111, "Test1", 12601.98, 11, 5);

        when(monitorService.findById(anyLong())).thenReturn(monitorFullDTO);
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void patch() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String url1 = "/monitor/patch/{id}";
        Long id = 2L;
        MonitorDTO monitorDTO = new MonitorDTO(1111, "Test1", 12601.98, 11, 5);
        MonitorFullDTO monitorFullDTO = new MonitorFullDTO(2L, 1111, "Test1", 12601.98, 11, 5);

        JSONObject object = new JSONObject();
        object.put("serialNumber", monitorDTO.getSerialNumber());

        Monitor monitor = new Monitor();
        monitor.setId(2L);
        monitor.setSerialNumber(1111);
        monitor.setManufacturer("Test1");
        monitor.setPrice(12601.98);
        monitor.setQuantity(11);
        monitor.setDiagonal(5);
        when(monitorRepository.findById(any())).thenReturn(Optional.of(monitor));
        when(monitorService.patch(id, monitorDTO)).thenReturn(monitorFullDTO);
        when(monitorRepository.save(monitor)).thenReturn(monitor);
        mockMvc.perform(MockMvcRequestBuilders
                        .patch(url1, id)
                        .content(String.valueOf(object))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
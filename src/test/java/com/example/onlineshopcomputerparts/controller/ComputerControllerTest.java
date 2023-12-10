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

import com.example.onlineshopcomputerparts.dto.ComputerDTO;
import com.example.onlineshopcomputerparts.dto.ComputerFullDTO;
import com.example.onlineshopcomputerparts.entity.Computer;
import com.example.onlineshopcomputerparts.repository.ComputerRepository;
import com.example.onlineshopcomputerparts.service.ComputerService;

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

@WebMvcTest(ComputerController.class)
class ComputerControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private ComputerController computerController;
    @MockBean
    private ComputerService computerService;
    @MockBean
    private ComputerRepository computerRepository;


    @Test
    void contextLoads() {
        assertNotNull(computerController);
    }


    @Test
    void add() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String url = "/computer/add";
        ComputerDTO computerDTO = new ComputerDTO(1111, "Test1", 12601.98,
                11, "mini1");
        ComputerFullDTO computerFullDTO = new ComputerFullDTO(2L, 1111, "Test1", 12601.98,
                11, "mini1");

        Computer computer = new Computer();
        computer.setId(2L);
        computer.setSerialNumber(1111);
        computer.setManufacturer("Test1");
        computer.setPrice(12601.98);
        computer.setQuantity(11);
        computer.setForm("mini1");

        JSONObject object = new JSONObject();
        object.put("serialNumber", computerDTO.getSerialNumber());
        object.put("manufacturer", computerDTO.getManufacturer());
        object.put("price", computerDTO.getPrice());
        object.put("quantity", computerDTO.getQuantity());
        object.put("form", computerDTO.getForm());

        when(computerService.add(computerDTO)).thenReturn(computerFullDTO);
        when(computerRepository.save(computer)).thenReturn(computer);
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
        String url = "/computer/all";
        ComputerDTO computerDTO = new ComputerDTO(1111, "Test1", 12601.98,
                11, "mini1");

        ComputerFullDTO computerFullDTO = new ComputerFullDTO(2L, 1111, "Test1", 12601.98,
                11, "mini1");

        when(computerService.findAll()).thenReturn(List.of(computerFullDTO));
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        String url = "/computer/2";
        ComputerDTO computerDTO = new ComputerDTO(1111, "Test1", 12601.98,
                11, "mini1");

        ComputerFullDTO computerFullDTO = new ComputerFullDTO(2L, 1111, "Test1", 12601.98,
                11, "mini1");

        when(computerService.findById(anyLong())).thenReturn(computerFullDTO);
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
//    void patch() throws Exception {
//        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//        String url1 = "/hdd/patch/{id}";
//        Long id = 2L;
//        ComputerDTO computerDTO = new ComputerDTO(1111, "Test1", 12601.98,
//                11, "mini1");
//        ComputerFullDTO computerFullDTO = new ComputerFullDTO(2L, 1111, "Test1", 12601.98,
//                11, "mini1");
//
//        JSONObject object = new JSONObject();
//        object.put("serialNumber", computerDTO.getSerialNumber());

//        Computer computer = new Computer();
//        computer.setId(2L);
//        computer.setSerialNumber(1111);
//        computer.setManufacturer("Test1");
//        computer.setPrice(12601.98);
//        computer.setQuantity(11);
//        computer.setForm("mini1");
//
//        when(computerRepository.findById(any())).thenReturn(Optional.of(computer));
//        when(computerService.patch(id, computerDTO)).thenReturn(computerFullDTO);
//        when(computerRepository.save(computer)).thenReturn(computer);

//        mockMvc.perform(MockMvcRequestBuilders
//                        .patch(url1, id)
//                        .content(String.valueOf(object))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
}
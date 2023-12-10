package com.example.onlineshopcomputerparts.controller;

import com.example.onlineshopcomputerparts.dto.LaptopDTO;
import com.example.onlineshopcomputerparts.dto.LaptopFullDTO;
import com.example.onlineshopcomputerparts.service.LaptopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Контроллер для ноутбуков
 */

@RestController
@RequestMapping("/laptop")
@Tag(name = "Ноутбуки")
@Slf4j
@RequiredArgsConstructor
public class LaptopController {

    private final LaptopService laptopService;

    @Operation(summary = "Добавление ноутбуков на склад магазина")
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "400",
            description = "bad request"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
    )
    @PostMapping(value = "/add")
    public ResponseEntity<LaptopFullDTO> add(LaptopDTO laptopDTO) {
        return ResponseEntity.ok(laptopService.add(laptopDTO));
    }

    @Operation(summary = "Изменение данных ноутбука на складе магазина")
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "400",
            description = "bad request"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
    )
    @PatchMapping(value = "/patch/{id}")
    public ResponseEntity<LaptopFullDTO> patch(@PathVariable Long id,  @RequestBody LaptopDTO laptopDTO) {
        return ResponseEntity.ok(
                laptopService.patch(id, laptopDTO));
    }

    @Operation(summary = "Все ноутбуки на складе магазина")
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "400",
            description = "bad request"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
    )
    @GetMapping(value = "/all")
    public ResponseEntity<Collection<LaptopFullDTO>> findAll() {
        return ResponseEntity.ok(laptopService.findAll());
    }

    @Operation(summary = "Ноутбук по идентификатору на складе магазина")
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "400",
            description = "bad request"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<LaptopFullDTO> findById(
            @PathVariable(name = "id") @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok(laptopService.findById(id));
    }

}

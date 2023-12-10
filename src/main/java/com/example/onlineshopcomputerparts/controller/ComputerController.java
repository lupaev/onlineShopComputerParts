package com.example.onlineshopcomputerparts.controller;

import com.example.onlineshopcomputerparts.dto.ComputerDTO;
import com.example.onlineshopcomputerparts.dto.ComputerFullDTO;
import com.example.onlineshopcomputerparts.service.ComputerService;
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
 * Контроллер для компьютеров
 */

@RestController
@RequestMapping("/computer")
@Tag(name = "Компьютеры")
@Slf4j
@RequiredArgsConstructor
public class ComputerController {

    private final ComputerService computerService;

    @Operation(summary = "Добавление компьютеров на склад магазина")
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
    public ResponseEntity<ComputerFullDTO> add(ComputerDTO computer) {
        return ResponseEntity.ok(computerService.add(computer));
    }


    @Operation(summary = "Изменение данных компьютера на складе магазина")

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
    public ResponseEntity<ComputerFullDTO> patch(@PathVariable Long id, @RequestBody ComputerDTO computerDTO) {
        return ResponseEntity.ok(computerService.patch(id, computerDTO));
    }

    @Operation(summary = "Все компьютеры на складе магазина")
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
    public ResponseEntity<Collection<ComputerFullDTO>> findAll() {
        return ResponseEntity.ok(computerService.findAll());
    }

    @Operation(summary = "Компьютер по идентификатору на складе магазина")
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
    public ResponseEntity<ComputerFullDTO> findById(
            @PathVariable(name = "id") @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok(computerService.findById(id));
    }

}

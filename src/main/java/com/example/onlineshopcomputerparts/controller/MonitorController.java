package com.example.onlineshopcomputerparts.controller;

import com.example.onlineshopcomputerparts.dto.MonitorDTO;
import com.example.onlineshopcomputerparts.dto.MonitorFullDTO;
import com.example.onlineshopcomputerparts.service.MonitorService;
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
 * Контроллер для Мониторов
 */

@RestController
@RequestMapping("/monitor")
@Tag(name = "Мониторы")
@Slf4j
@RequiredArgsConstructor
public class MonitorController {

    private final MonitorService monitorService;

    @Operation(summary = "Добавление мониторов на склад магазина")
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
    public ResponseEntity<MonitorFullDTO> add(MonitorDTO monitorDTO) {
        return ResponseEntity.ok(monitorService.add(monitorDTO));
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
    public ResponseEntity<MonitorFullDTO> patch(@PathVariable Long id,  @RequestBody MonitorDTO monitorDTO) {
        return ResponseEntity.ok(monitorService.patch(id, monitorDTO));
    }

    @Operation(summary = "Все мониторы на складе магазина")
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
    public ResponseEntity<Collection<MonitorFullDTO>> findAll() {
        return ResponseEntity.ok(monitorService.findAll());
    }

    @Operation(summary = "Монитор по идентификатору на складе магазина")
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
    public ResponseEntity<MonitorFullDTO> findById(
            @PathVariable(name = "id") @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok(monitorService.findById(id));
    }

}

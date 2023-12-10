package com.example.onlineshopcomputerparts.controller;

import com.example.onlineshopcomputerparts.dto.HddDTO;
import com.example.onlineshopcomputerparts.dto.HddFullDTO;
import com.example.onlineshopcomputerparts.service.HddService;
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
 * Контроллер для Жестких дисков
 */

@RestController
@RequestMapping("/hdd")
@Tag(name = "Жесткие диски")
@Slf4j
@RequiredArgsConstructor
public class HddController {

    private final HddService hddService;

    @Operation(summary = "Добавление жестких дисков на склад магазина")
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
    public ResponseEntity<HddFullDTO> add(HddDTO hddDTO) {
        return ResponseEntity.ok(hddService.add(hddDTO));
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
    public ResponseEntity<HddFullDTO> patch(@PathVariable Long id, @RequestBody HddDTO hddDTO) {
        return ResponseEntity.ok(hddService.patch(id, hddDTO));
    }

    @Operation(summary = "Все жесткие диски на складе магазина")
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
    public ResponseEntity<Collection<HddFullDTO>> findAll() {
        return ResponseEntity.ok(hddService.findAll());
    }

    @Operation(summary = "Жесткий диск по идентификатору на складе магазина")
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
    public ResponseEntity<HddFullDTO> findById(
            @PathVariable(name = "id") @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok(hddService.findById(id));
    }

}

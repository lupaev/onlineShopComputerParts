package com.example.onlineshopcomputerparts.controller;

import com.example.onlineshopcomputerparts.dto.ComputerDTO;
import com.example.onlineshopcomputerparts.service.ComputerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
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
    public ResponseEntity<ComputerDTO> add(ComputerDTO computer) {
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
    public ResponseEntity<ComputerDTO> patch(@NotBlank(message = "Поле обязательное для заполнения")
                                             @PathVariable(name = "id") Long id,
                                             @Parameter(description = "Форм-фактор") @RequestParam(name = "form", required = false) String form,
                                             @Parameter(description = "серийный номер")
                                             @RequestParam(name = "serial number", required = false) Integer serialNumber,
                                             @Parameter(description = "Изготовитель")
                                             @RequestParam(name = "manufacturer", required = false) String manufacturer,
                                             @Parameter(description = "Цена товара")
                                             @RequestParam(name = "price", required = false) Double price,
                                             @Parameter(description = "Количество товара на складе")
                                             @RequestParam(name = "quantity", required = false) Integer quantity) {
        return ResponseEntity.ok(computerService.patch(id, form, serialNumber, manufacturer,
                price, quantity));
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
    public ResponseEntity<Collection<ComputerDTO>> findAll() {
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
    public ResponseEntity<ComputerDTO> findById(
            @PathVariable(name = "id") @Parameter(description = "Идентификатор") Long id) {
        return ResponseEntity.ok(computerService.findById(id));
    }

}

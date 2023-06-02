package com.example.onlineshopcomputerparts.Controller;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.Service.ComputerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.Collection;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/computer")
@Tag(name = "Компьютеры")
@Slf4j
public class ComputerController {

  private final ComputerService computerService;

  public ComputerController(ComputerService computerService) {
    this.computerService = computerService;
  }

  @Operation(summary = "Добавление компьютеров на склад магазина")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK"
      ),
      @ApiResponse(
          responseCode = "400",
          description = "bad request"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "Internal Server Error"
      ),
  })
  @PostMapping(value = "/add")
  public ResponseEntity<ComputerDTO> add(ComputerDTO computer) throws IOException {
    return ResponseEntity.ok(computerService.add(computer));
  }


  @Operation(summary = "Изменение данных компьютера на складе магазина")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK"
      ),
      @ApiResponse(
          responseCode = "400",
          description = "bad request"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "Internal Server Error"
      ),
  })
  @PatchMapping(value = "/patch/{id}")
  public ResponseEntity<ComputerDTO> change(@NotBlank(message = "Поле обязательное для заполнения")
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
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK"
      ),
      @ApiResponse(
          responseCode = "400",
          description = "bad request"
      ),
      @ApiResponse(
          responseCode = "500",
          description = "Internal Server Error"
      ),
  })
  @GetMapping(value = "/all")
  public ResponseEntity<Collection<ComputerDTO>> findAll() {
    return ResponseEntity.ok(computerService.findAll());
  }

}

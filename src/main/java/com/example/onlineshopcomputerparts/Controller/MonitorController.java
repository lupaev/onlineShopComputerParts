package com.example.onlineshopcomputerparts.Controller;

import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.DTO.MonitorDTO;
import com.example.onlineshopcomputerparts.Service.MonitorService;
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

/**
 * Контроллер для Мониторов
 */

@RestController
@RequestMapping("/monitor")
@Tag(name = "Мониторы")
@Slf4j
public class MonitorController {

  private final MonitorService monitorService;

  public MonitorController(MonitorService monitorService) {
    this.monitorService = monitorService;
  }

  @Operation(summary = "Добавление мониторов на склад магазина")
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
  public ResponseEntity<MonitorDTO> add(MonitorDTO monitorDTO) throws IOException {
    return ResponseEntity.ok(monitorService.add(monitorDTO));
  }

  @Operation(summary = "Изменение данных ноутбука на складе магазина")
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
  public ResponseEntity<MonitorDTO> patch(@NotBlank(message = "Поле обязательное для заполнения")
  @PathVariable(name = "id") Long id,
      @Parameter(description = "Диагональ") @RequestParam(name = "diagonal", required = false) Integer diagonal,
      @Parameter(description = "серийный номер")
      @RequestParam(name = "serial number", required = false) Integer serialNumber,
      @Parameter(description = "Изготовитель")
      @RequestParam(name = "manufacturer", required = false) String manufacturer,
      @Parameter(description = "Цена товара")
      @RequestParam(name = "price", required = false) Double price,
      @Parameter(description = "Количество товара на складе")
      @RequestParam(name = "quantity", required = false) Integer quantity) {
    return ResponseEntity.ok(
        monitorService.patch(id, diagonal, serialNumber, manufacturer, price, quantity));
  }

  @Operation(summary = "Все мониторы на складе магазина")
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
  public ResponseEntity<Collection<MonitorDTO>> findAll() {
    return ResponseEntity.ok(monitorService.findAll());
  }

  @Operation(summary = "Монитор по идентификатору на складе магазина")
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
  @GetMapping(value = "/{id}")
  public ResponseEntity<MonitorDTO> findById(
      @PathVariable(name = "id") @Parameter(description = "Идентификатор") Long id) {
    return ResponseEntity.ok(monitorService.findById(id));
  }

}

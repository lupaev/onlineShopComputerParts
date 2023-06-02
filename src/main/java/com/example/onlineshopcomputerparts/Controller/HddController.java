package com.example.onlineshopcomputerparts.Controller;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.Service.HddService;
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
@RequestMapping("/hdd")
@Tag(name = "Жесткие диски")
@Slf4j
public class HddController {

  private final HddService hddService;

  public HddController(HddService hddService) {
    this.hddService = hddService;
  }

  @Operation(summary = "Добавление жестких дисков на склад магазина")
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
  public ResponseEntity<HddDTO> add(HddDTO hddDTO) throws IOException {
    return ResponseEntity.ok(hddService.add(hddDTO));
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
  public ResponseEntity<HddDTO> change(@NotBlank(message = "Поле обязательное для заполнения")
  @PathVariable(name = "id") Long id,
      @Parameter(description = "Объем в ГБ") @RequestParam(name = "VolumeGb", required = false) Integer volumeGb,
      @Parameter(description = "серийный номер")
      @RequestParam(name = "serial number", required = false) Integer serialNumber,
      @Parameter(description = "Изготовитель")
      @RequestParam(name = "manufacturer", required = false) String manufacturer,
      @Parameter(description = "Цена товара")
      @RequestParam(name = "price", required = false) Double price,
      @Parameter(description = "Количество товара на складе")
      @RequestParam(name = "quantity", required = false) Integer quantity) {
    return ResponseEntity.ok(hddService.patch(id, volumeGb, serialNumber, manufacturer,
        price, quantity));
  }

  @Operation(summary = "Все жесткие диски на складе магазина")
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
  public ResponseEntity<Collection<HddDTO>> findAll() {
    return ResponseEntity.ok(hddService.findAll());
  }

  @Operation(summary = "Жесткий диск по идентификатору на складе магазина")
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
  public ResponseEntity<HddDTO> findById(
      @PathVariable(name = "id") @Parameter(description = "Идентификатор") Long id) {
    return ResponseEntity.ok(hddService.findById(id));
  }

}

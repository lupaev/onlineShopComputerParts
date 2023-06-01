package com.example.onlineshopcomputerparts.Controller;

import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.DTO.LaptopDTO;
import com.example.onlineshopcomputerparts.Service.HddService;
import com.example.onlineshopcomputerparts.Service.LaptopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/laptop")
@Tag(name = "Ноутбуки")
@Slf4j
public class LaptopController {

  private final LaptopService laptopService;

  public LaptopController(LaptopService LaptopService) {
    this.laptopService = LaptopService;
  }

  @Operation(summary = "Добавление ноутбуков на склад магазина")
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
  public ResponseEntity<LaptopDTO> add(LaptopDTO laptopDTO) throws IOException {
    return ResponseEntity.ok(laptopService.add(laptopDTO));
  }

}

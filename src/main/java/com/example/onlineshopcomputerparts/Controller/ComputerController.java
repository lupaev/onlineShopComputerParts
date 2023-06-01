package com.example.onlineshopcomputerparts.Controller;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.Entity.Computer;
import com.example.onlineshopcomputerparts.Service.ComputerService;
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

}

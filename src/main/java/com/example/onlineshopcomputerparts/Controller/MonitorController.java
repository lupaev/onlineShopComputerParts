package com.example.onlineshopcomputerparts.Controller;

import com.example.onlineshopcomputerparts.DTO.MonitorDTO;
import com.example.onlineshopcomputerparts.Service.MonitorService;
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

}

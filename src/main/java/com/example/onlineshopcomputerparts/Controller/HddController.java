package com.example.onlineshopcomputerparts.Controller;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import com.example.onlineshopcomputerparts.DTO.HddDTO;
import com.example.onlineshopcomputerparts.Service.ComputerService;
import com.example.onlineshopcomputerparts.Service.HddService;
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

}

package com.example.onlineshopcomputerparts.exception;


import com.example.onlineshopcomputerparts.logger.FormLogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Slf4j
public class ElemNotFound extends RuntimeException {
  public ElemNotFound(String message) {
    super("Exception: " + message + FormLogInfo.getInfo());
    log.info("Exception: " + message + FormLogInfo.getException());
  }
}

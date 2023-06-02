package com.example.onlineshopcomputerparts.Exception;


import com.example.onlineshopcomputerparts.Loger.FormLogInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElemNotFound extends RuntimeException {

  public ElemNotFound() {
  }

  public ElemNotFound(String message) {
    super("Exception: " + message + FormLogInfo.getInfo());
    System.err.println("Exception: " + message + FormLogInfo.getException());
  }
}

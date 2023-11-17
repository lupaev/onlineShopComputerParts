package com.example.onlineshopcomputerparts.logger;

public class FormLogInfo {

  private FormLogInfo() {}

  public static String getInfo() {
    StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
    String methodName = stackTraceElement.getMethodName();
    String className = stackTraceElement.getClassName();
    return "Старт метода " +
            "\"" +
            methodName +
            "\"" +
            " из класса " +
            "\"" +
            className +
            "\"";
  }

  public static String getException() {
    StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
    String methodName = stackTraceElement.getMethodName();
    String className = stackTraceElement.getClassName();
    return " ВНИМАНЕИЕ: " +
            " исключение в методе " +
            "\"" +
            methodName +
            "\"" +
            " из класса " +
            "\"" +
            className +
            "\"";
  }

}

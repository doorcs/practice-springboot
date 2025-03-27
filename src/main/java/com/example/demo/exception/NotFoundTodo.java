package com.example.demo.exception;

public class NotFoundTodo extends RuntimeException {
  public NotFoundTodo(String message) {
    super(message);
  }
}

package com.example.grpc.exceptions;

public class InvalidCommandException extends Exception {
  public InvalidCommandException(String msg) {
    super(msg);
  }
}

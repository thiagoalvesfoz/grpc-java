package com.example.grpc.cmd;

public class InvalidCommandException extends Exception {
  public InvalidCommandException(String msg) {
    super(msg);
  }
}

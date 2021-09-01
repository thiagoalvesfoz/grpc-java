package com.example.grpc.exceptions;

public class GrpcApplicationException extends Exception {

  public GrpcApplicationException(String msg) {
    super(msg);
  }

}

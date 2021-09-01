package com.example.grpc;

public class GrpcApplicationException extends Exception {

  public GrpcApplicationException(String msg) {
    super(msg);
  }

}

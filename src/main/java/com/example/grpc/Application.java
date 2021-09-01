package com.example.grpc;


import com.example.grpc.cmd.InvalidCommandException;
import com.example.grpc.cmd.CmdInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.grpc.helpers.Utils.getPortNumber;

public class Application {

  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    try {
      CmdInterface.initialize(getPortNumber(args), args);
    } catch (InvalidCommandException e) {
      logger.warn(e.getMessage());
      CmdInterface.showInstructions();
    } catch (GrpcApplicationException e) {
      logger.warn(e.getMessage());
    }
  }

}

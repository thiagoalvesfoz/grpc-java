package com.example.grpc.cmd;

import com.example.grpc.exceptions.GrpcApplicationException;
import com.example.grpc.adapters.client.GrpcClient;
import com.example.grpc.adapters.server.GrpcServer;
import com.example.grpc.exceptions.InvalidCommandException;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.example.grpc.cmd.Commands.*;

public abstract class CmdInterface {
  private static final Logger logger = LoggerFactory.getLogger(CmdInterface.class);

  public static void initialize(String hostname, int port, String... args) throws InvalidCommandException, GrpcApplicationException {
    String selected = args.length == 0 ? HELP : args[0];

    switch (selected) {
      case CLIENT_MODE:
      case CLIENT_MODE_OPT:
        CmdInterface.runClient(hostname, port, args);
        break;
      case SERVE_MODE:
      case SERVE_MODE_OPT:
        CmdInterface.runServer(hostname, port);
        break;
      case HELP:
      case HELP_OPT:
        CmdInterface.showInstructions();
        break;
      case PORT:
      case HOSTNAME:
      case FIRST_NAME:
      case FIRST_NAME_OPT:
      case LAST_NAME:
      case LAST_NAME_OPT:
        throw new InvalidCommandException(
                "you must choose as the first argument whether you want to start in server mode or in client mode.\n" +
                "If you need help, use the \"" + HELP + "\" command\n"
        );
      default:
        throw new InvalidCommandException("Unknown command!");
    }
  }

  public static void showInstructions() {
    String commands = new StringBuilder()
            .append("\nCLI created to explore the features that gRPC offers\n\n")
            .append("[HELP] usage: -client [options] | -serve [options]\n\n")
            .append(getHelp())
            .append(getServeMode())
            .append(getClientMode())
            .append(getPort())
            .append("\n")
            .append("[OPTIONAL] client mode commands\n\n")
            .append(getHost())
            .append(getFirstName())
            .append(getLastName())
            .append("\n")
            .append("2021 - Developed by Thiago Alves. :)\n")
            .toString();

    System.out.println(commands);
  }

  public static void runClient(String hostname, int port, String... args) throws InvalidCommandException, GrpcApplicationException {
    String firstName = Arrays.stream(args).filter(c -> c.startsWith(FIRST_NAME) || c.startsWith(FIRST_NAME_OPT)).collect(Collectors.joining());
    String lastName = Arrays.stream(args).filter(c -> c.startsWith(LAST_NAME) || c.startsWith(LAST_NAME_OPT)).collect(Collectors.joining());

    if (!firstName.isBlank()) {

      if (firstName.startsWith(FIRST_NAME)) firstName = firstName.substring(FIRST_NAME.length());
      else firstName = firstName.substring(FIRST_NAME_OPT.length());

      if (firstName.isBlank())
        throw new InvalidCommandException("Please insert a name or remove the flag");
    }

    if (!lastName.isBlank()) {

      if (lastName.startsWith(LAST_NAME)) lastName = lastName.substring(LAST_NAME.length());
      else lastName = lastName.substring(LAST_NAME_OPT.length());

      if (lastName.isBlank())
        throw new InvalidCommandException("Please insert a lastName or remove the flag");
    }

    try {
      logger.info("Client started. Connecting to server");
      GrpcClient.run(hostname, port, firstName, lastName);
    } catch (StatusRuntimeException e) {
      throw new GrpcApplicationException("Could not connect to server. " + e.getCause().getMessage());
    }

  }

  public static void runServer(String HOSTNAME, int PORT_NUMBER) throws GrpcApplicationException {
    try {

      logger.info("Starting server on {}:{}", HOSTNAME, PORT_NUMBER);
      GrpcServer.run(PORT_NUMBER);

    } catch (IOException | InterruptedException e) {
      throw new GrpcApplicationException("An error occurred starting the server. " + e.getCause().getMessage());
    }
  }

}

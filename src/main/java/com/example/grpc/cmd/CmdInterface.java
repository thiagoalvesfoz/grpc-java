package com.example.grpc.cmd;

import com.example.grpc.GrpcApplicationException;
import com.example.grpc.adapters.client.GrpcClient;
import com.example.grpc.adapters.server.GrpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.example.grpc.cmd.Commands.*;

public abstract class CmdInterface {
  private static final Logger logger = LoggerFactory.getLogger(CmdInterface.class);

  public static void initialize(String hostname, int port, String... args) throws InvalidCommandException, GrpcApplicationException {
    String selected = args.length == 0 ? HELP : args[0].toLowerCase();

    switch (selected) {
      case CLIENT_MODE:
        CmdInterface.runClient(hostname, port, args);
        break;
      case SERVE_MODE:
        CmdInterface.runServer(hostname, port);
        break;
      case HELP:
        CmdInterface.showInstructions();
        break;
      case PORT:
      case HOSTNAME:
      case FIRST_NAME:
      case LAST_NAME:
        throw new InvalidCommandException(
                "you must choose as the first argument whether you want to start in server mode or in client mode.\n" +
                "If you need help, use the \"" + HELP + "\" command\n"
        );
      default:
        throw new InvalidCommandException("Unknown command!\n");
    }
  }

  public static void showInstructions() {
    String commands = new StringBuilder()
            .append("[HELP]\n")
            .append("Usage this command to execute this program:\n\n")
            .append(getServeMode())
            .append(getClientMode())
            .append(getPort())
            .append(getHelp())
            .append("\n")
            .append("[OPTIONAL] client mode commands\n\n")
            .append(getHost())
            .append(getFirstName())
            .append(getLastName())
            .append("\n")
            .append("Developed by Thiago Alves. :)\n")
            .toString();

    System.out.println(commands);
  }

  public static void runClient(String hostname, int port, String... args) throws InvalidCommandException {
    String firstName = Arrays.stream(args).filter(c -> c.startsWith(FIRST_NAME)).collect(Collectors.joining());
    String lastName = Arrays.stream(args).filter(c -> c.startsWith(LAST_NAME)).collect(Collectors.joining());

    if (!firstName.isBlank()) {

      firstName = firstName.substring(11);

      if (firstName.isBlank())
        throw new InvalidCommandException("Please insert a name or remove the flag");
    }

    if (!lastName.isBlank()) {
      lastName = lastName.substring(10);

      if (lastName.isBlank())
        throw new InvalidCommandException("Please insert a lastName or remove the flag");
    }


    logger.info("Client started. Connecting to server {}:{}\n", hostname, port);
    GrpcClient.run(hostname, port, firstName, lastName);
    logger.info("Client Stopped");
  }

  public static void runServer(String HOSTNAME, int PORT_NUMBER) throws GrpcApplicationException {
    try {

      logger.info("Starting server on {}:{}", HOSTNAME, PORT_NUMBER);
      GrpcServer.run(PORT_NUMBER);

    } catch (IOException | InterruptedException e) {
      throw new GrpcApplicationException(e.getMessage() + "\nCause: " + e.getCause());
    } finally {
      logger.info("Server Stopped");
    }
  }

}

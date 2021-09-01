package com.example.grpc.helpers;

import com.example.grpc.exceptions.InvalidCommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.example.grpc.cmd.Commands.HOSTNAME;
import static com.example.grpc.cmd.Commands.PORT;

public abstract class Utils {

  private static final Logger logger = LoggerFactory.getLogger(Utils.class);

  /* TCP CONNECTIONS */
  public static final String DEFAULT_HOST = "localhost";
  public static final int DEFAULT_PORT = 50051;
  public static final int RANGE_START_PORT = 20;
  public static final int RANGE_END_PORT = 99999;

  public static Integer getPortNumber(String... args) {

    String port = Arrays.stream(args).filter(c -> c.startsWith(PORT)).collect(Collectors.joining());

    if (!port.isBlank() && isInteger(port.substring(PORT.length()))) {

      int portNumber = Integer.parseInt(port.substring(PORT.length()));

      if (portNumber > RANGE_START_PORT && portNumber < RANGE_END_PORT) {
        return portNumber;
      }

      logger.info("invalid port, the port {} has been selected", DEFAULT_PORT);
    }

    return DEFAULT_PORT;
  }

  private static boolean isInteger(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static String getHostname(String ...args) throws InvalidCommandException {
    String hostname = Arrays.stream(args).filter(c -> c.startsWith(HOSTNAME)).collect(Collectors.joining());

    if (!hostname.isBlank()) {

      hostname = hostname.substring(HOSTNAME.length());

      if (hostname.isBlank())
        throw new InvalidCommandException("The host must be not blank, Please insert a host or remove the flag");

      return hostname;
    }

    return DEFAULT_HOST;
  }

}

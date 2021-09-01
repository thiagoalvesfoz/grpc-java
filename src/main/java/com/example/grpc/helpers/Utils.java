package com.example.grpc.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.example.grpc.cmd.Commands.PORT;

public abstract class Utils {

  private static final Logger logger = LoggerFactory.getLogger(Utils.class);

  /* TCP CONNECTIONS */
  public static final String DEFAULT_HOST = "localhost";
  public static final int DEFAULT_PORT = 50051;
  public static final int RANGE_START_PORT = 49152;
  public static final int RANGE_END_PORT = 99999;

  public static Integer getPortNumber(String... args) {

    String port = Arrays.stream(args).filter(c -> c.startsWith(PORT)).collect(Collectors.joining());

    if (!port.isBlank() && isInteger(port.substring(6))) {

      int portNumber = Integer.parseInt(port.substring(6));

      if (portNumber > RANGE_START_PORT && portNumber < RANGE_END_PORT) {
        return portNumber;
      }

      logger.info("invalid port, the port {} has been selected\n\n", DEFAULT_PORT);
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

}

package com.example.grpc.cmd;

import static com.example.grpc.helpers.Utils.*;

public abstract class Commands {

  /* COMMANDS GUIDE LINES */
  public static final String PREFIX = "-";

  public static final String SERVE_MODE         = PREFIX + "serve";
  public static final String SERVE_MODE_OPT     = PREFIX + "S";

  public static final String CLIENT_MODE        = PREFIX + "client";
  public static final String CLIENT_MODE_OPT    = PREFIX + "C";

  public static final String FIRST_NAME         = PREFIX + PREFIX + "firstName=";
  public static final String FIRST_NAME_OPT     = PREFIX + "fn=";

  public static final String LAST_NAME          = PREFIX + PREFIX + "lastName=";
  public static final String LAST_NAME_OPT      = PREFIX + "ln=";


  public static final String HOSTNAME           = PREFIX + PREFIX + "host=";
  public static final String PORT               = PREFIX + PREFIX + "port=";

  public static final String HELP               = PREFIX + "help";
  public static final String HELP_OPT           = PREFIX + "h";

  public static String getServeMode() {
    return new StringBuilder()
            .append(" ")
            .append(String.format("%s, %s", SERVE_MODE, SERVE_MODE_OPT))
            .append("\t\t\t")
            .append("Run serve mode")
            .append("\n")
            .toString();
  }

  public static String getClientMode() {
    return new StringBuilder()
            .append(" ")
            .append(String.format("%s, %s", CLIENT_MODE, CLIENT_MODE_OPT))
            .append("\t\t\t")
            .append("Run client mode")
            .append("\n")
            .toString();
  }

  public static String getPort() {
    return new StringBuilder()
            .append(" ")
            .append(PORT)
            .append("<NUMBER>")
            .append("\t\t")
            .append("Specify a port in the range ")
            .append(RANGE_START_PORT + " - " + RANGE_END_PORT + ". ")
            .append("Default is " + DEFAULT_PORT)
            .append("\n")
            .toString();
  }

  public static String getHost() {
    return new StringBuilder()
            .append(" ")
            .append(HOSTNAME)
            .append("<HOST>")
            .append("\t\t\t")
            .append("Specify a hostname or IP to connect. ")
            .append("Default is " + DEFAULT_HOST)
            .append("\n")
            .toString();
  }

  public static String getHelp() {
    return new StringBuilder()
            .append(" ")
            .append(String.format("%s, %s", HELP, HELP_OPT))
            .append("\t\t\t")
            .append("Show help commands")
            .append("\n")
            .toString();
  }

  public static String getFirstName() {
    return new StringBuilder()
            .append(" ")
            .append(String.format("%s<FOO>, %s<FOO>", FIRST_NAME, FIRST_NAME_OPT))
            .append("\t")
            .append("Override firstname message")
            .append("\n")
            .toString();
  }

  public static String getLastName() {
    return new StringBuilder()
            .append(" ")
            .append(String.format("%s<BAR>, %s<BAR>", LAST_NAME, LAST_NAME_OPT))
            .append("\t")
            .append("Override lastname message")
            .append("\n")
            .toString();
  }


}

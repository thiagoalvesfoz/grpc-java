package com.example.grpc.cmd;

import static com.example.grpc.helpers.Utils.*;

public abstract class Commands {

  /* COMMANDS GUIDE LINES */
  public static final char PREFIX = '-';

  public static final String SERVE_MODE   = PREFIX + "serve";
  public static final String CLIENT_MODE  = PREFIX + "client";
  public static final String FIRST_NAME   = PREFIX + "firstName=";
  public static final String LAST_NAME    = PREFIX + "lastName=";
  public static final String HOSTNAME     = PREFIX + "host=";
  public static final String PORT         = PREFIX + "port=";
  public static final String HELP         = PREFIX + "help";

  public static String getServeMode() {
    return new StringBuilder()
            .append(" ")
            .append(SERVE_MODE)
            .append("\t\t\t")
            .append("run serve mode")
            .append("\n")
            .toString();
  }

  public static String getClientMode() {
    return new StringBuilder()
            .append(" ")
            .append(CLIENT_MODE)
            .append("\t\t")
            .append("run client mode")
            .append("\n")
            .toString();
  }

  public static String getPort() {
    return new StringBuilder()
            .append(" ")
            .append(PORT)
            .append("<NUMBER>")
            .append("\t\t")
            .append("specify a port in the range ")
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
            .append("\t\t")
            .append("specify a hostname or IP to connect. ")
            .append("Default is " + DEFAULT_HOST)
            .append("\n")
            .toString();
  }

  public static String getHelp() {
    return new StringBuilder()
            .append(" ")
            .append(HELP)
            .append("\t\t\t")
            .append("show help commands")
            .append("\n")
            .toString();
  }

  public static String getFirstName() {
    return new StringBuilder()
            .append(" ")
            .append(FIRST_NAME)
            .append("<FOO>")
            .append("\t")
            .append("override firstname message")
            .append("\n")
            .toString();
  }

  public static String getLastName() {
    return new StringBuilder()
            .append(" ")
            .append(LAST_NAME)
            .append("<BAR>")
            .append("\t")
            .append("override lastname message")
            .append("\n")
            .toString();
  }


}

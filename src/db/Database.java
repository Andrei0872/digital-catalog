package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  private static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/digital_catalog";
  private static final String USER = "andrei";
  private static final String PASSWORD = "pass123";

  private static Connection connection;

  public static Connection getConnection () {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return connection;
  }
}

package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
  private static final String URL = "jdbc:mysql://localhost/";
  private static final String DB_NAME = "usermanagement";
  private static final String PARAMETERS = "?useUnicode=true&characterEncoding=utf8";
  private static final String USER = "root";
  private static final String PASS = "password";
  
  public static Connection getConnection() {

    Connection con = null;

    try {
      // jdbcドライバへアクセス
      Class.forName("com.mysql.jdbc.Driver");

      con = DriverManager.getConnection(URL + DB_NAME + PARAMETERS, USER, PASS);
      System.out.println("DB Conncected");
      return con;

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      throw new IllegalMonitorStateException();
    }


  }



}

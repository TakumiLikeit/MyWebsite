package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import base.DBManager;
import beans.UserDataBeans;

public class UserDAO {


  public static UserDataBeans getUser(String loginId, String password) throws SQLException {
    UserDataBeans udb = new UserDataBeans();
    Connection con = null;
    PreparedStatement st = null;
    int id = 100;

    try {
      con = DBManager.getConnection();
      String sql = "SELECT * FROM user WHERE login_id = ? AND password = ?";
      st = con.prepareStatement(sql);
      st.setString(1, loginId);
      st.setString(2, password);
      ResultSet rs = st.executeQuery();

      if (rs.next()) {
        // udb.setId(rs.getInt("id"));
        id = rs.getInt("id");
        udb.setId(id);

        udb.setLoginId(rs.getString("login_id"));
        udb.setName(rs.getString("name"));
        udb.setPassword(rs.getString("password"));
        udb.setCreateDate(rs.getTimestamp("create_date"));
        udb.setUpdateDate(rs.getTimestamp("update_date_date"));
      }

      st.close();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      // throw new SQLException(e);
      e.printStackTrace();

    } finally {
      if (con != null) {
        con.close();
      }
      if (st != null) {
        st.close();
      }
    }

    System.out.println("UserDAO内、id : " + id);
    System.out.println("Searching UserDataBeans by userId and password has been completed.");
    return udb;
  }


}

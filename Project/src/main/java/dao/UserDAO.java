package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import base.DBManager;
import beans.UserDataBeans;

public class UserDAO {


  public UserDataBeans getUser(String loginId, String password) throws SQLException {
    UserDataBeans udb = new UserDataBeans();
    Connection con = null;
    PreparedStatement st = null;

    try {
      con = DBManager.getConnection();
      String sql = "SELECT * FROM user WHERE id = ? AND password = ?";
      st = con.prepareStatement(sql);
      st.setString(1, loginId);
      st.setString(2, password);
      ResultSet rs = st.executeQuery();

      if (rs.next()) {
        udb.setId(rs.getInt("id"));
        udb.setLoginId(rs.getString("login_id"));
        udb.setName(rs.getString("name"));
        udb.setPassword(rs.getString("password"));
        udb.setCreateDate(rs.getTimestamp("create_date"));
        udb.setUpdateDate(rs.getTimestamp("update_date_date"));
      }

      st.close();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      throw new SQLException(e);

    } finally {
      if (con != null) {
        con.close();
      }
      if (st != null) {
        st.close();
      }
    }

    System.out.println("Searching UserDataBeans by userId and password has been completed.");
    return udb;
  }


}

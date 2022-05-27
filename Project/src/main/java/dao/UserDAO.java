package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import base.DBManager;
import beans.UserDataBeans;
import util.ExpenseHelper;
import util.UserHelper;

public class UserDAO {


  public static UserDataBeans getUser(String loginId, String password) throws SQLException {
    UserDataBeans udb = null;
    Connection con = null;
    PreparedStatement st = null;

    try {
      con = DBManager.getConnection();
      String sql = "SELECT * FROM user WHERE login_id = ? AND password = ?";
      st = con.prepareStatement(sql);
      st.setString(1, loginId);
      st.setString(2, password);
      ResultSet rs = st.executeQuery();

      if (rs.next()) {
        udb = new UserDataBeans();

        udb.setId(rs.getInt("id"));
        udb.setLoginId(rs.getString("login_id"));
        udb.setName(rs.getString("name"));
        udb.setPassword(rs.getString("password"));
        udb.setCreateDate(rs.getTimestamp("create_date"));
        udb.setUpdateDate(rs.getTimestamp("update_date"));
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

    return udb;
  }

  public static UserDataBeans getUserById(int userId) throws SQLException {
    UserDataBeans udb = null;
    Connection con = null;
    PreparedStatement st = null;

    try {
      con = DBManager.getConnection();
      String sql = "SELECT * FROM user WHERE id=?";
      st = con.prepareStatement(sql);
      st.setInt(1, userId);
      ResultSet rs = st.executeQuery();

      if (rs.next()) {
        udb = new UserDataBeans();

        udb.setId(rs.getInt("id"));
        udb.setLoginId(rs.getString("login_id"));
        udb.setName(rs.getString("name"));
        udb.setPassword(rs.getString("password"));
        udb.setCreateDate(rs.getTimestamp("create_date"));
        udb.setUpdateDate(rs.getTimestamp("update_date"));
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

    return udb;
  }

  public static void addUser(String loginId, String password, String userName) {

    System.out.println("UserDAO、addUser内");
    Connection con = null;
    PreparedStatement st = null;

    try {
      con = DBManager.getConnection();
      String sql =
          "INSERT INTO user (login_id,name,password) VALUES (?,?,?)";
      st = con.prepareStatement(sql);
      st.setString(1, loginId);
      st.setString(2, password);
      st.setString(3, userName);

      int result = st.executeUpdate();

      System.out.println("result: " + result);

      st.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

  }

  public static void updateUser(int userId, String password, String passwordConfirm,
      String userName) {
    System.out.println("UserDAO、updateUser内");
    Connection con = null;
    PreparedStatement st = null;
    
    try {
      con = DBManager.getConnection();
      String str1 = "UPDATE user " + "SET name=? WHERE id = ?";
      String str2 = "UPDATE user " + "SET name=?, password=? WHERE id = ?";

      if (UserHelper.isEmptyBoth(password, passwordConfirm)) {
        st = con.prepareStatement(str1);
        st.setString(1, userName);
        st.setInt(2, userId);
      } else {
        String encodedPassword = ExpenseHelper.encodePassword(password);
        st = con.prepareStatement(str2);
        st.setString(1, userName);
        st.setString(2, encodedPassword);
        st.setInt(3, userId);
      }

      int result = st.executeUpdate();
      System.out.println("result: " + result);

      st.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

  }

  public static void deleteUser(int userId) {
    System.out.println("UserDAO、deleteUser内");
    Connection con = null;
    PreparedStatement st = null;

    try {
      con = DBManager.getConnection();
      String sql = "DELETE FROM user WHERE id = ?";
      st = con.prepareStatement(sql);
      st.setInt(1, userId);


      if (st.executeUpdate() == 1) {
        System.out.println("削除成功");
      } else {
        System.out.println("削除失敗");
      }

      st.close();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

  }




  public static boolean existsLoginId(String loginId) {
    Connection con = null;
    PreparedStatement st = null;
    boolean existsLoginId = false;

    try {
      con = DBManager.getConnection();
      String sql = "SELECT * FROM user WHERE login_id = ?";
      st = con.prepareStatement(sql);
      st.setString(1, loginId);
      ResultSet rs = st.executeQuery();

      if (rs.next()) {
        existsLoginId = true;
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      // throw new SQLException(e);
      e.printStackTrace();

    } finally {
      try {
        if (con != null) {
          con.close();
        }
        if (st != null) {
          st.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return existsLoginId;
  }


}

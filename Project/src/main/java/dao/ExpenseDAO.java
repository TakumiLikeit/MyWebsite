package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import base.DBManager;
import beans.ExpenseDataBeans;

public class ExpenseDAO {

  // 全ての出費を取得するメソッド（戻り値：リスト）
  public static List<ExpenseDataBeans> findAll(int userId) {
    System.out.println("ExpenseDAO、findAll内");
    List<ExpenseDataBeans> expenseList = new ArrayList<ExpenseDataBeans>();
    Connection con = null;
    PreparedStatement st = null;
    
    try {
      con = DBManager.getConnection();
      String sql = "SELECT * FROM expense WHERE user_id = ? ORDER BY date ASC"; // WHERE user_id = ?
      st = con.prepareStatement(sql);
      st.setInt(1, userId);// テスト的に1を代入してみる
      ResultSet rs = st.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        // int userId = rs.getInt("user_id");
        int categoryId = rs.getInt("category_id");
        String name = rs.getString("name");
        int price = rs.getInt("price");
        Date expenseDate = rs.getDate("date");
        String note = rs.getString("note");
        Timestamp createDate = rs.getTimestamp("create_date");
        Timestamp updateDate = rs.getTimestamp("update_date");
        String categoryName = CategoryDAO.getCategoryName(categoryId);

        ExpenseDataBeans edb = new ExpenseDataBeans(id, userId, categoryId, name, price,
            expenseDate, note, createDate, updateDate, categoryName);

        expenseList.add(edb);

      }

      st.close();

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (con!=null) {
        try {
          con.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    
    return expenseList;
  }
  
  // 出費IDから出費情報を取得するメソッド（戻り値：インスタンス）
  public static ExpenseDataBeans findById(int expenseId) {
    System.out.println("ExpenseDAO、findById内");
    ExpenseDataBeans edb = new ExpenseDataBeans();
    Connection con = null;
    PreparedStatement st = null;

    try {
      con = DBManager.getConnection();
      String sql = "SELECT * FROM expense WHERE id = ?";
      st = con.prepareStatement(sql);
      st.setInt(1, expenseId);// テスト的に1を代入してみる
      ResultSet rs = st.executeQuery();

      
      
      if (rs.next()) {
        // int id = rs.getInt("id");
        edb.setId(rs.getInt("id")); // expenseId
        edb.setUserId(rs.getInt("user_id"));
        int categoryId = rs.getInt("category_id");
        edb.setCategoryId(categoryId);
        edb.setName(rs.getString("name"));
        edb.setPrice(rs.getInt("price"));
        edb.setExpenseDate(rs.getDate("date"));
        edb.setNote(rs.getString("note"));
        edb.setCreateDate(rs.getTimestamp("create_date"));
        edb.setUpdateDate(rs.getTimestamp("update_date"));
        edb.setCategoryName(CategoryDAO.getCategoryName(categoryId));
      }

      st.close();

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    return edb;
  }

  // 検索条件に該当する出費を探し出すメソッド（戻り値：リスト）
  public static List<ExpenseDataBeans> searchExpense(int userId, String expenseName,
      String categoryId, String startDate, String endDate) {
    System.out.println("ExpenseDAO、searchExpense内");
    List<ExpenseDataBeans> expenseList = new ArrayList<ExpenseDataBeans>();
    Connection con = null;
    PreparedStatement st = null;
    
    try {
      con = DBManager.getConnection();
      String sqlSelect = "SELECT * FROM expense WHERE user_id = ?";
      String sqlOrder = " ORDER BY date ASC";

      List<String> parameterList = new ArrayList<String>();
      StringBuilder sb = new StringBuilder();

      sb.append(sqlSelect);
      if (!expenseName.equals("")) {
        sb.append(" AND name LIKE CONCAT('%',?,'%')");
        parameterList.add(expenseName);
      }
      if (!categoryId.equals("")) {
        sb.append(" AND category_id = ?");
        parameterList.add(categoryId);
      }
      if (!startDate.equals("") && !endDate.equals("")) {
        sb.append(" AND date BETWEEN ? AND ?");
        parameterList.add(startDate);
        parameterList.add(endDate);
      }

      /*
       * if (!price.equals("")) { sb.append(" AND price = ?"); parameterList.add(price); } if
       * (!note.equals("")) { sb.append("AND note LIKE CONCAT('%',?,'%')"); parameterList.add(note);
       * }
       */

      sb.append(sqlOrder);


      st = con.prepareStatement(sb.toString());
      st.setInt(1, userId);

      for (int i = 0; i < parameterList.size(); i++) {
        st.setString(i + 2, parameterList.get(i));
      }
      ResultSet rs = st.executeQuery();


      while (rs.next()) {
        int id = rs.getInt("id");
        // int userId = rs.getInt("user_id");
        int categoryId2 = rs.getInt("category_id");
        String name = rs.getString("name");
        int price2 = rs.getInt("price");
        Date expenseDate = rs.getDate("date");
        String note2 = rs.getString("note");
        Timestamp createDate = rs.getTimestamp("create_date");
        Timestamp updateDate = rs.getTimestamp("update_date");
        String categoryName = CategoryDAO.getCategoryName(categoryId2);

        ExpenseDataBeans edb = new ExpenseDataBeans(id, userId, categoryId2, name, price2,
            expenseDate, note2, createDate, updateDate, categoryName);

        expenseList.add(edb);

      }

      st.close();

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      if (con!=null) {
        try {
          con.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    
    return expenseList;
  }

  // 出費を追加するメソッド（引数は全て、Stringのままで良い）
  // expenseName, price, categoryId, expenseDate, note
  public static boolean addExpenseSuccess(String userId, String expenseName, String price,
      String categoryId, String expenseDate, String note) {
    System.out.println("ExpenseDAO、addExpense内");
    Connection con = null;
    PreparedStatement st = null;
    boolean result = false;

    try {
      con = DBManager.getConnection();
      String sql =
          "INSERT INTO expense (user_id,category_id,name,price,date,note) VALUES (?,?,?,?,?,?)";
      st = con.prepareStatement(sql);
      st.setString(1, userId);
      st.setString(2, categoryId);
      st.setString(3, expenseName);
      st.setString(4, price);
      st.setString(5, expenseDate);
      st.setString(6, note);

      if (st.executeUpdate() == 1) {
        result = true;
      } else {
        return result;
      }

      st.close();

    } catch (SQLException e) {
      e.printStackTrace();
      return result;
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    return result;
  }

  public static boolean updateExpenseSuccess(String expenseId, String userId, String expenseName,
      String price, String categoryId, String expenseDate, String note) {
    System.out.println("ExpenseDAO、updateExpense内");
    Connection con = null;
    PreparedStatement st = null;
    boolean result = false;

    try {
      con = DBManager.getConnection();
      String sql =
          "UPDATE expense " + "SET category_id=?, name=?, price=?, date=?, note=? WHERE id = ?";
      st = con.prepareStatement(sql);
      st.setString(1, categoryId);
      st.setString(2, expenseName);
      st.setString(3, price);
      st.setString(4, expenseDate);
      st.setString(5, note);
      st.setString(6, expenseId);


      if (st.executeUpdate() == 1) {
        result = true;
      } else {
        return result;
      }

      st.close();

    } catch (SQLException e) {
      e.printStackTrace();
      return result;
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    return result;
  }


  public static void deleteExpense(String expenseId) {
    System.out.println("ExpenseDAO、deleteExpense内");
    Connection con = null;
    PreparedStatement st = null;

    try {
      con = DBManager.getConnection();
      String sql =
          "DELETE FROM expense WHERE id = ?";
      st = con.prepareStatement(sql);
      st.setString(1, expenseId);


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

  public static void deleteExpenseByUserId(int userId) {
    System.out.println("ExpenseDAO、deleteExpenseByUserId内");
    Connection con = null;
    PreparedStatement st = null;

    try {
      con = DBManager.getConnection();
      String sql = "DELETE FROM expense WHERE user_id = ?";
      st = con.prepareStatement(sql);
      st.setInt(1, userId);

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

}


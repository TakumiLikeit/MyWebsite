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
    List<ExpenseDataBeans> expenseList = null; // expenseListはnullで初期化
    Connection con = null;
    PreparedStatement st = null;
    PreparedStatement stSum = null;
    
    try {
      con = DBManager.getConnection();
      String str1 = "SELECT * FROM expense WHERE user_id = ? ORDER BY date DESC"; // 新しいものが上にくるように表示
      String str2 = "SELECT SUM(price) AS totalExpense FROM expense WHERE user_id = ?";

      // 合計金額を取得
      stSum = con.prepareStatement(str2);
      stSum.setInt(1, userId);
      ResultSet rsSum = stSum.executeQuery();

      int totalExpense = 0;
      if (rsSum.next()) {
        totalExpense = rsSum.getInt("totalExpense");
      }

      // 該当する出費を取得
      st = con.prepareStatement(str1);
      st.setInt(1, userId);
      ResultSet rs = st.executeQuery();

      // 該当する出費がある場合、expenseListをArrayListで再定義して、取得した出費をリストに追加
      if (rs.next()) {
        expenseList = new ArrayList<ExpenseDataBeans>();

        do {
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
              expenseDate, note, createDate, updateDate, categoryName, totalExpense); // totalExpenseをインスタンスに持たせたいのでExpenseDataBeansのフィールドにtotalExpenseを追加する

          expenseList.add(edb);
        } while (rs.next());

      }


      st.close();
      stSum.close();

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
      st.setInt(1, expenseId);
      ResultSet rs = st.executeQuery();
      
      if (rs.next()) {
        // int id = rs.getInt("id");
        edb.setId(expenseId);
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
    List<ExpenseDataBeans> expenseList = null; // expenseListはnullで初期化
    Connection con = null;
    PreparedStatement stList = null;
    PreparedStatement stSum = null;

    
    try {
      con = DBManager.getConnection();
      String sqlSelect = "SELECT * FROM expense WHERE user_id = ?";
      String sqlOrder = " ORDER BY date DESC";
      String sqlTotalExpense = "SELECT SUM(price) AS totalExpense FROM expense WHERE user_id = ?";

      // PreparedStatementで使うパラメターを格納するリストと、SQL文を組み立てるStringBuilderを作成
      List<String> parameterList = new ArrayList<String>();
      StringBuilder sbList = new StringBuilder(sqlSelect);
      StringBuilder sbSum = new StringBuilder(sqlTotalExpense);

      // 条件により、リストにパラメターを格納し、StringBuilderでSQL文を組み立てる
      if (!expenseName.equals("")) {
        sbList.append(" AND name LIKE CONCAT('%',?,'%')");
        sbSum.append(" AND name LIKE CONCAT('%',?,'%')");
        parameterList.add(expenseName);
      }
      if (!categoryId.equals("")) {
        sbList.append(" AND category_id = ?");
        sbSum.append(" AND category_id = ?");
        parameterList.add(categoryId);
      }
      if (!(startDate.equals("") || endDate.equals(""))) {
        sbList.append(" AND date BETWEEN ? AND ?");
        sbSum.append(" AND date BETWEEN ? AND ?");

        parameterList.add(startDate);
        parameterList.add(endDate);
      }

      // 日付順に並び替えをする、命令文を追加
      sbList.append(sqlOrder);

      // SQL文をPreparedStatementに挿入し、userIdをindex=1にセット
      stList = con.prepareStatement(sbList.toString());
      stSum = con.prepareStatement(sbSum.toString());
      stList.setInt(1, userId);
      stSum.setInt(1, userId);

      // userIdは必ずindex=1の値にセットするので、indexは2から始める
      for (int i = 0; i < parameterList.size(); i++) {
        stList.setString(i + 2, parameterList.get(i));
        stSum.setString(i + 2, parameterList.get(i));
      }

      // 合計金額を先に取得
      ResultSet rsSum = stSum.executeQuery();
      int totalExpense = 0;
      if (rsSum.next()) {
        totalExpense = rsSum.getInt("totalExpense");
      }

      // 該当するリストを取得
      // 該当する出費がある場合、expenseListをArrayListで再定義して、取得した出費をリストに追加
      ResultSet rsList = stList.executeQuery();

      if (rsList.next()) {
        expenseList = new ArrayList<ExpenseDataBeans>();

        do {
          int id = rsList.getInt("id");
          // int userId = rs.getInt("user_id");
          int categoryId2 = rsList.getInt("category_id");
          String name = rsList.getString("name");
          int price2 = rsList.getInt("price");
          Date expenseDate = rsList.getDate("date");
          String note2 = rsList.getString("note");
          Timestamp createDate = rsList.getTimestamp("create_date");
          Timestamp updateDate = rsList.getTimestamp("update_date");
          String categoryName = CategoryDAO.getCategoryName(categoryId2);

          ExpenseDataBeans edb = new ExpenseDataBeans(id, userId, categoryId2, name, price2,
              expenseDate, note2, createDate, updateDate, categoryName, totalExpense);

          expenseList.add(edb);

        } while (rsList.next());
      }


      stList.close();
      stSum.close();

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

  // 出費を追加するメソッド（戻り値：なし）（引数は全て、Stringのままで良い）
  public static void addExpense(String userId, String expenseName, String price,
      String categoryId, String expenseDate, String note) {
    System.out.println("ExpenseDAO、addExpense内");
    Connection con = null;
    PreparedStatement st = null;

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

  // 出費を更新するメソッド（戻り値：なし）
  public static void updateExpense(String expenseId, String userId, String expenseName,
      String price, String categoryId, String expenseDate, String note) {
    System.out.println("ExpenseDAO、updateExpense内");
    Connection con = null;
    PreparedStatement st = null;

    try {
      con = DBManager.getConnection();
      String sql =
          "UPDATE expense SET category_id=?, name=?, price=?, date=?, note=? WHERE id = ?";
      st = con.prepareStatement(sql);
      st.setString(1, categoryId);
      st.setString(2, expenseName);
      st.setString(3, price);
      st.setString(4, expenseDate);
      st.setString(5, note);
      st.setString(6, expenseId);

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

  // 出費を削除するメソッド（戻り値：なし）
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

  // 退会するユーザーの出費を削除するメソッド（戻り値：なし）
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


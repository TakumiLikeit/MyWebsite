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

  //とりあえずメソッドを作りたい、でも先にBeansを作成しないと、クラスを定義できない

  public static List<ExpenseDataBeans> findAll() {
    System.out.println("ExpenseDAO、findAll内");
    List<ExpenseDataBeans> expenseList = new ArrayList<ExpenseDataBeans>();
    Connection con = null;
    PreparedStatement st = null;
    
    try {
      con = DBManager.getConnection();
      String sql = "SELECT * FROM expense ORDER BY date ASC";
      st = con.prepareStatement(sql);
      ResultSet rs = st.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        int userId = rs.getInt("user_id");
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
  
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import base.DBManager;

public class CategoryDAO {



  // カテゴリー名を返す、メソッドを作成
  public static String getCategoryName(int id) {
    System.out.println("CategoryDAO、getCategoryName内");
    String categoryName = null;
    Connection con = null;
    PreparedStatement st = null;

    try {
      con = DBManager.getConnection();
      String sql = "SELECT * FROM category WHERE id = ?";
      st = con.prepareStatement(sql);
      st.setInt(1, id);
      ResultSet rs = st.executeQuery();
      
      categoryName = rs.getString("id");

      st.close();

    } catch (SQLException e) {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    } finally {

      try {
        if (con != null) {
        con.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }


    return categoryName;
  }
}

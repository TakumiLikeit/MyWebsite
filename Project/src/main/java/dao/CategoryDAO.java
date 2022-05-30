package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import base.DBManager;

public class CategoryDAO {



  // カテゴリー名を取得するメソッド（戻り値：文字列）
  public static String getCategoryName(int categoryId) {
    String categoryName = null;
    Connection con = null;
    PreparedStatement st = null;

    try {
      con = DBManager.getConnection();
      String sql = "SELECT * FROM category WHERE id = ?";
      st = con.prepareStatement(sql);
      st.setInt(1, categoryId);
      ResultSet rs = st.executeQuery();

      if (rs.next()) {
        categoryName = rs.getString("name");
      } else {
        categoryName = "不明";
      }

      st.close();

    } catch (SQLException e) {
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

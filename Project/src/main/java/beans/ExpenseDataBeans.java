package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ExpenseDataBeans implements Serializable {
  private int id;
  private int userId;
  private int categoryId;
  private String name;
  private int price;
  private Date expenseDate;
  private String note;
  private Timestamp createDate;
  private Timestamp updateDate;
  private String categoryName;

  public ExpenseDataBeans() {
    super();
  }

  public ExpenseDataBeans(int id, int userId, int categoryId, String name, int price,
      Date expenseDate, String note, Timestamp createDate, Timestamp updateDate,
      String categoryName) {
    super();
    this.id = id;
    this.userId = userId;
    this.categoryId = categoryId;
    this.name = name;
    this.price = price;
    this.expenseDate = expenseDate;
    this.note = note;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.categoryName = categoryName;
  }

  // getterとsetter
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Date getExpenseDate() {
    return expenseDate;
  }

  public void setExpenseDate(Date expenseDate) {
    this.expenseDate = expenseDate;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }

  public Timestamp getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Timestamp updateDate) {
    this.updateDate = updateDate;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }




}

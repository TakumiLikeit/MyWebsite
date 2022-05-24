package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserDataBeans implements Serializable {
  private int id;
  private String loginId;
  private String name;
  private String password;
  private Timestamp createDate;
  private Timestamp updateDate;


  public UserDataBeans() {
    super();
  }

  public UserDataBeans(int id, String loginId, String name, String password, Timestamp createDate,
      Timestamp updateDate) {
    super();
    this.id = id;
    this.loginId = loginId;
    this.name = name;
    this.password = password;
    this.createDate = createDate;
    this.updateDate = updateDate;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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



}

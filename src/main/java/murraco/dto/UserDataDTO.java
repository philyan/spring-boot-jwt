package murraco.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserDataDTO {
  
  @ApiModelProperty
  private String username;
  @ApiModelProperty
  private String email;
  @ApiModelProperty
  private String password;
  @ApiModelProperty
  private String phone;
  @ApiModelProperty
  private String open_id;
  @ApiModelProperty
  private String open_account_type;
  @ApiModelProperty
  String roles;
  @ApiModelProperty
  private String signup_type;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getOpen_id() {
    return open_id;
  }

  public void setOpen_id(String open_id) {
    this.open_id = open_id;
  }

  public String getOpen_account_type() {
    return open_account_type;
  }

  public void setOpen_account_type(String open_account_type) {
    this.open_account_type = open_account_type;
  }

  public String getSignup_type() {
    return signup_type;
  }

  public void setSignup_type(String signup_type) {
    this.signup_type = signup_type;
  }
}

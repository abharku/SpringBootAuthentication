package com.bluenimbus.spring.auth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Model to reprsent a sign in Request", value="SignInRequest")
public class SignInRequestDTO {
	
	  @ApiModelProperty(position = 0, required=true)
	  private String username;
	  @ApiModelProperty(position = 1, required=true)
	  private String password;
	  
	  
		  public String getUserName() {
		    return username;
		  }

		  public void setUserName(String username) {
		    this.username = username;
		  }

		  public String getPassword() {
		    return password;
		  }

		  public void setPassword(String password) {
		    this.password = password;
		  }
		

}

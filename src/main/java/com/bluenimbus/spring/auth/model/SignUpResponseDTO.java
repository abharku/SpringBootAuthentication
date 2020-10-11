package com.bluenimbus.spring.auth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Model to reprsent a Signup Response", value="SignUpResponse")
public class SignUpResponseDTO {
	@ApiModelProperty(position = 0)
	 private boolean status=false;
	@ApiModelProperty(position = 1)
	 private String httpStatus;
	
	 @ApiModelProperty(position = 2, required = false)
	 private String token;
	 
	
	public boolean getStatus() {
	    return status;
	}

	public void setStatus(boolean status) {
	    this.status = status;
	}
	public String getHttpStatus() {
	    return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
	    this.httpStatus = httpStatus;
  }
	public String getToken() {
	    return token;
	}

	public void setToken(String token) {
	    this.token = token;
	}
	
}

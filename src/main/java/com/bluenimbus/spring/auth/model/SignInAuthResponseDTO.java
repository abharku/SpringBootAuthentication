package com.bluenimbus.spring.auth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Model to reprsent a Auth response", value="SignInAuthResponse")
public class SignInAuthResponseDTO {
	 @ApiModelProperty(position = 0)
	 private boolean status=false;
	 @ApiModelProperty(position = 1)
	 private String token;
	 @ApiModelProperty(position = 2, required = false)
	 private String message;
	 @ApiModelProperty(position = 3)
	 private String httpStatus;
	 
	 public boolean getStatus() {
		    return status;
	 }

      public void setStatus(boolean status) {
		    this.status = status;
	}
      
    public String getToken() {
		    return token;
	 }

    public void setToken(String token) {
		    this.token = token;
	}
    public String getHttpStatus() {
	    return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
	    this.httpStatus = httpStatus;
    }

}

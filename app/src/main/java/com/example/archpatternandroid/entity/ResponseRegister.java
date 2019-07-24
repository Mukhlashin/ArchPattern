package com.example.archpatternandroid.entity;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("msg")
	private String msg;

	@SerializedName("isSuccess")
	private boolean isSuccess;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setIsSuccess(boolean isSuccess){
		this.isSuccess = isSuccess;
	}

	public boolean isIsSuccess(){
		return isSuccess;
	}

	@Override
 	public String toString(){
		return 
			"ResponseRegister{" + 
			"msg = '" + msg + '\'' + 
			",isSuccess = '" + isSuccess + '\'' + 
			"}";
		}
}
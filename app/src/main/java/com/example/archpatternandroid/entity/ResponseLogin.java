package com.example.archpatternandroid.entity;


import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private Data data;

	@SerializedName("isSuccess")
	private boolean isSuccess;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
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
			"ResponseLogin{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",isSuccess = '" + isSuccess + '\'' + 
			"}";
		}
}
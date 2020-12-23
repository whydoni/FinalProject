package com.example.mybtpns.model;

import com.google.gson.annotations.SerializedName;

public class Nasabah {

	@SerializedName("password")
	private String password;

	@SerializedName("nasabahId")
	private int nasabahId;

	@SerializedName("userId")
	private int userId;

	@SerializedName("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setNasabahId(int nasabahId){
		this.nasabahId = nasabahId;
	}

	public int getNasabahId(){
		return nasabahId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}
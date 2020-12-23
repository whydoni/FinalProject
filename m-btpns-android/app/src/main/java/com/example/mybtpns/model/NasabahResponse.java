package com.example.mybtpns.model;

import com.google.gson.annotations.SerializedName;

public class NasabahResponse {

	@SerializedName("data")
	private Nasabah nasabah;

	@SerializedName("response")
	private int response;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setNasabah(Nasabah nasabah){
		this.nasabah = nasabah;
	}

	public Nasabah getNasabah(){
		return nasabah;
	}

	public void setResponse(int response){
		this.response = response;
	}

	public int getResponse(){
		return response;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}
package com.perscholas.travelcorps.models;

public class Member {
	private int memberId;
	private String name;
	private String email;
	private String password;
	private String favoriteLanguage;
	
	public Member() {
		super();
	}

	public Member(String name, String email, String password, String favoriteLanguage) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.favoriteLanguage = favoriteLanguage;
	}
	
	public Member(int memberId, String name, String email, String password, String favoriteLanguage) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.favoriteLanguage = favoriteLanguage;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}
	
	
	
	

}

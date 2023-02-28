package com.shop.vo;
// 회원에 대한 정보들을 저장할 목적으로 만든 객체
public class MemberVO {    
	
	private String name;
	private String id; 
	private String passwd; 
	private String mail;
	private String address;
	private String phone;
	private String gender;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}
	
	public String getphone() {
		return phone;
	}

	public void setphone(String phone) {
		this.phone = phone;
	}
	
	
	public String getgender() {
		return gender;
	}

	public void setgender(String gender) {
		this.gender = gender;
	}

}

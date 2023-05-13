package com.shop.vo;
// 회원에 대한 정보들을 저장할 목적으로 만든 객체
public class MemberVO {    
	
	private String customers_id;
	private String name;
	private String id; 
	private String nowpasswd;
	private String newpasswd;
	private String mail;
	private String address;
	private String phone;
	private String gender;
	
	
	
	
	public String getcustomers_id() {
		return customers_id;
	}

	public void setcustomers_id(String customers_id) {
		this.customers_id = customers_id;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getnowPasswd() {
		return nowpasswd;
	}

	public void setnowPasswd(String passwd) {
		this.nowpasswd = passwd;
	}
	
	public String getnewPasswd() {
		return newpasswd;
	}

	public void setnewPasswd(String passwd) {
		this.newpasswd = passwd;
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

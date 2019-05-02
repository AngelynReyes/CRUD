package com.dev.trainee.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="Trainee")
@RequestScoped
public class Trainee {
	private Integer id;
	private String Username;
	private String Pass;

	public Trainee() {
		
	}
	public Trainee(Integer id,String user, String pass) {
		this.id = id;
		this.Username = user;
		this.Pass = pass;
	}
	public String getUsername() {
		return Username;
	}
	public String getPass() {
		return Pass;
	}	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.Username = username;
	}
	public void setPass(String password) {
		this.Pass = password;
	}
}

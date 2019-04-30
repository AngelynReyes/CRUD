package com.dev.trainee.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="Trainee")
@RequestScoped
public class Trainee {

	private String Username;
	private String Pass;
	
	public Trainee(String user, String pass) {
		this.Username = user;
		this.Pass = pass;
	}
	public String getUsername() {
		return Username;
	}
	public String getPass() {
		return Pass;
	}	
}

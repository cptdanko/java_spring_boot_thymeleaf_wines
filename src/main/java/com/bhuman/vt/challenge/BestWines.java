package com.bhuman.vt.challenge;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.bhuman.vt.challenge.data.DataStore;

@ManagedBean(name = "bestWines", eager=true)
@RequestScoped
public class BestWines {
	
	@ManagedProperty(value = "#{message}")
	private Message messageBean;
	private String message;
	
	@ManagedProperty(value = "#{dataStore}")
	private DataStore dataStore;
	
	public BestWines() {
		System.out.println("Hello to the best wines");
	}
	
	public String getMessage() {
		if(message == null) {
			message = messageBean.getMessage();
		} 
		return message;
	}
	public void setMessageBean(Message messageBean) {
		this.messageBean = messageBean;
	}
}

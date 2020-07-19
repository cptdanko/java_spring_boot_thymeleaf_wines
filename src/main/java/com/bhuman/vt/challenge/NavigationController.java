package com.bhuman.vt.challenge;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {

	private static final long serialVersionUID = 8296015591703399370L;
	
	public String getNav() {
		return "This is the best navigation";
	}
	
	public String goHome() {
		return "home";
	}
}
package com.bhuman.vt.challenge;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.bhuman.vt.challenge.data.DataStore;
import com.bhuman.vt.challenge.model.Wine;

@ManagedBean(name = "wineDetail", eager = true)
@RequestScoped
public class WineDetail {

	@ManagedProperty(value = "#{param.wineCode}")
	private String wineCode;

	private Wine wine;

	private DataStore ds = new DataStore();

	/*
	 * When directed to this page, show wine Details
	 */
	public String showDetail() {
		this.wine = ds.getWine(wineCode);
		return "wineDetail";
	}

	public String getWineCode() {
		return wineCode;
	}

	public void setWineCode(String wineCode) {
		this.wineCode = wineCode;
	}

	public Wine getWineToShow() {
		return wine;
	}

	public void setWineToShow(Wine wineToShow) {
		this.wine = wineToShow;
	}

}

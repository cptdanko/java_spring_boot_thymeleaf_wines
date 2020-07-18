package com.bhuman.vt.challenge.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.bhuman.vt.challenge.model.GrapeComponent;
import com.bhuman.vt.challenge.model.Wine;

@ManagedBean(name = "dataStore", eager = true)
@RequestScoped
public class DataStore {
	
	private List<Wine> wines = new ArrayList<Wine>();
	private Map<String, Wine> wineMap = new HashMap<String, Wine>();
	
	public DataStore() {
		this.setupData();
	}
	/*
	 * At this stage, all the wine data is hard coded, to be removed later
	 * based on newer requirements.
	 */
	public void setupData() {
		Wine ppnoo2vk = new Wine("15MPPN002-VK",100000.0);
		ppnoo2vk.setDescription("2015 Mornington Peninsula Pinot Noir - Vintage Kerr special batch");
		ppnoo2vk.setTankCode("T100-03");
		ppnoo2vk.setProductState("Filtered");
		ppnoo2vk.setOwnerName("Vintage Kerr");
		
		ppnoo2vk.getComponents().add(new GrapeComponent(60D, 2015, "Pinot Noir", "Mornington"));
		ppnoo2vk.getComponents().add(new GrapeComponent(2D, 2015, "Pinot Noir", "Yarra Valley"));
		ppnoo2vk.getComponents().add(new GrapeComponent(5D, 2014, "Pinot Noir", "Yarra Valley"));
		ppnoo2vk.getComponents().add(new GrapeComponent(3D, 2015, "Merlot", "Yarra Valley"));
		ppnoo2vk.getComponents().add(new GrapeComponent(1D, 2015, "Shiraz", "Mornington"));
		ppnoo2vk.getComponents().add(new GrapeComponent(2D, 2015, "Zinfandel", "Macedon"));
		ppnoo2vk.getComponents().add(new GrapeComponent(2D, 2014, "Malbec", "Port Phillip"));
		ppnoo2vk.getComponents().add(new GrapeComponent(10D, 2015, "Pinot Noir", "Mornington"));
		ppnoo2vk.getComponents().add(new GrapeComponent(10D, 2014, "Pinot Noir", "Mornington"));
		ppnoo2vk.getComponents().add(new GrapeComponent(5D, 2013, "Cabernet", "Heathcote"));
		
		Wine w = new Wine("11YVCHAR001", 1000);
		w.setDescription("2011 Yarra Valley Chardonnay");
		w.setTankCode("T25-01");
		w.setProductState("Ready for bottling");
		w.setOwnerName("YV Wines Pty Ltd");

		w.getComponents().add(new GrapeComponent(80D, 2011, "Chardonnay", "Yarra Valley"));
		w.getComponents().add(new GrapeComponent(10D, 2010, "Chardonnay", "Macedon"));
		w.getComponents().add(new GrapeComponent(5D, 2011, "Pinot Noir", "Mornington"));
		w.getComponents().add(new GrapeComponent(5D, 2010, "Pinot Noir", "Macedon"));
		
		String key1 = ppnoo2vk.getLotCode() + " (" + ppnoo2vk.getTankCode() +")";
		wineMap.put(key1, ppnoo2vk);
		wineMap.put(w.getLotCode(), w);
		wines.add(ppnoo2vk);
		wines.add(w);		
	}
	public List<Wine> getWines() {
		return wines;
	}
	public void setWines(List<Wine> wines) {
		this.wines = wines;
	}
}

package com.bhuman.vt.challenge.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhuman.vt.challenge.analytics.WineAnalytics;
import com.bhuman.vt.challenge.data.DataStore;
import com.bhuman.vt.challenge.model.Wine;

@RestController
public class AnalyticsAPI {

	@Autowired
	private WineAnalytics analytics;
	
	//we could potentially have this autowired too, 
	//left it like this on purpose 
	private DataStore dataStore = new DataStore();
	
	/*
	 * We don't need to worry about parsing it to json 
	 * it's automatically handled by Spring's 
	 * MappingJackson2HttpMessageConverter. Jackson is on 
	 * classPath so the data returned is converted to JSON
	 */
	@GetMapping("/api/breakdown/year")
	public Map<String, Double> getYearBreakdown(@RequestParam(value = "code", defaultValue = "15MPPN002-VK") 
	String code, Model model) {
		Wine w = dataStore.getWine(code);
		return analytics.getYearBreakdown(w);
	}
	@GetMapping("/api/breakdown/region")
	public Map<String, Double> getRegionBreakdown(@RequestParam(value = "code", defaultValue = "15MPPN002-VK") 
	String code, Model model) {
		Wine w = dataStore.getWine(code);
		return analytics.getRegionBreakdown(w);
	}
	
	@GetMapping("/api/breakdown/variety")
	public Map<String, Double> getVarietyBreakdown(@RequestParam(value = "code", defaultValue = "15MPPN002-VK") 
	String code, Model model) {
		Wine w = dataStore.getWine(code);
		return analytics.getVarietyBreakdown(w);
	}
	
	@GetMapping("/api/breakdown/yearNvariety")
	public Map<String, Map<String, Double>> getYrnVBreakdown(@RequestParam(value = "code", defaultValue = "15MPPN002-VK") 
	String code, Model model) {
		Wine w = dataStore.getWine(code);
		return analytics.getYrnVBreakdown(w);
	}
}

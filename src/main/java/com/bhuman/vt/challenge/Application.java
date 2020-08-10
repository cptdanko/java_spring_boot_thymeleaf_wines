package com.bhuman.vt.challenge;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bhuman.vt.challenge.analytics.WineAnalytics;
import com.bhuman.vt.challenge.analytics.WineStatistics;
import com.bhuman.vt.challenge.model.GrapeComponent;
import com.bhuman.vt.challenge.model.Wine;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
	    Wine w = new Wine("11YVCHAR001", 1000);
		w.setDescription("2011 Yarra Valley Chardonnay");
		w.setTankCode("T25-01");
		w.setProductState("Ready for bottling");
		w.setOwnerName("YV Wines Pty Ltd");

		w.getComponents().add(new GrapeComponent(80D, 2011, "Chardonnay", "Yarra Valley"));
		w.getComponents().add(new GrapeComponent(10D, 2010, "Chardonnay", "Macedon"));
		w.getComponents().add(new GrapeComponent(5D, 2011, "Pinot Noir", "Mornington"));
		w.getComponents().add(new GrapeComponent(5D, 2010, "Pinot Noir", "Macedon"));
		
		WineStatistics wineStats = new WineAnalytics();

		System.out.println("     Year Breakdown    ");
		System.out.println("========================");
		printYearBreakdown(w, wineStats);
		System.out.println("     Variety Breakdown     ");
		System.out.println("========================");
		printVarietyBreakdown(w, wineStats);
		System.out.println("     Region Breakdown     ");
		System.out.println("========================");
		printRegionBreakdown(w, wineStats);
		System.out.println("     Year & Variety Breakdown     ");
		System.out.println("========================");
		printYearAndVarietyBreakdown(w, wineStats);
		SpringApplication.run(Application.class, args);
	}
	
	private static void printVarietyBreakdown(Wine w, WineStatistics stats) {
		Map<String, Double> varietyMap = stats.getVarietyBreakdown(w);
		printData(varietyMap);
	}

	private static void printYearBreakdown(Wine w, WineStatistics stats) {
		Map<String, Double> yearMap = stats.getYearBreakdown(w);
		printData(yearMap);
	}

	private static void printRegionBreakdown(Wine w, WineStatistics stats) {
		Map<String, Double> regionMap = stats.getRegionBreakdown(w);
		printData(regionMap);
	}

	private static void printYearAndVarietyBreakdown(Wine w, WineStatistics stats) {
		Map<String, Map<String, Double>> yrNVariety = stats.getYrnVBreakdown(w);
		for (Entry<String, Map<String, Double>> entrySet : yrNVariety.entrySet()) {
			System.out.println(entrySet.getKey() + ",");
			printData(entrySet.getValue());
		}
	}

	private static void printData(Map<String, Double> dataMap) {
		StringBuilder toPrint = new StringBuilder();
		for (Entry<String, Double> entry : dataMap.entrySet()) {
			toPrint.append(Math.round(entry.getValue()) + "% - " + entry.getKey() + "\n");
		}
		System.out.println(toPrint.toString());
		System.out.println("------------------");
	}
}
package com.bhuman.vt.challenge;

import java.util.Map;
import java.util.Map.Entry;

import com.bhuman.vt.challenge.analytics.WineStatistics;
import com.bhuman.vt.challenge.model.GrapeComponent;
import com.bhuman.vt.challenge.model.Wine;

public class WineTestMain {
	
	/*
	 * Old code, only here for for now, to be removed
	 */
	private static void setupTestCase2(WineStatistics stats) {
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
		
		System.out.println("=========Testing 15MPPN002=========");
		System.out.println("     Year Breakdown    ");
		System.out.println("========================");
		printYearBreakdown(ppnoo2vk, stats);
		System.out.println("     Variety Breakdown     ");
		System.out.println("========================");
		printVarietyBreakdown(ppnoo2vk, stats);
		System.out.println("     Region Breakdown     ");
		System.out.println("========================");
		printRegionBreakdown(ppnoo2vk, stats);
		System.out.println("     Year & Variety Breakdown     ");
		System.out.println("========================");
		printYearAndVarietyBreakdown(ppnoo2vk, stats);
		
	}
	/*
	 * Commented out the code so we can run the main app 
	 * public static void main(String[] args) {

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
		setupTestCase2(wineStats);
		

	}*/

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

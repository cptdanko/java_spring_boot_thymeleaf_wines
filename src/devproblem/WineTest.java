package devproblem;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import devproblem.models.Wine;

public class WineTest {

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
		
		System.out.println("     Year Breakdown    ");
		System.out.println("========================");
		printYearBreakdown(w);
		System.out.println("     Variety Breakdown     ");
		System.out.println("========================");
		printVarietyBreakdown(w);
		System.out.println("     Region Breakdown     ");
		System.out.println("========================");
		printRegionBreakdown(w);
		System.out.println("     Year & Variety Breakdown     ");
		System.out.println("========================");
		printYearAndVarietyBreakdown(w);

	}

	private static void printVarietyBreakdown(Wine w) {
		Map<String, Double> varietyMap = new HashMap<String, Double>();
		for (GrapeComponent gc : w.getComponents()) {
			Double percentage = varietyMap.get(gc.getVariety());
			if (percentage != null) {
				varietyMap.put(gc.getVariety(), percentage + gc.getPercentage());
			} else {
				varietyMap.put(gc.getVariety(), gc.getPercentage());
			}
		}
		printData(varietyMap);
	}

	private static void printYearBreakdown(Wine w) {
		Map<String, Double> yearMap = new HashMap<>();
		for (GrapeComponent gc : w.getComponents()) {
			String yearStr = "" + gc.getYear();
			Double percentage = yearMap.get(yearStr);
			if (percentage != null) {
				yearMap.put(yearStr, percentage += gc.getPercentage());
			} else {
				yearMap.put(yearStr, gc.getPercentage());
			}
		}
		printData(yearMap);
	}

	private static void printRegionBreakdown(Wine w) {
		Map<String, Double> regionMap = new HashMap<String, Double>();
		for (GrapeComponent gc : w.getComponents()) {
			Double percentage = regionMap.get(gc.getRegion());
			if (percentage == null) {
				regionMap.put(gc.getRegion(), gc.getPercentage());
			} else {
				regionMap.put(gc.getRegion(), percentage += gc.getPercentage());
			}
		}
		printData(regionMap);
	}

	private static void printYearAndVarietyBreakdown(Wine w) {
		Map<String, Map<String, Double>> yrNVariety = new HashMap<String, Map<String, Double>>();
		for (GrapeComponent gc : w.getComponents()) {
			String yearStr = "" + gc.getYear();
			Map<String, Double> yearVariety = yrNVariety.get(yearStr);
			if (yearVariety == null) {
				Map<String, Double> vMap = new HashMap<String, Double>();
				vMap.put(gc.getVariety(), gc.getPercentage());
				yrNVariety.put(yearStr, vMap);
			} else {
				Double existingPercent = yearVariety.get(gc.getVariety());
				if (existingPercent == null) {
					yearVariety.put(gc.getVariety(), gc.getPercentage());
				} else {
					yearVariety.put(gc.getVariety(), gc.getPercentage() + existingPercent);
				}
				yrNVariety.put(yearStr, yearVariety);
			}
		}
		for(Entry<String, Map<String, Double>> entrySet: yrNVariety.entrySet()) {
			System.out.println(entrySet.getKey()+",");
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
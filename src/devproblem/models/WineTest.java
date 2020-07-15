package devproblem.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import devproblem.GrapeComponent;

public class WineTest {
	
	/* Intentionally not using Java 8 streams */
	Wine w = new Wine("11YVCHAR001", 1000);

	@Before
	public void setUp() throws Exception {
		w.setDescription("2011 Yarra Valley Chardonnay");
		w.setTankCode("T25-01");
		w.setProductState("Ready for bottling");
		w.setOwnerName("YV Wines Pty Ltd");

		w.getComponents().add(new GrapeComponent(80D, 2011, "Chardonnay", "Yarra Valley"));
		w.getComponents().add(new GrapeComponent(10D, 2010, "Chardonnay", "Macedon"));
		w.getComponents().add(new GrapeComponent(5D, 2011, "Pinot Noir", "Mornington"));
		w.getComponents().add(new GrapeComponent(5D, 2010, "Pinot Noir", "Macedon"));
	}

	@Test
	public void testVarietyBreakdown() {
		Map<String, Double> varietyMap = new HashMap<String, Double>();
		for (GrapeComponent gc : w.getComponents()) {
			Double percentage = varietyMap.get(gc.getVariety());
			if (percentage != null) {
				varietyMap.put(gc.getVariety(), percentage + gc.getPercentage());
			} else {
				varietyMap.put(gc.getVariety(), gc.getPercentage());
			}
		}
		Double chardPercent = varietyMap.get("Chardonnay");
		Double pinotPercent = varietyMap.get("Pinot Noir");
		Double expected = 90D;
		Double pExpect = 10D;
		Assert.assertEquals(expected, chardPercent);
		Assert.assertEquals(pExpect, pinotPercent);
		System.out.println("     Variety Breakdown     ");
		System.out.println("========================");
		printData(varietyMap);
	}

	@Test
	public void testYearBreakdown() {
		Map<String, Double> yearMap = new HashMap<String, Double>();
		for (GrapeComponent gc : w.getComponents()) {
			String yearStr = "" + gc.getYear();
			Double percentage = yearMap.get(yearStr);
			if (percentage != null) {
				yearMap.put(yearStr, percentage += gc.getPercentage());
			} else {
				yearMap.put(yearStr, gc.getPercentage());
			}
		}
		Double percent = yearMap.get("2011");
		Double actualPercent = 85D;
		Assert.assertEquals(actualPercent, percent);
		Assert.assertEquals(2, yearMap.size());
		System.out.println("     Year Breakdown    ");
		System.out.println("========================");
		printData(yearMap);
	}

	@Test
	public void testRegionBreakdown() {
		Map<String, Double> regionMap = new HashMap<String, Double>();
		for (GrapeComponent gc : w.getComponents()) {
			Double percentage = regionMap.get(gc.getRegion());
			if (percentage == null) {
				regionMap.put(gc.getRegion(), gc.getPercentage());
			} else {
				regionMap.put(gc.getRegion(), percentage += gc.getPercentage());
			}
		}
		Double mPercent = 15D;
		Double yvPercent = 80D;
		Double mtPercent = 5D;
		Assert.assertEquals(3, regionMap.size());
		Assert.assertEquals(mPercent, regionMap.get("Macedon"));
		Assert.assertEquals(yvPercent, regionMap.get("Yarra Valley"));
		Assert.assertEquals(mtPercent, regionMap.get("Mornington"));

		System.out.println("     Region Breakdown     ");
		System.out.println("========================");
		printData(regionMap);
	}

	@Test
	public void testYearAndVarietyBreakdown() {
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
		Assert.assertEquals(2, yrNVariety.size());
		System.out.println("     Year & Variety Breakdown     ");
		System.out.println("========================");
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

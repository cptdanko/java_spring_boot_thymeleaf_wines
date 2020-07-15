package devproblem.models;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import devproblem.GrapeComponent;
import devproblem.wine.analytics.WineAnalytics;
import devproblem.wine.analytics.WineStatistics;

public class WineTest {
	
	/* Intentionally not using Java 8 streams */
	Wine vcharoo1 = new Wine("11YVCHAR001", 1000);
	Wine ppnoo2vk = new Wine("15MPPN002-VK",100000.0);
	WineStatistics wineStats = new WineAnalytics();

	@Before
	public void setUp() throws Exception {
		vcharoo1.setDescription("2011 Yarra Valley Chardonnay");
		vcharoo1.setTankCode("T25-01");
		vcharoo1.setProductState("Ready for bottling");
		vcharoo1.setOwnerName("YV Wines Pty Ltd");

		vcharoo1.getComponents().add(new GrapeComponent(80D, 2011, "Chardonnay", "Yarra Valley"));
		vcharoo1.getComponents().add(new GrapeComponent(10D, 2010, "Chardonnay", "Macedon"));
		vcharoo1.getComponents().add(new GrapeComponent(5D, 2011, "Pinot Noir", "Mornington"));
		vcharoo1.getComponents().add(new GrapeComponent(5D, 2010, "Pinot Noir", "Macedon"));
		
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
		
	}
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testVarietyBreakdown() {
		Map<String, Double> varietyMap = wineStats.getVarietyBreakdown(vcharoo1);
		Double chardPercent = varietyMap.get("Chardonnay");
		Double pinotPercent = varietyMap.get("Pinot Noir");
		Double expected = 90D;
		Double pExpect = 10D;
		Assert.assertEquals(expected, chardPercent);
		Assert.assertEquals(pExpect, pinotPercent);
		
		Map<String, Double> vMap2 = wineStats.getVarietyBreakdown(ppnoo2vk);
		Double ppExpected = 87D;
		//87% - Pinot Noir
		Assert.assertEquals(ppExpected, vMap2.get("Pinot Noir"));
		
		System.out.println("     Variety Breakdown     ");
		System.out.println("========================");
		printData(varietyMap);
	}

	@Test
	public void testYearBreakdown() {
		Map<String, Double> yearMap = wineStats.getYearBreakdown(vcharoo1);
		
		Double percent = yearMap.get("2011");
		Double actualPercent = 85D;
		Assert.assertEquals(actualPercent, percent);
		Assert.assertEquals(2, yearMap.size());
		
		Map<String, Double> yMap2 = wineStats.getYearBreakdown(ppnoo2vk);
		Double expected = 78D;
		Assert.assertEquals(expected, yMap2.get("2015"));
		System.out.println("     Year Breakdown    ");
		System.out.println("========================");
		printData(yearMap);
	}

	@Test
	public void testRegionBreakdown() {
		Map<String, Double> regionMap = wineStats.getRegionBreakdown(vcharoo1);

		Double mPercent = 15D;
		Double yvPercent = 80D;
		Double mtPercent = 5D;
		Assert.assertEquals(3, regionMap.size());
		Assert.assertEquals(mPercent, regionMap.get("Macedon"));
		Assert.assertEquals(yvPercent, regionMap.get("Yarra Valley"));
		Assert.assertEquals(mtPercent, regionMap.get("Mornington"));
		
		Map<String, Double> r2 = wineStats.getRegionBreakdown(ppnoo2vk);
		Double expected = 5D;
		Assert.assertEquals(expected, r2.get("Heathcote"));

		System.out.println("     Region Breakdown     ");
		System.out.println("========================");
		printData(regionMap);
	}

	@Test
	public void testYearAndVarietyBreakdown() {
		Map<String, Map<String, Double>> yrNVariety = wineStats.getYrnVBreakdown(vcharoo1); 

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

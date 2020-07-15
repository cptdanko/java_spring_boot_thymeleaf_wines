# JavaPartitions

This repository aims to analyse Wine data with regards to wines manufactured in Australia. 

At this stage, this is a simple repo that uses pre-java 8 techniques to parition the Wine data.

## Class structure

```
public interface WineStatistics {
  /* distribution */
  public Map<String, Double> getYearBreakdown(Wine w);
  public Map<String, Double> getRegionBreakdown(Wine w);
  public Map<String, Double> getVarietyBreakdown(Wine w);
  public Map<String, Map<String,Double>> getYrnVBreakdown(Wine w);
}

public class WineAnalytics implements WineStatistics {

	@Override
	public Map<String, Double> getYearBreakdown(Wine w) {
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
		return yearMap;
	}
....
```

## Tests

It has 2 tests, 

1. It prints the wine statistics using via a Java main class
2. It tests and prints the wine statistics via JUnit test 

### JUnit test example

```
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
```
# Run

At this stage, it's an Eclipse project, hence import it in Eclipse and run it on the command line

# To be added
This will be converted to a full Java web project using Maven and Spring Framework.

package devproblem.wine.analytics;

import java.util.HashMap;
import java.util.Map;

import devproblem.GrapeComponent;
import devproblem.models.Wine;

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

	@Override
	public Map<String, Double> getRegionBreakdown(Wine w) {
		Map<String, Double> regionMap = new HashMap<String, Double>();
		for (GrapeComponent gc : w.getComponents()) {
			Double percentage = regionMap.get(gc.getRegion());
			if (percentage == null) {
				regionMap.put(gc.getRegion(), gc.getPercentage());
			} else {
				regionMap.put(gc.getRegion(), percentage += gc.getPercentage());
			}
		}
		return regionMap;
	}

	@Override
	public Map<String, Double> getVarietyBreakdown(Wine w) {
		Map<String, Double> varietyMap = new HashMap<String, Double>();
		for (GrapeComponent gc : w.getComponents()) {
			Double percentage = varietyMap.get(gc.getVariety());
			if (percentage != null) {
				varietyMap.put(gc.getVariety(), percentage + gc.getPercentage());
			} else {
				varietyMap.put(gc.getVariety(), gc.getPercentage());
			}
		}
		return varietyMap;
	}

	@Override
	public Map<String, Map<String, Double>> getYrnVBreakdown(Wine w) {
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
		return yrNVariety;
	}
}

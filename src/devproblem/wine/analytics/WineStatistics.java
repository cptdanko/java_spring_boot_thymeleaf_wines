package devproblem.wine.analytics;

import java.util.Map;

import devproblem.models.Wine;

public interface WineStatistics {
	/* distribution */
	public Map<String, Double> getYearBreakdown(Wine w);
	public Map<String, Double> getRegionBreakdown(Wine w);
	public Map<String, Double> getVarietyBreakdown(Wine w);
	public Map<String, Map<String,Double>> getYrnVBreakdown(Wine w);
}

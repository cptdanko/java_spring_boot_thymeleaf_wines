package com.bhuman.vt.challenge.analytics;

import java.util.Map;

import com.bhuman.vt.challenge.model.Wine;

public interface WineStatistics {
	
	/* distribution */
	public Map<String, Double> getYearBreakdown(Wine w);

	public Map<String, Double> getRegionBreakdown(Wine w);

	public Map<String, Double> getVarietyBreakdown(Wine w);

	public Map<String, Map<String, Double>> getYrnVBreakdown(Wine w);
}

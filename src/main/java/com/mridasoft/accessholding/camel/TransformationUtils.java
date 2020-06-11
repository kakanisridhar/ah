package com.mridasoft.accessholding.camel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mridasoft.accessholding.dto.CountryCityPopulation;

public class TransformationUtils {
	
	public List<CountryCityPopulation> populationMapToList(Map<String, CountryCityPopulation> populationMap) {
		return new ArrayList<>(populationMap.values());
	}

}

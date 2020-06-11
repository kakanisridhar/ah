package com.mridasoft.accessholding.camel;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import com.mridasoft.accessholding.dto.CityPopulation;
import com.mridasoft.accessholding.dto.CountryCityPopulation;
import com.mridasoft.accessholding.dto.CountryCityPopulationRecord;

public class GroupByCountryAggregator implements AggregationStrategy {
	@SuppressWarnings("unchecked")
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		CountryCityPopulationRecord popRecord = (CountryCityPopulationRecord) newExchange.getIn().getBody();
		Map<String, CountryCityPopulation> map = null;
		if (oldExchange == null) {
			map = new HashMap<String, CountryCityPopulation>();
			CountryCityPopulation contCityPop = new CountryCityPopulation();
			contCityPop.setCountry(popRecord.getCountry());
			contCityPop.getCities().add(new CityPopulation(popRecord.getCity(), popRecord.getPopulation()));
			map.put(popRecord.getCountry(), contCityPop);
			newExchange.getIn().setBody(map);
			return newExchange;
		} else {
			map = oldExchange.getIn().getBody(Map.class);
			CountryCityPopulation contCityPop = map.get(popRecord.getCountry());
			if (contCityPop == null) {
				contCityPop = new CountryCityPopulation();
				contCityPop.setCountry(popRecord.getCountry());
			}
			contCityPop.getCities().add(new CityPopulation(popRecord.getCity(), popRecord.getPopulation()));
			map.put(popRecord.getCountry(), contCityPop);

			oldExchange.setProperty("CamelSplitComplete", newExchange.getProperty("CamelSplitComplete"));
			return oldExchange;
		}
	}

}

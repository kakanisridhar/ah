package com.mridasoft.accessholding.camel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import com.mridasoft.accessholding.model.CityPopulation;

public class GroupByCountryAggregator implements AggregationStrategy {
	@Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        CityPopulation newBody = (CityPopulation)newExchange.getIn().getBody();
        Map<String, List<CityPopulation>> map = null;
        if (oldExchange == null) {
            map = new HashMap<String, List<CityPopulation>>();
            ArrayList<CityPopulation> list = new ArrayList<>();
            list.add(newBody);
            map.put(newBody.getCountry(), list);
            newExchange.getIn().setBody(map);
            return newExchange;
        } else {
            map = oldExchange.getIn().getBody(Map.class);
            List<CityPopulation> list = map.get(newBody.getCountry());
            if ( list == null ) {
                list = new ArrayList<CityPopulation>();
            }
            list.add(newBody);
            map.put(newBody.getCountry(), list);

            oldExchange.setProperty("CamelSplitComplete", newExchange.getProperty("CamelSplitComplete"));
            return oldExchange;
        }
    }

}

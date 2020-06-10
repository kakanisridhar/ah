package com.mridasoft.accessholding.camel;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mridasoft.accessholding.model.CityPopulation;

@Component
public class AppRoutebuilder extends RouteBuilder  {
	
	@Value("${data.file.location}")
	private String dataFileLocation;

	@Override
	public void configure() throws Exception {
		from("file:" + dataFileLocation + "?noop=true&delete=true&delay=10000")
		.split(body().tokenize("\n"))
        .log("Read line ${body}")
        .unmarshal()
        .bindy(BindyType.Csv, CityPopulation.class)
        .aggregate(constant(true), new GroupByCountryAggregator())
           .completionPredicate(simple("${exchangeProperty.CamelSplitComplete} == true"))
        .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                Map results = (Map) exchange.getIn().getBody();
                System.out.println("Got results for " + results.size() + " countries");
            }
        });
	}

}

package com.mridasoft.accessholding.camel;

import org.apache.camel.Processor;
import com.mridasoft.accessholding.dto.CountryCityPopulation;
import org.apache.camel.Exchange;

public class FileNameProcessor implements Processor {
	public void process(Exchange exchange) throws Exception {
		CountryCityPopulation record = exchange.getIn().getBody(CountryCityPopulation.class);
		exchange.getIn().setHeader(Exchange.FILE_NAME, record.getCountry() + ".xml");
	}
}
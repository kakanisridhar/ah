package com.mridasoft.accessholding.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mridasoft.accessholding.dto.CountryCityPopulation;
import com.mridasoft.accessholding.dto.CountryCityPopulationRecord;

@Component
public class PopulationDataFileProcessingRoute extends RouteBuilder  {
	
	@Value("${app.route.datawriter}")
	private String dataWriterRoute;
	
	@Value("${app.route.xmlwriter}")
	private String xmlWriterRoute;
	
	@Value("${app.route.datareader}")
	private String dataReaderRoute; 

	@Override
	public void configure() throws Exception {
		
		JaxbDataFormat jaxbDataFormat = new JaxbDataFormat();
		jaxbDataFormat.setContextPathIsClassName("true");
		jaxbDataFormat.setContextPath(CountryCityPopulation.class.getName());

		from(dataReaderRoute)
		.log("processing file ${in.headers.CamelFileName}")
		.split(body().tokenize("\n"))
        	.unmarshal()
        	.bindy(BindyType.Csv, CountryCityPopulationRecord.class)
        	.aggregate(constant(true), new GroupByCountryAggregator())
        	.completionPredicate(simple("${exchangeProperty.CamelSplitComplete} == true"))
         .bean(TransformationUtils.class,"populationMapToList")	
         .split(body())    
         	.to(xmlWriterRoute)
         	.end()
         .log("Done prcessing of file ${in.headers.CamelFileName}");
		
		from(xmlWriterRoute)
		.process(new FileNameProcessor())
		.marshal(jaxbDataFormat)
		.to(dataWriterRoute);
	}
}

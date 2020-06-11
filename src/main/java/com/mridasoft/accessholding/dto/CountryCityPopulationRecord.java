package com.mridasoft.accessholding.dto;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",")
public class CountryCityPopulationRecord {
	
	@DataField(pos = 1)
    private String country;

    @DataField(pos = 2)
    private String city;
    
    @DataField(pos = 3)
    private String population;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}
}

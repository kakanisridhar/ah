package com.mridasoft.accessholding.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class CityPopulation {

	@XmlAttribute(name = "name")
	private final String city;

    @XmlElement
	private final String population;

	public CityPopulation(String city, String population) {
		this.city = city;
		this.population = population;
	}

	public String getCity() {
		return city;
	}

	public String getPopulation() {
		return population;
	}
	
}

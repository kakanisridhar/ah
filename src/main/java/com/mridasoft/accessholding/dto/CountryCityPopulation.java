package com.mridasoft.accessholding.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "cities")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryCityPopulation {
	
	@XmlTransient
	private String country;
	
	@XmlElement(name = "city")
	private List<CityPopulation> cities = new ArrayList<>();

	public CountryCityPopulation() {
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<CityPopulation> getCities() {
		return cities;
	}

	public void setCities(List<CityPopulation> cities) {
		this.cities = cities;
	}

}

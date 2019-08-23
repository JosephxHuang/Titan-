package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Venue {
	private Lines address;
	private City city;
	
	public Lines getAddress() {
		return address;
	}
	public City getCity() {
		return city;
	}
}

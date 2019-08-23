package entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Embedded {
	private List<Venue> venues;

	public List<Venue> getVenues() {
		return venues;
	}
	
}

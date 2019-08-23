
package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
	private String name;

	public String getName() {
		return name;
	}
}


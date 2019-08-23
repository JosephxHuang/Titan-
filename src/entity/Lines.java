package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lines {

	private String line1;
	private String line2;
	private String line3;
	
	public String getLine1() {
		return line1;
	}
	public String getLine2() {
		return line2;
	}

	public String getLine3() {
		return line3;
	}
}


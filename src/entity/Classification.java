package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Classification {
	private Segment segment;

	public Segment getSegment() {
		return segment;
	}
}

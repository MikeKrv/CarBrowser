package com.forte.login;

public class SubModel {
	public final long id;
	public final String header;
	public final String tabHeader;
	public final String classification;
	public final String description;
	public final String image;
	public final long carModelId;

	public SubModel(long id, String header, String tabHeader, String classification, String description, 
			String image, long carModelId) {
		this.id = id;
		this.header = header;
		this.tabHeader = tabHeader;
		this.classification = classification;
		this.description = description;
		this.image = image;
		this.carModelId = carModelId;
	}
	
	@Override
	public String toString() {
		return header;
	}	

}

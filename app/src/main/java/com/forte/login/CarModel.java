package com.forte.login;

public class CarModel {
	public final long id;
	public final String header;
	public final SubModel[] subModels;
	public final long carBrandId;

	public CarModel(long id, String header, SubModel[] subModels, long carBrandId) {
		this.id = id;
		this.header = header;
		this.subModels = subModels;
		this.carBrandId = carBrandId;
	}
	
	@Override
	public String toString() {
		return header;
	}	

}

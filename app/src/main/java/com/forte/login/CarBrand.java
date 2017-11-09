package com.forte.login;

public class CarBrand {
	public long id;
	public final String name;
	public final CarModel[] models;
	public final String icon;
	
	public CarBrand(long id, String name, String icon, CarModel[] models) {
		this.id = id;
		this.name = name;
		this.models = models;
		this.icon = icon;
	}

	@Override
	public String toString() {
		return name;
	}
		
}

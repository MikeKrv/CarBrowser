package com.forte.carbrowser.database;

import java.util.ArrayList;
import java.util.List;

import com.forte.login.CarBrand;
import com.forte.login.CarModel;
import com.forte.login.SubModel;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CarBrowserDataSource {
	private SQLiteDatabase database;
	private CarBrowserDatabaseHelper dbHelper;
	private String sqlQuery = "select * from " + CarBrowserDatabaseHelper.TABLE_CAR_MODEL + " where " + CarBrowserDatabaseHelper.COLUMN_BRAND_ID + " = ?";
	private String sqlQueryCarBrand = "select * from " + CarBrowserDatabaseHelper.TABLE_CAR_BRAND + " where " + CarBrowserDatabaseHelper.COLUMN_ID + " = ?";

	public CarBrowserDataSource(Context context) {
		dbHelper = new CarBrowserDatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public List<CarBrand> getAllCarBrands() {
		List<CarBrand> carBrands = new ArrayList<CarBrand>();

		Cursor cursor = database.query(CarBrowserDatabaseHelper.TABLE_CAR_BRAND, CarBrowserDatabaseHelper.CAR_BRAND_COLUMNS, null,
				null, null, null, null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			CarBrand carBrand = cursorToCarBrand(cursor);
			carBrands.add(carBrand);
			cursor.moveToNext();
		}
		cursor.close();
		return carBrands;
	}
	
	private CarBrand cursorToCarBrand(Cursor cursor) {
		CarBrand carBrand = new CarBrand(cursor.getLong(0), cursor.getString(1), cursor.getString(2), null);
		return carBrand;
	}
	
	public List<CarModel> getCarModels(long carBrandId){
		List<CarModel> carModels = new ArrayList<CarModel>();
		
		Cursor cursor = database.rawQuery(sqlQuery, new String[] {Long.toString(carBrandId)}); 
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			CarModel carModel = cursorToCarModel(cursor);
			carModels.add(carModel);
			cursor.moveToNext();
		}
		cursor.close();
		return carModels;
	}
	
	private CarModel cursorToCarModel(Cursor cursor) {
		CarModel carModel = new CarModel(cursor.getLong(0), cursor.getString(2), null, cursor.getLong(1));
		return carModel;
	}
	
	private SubModel cursorToSubModel(Cursor cursor) {
		SubModel subModel = new SubModel(cursor.getLong(0), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getLong(1));
		return subModel;
	}
	
	public CarBrand getCarBrand(long carBrandId){
		
		Cursor cursor = database.rawQuery(sqlQueryCarBrand, new String[] {Long.toString(carBrandId)}); 

		CarBrand carBrand = cursorToCarBrand(cursor);
		cursor.close();
		
		return carBrand;
		
	}
	
}

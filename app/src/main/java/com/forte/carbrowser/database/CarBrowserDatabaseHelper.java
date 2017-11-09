package com.forte.carbrowser.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.forte.login.CarBrand;
import com.forte.login.CarModel;
import com.forte.login.CarBrandsCollection;
import com.forte.login.SubModel;

public class CarBrowserDatabaseHelper extends SQLiteOpenHelper {
	public static final String TABLE_CAR_BRAND = "carbrand";
	public static final String TABLE_CAR_MODEL = "carmodel";
	public static final String TABLE_SUB_MODEL = "submodel";
	public static final String COLUMN_ID = "_id";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_ICON = "icon";
	public static final String COLUMN_BRAND_ID = "brand_id";
	public static final String COLUMN_MODEL_ID = "model_id";
	private static final String COLUMN_HEADER = "header";
	private static final String COLUMN_TABHEADER = "tabheader";
	private static final String COLUMN_CLASSIFICATION = "classification";
	private static final String COLUMN_DESCRIPTION = "description";
	private static final String COLUMN_IMAGE = "image";
	private static final String CREATE_SUBMODEL_TABLE = "create table " + TABLE_SUB_MODEL + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_MODEL_ID
			+ " integer not null, " + COLUMN_HEADER + " text not null, " + COLUMN_TABHEADER + " text not null, "
			+ COLUMN_CLASSIFICATION + " text not null, " + COLUMN_DESCRIPTION + " text not null, " + COLUMN_IMAGE + " text not null, FOREIGN KEY (" + COLUMN_MODEL_ID
			+ ") REFERENCES " + TABLE_CAR_MODEL + " (" + COLUMN_ID + "));";
	private static final String CREATE_CARMODEL_TABLE = "create table " + TABLE_CAR_MODEL + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_BRAND_ID
				+ " integer not null, " + COLUMN_HEADER + " text not null, FOREIGN KEY (" + COLUMN_BRAND_ID
				+ ") REFERENCES " + TABLE_CAR_BRAND + " (" + COLUMN_ID + "));";
	private static final String CREATE_CARBRAND_TABLE = "create table " + TABLE_CAR_BRAND + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_NAME + " text not null, " + COLUMN_ICON + " text not null)";

	private static final String DATABASE_NAME = "carbrowser.db";
	private static final int DATABASE_VERSION = 1;

	public static final String[] CAR_BRAND_COLUMNS = {
			COLUMN_ID, COLUMN_NAME, COLUMN_ICON
	};
	public static final String[] CAR_MODEL_COLUMNS = {
			COLUMN_ID, COLUMN_BRAND_ID, COLUMN_HEADER
	};
	public static final String[] SUBMODEL_COLUMNS = {
		COLUMN_ID, COLUMN_MODEL_ID, COLUMN_HEADER, COLUMN_TABHEADER, COLUMN_CLASSIFICATION, COLUMN_DESCRIPTION, COLUMN_IMAGE
};


	public CarBrowserDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CARBRAND_TABLE);
		db.execSQL(CREATE_CARMODEL_TABLE);
		db.execSQL(CREATE_SUBMODEL_TABLE);

		ContentValues cvBrand = new ContentValues();
		ContentValues cvModel = new ContentValues();
		ContentValues cvSubmodel = new ContentValues();

		for (CarBrand carBrand : CarBrandsCollection.CAR_BRANDS) {
			cvBrand.clear();
			cvBrand.put(COLUMN_ID, carBrand.id);
			cvBrand.put(COLUMN_NAME, carBrand.name);
			cvBrand.put(COLUMN_ICON, carBrand.icon);
			db.insert(TABLE_CAR_BRAND, null, cvBrand);

			for (CarModel carModel : carBrand.models) {
				cvModel.clear();
				cvModel.put(COLUMN_ID, carModel.id);
				cvModel.put(COLUMN_BRAND_ID, carBrand.id);
				cvModel.put(COLUMN_HEADER, carModel.header);
				db.insert(TABLE_CAR_MODEL, null, cvModel);
				
				for(SubModel subModel : carModel.subModels) {
					cvSubmodel.clear();
					cvSubmodel.put(COLUMN_ID, subModel.id);
					cvSubmodel.put(COLUMN_MODEL_ID, carModel.id);
					cvSubmodel.put(COLUMN_HEADER, subModel.header);
					cvSubmodel.put(COLUMN_TABHEADER, subModel.tabHeader);
					cvSubmodel.put(COLUMN_CLASSIFICATION, subModel.classification);
					cvSubmodel.put(COLUMN_DESCRIPTION, subModel.description);
					cvSubmodel.put(COLUMN_IMAGE, subModel.image);
					db.insert(TABLE_SUB_MODEL, null , cvSubmodel);
				}
			}
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(CarBrowserDatabaseHelper.class.getName(), "Upgrading database from version " 
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR_MODEL);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR_BRAND);
		onCreate(db);

	}

}

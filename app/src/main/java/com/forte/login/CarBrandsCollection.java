package com.forte.login;

public class CarBrandsCollection {
	public static final String[] DESCRIPTION = {
			"Efficient dynamics", "The best or nothing", "Think different"
	};

	private static final SubModel[] ONE_SERIES = new SubModel[] {
		new SubModel(0, "1-series", "3D", "C-class hatchback", "Last model index - F20. Available two options: Sport Line and Urban Line", Integer.toString(R.drawable.bmw1s3d), 0),
		new SubModel(1, "1-series", "5D", "C-class hatchback", "Last model index - F20. Available two options: Sport Line and Urban Line", Integer.toString(R.drawable.bmw1s5d), 0)
	};
	
	private static final SubModel[] THREE_SERIES = new SubModel[] {
		new SubModel(0, "3-series", "Sedan", "D-class", "Last model index - F20. Available three options: Sport, Modern and Luxury", Integer.toString(R.drawable.bmw3ss), 1),
		new SubModel(1, "3-series", "Touring", "D-class", "Last model index - F20. Available three options: Sport, Modern and Luxury", Integer.toString(R.drawable.bmw3st), 1),
		new SubModel(2, "3-series", "GT", "D-class", "Last model index - F20. Available three options: Sport, Modern and Luxury", Integer.toString(R.drawable.bmw3sgt), 1)
	};
	
	private static final SubModel[] FIVE_SERIES = new SubModel[] {
		new SubModel(0, "5-series", "Sedan", "E-class", "Last model index - F10. Available three options: Sport, Modern and Luxury", Integer.toString(R.drawable.bmw5ss), 2),
		new SubModel(1, "5-series", "Touring", "E-class", "Last model index - F10. Available three options: Sport, Modern and Luxury", Integer.toString(R.drawable.bmw5st), 2),
		new SubModel(2, "5-series", "GT", "E-class", "Last model index - F10. Available three options: Sport, Modern and Luxury", Integer.toString(R.drawable.bmw5sgt), 2)
	};
	
	private static final SubModel[] SEVEN_SERIES = new SubModel[] {
		new SubModel(0, "7-series", "Base", "F-class", "Last model index - F01", Integer.toString(R.drawable.bmw7s), 3),
		new SubModel(1, "7-series", "Long", "F-class", "Last model index - F02", Integer.toString(R.drawable.bmw7sl), 3)
	};
	
	private static final SubModel[] X_SERIES = new SubModel[] {
		new SubModel(0, "X-series", "X1", "Compact crossover SUV", "Last model index - E84", Integer.toString(R.drawable.bmwx1), 4),
		new SubModel(1, "X-series", "X3", "Compact crossover SUV", "Last model index - F25", Integer.toString(R.drawable.bmwx3), 4),
		new SubModel(2, "X-series", "X5", "Mid-size crossover SUV", "Last model index - F15", Integer.toString(R.drawable.bmwx5), 4),
		new SubModel(3, "X-series", "X6", "SAC - Sports Activity Coupe", "Last model index - E71", Integer.toString(R.drawable.bmwx6), 4)
	};
	
	private static final SubModel[] MERCEDES = new SubModel[] {
		new SubModel(0, "A-class", "A", "C-class hatchback", "Last model index - W176", Integer.toString(R.drawable.mercedes_a), 5)
	};
	
	private static final SubModel[] AUDI = new SubModel[] {
		new SubModel(0, "A1", "A1", "B-class hatchback", "Based on the Volkswagen Group PQ25 platfrom", Integer.toString(R.drawable.audi_a1), 6)
	};
	
	private static final CarModel[] BMW_CAR_MODELS = new CarModel[] {
			new CarModel(0, "1-series", ONE_SERIES, 0),
			new CarModel(1, "3-series", THREE_SERIES, 0),
			new CarModel(2, "5-series", FIVE_SERIES, 0),
			new CarModel(3, "7-series", SEVEN_SERIES, 0),
			new CarModel(4, "X-series", X_SERIES, 0),
	};
	private static final CarModel[] MERCEDES_CAR_MODELS = new CarModel[] {
		new CarModel(5, "A-class", MERCEDES, 1)
	};
	private static final CarModel[] AUDI_CAR_MODELS = new CarModel[] {
		new CarModel(6, "A1", AUDI, 2)
	};

	public static CarBrand[] CAR_BRANDS = new CarBrand[] {
			new CarBrand(0, "BMW", Integer.toString(R.drawable.bmw_logo), BMW_CAR_MODELS),
			new CarBrand(1, "Mercedes", Integer.toString(R.drawable.mercedes), MERCEDES_CAR_MODELS),
			new CarBrand(2, "Audi", Integer.toString(R.drawable.audi2), AUDI_CAR_MODELS)
	};

}

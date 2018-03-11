package converter;

public enum Length {
	// you must write the enum members first, separated by comma
	Mile(1609.344), Kilometer(1000.0), Meter(1.0), Centimeter(0.01), Foot(0.30480), Wa(2.000); // attributes of the enum
																								// members
	private final double value;

	// enum constructor must be private
	private Length(double value) {
		this.value = value;
	}

	// methods are just like in a class
	public double getValue() {
		return this.value;
	}

}
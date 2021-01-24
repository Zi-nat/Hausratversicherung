package client;

import java.text.DecimalFormat;


public enum Products {

	COMPACT(650), OPTIMAL(700);

	private int price;

	private Products(int price) {
		this.price = price;
	}
	
	//insurance-method returns the sum of final product 
	//depends on the building-area and products that have been chosen  
	public String insurance(double area) {
        double sum = 0; // Sum initialised
		sum = area * price;
		DecimalFormat roundedSum= new DecimalFormat(".00");
		String result = roundedSum.format(sum);
		return result;

	}

}

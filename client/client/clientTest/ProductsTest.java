package client.clientTest;

import static org.junit.Assert.*;
import org.junit.Test;

import client.Products;


public class ProductsTest {

	@Test
	public void testInsurance() {
		
		String compactTestString = Products.COMPACT.insurance(38.08965);
		assertEquals(compactTestString, "24758.27");
		String optimalTestString = Products.OPTIMAL.insurance(38.08965);
		assertEquals(optimalTestString, "26662.76");
	}

}

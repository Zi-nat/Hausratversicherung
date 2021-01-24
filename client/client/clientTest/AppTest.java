package client.clientTest;

import static org.junit.Assert.*;
import org.junit.Test;

import client.App;

public class AppTest {

	@Test
	public void testIsValid() throws Exception {

		String inputString = "test";
		assertTrue("^.[A-Za-z]+", App.isValid(inputString));

	}

}

package com.example.JunitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UtilsTest {

	@Before
	public void setUp() throws Exception {
	}



	@Test
	public void testSum() {
		int[] myint = {3, 4, 5};
		int sum = Utils.sum(myint);
		assertEquals(12, sum);
	}

}

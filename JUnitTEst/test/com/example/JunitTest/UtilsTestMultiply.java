package com.example.JunitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UtilsTestMultiply {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMultiply() {
		int[] myint = {2, 3, 4};
		int num = Utils.multiply(myint);
		assertEquals(24, num);
	}

}

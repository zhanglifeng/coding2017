package com.coderising.jvm.error;

import static org.junit.Assert.*;

import org.junit.Test;

public class ErrorCaseTest {

	@Test
	public void testOutOfMemoryCase() {
		new ErrorCase().outOfMemoryCase();
	}
	
	@Test
	public void testStackOverFlowErrorCase() {
		new ErrorCase().stackOverFlowErrorCase();
	}
}

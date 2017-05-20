package com.coderising.jvm.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorCase {
	
	
	public void outOfMemoryCase() {
		List list = new ArrayList();
		while (true) {
			list.add(new ErrorCase());
		}
	}
	
	public void stackOverFlowErrorCase() {
		stackOverFlowErrorCase();
	}
	
}

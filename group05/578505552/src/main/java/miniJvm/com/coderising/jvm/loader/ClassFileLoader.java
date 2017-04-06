package miniJvm.com.coderising.jvm.loader;

import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		
		return null;	
		
		
	}

	public void addClassPath(String path) {

		if (path == null){
			return;
		}
		clzPaths.add(path);
	}

	public String getClassPath(){

		StringBuffer stringBuffer = new StringBuffer();
		for (String path : clzPaths){
			stringBuffer.append(path).append(";");
		}
		return stringBuffer.substring(0, stringBuffer.length() - 1);
	}

	

	

}

package bz.sunlight;

import org.junit.Test;

import bz.sunlight.util.PropertiesUtil;

public class PropertyTest {

	@Test
	public void get(){
		PropertiesUtil instanse = PropertiesUtil.getInstanse();
		String str = instanse.getString("Allow.Origin");
		System.out.println(str);
	}
}

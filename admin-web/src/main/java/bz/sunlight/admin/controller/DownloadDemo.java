package bz.sunlight.admin.controller;

import bz.sunlight.util.HttpUtil;

public class DownloadDemo {

	private final static String requestUrl="http://localhost:8080/web/download/data.csv";
	private final static String desPath="D:/temp";
	public static void main(String[] args) {
		
			HttpUtil.downloadFromServer(requestUrl, desPath);
		
	}
}

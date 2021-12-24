package com.tvm.vip.utils;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

public class AWSTimestreamUtils {

	//public  static final AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIAYIZ4FQMI7IQUVSUH", "FeapP71f+SlVvlzyq43pWu6J4eUVEV9Tx9BEpWsz");
		public static final AwsBasicCredentials credentials = AwsBasicCredentials.create("AKIAZGY2J73J3ZHKCY2T", "61BYJk+KgF1qAWGFy2LY6FH3GW0X1rJxdHgbllww"); 
		public  static final StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
		  
		  public static final String DATABASE_NAME = "SalesAndStatus";
		  public static final String STATUS_TABLE = "status";
		  public static final String MODULE_TABLE = "module";
		  
		  public static final String SALES_TABLE = "sales";
}

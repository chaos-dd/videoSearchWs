package casia.isiteam.videosearch.master;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	
	String host=null;
	int servicePort;
	int fileTransferPort;
	String tempFileDir=null;
		
	
	private Properties properties=new Properties();
	public Configuration(String configFilePath) throws IOException {
		
		InputStream in = new FileInputStream(configFilePath);
		properties.load(in);


		host = properties.getProperty("host");
		if (host == null || host.length() == 0) {
			host = "0.0.0.0";
			System.err.println("default localhost is \"0.0.0.0\"");
		}

		
		try {
			servicePort = Integer.parseInt(properties
					.getProperty("servicePort"));
		} catch (NumberFormatException e) {
			throw new IOException("servicePort can not be null");
		}

		
		try {
			fileTransferPort = Integer.parseInt(properties
					.getProperty("fileTransferPort"));
		} catch (NumberFormatException e) {
			throw new IOException("fileTransferPort can not be null");
		}
		
		
		tempFileDir = properties.getProperty("tempFileDir");
		if (tempFileDir == null || tempFileDir.length() == 0) {
			throw new IOException("tempFileDir can not be null!");
		}
		File file=new File(tempFileDir);
		if (file.exists() == false) {
			System.err.println("temporary file directory doesn't exists and mkdir called");
			file.mkdirs();
		}
	}
}

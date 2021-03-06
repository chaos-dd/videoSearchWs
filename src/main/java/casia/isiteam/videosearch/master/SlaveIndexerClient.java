package casia.isiteam.videosearch.master;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import casia.isiteam.videosearch.master.SlaveRegisterService;
import casia.isiteam.videosearch.slave.SlaveIndexerService;
import casia.isiteam.videosearch.util.filetransfer.FileSender;

public class SlaveIndexerClient {
	String groupName;
	String host;
	int servicePort;
	int fileTransferPort;

	SlaveIndexerService slaveIndexerService;

	public SlaveIndexerClient(String groupName, String host, int servicePort,
			int fileTransferPort) throws MalformedURLException {

		URL url = new URL("http", host, servicePort, "/"
				+ SlaveRegisterService.class.getSimpleName());

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		// factory.
		factory.setServiceClass(SlaveIndexerService.class);
		factory.setAddress(url.toString());
		this.slaveIndexerService = (SlaveIndexerService) factory.create();

		this.groupName = groupName;
		this.host = host;
		this.fileTransferPort = fileTransferPort;
	}

	public int upLoadFile(String fileName) throws Exception {

		File file = new File(fileName);
		FileSender.sendFile(file, host, fileTransferPort);
		return 0;
	}

	public String getGroupName() {
		return groupName;
	}

	public int addVideo(String fileName) {
		return slaveIndexerService.addVideo(fileName);

	}

	public int deleteVideo(String fileName) {
		return slaveIndexerService.deleteVideo(fileName);
	}

	public String searchVideo(String fileName) throws Exception {

		upLoadFile(fileName);
		return slaveIndexerService.searchVideo(fileName);
	}
}

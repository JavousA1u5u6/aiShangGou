package com.taotao.fastdfs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.aishanggou.utils.FastDFSClient;

public class FastDFSTest {
	
	@Test
	public void testUpload() throws Exception {
		//1.创建一个配置文件fast_dfs.conf， 配置文件的内容就是指定TrackerServer的地址
		//2.加载配置文件
		ClientGlobal.init("F:/javacode/maven_code/taotao-manager-web/src/main/resources/resource/fast_dfs.conf");
		//3.创建一个TrackerClient对象。
		TrackerClient trackerClient = new TrackerClient();
		//4.通过TrackerClient对象获得TrackerServer对象。
		TrackerServer trackerServer = trackerClient.getConnection();
		//5.创建一个StorageServer的引用。null就可以。
		StorageServer storageServer = null;
		//6.创建一个StorageClient对象。两个参数TrackerServer、StorageServer
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//7.使用StorageClient对象上传文件。
		//参数1：文件名 参数2：文件扩展名，不包含“.” 参数3：文件的元数据
		String[] strings = storageClient.upload_file("C:\\Users\\JavousAiMe\\Desktop\\like.jpg", "jpg", null);
		//storageClient.upload
		for (String string : strings) {
			System.out.println(string);
			/**
			 * group1
			 *  M00/00/01/wKiVgVpDa8mAAPReAATRJlfUius936.jpg
			 */
		}
	}
	
	@Test
	public void testDeleteforFastDfs() throws FileNotFoundException, IOException, MyException{
		ClientGlobal.init("F:/javacode/maven_code/taotao-manager-web/src/main/resources/resource/fast_dfs.conf");
		//3.创建一个TrackerClient对象。
		TrackerClient trackerClient = new TrackerClient();
		//4.通过TrackerClient对象获得TrackerServer对象。
		TrackerServer trackerServer = trackerClient.getConnection();
		//5.创建一个StorageServer的引用。null就可以。
		StorageServer storageServer = null;
		//6.创建一个StorageClient对象。两个参数TrackerServer、StorageServer
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//7.使用StorageClient对象上传文件。
		//参数1：组名 参数2：文件在storage服务器上具体存储的文件路径名   
		//进行删除操作 返货的若是 0  代表删除成功 若是返回2 删除失败
		 int status = storageClient.delete_file("group1", "M00/00/01/wKiVglpEpeaAOyN2AATRJlfUius960.jpg");
		System.out.println(status);
	}
	
	/**
	 * 由于重复代码太多 所以决定进行抽取
	 * 1.可以发现每此操作都是基于storageClient 进行操作的
	 * 2.可以将创建storageClient的代码提取出来    
	 * 3.由于需要修改的就只是tacker server 服务器的ip 端口号 所以可以将此 配置提取出来 用的时候注入就行 方便修改
	 * @throws Exception
	 */
	@Test
	public void testFastDfsClient() throws Exception {
		FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/fast_dfs.conf");
		String string = fastDFSClient.uploadFile("C:\\Users\\JavousAiMe\\Desktop\\like.jpg");
		System.out.println(string);
	}
}

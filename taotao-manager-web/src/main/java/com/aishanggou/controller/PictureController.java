package com.aishanggou.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.impl.Log4JLogger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aishanggou.utils.FastDFSClient;
import com.aishanggou.utils.JsonUtils;

@Controller
public class PictureController {
	
	//简单类型注入
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	@RequestMapping(value = "/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	/**
	 * 需要返回一个map 或者对象 中  erro url
	 * 拿到这个url  进行回显
	 * 失败 需要返回错误消息
	 * 
	 * 因为浏览器的兼容性问题 所以需要处理乱码
	 * 设置响应类型
	 */
	public String uploadPic(MultipartFile uploadFile){
		//获得是当前包的路径
		System.out.println(this.getClass().getResource(""));
		//获得的是classpath下的路径
		System.out.println(this.getClass().getResource("/"));
		
		//接收页面提交的参数
		try {
			byte[] content = uploadFile.getBytes();
			String originalFilename = uploadFile.getOriginalFilename();
			String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			
			//把内容上传到storage图片服务器
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:/resource/fast_dfs.conf");
			String url = fastDFSClient.uploadFile(content);
			//封装数据  进行返回
			Map result = new HashMap<>();
			result.put("error", 0);
			result.put("url", IMAGE_SERVER_URL+url);
			String jsonStr = JsonUtils.objectToJson(result);
			return jsonStr;
		} catch (IOException e) {
			e.printStackTrace();
			//如果出现异常 就应该将错误信息返回
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			String jsonStr = JsonUtils.objectToJson(result);
			return jsonStr;
		}
		//去除文件信息
 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//传会的指示一个图片地址  还需要拼接上 服务器的地址 端口号
		
		return null;
	}

}

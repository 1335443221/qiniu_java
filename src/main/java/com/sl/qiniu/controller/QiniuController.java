package com.sl.qiniu.controller;

import java.io.File;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sl.qiniu.service.QiniuDataService;
import com.sl.qiniu.utils.QiniuUpload;

@Controller
@RequestMapping("/qiniu")
public class QiniuController {
	
	@Autowired
	QiniuDataService qiniuDataService;

	
	
	/**
	 * 上传文件
	 * @param stg
	 * @return
	 */
	@RequestMapping("/uploadingQiniu")
	@ResponseBody
	public Object  uploading(@RequestParam Map<String, Object> map,MultipartFile  file){
		return qiniuDataService.uploadingQiniu(map,file);
	}
	
	
	
	/**
	 * 获取覆盖凭证
	 * @param stg
	 * @return
	 */
	@RequestMapping("/getCoverUpToken")
	@ResponseBody
	public String  getCoverUpToken(String key){
		return QiniuUpload.getCoverUpToken(key);
	}
	
	
	
	/**
	 * 获取简单上传凭证
	 * @param stg
	 * @return
	 */
	@RequestMapping("/getUpToken")
	@ResponseBody
	public String  getUpToken(@RequestParam Map<String, Object> map){
		
		return QiniuUpload.getUpToken();
	}
	
	/**
	 * 版本管理页面
	 * @param stg
	 * @return
	 */
	@RequestMapping("/index")
	public String  index(Model model){
		return "priceappPag/index";
	}
	
	
	/**
	 * 去增加页面
	 * @param stg
	 * @return
	 */
	@RequestMapping("/goAddVersion")
	public String  goAddVersion(){
		return "priceappPag/versionPag/addVersion";
	}
	
}
	

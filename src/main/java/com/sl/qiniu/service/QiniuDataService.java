package com.sl.qiniu.service;

import java.io.File;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface QiniuDataService {
	
	//上传版本
	public Object uploadingQiniu(Map<String, Object> map,MultipartFile  file);
	
}

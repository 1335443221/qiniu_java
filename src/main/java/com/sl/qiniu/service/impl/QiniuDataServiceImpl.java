package com.sl.qiniu.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sl.qiniu.config.AccountMgr;
import com.sl.qiniu.service.QiniuDataService;
import com.sl.qiniu.utils.FileUtils;
import com.sl.qiniu.utils.QiniuUpload;
import com.sl.qiniu.utils.Result;


@Service("versionDataImpl")
public class QiniuDataServiceImpl implements QiniuDataService {
	


	@Override
	public Object uploadingQiniu(Map<String, Object> map,MultipartFile  file) {
		if(file==null){
			return 0;
		}else{
			String filename=file.getOriginalFilename();
			File toFile =null;
			InputStream ins = null;
            try {
				ins = file.getInputStream();
				toFile = new File(file.getOriginalFilename());   //生成文件  在当前目录下
		        FileUtils.inputStreamToFile(ins, toFile);
		        ins.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new QiniuUpload().upload(toFile, filename, AccountMgr.BucketName);
			map.put("look_url","pp2gd2arl.bkt.clouddn.com/"+filename);
			map.put("download_url","pp2gd2arl.bkt.clouddn.com/"+filename+"?attname=");
			/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String mon = sdf.format(new Date());
			map.put("upload_at", mon);*/
			return Result.success(map);
		}
	}
	
	
	

	


}

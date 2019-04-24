package com.sl.qiniu.utils;

import java.io.File;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.sl.qiniu.config.AccountMgr;
 
/**
 * 七牛 java sdk 简单上传，覆盖上传，文件上传
 * @author yunlingfly
 */
public class QiniuUpload {
 
    Auth auth = Auth.create(AccountMgr.ACCESS_KEY, AccountMgr.SECRET_KEY);
    //构造一个带指定Zone对象的配置类
    Configuration cfg = new Configuration(Zone.zone1());
    UploadManager uploadManager = new UploadManager(cfg);
 
    /**
     * 获取凭证
     * @param bucketName 空间名称
     * @param key 如果需要覆盖上传则设置此参数
     * @return
     */
    public String getUpToken(String bucketName,String key) {
        return auth.uploadToken(bucketName);
    }
 
    /**
     * 上传方法1
     * @param filePath 文件路径  （也可以是字节数组、或者File对象）
     * @param key 上传到七牛上的文件的名称  （同一个空间下，名称【key】是唯一的）
     * @param bucketName 空间名称  （这里是为了获取上传凭证）
     */
    public void upload(String filePath, String key, String bucketName) {
        try {
            // 调用put方法上传
            Response res = uploadManager.put(filePath, key, getUpToken(bucketName,key));
            // 打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException qe) {
                // ignore
            }
        }
    }
    
    /**
     * 上传方法2
     * @param file 文件
     * @param key 上传到七牛上的文件的名称  （同一个空间下，名称【key】是唯一的）
     * @param bucketName 空间名称  （这里是为了获取上传凭证）
     */
    public void upload(File file, String key, String bucketName) {
        try {
            // 调用put方法上传
            Response res = uploadManager.put(file, key, getUpToken(bucketName,key));
            // 打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException qe) {
                // ignore
            }
        }
    }
 
    /**
     * 上传方法3 覆盖上传
     * @param path
     *            上传文件路径
     * @param bucketName
     *            空间名
     * @param key
     *            文件名
     */
    public void overrideUpload(String path, String key, String bucketName) {
        try {
            String token = getUpToken(bucketName, key);//获取 token
            Response response = uploadManager.put(path, key, token);//执行上传，通过token来识别 该上传是“覆盖上传”
            System.out.println(response.toString());
        } catch (QiniuException e) {
            System.out.println(e.response.statusCode);
            e.printStackTrace();
        }
    }
    
   
    /**
     * 简单上传的凭证token
     * @return
     */
    public static String  getUpToken(){
    	String accessKey = AccountMgr.ACCESS_KEY;
        String secretKey = AccountMgr.SECRET_KEY;
        String bucket = "ovelec_app";
        
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
	    return upToken;
    }
    
    
    
    /**
     * 覆盖上传的凭证token 传入文件名字
     * @return
     */
    public static String  getCoverUpToken(String key){
    	String accessKey = AccountMgr.ACCESS_KEY;
        String secretKey = AccountMgr.SECRET_KEY;
        String bucket = "ovelec_app";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, key);
	    return upToken;
    }
    
    
    
    
    
    
    /**
     * 主函数：程序入口，测试功能
     * @param args
     */
    	public static void main(String[] args) {
            String accessKey = AccountMgr.ACCESS_KEY;
            String secretKey = AccountMgr.SECRET_KEY;
            String bucket = "ovelec_app";
            
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		System.out.println(upToken);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
		/*   //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone1());
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            //...生成上传凭证，然后准备上传
            String accessKey = AccountMgr.ACCESS_KEY;
            String secretKey = AccountMgr.SECRET_KEY;
            String bucket = "ovelec_app";
            //如果是Linux情况下，格式是 /home/qiniu/test.png
            String localFilePath = "D:\\ovepmp.apk";
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            String key = "ovepmp";
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(localFilePath, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        
    		
    		// 上传文件的路径，因为在Mac下，所以路径和windows下不同
            String filePath = "D:\\123.txt";
            // 要上传的空间
            String bucketName = "ovelec_app";
            // 上传到七牛后保存的文件名
            String key = "sometext.txt";
            // 这里的filepath可以直接替换成File如下注释所示
            // File file=new File(filePath);
            // new SimpleUpload().upload(file, key, bucketName);
            new SimpleUpload().upload(filePath, key, bucketName);
            */
    	}
}

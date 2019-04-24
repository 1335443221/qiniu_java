package com.sl.qiniu.utils;

public class CodeMsg {
	private int code;
	private String msg;
	// 按照模块定义CodeMsg
	// 通用异常
	public static CodeMsg SUCCESS = new CodeMsg(1000,"OK");
	public static CodeMsg TOKEN_FAILS = new CodeMsg(1001,"token认证失败");
	public static CodeMsg  AUTH_EXPIRES= new CodeMsg(1002,"授权过期");
	public static CodeMsg  MISSING_PARAMETER= new CodeMsg(1003,"缺失参数");
	// 业务异常
	public static CodeMsg PW_INCORRECT = new CodeMsg(1004,"用户名/密码错误"); 
	public static CodeMsg NAME_ALREADY_EXISTS= new CodeMsg(1005,"手机号已注册");
	public static CodeMsg PWD_FALSE= new CodeMsg(1006,"原密码不正确");
	public static CodeMsg MISSING_PATH = new CodeMsg(1007,"地址路径错误");
	public static CodeMsg SERVER_ERROR = new CodeMsg(1008,"内部服务器错误");
	
	public static CodeMsg MISS_APPVERSION  = new CodeMsg(1010,"没有app版本");
	
	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}

package personnel.util.common;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ShiroMD5Privacy {
	
	//对明文密码进行加密
	//加密1024次
	//超级管理员
	public static String privacy(String loginname,String password) {

		  String  algorithmName = "MD5";
		  Object  source = password;
		  Object  salt = ByteSource.Util.bytes(loginname);
		  int     hashIterations = 1024; 
		  Object  privacy = new SimpleHash(algorithmName, source,salt,hashIterations);
		  String  privacypassword = privacy.toString();
		  return privacypassword;
	}

	public static void main(String[] args) {
		String a = privacy("admin","a123456");
		System.out.println(a);
	}

}

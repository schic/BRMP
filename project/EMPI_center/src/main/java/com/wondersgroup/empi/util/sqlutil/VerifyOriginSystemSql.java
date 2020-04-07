package com.wondersgroup.empi.util.sqlutil;

public class VerifyOriginSystemSql {

	public static String verify_system = "SELECT 'pass' as msg,username,origin_system_id FROM brmp_conf_origin_system_info where username = :username and password = :password ";
	
	public static String verify_apply_user = "SELECT 'pass' as msg,user_name as username,apply_id as origin_system_id FROM brmp_apply_base where user_name = :username and apply_id = :password";
	
	

}

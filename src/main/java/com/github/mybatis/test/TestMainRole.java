package com.github.mybatis.test;

import com.github.mybatis.entity.BasisInfo;
import com.github.mybatis.util.EntityInfoUtil;
import com.github.mybatis.util.Generator;
import com.github.mybatis.util.MySqlToJavaUtil;

import java.sql.SQLException;
import java.util.Date;

public class TestMainRole {
	//基础信息
	public static final String PROJECT="manager-show";
	public static final String AUTHOR="czs";
	public static final String VERSION="V1.0";
	//数据库连接信息
	public static final String URL="jdbc:mysql://127.0.0.1:3306/wl_equity?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false&useSSL=true&serverTimezone=GMT%2B8";
	public static final String NAME="root";
	public static final String PASSWORD="123456";
	public static final String DATABASE="wl_equity";
	//类信息
	public static final String TABLE="sys_role";
	public static final String CLASSNAME="Role";
	public static final String CLASSCOMMENT="管理用户";
	public static final String TIME="2019年4月3日";
	public static final String AGILE=new Date().getTime()+"";
	//路径信息
	public static final String ENTITY_URL="com.wanglai.equity_platform.manager.bean";
	public static final String DAO_URL="com.wanglai.equity_platform.manager.mapper";
	public static final String XML_URL="com.wanglai.equity_platform.manager.mapper.impl";
	public static final String SERVICE_URL="com.wanglai.equity_platform.manager.service";
	public static final String SERVICE_IMPL_URL="com.wanglai.equity_platform.manager.service.impl";
	public static final String CONTROLLER_URL="com.wanglai.equity_platform.manager.controller";
	
	
	public static void main(String[] args) {
		BasisInfo bi=new BasisInfo(PROJECT, AUTHOR, VERSION, URL, NAME, PASSWORD, DATABASE, TIME, AGILE, ENTITY_URL, DAO_URL, XML_URL, SERVICE_URL, SERVICE_IMPL_URL, CONTROLLER_URL);
		bi.setTable(TABLE);
		bi.setEntityName(MySqlToJavaUtil.getClassName(TABLE));
		bi.setObjectName(MySqlToJavaUtil.changeToJavaFiled(TABLE));
		bi.setEntityComment(CLASSCOMMENT);
		try {
			bi=EntityInfoUtil.getInfo(bi);
			String aa1=Generator.createEntity("E:\\Project\\manager\\manager\\manager\\src\\main\\java\\", bi).toString();
			String aa2=Generator.createDao("E:\\Project\\manager\\manager\\manager\\src\\main\\java\\", bi).toString();
			String aa3=Generator.createDaoImpl("E:\\Project\\manager\\manager\\manager\\src\\main\\java\\", bi).toString();
			String aa4=Generator.createService("E:\\Project\\manager\\manager\\manager\\src\\main\\java\\", bi).toString();
			String aa5=Generator.createServiceImpl("E:\\Project\\manager\\manager\\manager\\src\\main\\java\\", bi).toString();
			String aa6=Generator.createController("E:\\Project\\manager\\manager\\manager\\src\\main\\java\\", bi).toString();
			
			System.out.println(aa1);
			System.out.println(aa2);
			System.out.println(aa3);
			System.out.println(aa4);
			System.out.println(aa5);
			System.out.println(aa6);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

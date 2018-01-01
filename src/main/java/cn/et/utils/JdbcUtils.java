package cn.et.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JdbcUtils {
	/**
	 * 获取配置文件中的信息
	 */
	private static Properties pro = new Properties();
	static{
		//读取配置文件的流
		InputStream in = JdbcUtils.class.getResourceAsStream("jdbc.properties");
		try {
			//加载
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取一个连接
	 * @return
	 */
	public static Connection getConnection(){
		//获取驱动类全类名
		String driver = pro.getProperty("driver");
		//获取url
		String url = pro.getProperty("url");
		//获取用户名
		String user = pro.getProperty("user");
		//获取用户密码
		String password = pro.getProperty("password");
	
		Connection conn = null;
		try {
			//加载驱动类
			Class.forName(driver);
			//创建连接
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	/**
	 * 传入一个查询的sql语句返回查询的结果集
	 * @param sql  要查询的sql语句
	 * @return
	 */
	public static List<Map<String,String>> queryData(String sql){
		//创建一个存储表数据的list集合内部以Map的键值对的方式存对应的值
		List<Map<String,String>> data = new ArrayList<Map<String,String>> ();
		//获取连接
		Connection conn = getConnection();
		//创建用于执行sql语句的PreparedStatement
		PreparedStatement ps;
		try {
			//将需要执行的sql语句传入
			ps = conn.prepareStatement(sql);
			//创建获取的结果集
			ResultSet rs = ps.executeQuery();
			//创建获取列信息的ResxultSetmetadata对象
			ResultSetMetaData rsm = ps.getMetaData();
			//遍历获取的结果集
			while(rs.next()){
				//存放取出的值
				Map<String,String> map = new HashMap<String,String>();
				//通过获取的ResultSetMetaData列名获取对应的值
				for(int i = 1 ; i<=rsm.getColumnCount();i++){
					//获取列名
					String name = rsm.getColumnName(i);
					//获取对应的值
					String value = rs.getString(i);
					map.put(name, value);
				}
				data.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	
	}
	/**
	 * 执行某个非查询的sql语句返回受影响的行数
	 * @param sql  需要执行的sql语句
	 * @return
	 */
	public static int execute(String sql){
		//临时变量获取执行SQL语句所影响的行数
		int count = 0;
		//获取连接
		Connection conn = getConnection();
		//创建用于执行sql语句的PreparedStatement
		PreparedStatement ps;
		try {
			//将需要执行的sql语句传入
			ps = conn.prepareStatement(sql);
			//获取受影响的行数
			count = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
			
	}
}

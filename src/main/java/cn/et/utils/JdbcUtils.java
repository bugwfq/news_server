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
	 * ��ȡ�����ļ��е���Ϣ
	 */
	private static Properties pro = new Properties();
	static{
		//��ȡ�����ļ�����
		InputStream in = JdbcUtils.class.getResourceAsStream("jdbc.properties");
		try {
			//����
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡһ������
	 * @return
	 */
	public static Connection getConnection(){
		//��ȡ������ȫ����
		String driver = pro.getProperty("driver");
		//��ȡurl
		String url = pro.getProperty("url");
		//��ȡ�û���
		String user = pro.getProperty("user");
		//��ȡ�û�����
		String password = pro.getProperty("password");
	
		Connection conn = null;
		try {
			//����������
			Class.forName(driver);
			//��������
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	/**
	 * ����һ����ѯ��sql��䷵�ز�ѯ�Ľ����
	 * @param sql  Ҫ��ѯ��sql���
	 * @return
	 */
	public static List<Map<String,String>> queryData(String sql){
		//����һ���洢�����ݵ�list�����ڲ���Map�ļ�ֵ�Եķ�ʽ���Ӧ��ֵ
		List<Map<String,String>> data = new ArrayList<Map<String,String>> ();
		//��ȡ����
		Connection conn = getConnection();
		//��������ִ��sql����PreparedStatement
		PreparedStatement ps;
		try {
			//����Ҫִ�е�sql��䴫��
			ps = conn.prepareStatement(sql);
			//������ȡ�Ľ����
			ResultSet rs = ps.executeQuery();
			//������ȡ����Ϣ��ResxultSetmetadata����
			ResultSetMetaData rsm = ps.getMetaData();
			//������ȡ�Ľ����
			while(rs.next()){
				//���ȡ����ֵ
				Map<String,String> map = new HashMap<String,String>();
				//ͨ����ȡ��ResultSetMetaData������ȡ��Ӧ��ֵ
				for(int i = 1 ; i<=rsm.getColumnCount();i++){
					//��ȡ����
					String name = rsm.getColumnName(i);
					//��ȡ��Ӧ��ֵ
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
	 * ִ��ĳ���ǲ�ѯ��sql��䷵����Ӱ�������
	 * @param sql  ��Ҫִ�е�sql���
	 * @return
	 */
	public static int execute(String sql){
		//��ʱ������ȡִ��SQL�����Ӱ�������
		int count = 0;
		//��ȡ����
		Connection conn = getConnection();
		//��������ִ��sql����PreparedStatement
		PreparedStatement ps;
		try {
			//����Ҫִ�е�sql��䴫��
			ps = conn.prepareStatement(sql);
			//��ȡ��Ӱ�������
			count = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
			
	}
}

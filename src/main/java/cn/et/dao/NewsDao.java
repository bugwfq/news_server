package cn.et.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.et.utils.JdbcUtils;

public class NewsDao {
	/**
	 * �������ֵ�������ݿ�
	 * @param title ����
	 * @param content ����
	 * @param htmlPath html�ļ����ڵ�·��
	 * @param writerDate д������
	 * @return
	 */
	public int insertNews(String title,String content,String htmlPath){
		//���ݴ����ֵƴ���ַ���
		String sql = "insert into news(title,content,htmlPath,writerDate) values('"+title+"','"+content+"','"+htmlPath+"',now())";
		//ִ��sql��䲢�ҷ���ִ������
		int count = JdbcUtils.execute(sql);
		return count;
	}
	/**
	 * 
	 * @param lineNum
	 * @return
	 */
	public List<Map<String,String>> getNewsList(String lineNum){
		//����ƴ�ӻ�ȡ���ʮ�����ݵ� limit ���
		String temp = "";
		//�ж�����Ҫ��ȡ���linenum������ 
		if(lineNum!=null){
			
			temp = "LIMIT "+lineNum;
		}
		//���lineNum Ϊ������ʾ��������
		String sql = "SELECT * FROM  news GROUP BY id nid DESC "+temp;
		//��ȡ��Ӧ������
		List<Map<String,String>> list = JdbcUtils.queryData(sql);
		return list;
	}
}

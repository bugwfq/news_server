package cn.et.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.et.utils.JdbcUtils;

public class NewsDao {
	/**
	 * 将传入的值存入数据库
	 * @param title 标题
	 * @param content 内容
	 * @param htmlPath html文件所在的路径
	 * @param writerDate 写作日期
	 * @return
	 */
	public int insertNews(String title,String content,String htmlPath){
		//根据传入的值拼接字符串
		String sql = "insert into news(title,content,htmlPath,writerDate) values('"+title+"','"+content+"','"+htmlPath+"',now())";
		//执行sql语句并且返回执行行数
		int count = JdbcUtils.execute(sql);
		return count;
	}
	/**
	 * 
	 * @param lineNum
	 * @return
	 */
	public List<Map<String,String>> getNewsList(String lineNum){
		//用来拼接获取最后十条数据的 limit 语句
		String temp = "";
		//判断是需要获取最后linenum条数据 
		if(lineNum!=null){
			
			temp = "LIMIT "+lineNum;
		}
		//如果lineNum 为空则显示所有数据
		String sql = "SELECT * FROM  news GROUP BY id nid DESC "+temp;
		//获取对应的数据
		List<Map<String,String>> list = JdbcUtils.queryData(sql);
		return list;
	}
}

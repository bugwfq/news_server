package cn.et.manager;

import java.util.List;
import java.util.Map;

import cn.et.dao.NewsDao;

public class NewsManager {
	//获取dao对象
	private NewsDao dao = new NewsDao();
	/**
	 * 将插入的数据进行处理判断并插入数据库
	 * @param title   文章标题
	 * @param content 文章内容
	 * @param htmlPath html路径
	 * @return
	 */
	public boolean insertNews(String title,String content,String htmlPath){
		if(title == null){
			if(content== null){
				return false;
			}
			title = content.substring(0,200);
		}
		
		if(htmlPath == null){
			return false;
		}
		dao.insertNews(title, content, htmlPath);
		return true;
	}
	public List<Map<String,String>> getNews(String lineNum){
		return dao.getNewsList(lineNum);
	}
}

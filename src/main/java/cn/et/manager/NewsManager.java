package cn.et.manager;

import java.util.List;
import java.util.Map;

import cn.et.dao.NewsDao;

public class NewsManager {
	//��ȡdao����
	private NewsDao dao = new NewsDao();
	/**
	 * ����������ݽ��д����жϲ��������ݿ�
	 * @param title   ���±���
	 * @param content ��������
	 * @param htmlPath html·��
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

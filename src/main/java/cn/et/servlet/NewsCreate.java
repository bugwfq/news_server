package cn.et.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.manager.NewsManager;
import cn.et.utils.FreeMarkerUtils;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class NewsCreate
 */
public class NewsCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public NewsCreate() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private NewsManager manager = new NewsManager();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String rootPath ="E:/newsHtml";
		Map<String,Object> root = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
		long time = System.currentTimeMillis();
		root.put("title", title);
		root.put("content", content);
		root.put("date", sdf.format(time));
		String fileName = "/"+time+".html";
		String newFielPath = rootPath+fileName;
		String ftlPath = "src/main/resources/templates";
		String ftlName = "news.ftl";
		
		try {
			FreeMarkerUtils.writerFile(root, ftlPath, ftlName, newFielPath);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		manager.insertNews(title, content, fileName);
		response.setHeader("refresh","3;url='"+request.getContextPath()+"'");
	    response.getWriter().write("发表成功，页面将在3秒内跳转，如不跳转请点击<a href='"+request.getContextPath()+"'>返回</a>");
	}

}

package cn.et.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerUtils {
	/**
	 * 根据传入的信息和模板的内容，将完成的文件写入到指定目录
	 * @param root     				页面动态的值的集合    key - value   String - Object
	 * @param ftlPath   			模板的目录位置
	 * @param ftlName    			模板的名称
	 * @param newFilePath			新文件创建位置及文件名
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void writerFile(Map<String,Object> root,String ftlPath,String ftlName,String newFilePath) throws IOException, TemplateException{
		//创建您的配置实例
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		//指定模板所在的目录
		cfg.setDirectoryForTemplateLoading(new File(ftlPath));
		//设置生成模板的字符集
		cfg.setDefaultEncoding("UTF-8");
		//在web页面  开发  TemplateExceptionHandler发展。HTML调试处理程序更好。
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		//不要在FreeMarker内部记录异常，它会向你抛出:
		cfg.setLogTemplateExceptions(false);
		//指定模板名称
		Template temp = cfg.getTemplate(ftlName);
		File outFile = new File(newFilePath);
		
		//创建文件输出流
		FileOutputStream fos = new FileOutputStream(outFile);
		//创建输出的转换流
		Writer out = new OutputStreamWriter(fos,"UTF-8");
		temp.process(root, out);
		
		out.flush();
		out.close();
		fos.flush();
		fos.close();
		
		
	}

}

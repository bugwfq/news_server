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
	 * ���ݴ������Ϣ��ģ������ݣ�����ɵ��ļ�д�뵽ָ��Ŀ¼
	 * @param root     				ҳ�涯̬��ֵ�ļ���    key - value   String - Object
	 * @param ftlPath   			ģ���Ŀ¼λ��
	 * @param ftlName    			ģ�������
	 * @param newFilePath			���ļ�����λ�ü��ļ���
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void writerFile(Map<String,Object> root,String ftlPath,String ftlName,String newFilePath) throws IOException, TemplateException{
		//������������ʵ��
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		//ָ��ģ�����ڵ�Ŀ¼
		cfg.setDirectoryForTemplateLoading(new File(ftlPath));
		//��������ģ����ַ���
		cfg.setDefaultEncoding("UTF-8");
		//��webҳ��  ����  TemplateExceptionHandler��չ��HTML���Դ��������á�
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		//��Ҫ��FreeMarker�ڲ���¼�쳣�����������׳�:
		cfg.setLogTemplateExceptions(false);
		//ָ��ģ������
		Template temp = cfg.getTemplate(ftlName);
		File outFile = new File(newFilePath);
		
		//�����ļ������
		FileOutputStream fos = new FileOutputStream(outFile);
		//���������ת����
		Writer out = new OutputStreamWriter(fos,"UTF-8");
		temp.process(root, out);
		
		out.flush();
		out.close();
		fos.flush();
		fos.close();
		
		
	}

}

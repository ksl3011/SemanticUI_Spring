package com.edu.cmn;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.edu.vo.FileVO;

@Component
public class DownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		setResponseContentType(request, response);
		FileVO vo = (FileVO) model.get("vo");

		response.setContentLength((int)vo.getSize());
		this.setDownloadFileName(vo.getrName(), request, response);
		this.downloadFile(vo, request, response);
	}
	
	private void setDownloadFileName(String fileName
			, HttpServletRequest request
			, HttpServletResponse response) throws UnsupportedEncodingException{
		
		String userAgent = request.getHeader("User-Agent");//브라우저 정보
		
		boolean idIe = (userAgent.indexOf("MSIE") !=-1);
		
		if(idIe == true) {
			fileName = URLEncoder.encode(fileName,"utf-8");
		}else {
			String docName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			fileName = new String(docName.getBytes("UTF-8"));
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"; ");
		response.setHeader("Context-Transfer-Encoding", "binary");
	}

	private void downloadFile(FileVO vo, HttpServletRequest request
			, HttpServletResponse response) throws Exception{
		String path = Common.downloadDir();
		File f = new File(path + File.separator + vo.getrName());
		FileInputStream in = new FileInputStream(f);
		OutputStream out = response.getOutputStream();
		try {
			FileCopyUtils.copy(in, out);
			out.flush();
		}catch(Exception e) {
			throw e;
		}finally {
			try {if(null!=in)in.close();}catch(IOException e) {throw e;}
			try {if(null!=out)out.close();}catch(IOException e) {throw e;}			
		}
	}
	
}

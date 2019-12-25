package com.edu.cmn;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

public class Common {
	
	public static String pagination(int listSize, int oneIdxView, int nowPage, String scriptNm) {
		/*
			<div class="ui right floated pagination menu">
        		<a class="icon item">
		          <i class="left chevron icon"></i>
		        </a>
		        <a class="item">1</a>
		        <a class="icon item">
		          <i class="right chevron icon"></i>
		        </a>
      		</div>
		*/
		//총게시글수
		int totalIdxNum = listSize;
		//총 페이지블록수 [1] [2] ... [99] ...
		int totalBlock = ((totalIdxNum-1)/oneIdxView)+1;
		//마지막 페이지구역
		int endDistrict = ((totalBlock-1)/10)+1;
		//현재 페이지구역 1~10 11~20...
		int nowDistrict = ((nowPage-1)/10)+1;
		//현재 페이지구역의 첫 페이지블록숫자 11~20 -> 11
		int nowBlockFirst = ((nowPage-1)/10)*10+1;
		String script = "javascript:" + scriptNm;
		
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"ui right floated pagination menu\">");
		if(nowDistrict!=1) {//<
			sb.append("<a class=\"icon item\" onclick=\"" + script+ "(-11);\"><i class=\"angle double left icon\"></i></a>");
			sb.append("<a class=\"icon item\" onclick=\"" + script+ "(-1);\"><i class=\"left chevron icon\"></i></a>");
		}
		for(int i=nowBlockFirst ; i<totalBlock+1 && i<nowBlockFirst+10 ; i++) {
			if(nowPage==i)	sb.append("<a class=\"item selected\" onclick=\"" + script+ "("+i+");\">" + i + "</a>");
			else			sb.append("<a class=\"item\" onclick=\"" + script+ "("+i+");\">" + i + "</a>");
		}
		if(nowDistrict<endDistrict) {//>
			sb.append("<a class=\"icon item\" onclick=\"" + script+ "(0);\"><i class=\"right chevron icon\"></i></a>");
			sb.append("<a class=\"icon item\" onclick=\"" + script+ "(-10);\"><i class=\"angle double right icon\"></i></a>");
		}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static String filetest() {
		//String path = "/resources/download"; // c://resources/download
		
		Calendar c = Calendar.getInstance();
		StringBuilder path = new StringBuilder("/download/" + c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH)+1) +"/" + c.get(Calendar.DATE));

		File file = new File(path.toString());
		System.out.println("exist : "  + file.exists());
		System.out.println(file.getAbsolutePath());
		
		System.out.println(file.mkdirs());
		
		return file.getPath();
	}

//	public static void main(String args[]) {
//		//filetest();
//		
//		File f= new File("C:\\Users\\ABC\\Desktop\\2020_recruit2.gif");
//		System.out.println(f.exists());
//		
//		byte[] a = new byte[1024*1024*100];
//		
//		FileInputStream fis;
//		try {
//			fis = new FileInputStream(f);
//		
//		BufferedInputStream bifs = new BufferedInputStream(fis);
//		int b = bifs.read(a);
//		System.out.println(b);
//		for(byte i : a) {
//			//System.out.printf("%c",i);
//		}
//		
//		Calendar c = Calendar.getInstance();
//		StringBuilder path = new StringBuilder("download/" + c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH)+1) +"/" + c.get(Calendar.DATE));
//		File ff =new File(path.toString());
//		ff.mkdirs();
//		
//		path.append("/2020_recruit2.gif");
//		File f2 = new File(path.toString());
//		//FileWriter fw = new FileWriter(path.toString(), true);
//		FileOutputStream fos = new FileOutputStream(f2);
//		BufferedOutputStream bos = new BufferedOutputStream(fos);
//		//BufferedWriter bfw = new BufferedWriter(fw);
//		//fos.write(a);
////		for(byte i : a) {
////			System.out.printf("%c",i);
////			bfw.write(i);
////			
////		}bfw.flush();
//		
//		fos.write(a);
//		fos.flush();
//		
//		} catch (IOException e ) {
//			e.printStackTrace();
//			System.out.println(e.getStackTrace());
//		}
//		
//	}
}

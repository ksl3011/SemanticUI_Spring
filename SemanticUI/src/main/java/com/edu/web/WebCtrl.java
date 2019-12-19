package com.edu.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.vo.BoardVO;

@Controller
public class WebCtrl {
	private String webUrl = "board"; 
	private String webUrl2 = "contents"; 
	private int total = 10000000;
	
	@RequestMapping(value = "/board")
	public String inputData(HttpServletRequest req) {
		List<BoardVO> list = this.list(1, total);
		req.setAttribute("list", list);
		req.setAttribute("listSize", total);
		return webUrl;
	}
	
	@RequestMapping(value = "/contents", method = RequestMethod.GET)
	public String outputData(HttpServletRequest req) {
		int no = Integer.parseInt(req.getParameter("no"));
		int nowPage = Integer.parseInt(req.getParameter("nowPage"));
		
		List<BoardVO> list = this.list(nowPage, total);
		req.setAttribute("list", list);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("listSize", total);
		
		for(BoardVO vo : list) {
			if(vo.getNo() == no) {
				req.setAttribute("vo", vo);
			}
		}
		return webUrl2;
	}
	
	private List<BoardVO> list(int nowPage, int total){
		List<BoardVO> list = new ArrayList<BoardVO>();
		int start = ((nowPage-1)*10)+1;
		for(int i=start ; i<total+1 && i<start+11 ; i++) {
			BoardVO vo = new BoardVO();
			vo.setId("아이디"+i);
			vo.setContents("내용"+i);
			vo.setNo(i);
			vo.setRegDt("2020-01-"+i);
			vo.setTitle("제목"+i);
			list.add(vo);
		}
		return list;
	}
	
	@RequestMapping(value = "/goPage", method = RequestMethod.GET)
	public String goPage(HttpServletRequest req) {
		//갈 페이지
		int page = Integer.parseInt(req.getParameter("no"));
		int nowPage = Integer.parseInt(req.getParameter("nowPage"));
		
		if(page == 0) {
			page = ((((nowPage-1)/10)+1)*10)+1;
		}else if(page == -1) {
			page = (((nowPage-1)/10)*10);
		}else if(page == -10) {
			page = ((total-1)/10)+1;
		}else if(page == -11) {
			page = 1;
		}
		
		List<BoardVO> list = this.list(page, total);
		req.setAttribute("list", list);
		req.setAttribute("nowPage", page);
		req.setAttribute("listSize", total);
		
		return webUrl;
	}
}

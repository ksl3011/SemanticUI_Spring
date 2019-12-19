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
	
	@RequestMapping(value = "/board")
	public String inputData(HttpServletRequest req) {
		List<BoardVO> list = this.list();
		req.setAttribute("list", list);
		return webUrl;
	}
	
	@RequestMapping(value = "/contents", method = RequestMethod.GET)
	public String outputData(HttpServletRequest req) {
		List<BoardVO> list = this.list();
		req.setAttribute("list", list);
		
		int no = Integer.parseInt(req.getParameter("no"));
		for(BoardVO vo : list) {
			if(vo.getNo() == no) {
				req.setAttribute("vo", vo);
			}
		}
		return webUrl2;
	}
	
	private List<BoardVO> list(){
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		for(int i=1 ; i<10 ; i++) {
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
}

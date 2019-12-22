package com.edu.board.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.board.BoardService;
import com.edu.vo.BoardVO;
import com.edu.vo.SearchVO;
import com.google.gson.JsonObject;

@Controller
public class BoardCtrl {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private String boardMainUrl = "semantic/board";
	
	@Autowired
	private BoardService c;
	
	
	@RequestMapping(value = "semantic/post", method = RequestMethod.POST)
	public String save(BoardVO vo) {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: post");
		LOG.debug("vo : " + vo);
		LOG.debug("==================================");
		
		int flag = c.save(vo);
		
		LOG.debug("==================================");
		LOG.debug("2/2) Controller: post");
		LOG.debug("flag : " + flag);
		LOG.debug("==================================");
		
		return boardMainUrl;
	}
	
	@RequestMapping(value = "semantic/retrieve", method = RequestMethod.GET)
	public String retrieve(Model model, SearchVO vo) {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: retrieve");
		LOG.debug("vo : " + vo);
		LOG.debug("==================================");
		
		if(vo.getPageSize() == 0) {
			vo.setPageSize(10);
		}
		
		List<BoardVO> list= (List<BoardVO>) c.retrieve(vo);
		
		LOG.debug("==================================");
		LOG.debug("2/2) Controller: retrieve");
		LOG.debug("list : " + list);
		LOG.debug("==================================");
		
		model.addAttribute("postList", list);
		
		return boardMainUrl;
	}
}

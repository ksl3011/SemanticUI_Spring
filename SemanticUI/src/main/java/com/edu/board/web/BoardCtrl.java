package com.edu.board.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.vo.BoardVO;
import com.google.gson.JsonObject;

@Controller
public class BoardCtrl {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private String boardMainUrl = "semantic/board";
	
	@RequestMapping(value = "semantic/post", method = RequestMethod.GET)
	public String save(Model model, BoardVO vo) {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: post");
		LOG.debug("==================================");
		
		LOG.debug("==================================");
		LOG.debug("2/2) Controller: post");
		LOG.debug("model : " + model.getAttribute("test"));
		LOG.debug("vo : " + vo);
		
		LOG.debug("==================================");
		
		
		return boardMainUrl;
	}

}

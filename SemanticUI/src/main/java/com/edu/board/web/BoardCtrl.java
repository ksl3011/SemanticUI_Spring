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

@Controller
public class BoardCtrl {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private String boardMainUrl = "semantic/board";
	private String boardContentsUrl = "semantic/contents";
	
	@Autowired
	private BoardService c;
	
	
	@RequestMapping(value = "semantic/delete", method = RequestMethod.POST)
	public String delete(Model model, BoardVO vo) {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: delete");
		LOG.debug("vo : " + vo);
		LOG.debug("==================================");
		
		int flag = 0;
		
		if(vo.getPw().equals("") || vo.getPw() == null) {
			LOG.debug("null PW");
		}else {
			flag = c.delete(vo);
		}
		
		model = retrieveModel(model, new SearchVO());

		LOG.debug("==================================");
		LOG.debug("2/2) Controller: delete");
		LOG.debug("flag : " + flag);
		LOG.debug("==================================");
		
		return boardMainUrl;
	}
	
	@RequestMapping(value = "semantic/post", method = RequestMethod.POST)
	public String save(Model model, BoardVO vo) {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: post");
		LOG.debug("vo : " + vo);
		LOG.debug("==================================");
		
		int flag = 0;
		
		if(vo.getTitle().equals("") || vo.getTitle() == null) {
			LOG.debug("null title");
		}else {
			flag = c.save(vo);
		}
		
		model = retrieveModel(model, new SearchVO());
		
		LOG.debug("==================================");
		LOG.debug("2/2) Controller: post");
		LOG.debug("flag : " + flag);
		LOG.debug("==================================");
		
		return boardMainUrl;
	}
	
	@RequestMapping(value = "semantic/retrieve", method = RequestMethod.GET)
	public String retrieve(Model model, SearchVO vo) {
		model = retrieveModel(model, vo);
		return boardMainUrl;
	}
	
	@RequestMapping(value = "semantic/contents", method = RequestMethod.GET)
	public String selectOne(Model model, SearchVO svo, BoardVO bvo) {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: selectOne");
		LOG.debug("svo : " + svo + ", postNum : " + bvo.getPostNum());
		LOG.debug("==================================");
		
		model = retrieveModel(model, svo);
		
		BoardVO outvo = (BoardVO) c.selectOne(bvo);
		
		LOG.debug("==================================");
		LOG.debug("2/2) Controller: selectOne");
		LOG.debug("vo : " + bvo);
		LOG.debug("==================================");
		
		model.addAttribute("selectPost", outvo);
		
		return boardContentsUrl;
	}
	
	private Model retrieveModel(Model model, SearchVO vo) {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: retrieve");
		LOG.debug("vo : " + vo);
		LOG.debug("==================================");
		
		if(vo.getPageSize() == 0) {
			vo.setPageSize(10);
		}
		
		if(vo.getPageNum() == 0) {
			vo.setPageNum(1);
		}
		
		List<BoardVO> list= (List<BoardVO>) c.retrieve(vo);
		
		LOG.debug("==================================");
		LOG.debug("2/2) Controller: retrieve");
		LOG.debug("list : " + list);
		LOG.debug("==================================");

		model.addAttribute("postList", list);			//리스트
		model.addAttribute("nowPage", vo.getPageNum());	//가져온페이지
		model.addAttribute("listSize", (list.size()==0)?0:list.get(0).getTotal());	//총 게시물 수
		model.addAttribute("pageSize", vo.getPageSize());	//보여질 페이지 개수
		
		return model;
	}
}

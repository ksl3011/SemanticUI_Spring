package com.edu.board.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.edu.board.BoardService;
import com.edu.board.FileService;
import com.edu.cmn.Common;
import com.edu.vo.BoardVO;
import com.edu.vo.FileVO;
import com.edu.vo.SearchVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class BoardCtrl {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private String boardMainUrl = "semantic/board";
	private String boardContentsUrl = "semantic/contents";
	private String boardPostUrl = "semantic/post";
	
	@Autowired
	private BoardService c;

	@Autowired
	private FileService fc;
	
	@Autowired
	private View downloadView;
	
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
			flag += fc.delete(vo);
		}
		
		model = retrieveModel(model, new SearchVO());

		LOG.debug("==================================");
		LOG.debug("2/2) Controller: delete");
		LOG.debug("flag : " + flag);
		LOG.debug("==================================");
		
		return boardMainUrl;
	}
	
	@RequestMapping(value = "semantic/post", method = RequestMethod.POST)
	public String save(Model model, BoardVO vo, MultipartFile files0
			, MultipartFile files1, MultipartFile files2
			, MultipartFile files3, MultipartFile files4) throws FileNotFoundException {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: post");
		LOG.debug("vo : " + vo);
		LOG.debug("==================================");
		int flag = 0;
		
		if(vo.getTitle().equals("") || vo.getTitle() == null) {
			LOG.debug("null title");
		}else {
			flag = c.save(vo);
			
			int fcnt = 0;
			MultipartFile[] fileOb = {files0,files1,files2,files3,files4};
			FileVO fvo;
			for(MultipartFile files : fileOb) {//중복파일이름처리+fileVO설정->내용다운로드->close
				if(files != null && !files.getOriginalFilename().equals("")) {
					String path = Common.downloadDir();
					String oName = files.getOriginalFilename();
					String ext = oName.substring(oName.lastIndexOf("."), oName.length());
					long size = files.getSize();
					
					fvo = new FileVO();
					fvo.setoName(oName);
					fvo.setPostNum(flag);
					fvo.setSize(size);
					
					File f = new File(path + File.separator + oName);
					int cnt = 1;
					String rName = oName;//서버에 저장될 파일 이름
					while(f.exists()) {
						String reName = rName.substring(0, rName.lastIndexOf(".")) + (cnt++) + ext;
						f = new File(path + File.separator + reName);
					}
					fvo.setrName(rName);
					
					FileOutputStream fos = new FileOutputStream(f);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					LOG.debug(f.getPath() + " : " + files.getSize());
					
					try {
						if(files.getSize() > 1024*1024*10) continue;
						
						byte[] b = files.getBytes();
			
						bos.write(b);
						bos.flush();
						fc.save(fvo);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {bos.close();fos.close();} catch (IOException e) {}
					}
					fcnt++;
				}
			}
			vo.setFileCode(fcnt+"");
			c.update(vo);
			
			
		}
		
		model = retrieveModel(model, new SearchVO());
		
		LOG.debug("==================================");
		LOG.debug("2/2) Controller: post");
		LOG.debug("flag : " + flag);
		LOG.debug("==================================");
		
		return boardMainUrl;
	}
	/*
	 * form-> input type file로 전달 -> MultipartFile (multiple일경우 [])
	 * 
	 */
	
	@RequestMapping(value = "semantic/retrieve", method = RequestMethod.GET)
	public String retrieve(Model model, SearchVO vo) {
		model = retrieveModel(model, vo);
		return boardMainUrl;
	}
	
	@RequestMapping(value = "semantic/contents", method = RequestMethod.POST)
	public String selectOne(Model model, SearchVO svo, BoardVO bvo) {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: selectOne");
		LOG.debug("svo : " + svo + ", postNum : " + bvo.getPostNum());
		LOG.debug("==================================");
		
		model = retrieveModel(model, svo);
		
		BoardVO outvo = (BoardVO) c.selectOne(bvo);
		List<FileVO> fileList = (List<FileVO>) fc.retrieve(bvo);
		
		LOG.debug("==================================");
		LOG.debug("2/2) Controller: selectOne");
		LOG.debug("vo : " + bvo);
		LOG.debug("fileList" + fileList);
		LOG.debug("==================================");
		
		model.addAttribute("selectPost", outvo);
		model.addAttribute("fileList", fileList);
		
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
	
	@RequestMapping(value = "semantic/download", method = RequestMethod.POST)
	public ModelAndView downloadFile(ModelAndView mv, FileVO vo) {
		LOG.debug("==================================");
		LOG.debug("Controller: downloadFile");
		LOG.debug("==================================");
		
		mv.addObject("vo", vo);
		mv.setView(downloadView);
		
		return mv;
	}
	
	@RequestMapping(value = "semantic/update", method = RequestMethod.POST)
	public String update(Model model, BoardVO vo) {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: update");
		LOG.debug("==================================");
		
		vo = (BoardVO) c.selectOne(vo);
		List<FileVO> fvo = (List<FileVO>) fc.retrieve(vo);
		
		model.addAttribute("fileList", fvo);
		model.addAttribute("vo", vo);
		
		LOG.debug("==================================");
		LOG.debug("2/2) Controller: update");
		LOG.debug("vo : " + vo.toString());
		LOG.debug("fvo : " + fvo.toString());
		LOG.debug("==================================");
		
		return boardPostUrl;
	}
	
	@RequestMapping(value = "semantic/updateDo", method = RequestMethod.POST)
	public String updateDo(Model model, BoardVO vo, MultipartFile files0
			, MultipartFile files1, MultipartFile files2
			, MultipartFile files3, MultipartFile files4) throws FileNotFoundException {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: updateDo");
		LOG.debug("vo : " + vo);
		LOG.debug("==================================");
		int flag = 0;
		
		if(vo.getTitle().equals("") || vo.getTitle() == null) {
			LOG.debug("null title");
		}else {
			flag = c.update(vo);
			
			int fcnt = 0;
			MultipartFile[] fileOb = {files0,files1,files2,files3,files4};
			FileVO fvo;
			for(MultipartFile files : fileOb) {//중복파일이름처리+fileVO설정->내용다운로드->close
				if(files != null && !files.getOriginalFilename().equals("")) {
					String path = Common.downloadDir();
					String oName = files.getOriginalFilename();
					String ext = oName.substring(oName.lastIndexOf("."), oName.length());
					long size = files.getSize();
					
					fvo = new FileVO();
					fvo.setoName(oName);
					fvo.setPostNum(vo.getPostNum());
					fvo.setSize(size);
					
					File f = new File(path + File.separator + oName);
					int cnt = 1;
					String rName = oName;//서버에 저장될 파일 이름
					while(f.exists()) {
						String reName = rName.substring(0, rName.lastIndexOf(".")) + (cnt++) + ext;
						f = new File(path + File.separator + reName);
					}
					fvo.setrName(rName);
					
					FileOutputStream fos = new FileOutputStream(f);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					LOG.debug(f.getPath() + " : " + files.getSize());
					
					try {
						if(files.getSize() > 1024*1024*10) continue;
						
						byte[] b = files.getBytes();
			
						bos.write(b);
						bos.flush();
						fc.save(fvo);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {bos.close();fos.close();} catch (IOException e) {}
					}
					fcnt++;
				}
			}
			BoardVO aux = (BoardVO) c.selectOne(vo);
			int sum = Integer.parseInt((aux.getFileCode()==null)?"0":aux.getFileCode()) + fcnt;
			vo.setFileCode(sum+"");
			c.update(vo);
			
			
		}
		
		model = retrieveModel(model, new SearchVO());
		
		LOG.debug("==================================");
		LOG.debug("2/2) Controller: updateDo");
		LOG.debug("flag : " + flag);
		LOG.debug("==================================");
		
		return boardMainUrl;
	}
	
	@ResponseBody
	@RequestMapping(value = "semantic/deleteFileOne", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String deleteFileOne(FileVO vo) {
		LOG.debug("==================================");
		LOG.debug("1/2) Controller: deleteFileOne");
		LOG.debug("vo : " + vo);
		LOG.debug("==================================");
		
		int flag = fc.deleteOne(vo);

		LOG.debug("==================================");
		LOG.debug("2/2) Controller: deleteFileOne");
		LOG.debug("flag : " + flag);
		
		JsonObject jo = new JsonObject();
		jo.addProperty("msg", flag);
		
		return jo.toString();
	}
}

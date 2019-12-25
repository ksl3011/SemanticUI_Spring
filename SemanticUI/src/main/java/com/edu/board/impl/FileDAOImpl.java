package com.edu.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.board.BoardDAO;
import com.edu.board.DTO;
import com.edu.vo.FileVO;
import com.edu.vo.SearchVO;

public class FileDAOImpl implements BoardDAO {	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private String NAMESPACE = "com.edu.file";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int save(DTO dto) {
		FileVO vo = (FileVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("1/2) DAO: File save");
		LOG.debug("1/2) vo : " + vo.toString());
		LOG.debug("==================================");
		
		String statement = this.NAMESPACE + ".save";
		
		int flag = sqlSessionTemplate.insert(statement, vo);
		
		LOG.debug("==================================");
		LOG.debug("2/2) DAO: save");
		LOG.debug("2/2) flag : " + flag);
		LOG.debug("==================================");
		
		return flag;
	}

	@Override
	public List<?> retrieve(DTO dto) {
		SearchVO invo = (SearchVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("1/2) DAO: File retrieve");
		LOG.debug("1/2) vo : " + invo.toString());
		LOG.debug("==================================");
		
		String statement = this.NAMESPACE + ".retrieve";
		
		List<FileVO> list = sqlSessionTemplate.selectList(statement, invo);
		
		LOG.debug("==================================");
		LOG.debug("2/2) DAO: retrieve");
		LOG.debug("2/2) list : " + list);
		LOG.debug("==================================");
		
		return list;
	}

	@Override
	public DTO selectOne(DTO dto) {
		FileVO invo = (FileVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("1/2) DAO: File selectOne");
		LOG.debug("1/2) vo : " + invo.toString());
		LOG.debug("==================================");
		
		String statement = this.NAMESPACE + ".selectOne";
		
		FileVO outvo = sqlSessionTemplate.selectOne(statement, invo);
		
		LOG.debug("==================================");
		LOG.debug("2/2) DAO: selectOne");
		LOG.debug("2/2) outvo : " + outvo);
		LOG.debug("==================================");
		
		return outvo;
	}

	@Override
	public int update(DTO dto) {
		FileVO vo = (FileVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("1/2) DAO: File update");
		LOG.debug("1/2) vo : " + vo.toString());
		LOG.debug("==================================");
		
		String statement = this.NAMESPACE + ".update";
		
		int flag = sqlSessionTemplate.update(statement, vo);
		
		LOG.debug("==================================");
		LOG.debug("2/2) DAO: update");
		LOG.debug("2/2) flag : " + flag);
		LOG.debug("==================================");
		
		return flag;
	}

	@Override
	public int delete(DTO dto) {
		FileVO vo = (FileVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("1/2) DAO: File delete");
		LOG.debug("1/2) vo : " + vo.toString());
		LOG.debug("==================================");
		
		String statement = this.NAMESPACE + ".delete";
		
		int flag = sqlSessionTemplate.delete(statement, vo);
		
		LOG.debug("==================================");
		LOG.debug("2/2) DAO: delete");
		LOG.debug("2/2) flag : " + flag);
		LOG.debug("==================================");
		
		return flag;
	}



}

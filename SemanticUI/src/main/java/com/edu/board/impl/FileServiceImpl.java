package com.edu.board.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.board.BoardDAO;
import com.edu.board.BoardService;
import com.edu.board.DTO;
import com.edu.board.FileDAO;
import com.edu.board.FileService;

@Service
public class FileServiceImpl implements FileService {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileDAO dao;
	
	@Override
	public int save(DTO dto) {
		return dao.save(dto);
	}

	@Override
	public List<?> retrieve(DTO dto) {
		return dao.retrieve(dto);
	}

	@Override
	public DTO selectOne(DTO dto) {
		return dao.selectOne(dto);
	}

	@Override
	public int update(DTO dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(DTO dto) {
		return dao.delete(dto);
	}

}

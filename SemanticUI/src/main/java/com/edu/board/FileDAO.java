package com.edu.board;

import java.util.List;

public interface FileDAO {

	public int save(DTO dto);
	public List<?> retrieve(DTO dto);
	public DTO selectOne(DTO dto);
	public int update(DTO dto);
	public int delete(DTO dto);
	
}

package com.edu.messenger;

import org.springframework.stereotype.Repository;

import com.edu.board.DTO;

public interface MessengerDAO {

	public DTO save(DTO dto);
	public DTO update(DTO dto);
	public DTO selectOne(DTO dto);
	public DTO retrieve(DTO dto);
}

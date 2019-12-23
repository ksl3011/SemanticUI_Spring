package com.edu.cmn;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.edu.vo.BoardVO;

@WebAppConfiguration
@SpringBootTest
class test {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mock;
	
	//@Test
	void save() throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.post("/semantic/post")
										.param("title", "1제목")
										.param("pw", "1비번")
										.param("userId", "1아이디")
										.param("contents", "1내용");
		
		ResultActions resultAction = mock.perform(createMessage)
										.andExpect(status().isOk());
		
		MvcResult result = mock.perform(createMessage)
                .andExpect(status().isOk())
                .andReturn();
		
		LOG.debug("=====================================");
		LOG.debug("");
		LOG.debug("=====================================");
		
	}
	
	@Test
	void retrieve() throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/semantic/retrieve")
										.param("pageSize", "4");
		
		ResultActions resultAction = mock.perform(createMessage)
										.andExpect(status().isOk());
		
		MvcResult result = mock.perform(createMessage)
                .andExpect(status().isOk())
                .andReturn();
		
		List<BoardVO> list = (List<BoardVO>) result.getRequest().getAttribute("postList");
		LOG.debug("=====================================");
		LOG.debug("list :" + list);
		LOG.debug("=====================================");
		
	}
	
	@BeforeEach
	public void before() {
		mock = MockMvcBuilders.webAppContextSetup(context).build();
	}
}

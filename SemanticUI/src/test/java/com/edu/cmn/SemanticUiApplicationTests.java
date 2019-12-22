package com.edu.cmn;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
class SemanticUiApplicationTests {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mock;
	
	@Test
	void contextLoads() throws Exception {
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/semantic/post")
										.param("postNum", "990");
	
		
		ResultActions resultAction = mock.perform(createMessage)
										.andExpect(status().isOk());
		
		MvcResult result = mock.perform(createMessage)
                .andExpect(status().isOk())
                .andReturn();
		
		LOG.debug("=====================================");
		LOG.debug("");
		LOG.debug("=====================================");
		
	}
	
	//@Test
	public void do_save() throws Exception {
		
		
		MockHttpServletRequestBuilder createMessage = 
				MockMvcRequestBuilders.get("/semantic/post");

		
		ResultActions resultAction = mock.perform(createMessage)
	.andExpect(status().isOk());
		String result = resultAction.andDo(print())
										.andReturn()
										.getResponse()
										.getContentAsString();
		
		LOG.debug("=====================================================");
		LOG.debug("return = " + result);
		LOG.debug("=====================================================");
	}
	
	@BeforeEach
	public void before() {
		mock = MockMvcBuilders.webAppContextSetup(context).build();
	}
}

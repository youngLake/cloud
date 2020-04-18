package com.young.edge.cloud;

import com.alibaba.fastjson.JSONObject;
import com.young.edge.cloud.controller.UserController;
import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.dao.UserDao;
import com.young.edge.cloud.domain.User;
import com.young.edge.cloud.service.UserService;
import com.young.edge.cloud.service.impl.UserServiceImpl;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tornado Young
 * @date time 2020/4/18 11:08
 */
@SpringBootTest
@ExtendWith({MockitoExtension.class,SpringExtension.class})
@Slf4j
public class MockitoTest {
    @MockBean
    private UserDao dao;
    @MockBean
    UserService userService;
    @Autowired
    UserController controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setMockMvc(){
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testMock(){
        when(dao.findById(anyString())).thenReturn(any(Optional.class));
        userService.getMyProfile("1");
    }

    @Test
    public void testMvc() throws Exception{
        PageParameters parameters=new PageParameters();
        parameters.setIndex(1);
        parameters.setSize(10);
        parameters.setName("1");
        when(userService.userList(any())).thenReturn(any());
        Assert.isNull(null);
        mockMvc.perform(
                post("/userList")
                .content(JSONObject.toJSONString(parameters))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andDo(mvcResult -> {
                    String content = mvcResult.getResponse().getContentAsString();
                    System.out.println(content);
                }).andReturn();
    }
}

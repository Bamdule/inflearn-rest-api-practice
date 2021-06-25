package com.restapi.configs;

import com.restapi.accounts.Account;
import com.restapi.accounts.AccountRole;
import com.restapi.accounts.AccountService;
import com.restapi.common.AppProperties;
import com.restapi.common.BaseControllerTest;
import com.restapi.common.TestDescription;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AppProperties appProperties;

    @Test
    @TestDescription("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {
        //Given
        String username = appProperties.getUserUsername();
        String password = appProperties.getUserPassword();

        String clientId = appProperties.getClientId();
        String clientSecrent = appProperties.getClientSecret();
        this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic(clientId, clientSecrent))
                .param("username", username)
                .param("password", password)
                .param("grant_type", "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists())
        ;
    }

}
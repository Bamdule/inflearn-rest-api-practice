package com.restapi.accounts;

import com.restapi.common.AppProperties;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AppProperties appProperties;
    @Test
    public void findByUsername() {

        String username = appProperties.getUserUsername();
        String password = appProperties.getUserPassword();


        //When
        UserDetailsService userDetailsService = (UserDetailsService) accountService;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

//        //Then
//        Assertions.assertThat(userDetails.getPassword()).isEqualTo(password);

        //Then
        Assertions.assertThat(passwordEncoder.matches(password, userDetails.getPassword())).isTrue();

    }

    @Test(expected = UsernameNotFoundException.class)
    public void findByUsernameFail() {
        String username = "tester";
        this.accountService.loadUserByUsername(username);
    }

}
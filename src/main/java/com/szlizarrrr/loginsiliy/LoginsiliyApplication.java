package com.szlizarrrr.loginsiliy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.szlizarrrr.loginsiliy.domain.UserNon;

@SpringBootApplication
public class LoginsiliyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginsiliyApplication.class, args);
	}
	
	@Bean
	public List<UserNon> mockDb() {
		List<UserNon> userList = new ArrayList<UserNon>();
		userList.add(new UserNon(1L, "aaaa", "aaaa"));
		userList.add(new UserNon(2L, "bbbb", "bbbb"));
		userList.add(new UserNon(3L, "cccc", "cccc"));
		userList.add(new UserNon(4L, "dddd", "dddd"));
		return userList;
	}
	
	
}

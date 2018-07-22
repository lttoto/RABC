package com.lt.securitydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecuritydemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritydemoApplication.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "hello spring boot";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello world";
	}

	@RequestMapping("/roleAuth")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole() ...")
	@PostAuthorize("hasRole('ROLE_ADMIN')")
	@PostFilter("")
	@PreFilter("")
	public String role() {
		return "admin auth";
	}

	@RequestMapping("/test")
	@PreAuthorize("#id < 10 and principal.username.equals(#username) and #user.name.equals('abc')")
	@PostAuthorize("returnObject%2==0")
	public Integer test(Integer id, String username, SecurityProperties.User user) {
		return id;
	}

	@RequestMapping("/test2")
	@PostFilter("filterObject%2==0")
	@PreFilter("filterObject%4==0")
	public List<Integer> test2(List<Integer> id) {
		return id;
	}
}

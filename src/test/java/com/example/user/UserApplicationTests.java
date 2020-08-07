package com.example.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@SpringBootTest
class UserApplicationTests {

	@Test
	void contextLoads() {
		Resource resource = new ClassPathResource("pubkey.txt");
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
			BufferedReader br = new BufferedReader(inputStreamReader);
			System.out.println(br.lines().collect(Collectors.joining("\n")));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}

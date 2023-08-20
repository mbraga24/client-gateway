package com.pnc;

import com.pnc.user.User;
import com.pnc.user.UserRepository;
import com.pnc.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class SpringPncApplication {

	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =
		SpringApplication.run(SpringPncApplication.class, args);

//		printBeans(applicationContext);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository) {
		return args -> {
			User kevin = new User("Kevin",
					"Rice",
					"kevinRice",
					"kevin@email.com",
					passwordEncoder.encode("password"),
					Role.ADMIN,
					false,
					true);

			User dave = new User("Dave",
			"Bean",
			"daveBean",
			"dave@email.com",
			passwordEncoder.encode("password"),
			Role.USER,
			false,
			true);

			List<User> users = List.of(kevin, dave);
			userRepository.saveAll(users);
		};
	}

//	private static void printBeans(ConfigurableApplicationContext context) {
//		String[] beanDefinitionNames = context.getBeanDefinitionNames();
//		for (String beanDefinitionName : beanDefinitionNames) {
//			System.out.println(beanDefinitionName);
//		}
//	}

}

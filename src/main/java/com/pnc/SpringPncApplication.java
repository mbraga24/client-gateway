package com.pnc;

import com.pnc.appuser.User;
import com.pnc.appuser.UserRepository;
import com.pnc.appuser.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringPncApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =
		SpringApplication.run(SpringPncApplication.class, args);

//		printBeans(applicationContext);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository) {
		return args -> {
			User marlon = new User("Kevin",
					"Rice",
					"kevinRice",
					"kevin@email.com",
					"password",
					UserRole.ADMIN,
					false,
					true);
			User dave = new User("Dave",
			"Bean",
			"daveBean",
			"dave@email.com",
			"password",
			UserRole.USER,
			false,
			true);

			List<User> users = List.of(marlon, dave);
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

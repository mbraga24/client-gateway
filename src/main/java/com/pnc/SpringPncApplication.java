package com.pnc;

import com.pnc.appuser.AppUser;
import com.pnc.appuser.AppUserRepository;
import com.pnc.appuser.AppUserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.lang.module.Configuration;
import java.util.List;

@SpringBootApplication
public class SpringPncApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =
		SpringApplication.run(SpringPncApplication.class, args);

//		printBeans(applicationContext);
	}

	@Bean
	CommandLineRunner runner(AppUserRepository appUserRepository) {
		return args -> {
			AppUser marlon = new AppUser("Marlon",
					"marlonRice",
					"marlon@email.com",
					"password",
					AppUserRole.ADMIN,
					false,
					true);
			AppUser dave = new AppUser("Dave",
			"daveBean",
			"dave@email.com",
			"password",
			AppUserRole.USER,
			false,
			true);

			List<AppUser> appUsers = List.of(marlon, dave);
			appUserRepository.saveAll(appUsers);
		};
	}

//	private static void printBeans(ConfigurableApplicationContext context) {
//		String[] beanDefinitionNames = context.getBeanDefinitionNames();
//		for (String beanDefinitionName : beanDefinitionNames) {
//			System.out.println(beanDefinitionName);
//		}
//	}

}

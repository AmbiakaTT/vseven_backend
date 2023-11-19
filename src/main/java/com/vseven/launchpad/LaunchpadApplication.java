package com.vseven.launchpad;

import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.entity.UserQuickLink;
import com.vseven.launchpad.repository.UserQuickLinkRepository;
import com.vseven.launchpad.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@SpringBootApplication()
public class LaunchpadApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaunchpadApplication.class, args);
	}

	// Define a CommandLineRunner bean
}

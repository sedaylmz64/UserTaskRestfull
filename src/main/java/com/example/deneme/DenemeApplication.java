package com.example.deneme;

import com.example.deneme.model.entity.*;
import com.example.deneme.repositories.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class DenemeApplication implements CommandLineRunner {

	@Autowired
	UserRepository usersRepository;

	public static void main(String[] args) {
		SessionFactory factory = (SessionFactory) new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(UserEntity.class)
				.addAnnotatedClass(TaskEntity.class)
				.addAnnotatedClass(ProcessEntity.class)
				.addAnnotatedClass(MetricEntity.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		SpringApplication.run(DenemeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		usersRepository.deleteAll();
		usersRepository.save(generateUserEntity());
	}

	private UserEntity generateUserEntity() {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName("sedaylmz@gmail.com");
		userEntity.setPassword("1234");
		Set<Role> roles = getRoles();
		userEntity.setRoles(roles);
		return userEntity;
	}

	private Set<Role> getRoles() {
		Set<Role> roles = new HashSet<>();
		generateRole(roles);
		return roles;
	}

	private void generateRole(Set<Role> roles) {
		Role role = new Role();
		role.setRole("ADMIN");
		roles.add(role);
	}
}

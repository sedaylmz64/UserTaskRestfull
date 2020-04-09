package com.example.deneme;

import com.example.deneme.model.entity.ProcessEntity;
import com.example.deneme.model.entity.TaskEntity;
import com.example.deneme.model.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class DenemeApplication {

	public static void main(String[] args) {
		SessionFactory factory = (SessionFactory) new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(UserEntity.class)
				.addAnnotatedClass(TaskEntity.class)
				.addAnnotatedClass(ProcessEntity.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();
		SpringApplication.run(DenemeApplication.class, args);
	}

}

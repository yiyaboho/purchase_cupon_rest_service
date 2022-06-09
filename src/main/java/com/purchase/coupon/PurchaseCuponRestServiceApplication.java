package com.purchase.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.purchase.coupon.repository.model") 
@EnableJpaRepositories(basePackages = {"com.purchase.coupon.repository"})
public class PurchaseCuponRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseCuponRestServiceApplication.class, args);
	}

}

package com.purchase.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purchase.coupon.repository.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

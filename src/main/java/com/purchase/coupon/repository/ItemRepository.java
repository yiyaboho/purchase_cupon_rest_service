package com.purchase.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purchase.coupon.repository.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {


}

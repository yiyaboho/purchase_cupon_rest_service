package com.purchase.coupon.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.purchase.coupon.repository.model.Favorites;
import com.purchase.coupon.repository.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

	@Query("SELECT f.item.itemId AS itemId, COUNT(f.user) AS quantity FROM UserItem f"
			+ "	GROUP BY f.item ORDER BY quantity DESC" )
	List<Favorites> findTopFavorites(Pageable top);
	
	
}

package com.purchase.coupon.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.purchase.coupon.repository.model.Favorites;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, String> {

	@Query("select new com.purchase.coupon.repository.model.Favorites(f.itemId, f.quantity) from Favorites f order by f.quantity DESC" )
	List<Favorites> findTopFavorites(Pageable top);
	
	
}

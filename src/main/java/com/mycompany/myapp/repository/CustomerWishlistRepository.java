package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.CustomerWishlist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CustomerWishlist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerWishlistRepository extends JpaRepository<CustomerWishlist, Long> {

}

package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.ProductRating;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProductRating entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {

}

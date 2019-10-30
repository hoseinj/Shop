package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.ProductRelated;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProductRelated entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRelatedRepository extends JpaRepository<ProductRelated, Long> {

}

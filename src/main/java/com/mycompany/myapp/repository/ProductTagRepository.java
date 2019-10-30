package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.ProductTag;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProductTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {

}

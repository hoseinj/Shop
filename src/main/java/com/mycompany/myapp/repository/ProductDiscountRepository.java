package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.ProductDiscount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProductDiscount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, Long> {

}

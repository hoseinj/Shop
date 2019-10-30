package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.ShopPage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ShopPage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopPageRepository extends JpaRepository<ShopPage, Long> {

}

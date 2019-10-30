package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.ShopUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ShopUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopUserRepository extends JpaRepository<ShopUser, Long> {

}

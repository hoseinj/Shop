package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.ProductViewLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProductViewLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductViewLogRepository extends JpaRepository<ProductViewLog, Long> {

}

package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.StockStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StockStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockStatusRepository extends JpaRepository<StockStatus, Long> {

}

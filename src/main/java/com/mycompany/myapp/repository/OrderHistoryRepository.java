package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.OrderHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

}

package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.OrderLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderLogRepository extends JpaRepository<OrderLog, Long> {

}

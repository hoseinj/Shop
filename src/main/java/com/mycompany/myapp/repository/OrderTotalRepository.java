package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.OrderTotal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderTotal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderTotalRepository extends JpaRepository<OrderTotal, Long> {

}

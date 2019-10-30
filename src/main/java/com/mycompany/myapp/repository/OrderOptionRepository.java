package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.OrderOption;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderOption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderOptionRepository extends JpaRepository<OrderOption, Long> {

}

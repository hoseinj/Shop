package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.CustomerTransaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CustomerTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {

}

package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.CustomerIp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CustomerIp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerIpRepository extends JpaRepository<CustomerIp, Long> {

}

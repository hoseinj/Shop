package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.CustomerGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CustomerGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Long> {

}

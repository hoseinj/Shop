package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.PageGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PageGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PageGroupRepository extends JpaRepository<PageGroup, Long> {

}

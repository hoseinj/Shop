package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.PorductDescription;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PorductDescription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PorductDescriptionRepository extends JpaRepository<PorductDescription, Long> {

}

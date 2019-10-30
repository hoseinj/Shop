package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.BannerImageDescription;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BannerImageDescription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BannerImageDescriptionRepository extends JpaRepository<BannerImageDescription, Long> {

}

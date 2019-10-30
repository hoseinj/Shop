package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.BannerGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BannerGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BannerGroupRepository extends JpaRepository<BannerGroup, Long> {

}

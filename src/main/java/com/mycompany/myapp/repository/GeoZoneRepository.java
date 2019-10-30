package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.GeoZone;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GeoZone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeoZoneRepository extends JpaRepository<GeoZone, Long> {

}

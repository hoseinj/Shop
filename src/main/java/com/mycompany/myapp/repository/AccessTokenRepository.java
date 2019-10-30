package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.AccessToken;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccessToken entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

}

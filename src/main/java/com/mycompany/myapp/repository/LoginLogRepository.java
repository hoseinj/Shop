package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.LoginLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LoginLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {

}

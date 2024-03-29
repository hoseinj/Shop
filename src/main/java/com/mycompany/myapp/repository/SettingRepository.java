package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.Setting;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Setting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SettingRepository extends JpaRepository<Setting, Long> {

}

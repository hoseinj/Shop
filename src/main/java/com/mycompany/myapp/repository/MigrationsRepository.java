package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.Migrations;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Migrations entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MigrationsRepository extends JpaRepository<Migrations, Long> {

}

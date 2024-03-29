package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.CategoryDescription;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CategoryDescription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryDescriptionRepository extends JpaRepository<CategoryDescription, Long> {

}

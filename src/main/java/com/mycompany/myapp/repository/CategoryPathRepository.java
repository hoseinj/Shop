package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.CategoryPath;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CategoryPath entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryPathRepository extends JpaRepository<CategoryPath, Long> {

}

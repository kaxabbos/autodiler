package com.autodiler.repo;

import com.autodiler.model.CarDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDescriptionRepo extends JpaRepository<CarDescription, Long> {
}

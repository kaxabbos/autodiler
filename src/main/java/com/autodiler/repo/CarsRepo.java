package com.autodiler.repo;

import com.autodiler.model.Cars;
import com.autodiler.model.enums.BodyType;
import com.autodiler.model.enums.CarStatus;
import com.autodiler.model.enums.Fuel;
import com.autodiler.model.enums.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepo extends JpaRepository<Cars, Long> {
    List<Cars> findAllByStatus(CarStatus status);

    List<Cars> findAllByStatusAndDescription_BodyTypeAndDescription_TransmissionAndDescription_Fuel(CarStatus status, BodyType bodyType, Transmission transmission, Fuel fuel);
}

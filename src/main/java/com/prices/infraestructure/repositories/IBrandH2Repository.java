package com.prices.infraestructure.repositories;

import com.prices.infraestructure.repositories.entities.BrandEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandH2Repository extends CrudRepository<BrandEntity, Integer> {

}

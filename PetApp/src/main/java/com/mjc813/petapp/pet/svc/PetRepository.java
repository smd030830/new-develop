package com.mjc813.petapp.pet.svc;

import com.mjc813.petapp.pet.dto.PetEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Integer> {

	Slice<PetEntity> findByNameContains(String searchName, Pageable pageable);
}

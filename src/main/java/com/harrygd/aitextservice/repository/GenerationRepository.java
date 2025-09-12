package com.harrygd.aitextservice.repository;

import com.harrygd.aitextservice.model.Generation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerationRepository extends JpaRepository<Generation, Long> {
    // No code needed — JpaRepository gives you save(), findAll(), findById(), delete() etc.
}

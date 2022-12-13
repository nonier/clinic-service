package com.nonier.cliniccore.repository;

import com.nonier.cliniccore.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByUser_Id(Long userId);
}

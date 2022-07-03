package com.example.demo.repository;

import com.example.demo.model.Ball;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BallRepository extends JpaRepository<Ball, Long> {

    List<Ball> findBySizeGreaterThanAndWeightGreaterThan(Integer size, Float weight);
}

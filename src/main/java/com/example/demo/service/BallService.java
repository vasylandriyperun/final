package com.example.demo.service;

import com.example.demo.model.Ball;
import com.example.demo.repository.BallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BallService {

    @Autowired
    BallRepository repository;

    public List<Ball> getAllBalls() {
        return repository.findAllByOrderBySizeAsc();
    }

    public List<Ball> getBallsBiggerAndHeavierThan(Integer size, Float weight) {
        return repository.findBySizeGreaterThanAndWeightGreaterThan(size, weight);
    }

    public List<Ball> getHeaviestBalls(Integer number) {
        List<Ball> heavyBallsList = repository.findAllByOrderByWeightDesc();
        if (number != null && number < heavyBallsList.size())
            return heavyBallsList.subList(0, number);
        return heavyBallsList;
    }

    public Ball getBallById(Long id) {
        Optional<Ball> ballOptional = repository.findById(id);
        if (ballOptional.isPresent()) {
            return ballOptional.get();
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteBallById(Long id) {
        repository.deleteById(id);
    }

    public void createAndSaveBall(Ball ball) {
        if (ball.getId() == null
                || !repository.existsById(ball.getId()))
            repository.save(ball);
    }

    public void updateBall(Long id, Ball ball) {
        Ball ballExisting = getBallById(id);
        ballExisting.setSize(ball.getSize());
        ballExisting.setWeight(ball.getWeight());
        repository.save(ballExisting);
    }

}

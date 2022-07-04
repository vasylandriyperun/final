package com.example.demo.controller;

import com.example.demo.model.Ball;
import com.example.demo.service.BallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
public class BallController {

    @Autowired
    BallService service;

    @GetMapping(path = "/balls")
    public List<Ball> getBalls(@RequestParam(required = false) Integer size,
                               @RequestParam(required = false) Float weight) {
        if (size != null && weight != null)
            return service.getBallsBiggerAndHeavierThan(size, weight);
        return service.getAllBalls();
    }

    @GetMapping(path = "/balls/heavies")
    public List<Ball> getHeavyBalls(
            @RequestParam @NotNull @Positive Integer number) {
        return service.getHeaviestBalls(number);
    }

    @GetMapping(path = "/balls/{id}")
    public Ball getBallById(@PathVariable Long id) {
        return service.getBallById(id);
    }

    @DeleteMapping(path = "/balls/{id}")
    public void deleteBallById(@PathVariable Long id) {
        service.deleteBallById(id);
    }

    @PostMapping(path = "/balls/new")
    public void addBall(@RequestBody Ball ball) {
        service.createAndSaveBall(ball);
    }

    @PutMapping(path = "/balls/{id}")
    public void updateBall(@PathVariable Long id,
                           @RequestBody Ball ball) {
        service.updateBall(id, ball);
    }

}

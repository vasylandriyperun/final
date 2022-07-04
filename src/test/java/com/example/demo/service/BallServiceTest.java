package com.example.demo.service;

import com.example.demo.model.Ball;
import com.example.demo.repository.BallRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BallServiceTest {

    @Mock
    BallRepository repository;

    @InjectMocks
    BallService service;

    @Test
    void getBallByIdShouldReturnBallIfIdExists() {
        //given
        Ball ball1 = new Ball(1L, 2, 3F);
        when(repository.findById(1L)).thenReturn(Optional.of(ball1));
        //when
        Ball realBall = service.getBallById(1L);
        //then
        assertEquals(realBall, ball1);
    }

    @Test
    void getBallByIdShouldThrowExceptionIfIdDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.getBallById(1L));
    }

    @Test
    void updateBallShouldUpdateBall() {
        //given:
        Ball newBall = new Ball();
        newBall.setSize(10);
        newBall.setWeight(22.5F);
        Ball ball1 = new Ball(1L, 2, 3F);
        when(repository.findById(1L)).thenReturn(Optional.of(ball1));
        //when:
        service.updateBall(1L, newBall);
        //then:
        verify(repository).save(ball1);
        assertEquals(ball1.getSize(), (newBall.getSize()));
        assertEquals(ball1.getWeight(), (newBall.getWeight()));
    }

    @Test
    void updateBallShouldThrowExceptionIfIdDoesNotExist() {
        //given
        Ball newBall = new Ball();
        newBall.setSize(10);
        newBall.setWeight(22.5F);
        when(repository.findById(1L)).thenReturn(Optional.empty());
        //when
        assertThrows(RuntimeException.class, () -> service.updateBall(1L, newBall));

    }
}

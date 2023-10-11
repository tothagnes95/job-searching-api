package com.example.jobsearchingapi.unittest;

import com.example.jobsearchingapi.repositories.PositionRepository;
import com.example.jobsearchingapi.services.PositionServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PositionServiceTest {
    @Mock
    PositionRepository positionRepository;
    @InjectMocks
    PositionServiceImp positionServiceImp;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }


}

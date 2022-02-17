package com.example.numbergenerator.service;

import com.example.numbergenerator.exceptions.OversizeStorageException;
import com.example.numbergenerator.model.NumberModelImpl;
import com.example.numbergenerator.storage.NumberStorageImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class NumberServiceImplTest {

    private final NumberServiceImpl numberService;

    @MockBean
    NumberStorageImpl numberStorage;

    @Autowired
    NumberServiceImplTest(NumberServiceImpl numberService) {
        this.numberService = numberService;
    }

    @Test
    void getNextNumber() {
        when(numberStorage.getLast()).thenReturn(new NumberModelImpl("A000AA 116 RUS"));
        Assertions.assertEquals("A001AA 116 RUS", numberService.getNextNumber());
        when(numberStorage.getLast()).thenReturn(new NumberModelImpl("A001AA 116 RUS"));
        Assertions.assertEquals("A002AA 116 RUS", numberService.getNextNumber());
        when(numberStorage.getLast()).thenReturn(new NumberModelImpl("A005AH 116 RUS"));
        Assertions.assertEquals("A006AH 116 RUS", numberService.getNextNumber());
        when(numberStorage.getLast()).thenReturn(new NumberModelImpl("A999AH 116 RUS"));
        Assertions.assertEquals("A001AK 116 RUS", numberService.getNextNumber());
        when(numberStorage.getLast()).thenReturn(new NumberModelImpl("A999AY 116 RUS"));
        Assertions.assertEquals("A001BA 116 RUS", numberService.getNextNumber());
        when(numberStorage.getLast()).thenReturn(new NumberModelImpl("A999YA 116 RUS"));
        Assertions.assertEquals("A001YB 116 RUS", numberService.getNextNumber());
        when(numberStorage.getLast()).thenReturn(new NumberModelImpl("A999YY 116 RUS"));
        Assertions.assertEquals("B001AA 116 RUS", numberService.getNextNumber());
    }

    @Test
    @DisplayName("getNextNumber return correct number after last possible number ('Y999YY 116 RUS')")
    void getNextNumberAfterLastPossible() {
        when(numberStorage.getLast()).thenReturn((new NumberModelImpl("Y999YY 116 RUS")));
        Assertions.assertEquals("A001AA 116 RUS", numberService.getNextNumber());
    }

    @Test
    void getNextNumberDuplicate() {
        NumberModelImpl numberModel = new NumberModelImpl("A001AA 116 RUS");
        when(numberStorage.getLast()).thenReturn(numberModel);
        when(numberStorage.isExist(numberModel)).thenReturn(true);
        Assertions.assertEquals("A003AA 116 RUS", numberService.getNextNumber());
    }

    @Test
    void getNextNumberThrowsException() throws OversizeStorageException {
        NumberModelImpl numberModel = new NumberModelImpl("A001AA 116 RUS");
        when(numberStorage.getLast()).thenReturn(numberModel);
        doThrow(OversizeStorageException.class).when(numberStorage).add(numberModel);
        numberService.getNextNumber();
    }

    @Test
    void getRandomNumber() throws OversizeStorageException {
        int numberOfCalls = 100;
        for (int i = 0; i < numberOfCalls; i++) {
            numberService.getRandomNumber();
        }
        verify(numberStorage, times(numberOfCalls)).add(any());
    }


    @Test
    void getRandomNumberThrowsException() throws OversizeStorageException {
        doThrow(OversizeStorageException.class).when(numberStorage).add(new NumberModelImpl("A001AA 116 RUS"));
        numberService.getRandomNumber();
    }

}
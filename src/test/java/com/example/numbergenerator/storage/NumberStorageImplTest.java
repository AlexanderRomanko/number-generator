package com.example.numbergenerator.storage;

import com.example.numbergenerator.exceptions.OversizeStorageException;
import com.example.numbergenerator.model.NumberModelImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class NumberStorageImplTest {

    private final NumberModelImpl defaultNumber = new NumberModelImpl("A000AA 116 RUS");
    private final NumberModelImpl firstNumber = new NumberModelImpl("A001AA 116 RUS");
    private final NumberModelImpl secondNumber = new NumberModelImpl("A002AA 116 RUS");
    private final NumberStorageImpl numberStorage = new NumberStorageImpl();

    @Test
    void add() throws OversizeStorageException {
        NumberStorageImpl mockNumberStorage = mock(NumberStorageImpl.class);
        mockNumberStorage.add(firstNumber);
        verify(mockNumberStorage, times(1)).add(firstNumber);
    }

    @Test
    void isExist() throws OversizeStorageException {
        Assertions.assertFalse(numberStorage.isExist(defaultNumber));
        Assertions.assertFalse(numberStorage.isExist(firstNumber));
        numberStorage.add(firstNumber);
        Assertions.assertFalse(numberStorage.isExist(defaultNumber));
        Assertions.assertTrue(numberStorage.isExist(firstNumber));
        Assertions.assertFalse(numberStorage.isExist(secondNumber));
    }

    @Test
    void getLast() throws OversizeStorageException {
        Assertions.assertEquals(defaultNumber, numberStorage.getLast());
        numberStorage.add(firstNumber);
        Assertions.assertEquals(firstNumber, numberStorage.getLast());
        numberStorage.add(secondNumber);
        Assertions.assertEquals(secondNumber, numberStorage.getLast());
    }
}
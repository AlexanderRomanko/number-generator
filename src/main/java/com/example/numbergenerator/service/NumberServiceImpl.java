package com.example.numbergenerator.service;

import com.example.numbergenerator.model.NumberModelImpl;
import com.example.numbergenerator.storage.NumberStorageImpl;
import com.example.numbergenerator.exceptions.OversizeStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberServiceImpl implements NumberService {

    private final NumberStorageImpl numberStorage;

    @Autowired
    public NumberServiceImpl(NumberStorageImpl numberStorage) {
        this.numberStorage = numberStorage;
    }

    public String getNextNumber() {
        NumberModelImpl number = numberStorage.getLast();
        do {
            number = number.getNext();
        } while (numberStorage.isExist(number));
        try {
            numberStorage.add(number);
        } catch (OversizeStorageException e) {
            e.printStackTrace();
        }
        return number.toString();
    }

    public String getRandomNumber() {
        NumberModelImpl number;
        do {
            number = new NumberModelImpl();
        } while (numberStorage.isExist(number));
        try {
            numberStorage.add(number);
        } catch (OversizeStorageException e) {
            e.printStackTrace();
        }
        return number.toString();
    }

}

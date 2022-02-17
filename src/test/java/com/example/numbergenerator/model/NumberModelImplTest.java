package com.example.numbergenerator.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberModelImplTest {

    @Test
    void getNext() {
        Assertions.assertEquals("A001AA 116 RUS", new NumberModelImpl("A000AA 116 RUS").getNext().toString());
        Assertions.assertEquals("A002AA 116 RUS", new NumberModelImpl("A001AA 116 RUS").getNext().toString());
        Assertions.assertEquals("A006AH 116 RUS", new NumberModelImpl("A005AH 116 RUS").getNext().toString());
        Assertions.assertEquals("A001AK 116 RUS", new NumberModelImpl("A999AH 116 RUS").getNext().toString());
        Assertions.assertEquals("A001BA 116 RUS", new NumberModelImpl("A999AY 116 RUS").getNext().toString());
        Assertions.assertEquals("B001AA 116 RUS", new NumberModelImpl("A999YY 116 RUS").getNext().toString());
        Assertions.assertEquals("A001AA 116 RUS", new NumberModelImpl("Y999YY 116 RUS").getNext().toString());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("A000AA 116 RUS", new NumberModelImpl("A000AA 116 RUS").toString());
        Assertions.assertEquals("A001AA 116 RUS", new NumberModelImpl("A001AA 116 RUS").toString());
        Assertions.assertEquals("A005AH 116 RUS", new NumberModelImpl("A005AH 116 RUS").toString());
        Assertions.assertEquals("A999AH 116 RUS", new NumberModelImpl("A999AH 116 RUS").toString());
        Assertions.assertEquals("Y999YY 116 RUS", new NumberModelImpl("Y999YY 116 RUS").toString());
    }

    @Test
    void testEquals() {
        Assertions.assertEquals(new NumberModelImpl("A000AA 116 RUS"), new NumberModelImpl("A000AA 116 RUS"));
        Assertions.assertEquals(new NumberModelImpl("A001AA 116 RUS"), new NumberModelImpl("A001AA 116 RUS"));
        Assertions.assertEquals(new NumberModelImpl("A005AH 116 RUS"), new NumberModelImpl("A005AH 116 RUS"));
        Assertions.assertEquals(new NumberModelImpl("A999AH 116 RUS"), new NumberModelImpl("A999AH 116 RUS"));
        Assertions.assertEquals(new NumberModelImpl("A001BA 116 RUS"), new NumberModelImpl("A001BA 116 RUS"));
        Assertions.assertEquals(new NumberModelImpl("B001AA 116 RUS"), new NumberModelImpl("B001AA 116 RUS"));
        Assertions.assertEquals(new NumberModelImpl("Y999YY 116 RUS"), new NumberModelImpl("Y999YY 116 RUS"));
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(new NumberModelImpl("A000AA 116 RUS").hashCode(), new NumberModelImpl("A000AA 116 RUS").hashCode());
        Assertions.assertEquals(new NumberModelImpl("A001AA 116 RUS").hashCode(), new NumberModelImpl("A001AA 116 RUS").hashCode());
        Assertions.assertEquals(new NumberModelImpl("A005AH 116 RUS").hashCode(), new NumberModelImpl("A005AH 116 RUS").hashCode());
        Assertions.assertEquals(new NumberModelImpl("A999AH 116 RUS").hashCode(), new NumberModelImpl("A999AH 116 RUS").hashCode());
        Assertions.assertEquals(new NumberModelImpl("A001BA 116 RUS").hashCode(), new NumberModelImpl("A001BA 116 RUS").hashCode());
        Assertions.assertEquals(new NumberModelImpl("B001AA 116 RUS").hashCode(), new NumberModelImpl("B001AA 116 RUS").hashCode());
        Assertions.assertEquals(new NumberModelImpl("Y999YY 116 RUS").hashCode(), new NumberModelImpl("Y999YY 116 RUS").hashCode());
    }
}
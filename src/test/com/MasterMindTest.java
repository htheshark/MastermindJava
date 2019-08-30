package com;

import org.junit.Test;
import com.MasterMind;

import static org.junit.Assert.*;

public class MasterMindTest {
    public int[] scannerIndex;

    @Test
    public void testMain() {
        int[] userNum1 = new int[9];
        int[] userNum2 = new int[9];

        MasterMind.getUserInput();
        assertEquals(userNum1, )
    }

    public int fakeScanner() {

    }
}

public interface IScanner {
    public int nextInt();
}

public class FakeScanner implements IScanner {
    public int index;
    public int[] guesses;

    public FakeScanner() {
        index = 0;
        guesses = new int[1,2,3,4];
    }

    public int nextInt() {
        int input = guesses[index];
        index += 1;
        if(index > 3) { index = 0; }
        return input;
    }
}
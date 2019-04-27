package com.example.nfc;

import java.util.LinkedList;

public class ThrowTracker {
    private LinkedList<Throw> throwList;
    private Throw currentThrow;

    private static ThrowTracker instance;
    public static ThrowTracker instance()
    {
        if (instance == null)
        {
            instance = new ThrowTracker();
        }

        return instance;
    }

    private ThrowTracker()
    {
        this.throwList = new LinkedList<Throw>();
        this.currentThrow = new Throw();
    }

    public void recordThrowPosition()
    {
        currentThrow.recordThrowPosition();

        if (currentThrow.isDone())
        {
            throwList.add(currentThrow);
            currentThrow = new Throw();
        }
    }

    public int getThrowCount()
    {
        return throwList.size();
    }
}

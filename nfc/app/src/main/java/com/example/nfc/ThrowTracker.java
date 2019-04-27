package com.example.nfc;

import android.content.Context;

import java.util.LinkedList;

public class ThrowTracker {
    private LinkedList<Throw> throwList;
    private Throw currentThrow;
    private Context context;

    private static ThrowTracker instance;
    public static ThrowTracker instance(Context context)
    {
        if (instance == null)
        {
            instance = new ThrowTracker(context);
        }

        return instance;
    }

    private ThrowTracker(Context context)
    {
        this.context = context;
        this.throwList = new LinkedList<Throw>();
        this.currentThrow = new Throw(context);
    }

    public void recordThrowPosition()
    {
        currentThrow.recordThrowPosition();

        if (currentThrow.isDone())
        {
            throwList.add(currentThrow);
            currentThrow = new Throw(context);
        }
    }

    public int getThrowCount()
    {
        return throwList.size();
    }

    public float getLastThrowDistance()
    {
        int throwCount = throwList.size();
        if (throwCount > 0)
        {
            return throwList.peekLast().getDistance();
        }
        else
        {
            return 0f;
        }
    }
}

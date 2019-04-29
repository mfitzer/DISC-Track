package com.example.nfc;

import android.content.Context;

import com.example.nfc.Throw.ThrowState;

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
        GPSLocationManager.instance(context);
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
        if (throwList.size() > 0)
        {
            return throwList.peekLast().getDistance();
        }
        else
        {
            return -1f;
        }
    }

    public ThrowState getThrowState()
    {
        if (currentThrow != null)
        {
            return currentThrow.getThrowState();
        }

        return null;
    }
}

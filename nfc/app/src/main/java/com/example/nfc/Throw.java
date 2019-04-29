package com.example.nfc;

import android.content.Context;
import android.location.Location;
import android.widget.Toast;

public class Throw {
    public enum ThrowState { Idle, Started, Ended }
    private ThrowState throwState;

    private Location startPos;
    private Location endPos;

    private Context context;

    private float distance = -1f;

    public Throw(Context context)
    {
        this.context = context;
        throwState = ThrowState.Idle;
    }

    public void recordThrowPosition()
    {
        switch (throwState)
        {
            case Idle: //Throw has not started, initial state of a throw
                throwState = ThrowState.Started;
                setStartPosition();
                break;
            case Started: //Throw has started, end it
                throwState = ThrowState.Ended;
                setEndPosition();
                break;
        }
    }

    public boolean isStarted()
    {
        return throwState.equals(ThrowState.Started);
    }

    public boolean isDone()
    {
        return throwState.equals(ThrowState.Ended);
    }

    public ThrowState getThrowState() { return throwState; }

    //Get start GPS position
    public Location getStartPosition()
    {
        return startPos;
    }

    //Get end GPS position
    public Location getEndPosition()
    {
        return endPos;
    }

    public float getDistance()
    {
        return distance;
    }

    //Set start GPS position
    private void setStartPosition()
    {
        startPos = GPSLocationManager.instance(context).getLocation();
    }

    //Set end GPS position
    private void setEndPosition()
    {
        endPos = GPSLocationManager.instance(context).getLocation();
        calculateThrowDistance();
    }

    private void calculateThrowDistance()
    {
        if (startPos != null)
        {
            if (endPos != null)
            {
                float meterDistance = startPos.distanceTo(endPos);
                distance = meterDistance * 3.28084f; //Distance in feet, 1 m == 3.28084 ft
            }
            else
            {
                Toast.makeText(context, "End position could not be set, could not calculate distance.", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(context, "Start position was not set, could not calculate distance.", Toast.LENGTH_LONG).show();
        }
    }
}

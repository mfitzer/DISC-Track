package com.example.nfc;

public class Throw {
    private enum ThrowState { Idle, Started, Ended }
    private ThrowState throwState;

    public Throw()
    {
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

    public boolean isDone()
    {
        return throwState.equals(ThrowState.Ended);
    }

    //Get start GPS position
    public void getStartPosition()
    {

    }

    //Get end GPS position
    public void getEndPosition()
    {

    }

    //Set start GPS position
    private void setStartPosition()
    {

    }

    //Set end GPS position
    private void setEndPosition()
    {

    }
}

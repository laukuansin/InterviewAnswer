package com.example.carwashapplication.domain;

import android.util.Log;
import java.lang.reflect.Array;

import java.io.Console;

public abstract class Queue<T> implements IQueuable<T>{
    private final int CAPACITY = 100;
    int size;
    int headIndex;
    int endIndex;
    T[] array;

    public Queue(Class<T> type)
    {
        array =   (T[]) Array.newInstance(type, CAPACITY);
        size = 0;
        headIndex = 0;
        endIndex = -1;
    }

    public T[] Enqueue(T t)
    {
        if(size>=CAPACITY)
        {
            Log.d("Error","The queue is full. Please remove some element!");
            return null;
        }
        array[++endIndex] = t;

        size++;

        return array;
    }

    public T Dequeue()
    {
        if(size == 0)
        {
            Log.d("Error","The queue is empty!");
            return null;
        }

        T t = array[headIndex];
        for (int i = headIndex+1; i <= endIndex;i++ )
        {
            array[i - 1] = array[i];
        }
        size--;
        endIndex--;


        return t;

    }

    public T[] getQueue()
    {
        return array;
    }

    public T peek()
    {
        if(size == 0)
        {
            Log.d("Error","The queue is empty!");
            return null;
        }
        return array[headIndex];
    }

    public int getSize()
    {
        return size;
    }

    public abstract boolean checkIsExists(String plateNumber,int ticketNumber,boolean isPlate);
    public abstract Car returnExists(String plateNumber,int ticketNumber,boolean isPlate);

}

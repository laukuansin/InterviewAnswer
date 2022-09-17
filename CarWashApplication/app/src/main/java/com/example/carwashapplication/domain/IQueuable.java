package com.example.carwashapplication.domain;

public interface IQueuable<T> {
    T[] Enqueue(T value);
    T Dequeue();
    T[] getQueue();
    int getSize();
    T peek();
}

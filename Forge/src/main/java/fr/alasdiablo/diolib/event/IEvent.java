package fr.alasdiablo.diolib.event;

public interface IEvent<T> {
    void onEvent(T e);
}

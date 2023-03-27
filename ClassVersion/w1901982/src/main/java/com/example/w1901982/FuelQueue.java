package com.example.w1901982;

public class FuelQueue {
    Passenger passenger=new Passenger();
    int front = 0;
    int rear = -1;
    int size = 50;
    private int count=0;
    private final int capacity=size; /*private int capacity=size;*/
    FuelQueue [] waitingList = new FuelQueue[size];
    public void enQueue(FuelQueue item) {
        if (isFull()) {
            System.out.println("Overflow\nProgram terminated");
            System.exit(-1);
        } else {
            System.out.println("Inserting" + item);
            rear = (rear + 1) % capacity;
            waitingList[rear] = item;
            count++;
        }
    }
    /*customer enter to the waiting list.*/
    public FuelQueue deQueue(){
        if (isEmpty()){
            return null;
        }else {

            FuelQueue customer = waitingList[front];
            System.out.println("Removing" + customer);
            System.out.println("-------------------");
            front = (front + 1) % capacity;
            count--;

            return customer;
        }
    }
    /*customer take from waiting list*/

    public boolean isEmpty(){
        return (size()==0);
    } //check weather waiting list is empty
    public boolean isFull(){
        return (size()== capacity);
    } //check weather waiting list is full
    public int size(){
        return count;
    }
}

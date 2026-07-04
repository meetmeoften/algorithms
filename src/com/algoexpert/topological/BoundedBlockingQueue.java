package com.algoexpert.topological;

import java.util.LinkedList;
import java.util.Queue;

class BoundedBlockingQueue {

    private Queue<Integer> queue;
    private int capacity;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(int element) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.offer(element);
        notifyAll();
    }

    public synchronized int dequeue() throws InterruptedException {
        System.out.println("dequeue 1");
        while (queue.isEmpty()) {
            wait();
        }
        System.out.println("dequeue 2");
        int value = queue.poll();
        notifyAll();
        System.out.println("dequeue 3");
        return value;
    }

    public synchronized int size() {
        return queue.size();
    }

    public static void main(String[] args) {

        BoundedBlockingQueue queue = new BoundedBlockingQueue(2);

        Thread producer = new Thread(() -> {
            try {
                System.out.println("Producer adding 1");
                queue.enqueue(1);
                //Thread.sleep(3000);
                System.out.println("Producer adding 2");
                queue.enqueue(2);
                System.out.println("Producer adding 3 - waits if full");
                queue.enqueue(3);
                System.out.println("Producer added 3");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                //Thread.sleep(1000);
                System.out.println("Consumer removed");
                System.out.println("Consumer removed: " + queue.dequeue());
                Thread.sleep(1000);
                System.out.println("Consumer removed: " + queue.dequeue());
                Thread.sleep(1000);
                System.out.println("Consumer removed: " + queue.dequeue());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        consumer.start();
    }
}

package com.algoexpert;

class CustomStack {

    int[] stack;
    int[] increment;
    int maxSize;
    int size;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
        this.increment = new int[maxSize];
        this.size = 0;
    }

    public void push(int x) {
        if(size < maxSize) {
            stack[size] = x;
            size++;
        }
    }

    public int pop() {
        if(size ==0) return -1;
        size--;
        int res = stack[size] + increment[size];
        if(size > 0) {
            increment[size - 1] += increment[size];
        }
        increment[size] = 0;
        return res;
    }

    public void increment(int k, int val) {
        int index = Math.min(k, size) - 1;
        if(index >= 0) {
            increment[index] += val;
        }

    }

    public static void main(String[] args) {

        CustomStack obj = new CustomStack(3);
        obj.push(1);
        obj.push(2);
        obj.push(3);
        obj.push(4);
        System.out.println(obj.pop());
        obj.increment(5, 100);
        obj.increment(2, 100);
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
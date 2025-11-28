package com.google;

import java.util.TreeMap;

public class StockPriceFluctuation {

    private TreeMap<Integer, Integer> timestampToPrice;
    // TreeMap to keep track of prices and their frequencies
    private TreeMap<Integer, Integer> priceFrequency;
    // Variable to keep track of the latest timestamp
    private int latestTimestamp;

    public StockPriceFluctuation() {
        timestampToPrice = new TreeMap<>();
        priceFrequency = new TreeMap<>();
        latestTimestamp = 0;
    }

    public void update(int timestamp, int price) {
        // Update latest timestamp
        latestTimestamp = Math.max(latestTimestamp, timestamp);

        // If this timestamp already has a recorded price, remove the old price from frequency map
        if (timestampToPrice.containsKey(timestamp)) {
            int oldPrice = timestampToPrice.get(timestamp);
            priceFrequency.put(oldPrice, priceFrequency.get(oldPrice) - 1);

            // If frequency becomes 0, remove the price entry
            if (priceFrequency.get(oldPrice) == 0) {
                priceFrequency.remove(oldPrice);
            }
        }

        // Update the timestamp -> price mapping
        timestampToPrice.put(timestamp, price);

        // Update the price frequency map
        priceFrequency.put(price, priceFrequency.getOrDefault(price, 0) + 1);
    }

    public int current() {
        // Return the price at the latest timestamp
        return timestampToPrice.get(latestTimestamp);
    }

    public int maximum() {
        // Return the highest price (last key in the priceFrequency TreeMap)
        return priceFrequency.lastKey();
    }

    public int minimum() {
        // Return the lowest price (first key in the priceFrequency TreeMap)
        return priceFrequency.firstKey();
    }
}

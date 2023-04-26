package com.lld.ratelimiter3;

import java.time.Instant;

public class RateLimitHelper extends Thread {

	RateLimit rateLimit;

	public RateLimitHelper(String user, RateLimit rateLimitService) {
		super(user);
		this.rateLimit = rateLimitService;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 65; i++) {
			System.out.println("Thread Name - " + getName() + ", Time - " + i + ", rate limit: " + hit(getName(), Instant.now()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("DONE! " + getName());
	}

	public boolean hit(String user, Instant ts) {
		return rateLimit.hit(user, ts);
	}
}

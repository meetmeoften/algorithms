package com.lld.ratelimiter2;


public class RateLimitThrottleListener implements RateLimitListener {

	public RateLimitThrottleListener() {
	}

	@Override
	public void rateLimitThresholdBreached() {
		System.out.println("Received threshold breach callback notification");
	}
}

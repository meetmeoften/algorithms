package com.lld.ratelimiter3;

public class RateLimitService {

	public static void main(String[] args) {
		RateLimitService rateLimiterService = new RateLimitService();
		int limit = 5; // 5 requests per minute
		RateLimit rateLimit = new RateLimit(limit);
		new RateLimitHelper("UserA", rateLimit).start();
		new RateLimitHelper("userB", rateLimit).start();
	}

}
package com.lld.ratelimiter;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		final int MAX_REQUESTS_PER_SEC = 1;

		RateLimiter rateLimiter = new LeakyBucket(MAX_REQUESTS_PER_SEC);
		// RateLimiter rateLimiter = new TokenBucket(MAX_REQUESTS_PER_SEC);
		// RateLimiter rateLimiter = new TokenBucketLazyRefill(MAX_REQUESTS_PER_SEC);
		// RateLimiter rateLimiter = new FixedWindowCounter(MAX_REQUESTS_PER_SEC)

		/*
		 * The following two rate limiter will never end if request rate exceeds the
		 * limit
		 */
		// RateLimiter rateLimiter = new SlidingWindowLog(MAX_REQUESTS_PER_SEC);
		//RateLimiter rateLimiter = new SlidingWindow(MAX_REQUESTS_PER_SEC);

		Thread requestThread = new Thread(() -> {
			sendRequest(rateLimiter, 5, 10, "thread1");
			//sendRequest(rateLimiter, 20, 10);
			//sendRequest(rateLimiter, 50, 25);
			//			sendRequest(rateLimiter, 100, 10);
			//			sendRequest(rateLimiter, 200, 20);
			//			sendRequest(rateLimiter, 250, 25);
			//			sendRequest(rateLimiter, 500, 50);
			//			sendRequest(rateLimiter, 1000, 100);
		});

		Thread requestThread2 = new Thread(() -> {
			sendRequest(rateLimiter, 5, 10, "thread2");
		});


		long startTime = System.currentTimeMillis();
		requestThread.start();
		requestThread2.start();
		requestThread.join();
		requestThread2.join();

		double duration = (System.currentTimeMillis() - startTime) / 1000.0;
		System.out.println( duration + " per second" + " " );
	}

	private static void sendRequest(RateLimiter rateLimiter, int totalCnt, int requestPerSec, String threadName) {
		long startTime = System.currentTimeMillis();

		CountDownLatch doneSignal = new CountDownLatch(totalCnt);
		for (int i = 0; i < totalCnt; i++) {
			try {
				new Thread(() -> {
					while (!rateLimiter.allow()) {
						System.out.println("test1 " +  LocalDateTime.now() + "  " + threadName + " " + Thread.currentThread().getId());
						try {
							TimeUnit.MILLISECONDS.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					doneSignal.countDown();
				}).start();
				//System.out.println("test " +  LocalDateTime.now() + "  " + threadName);
				TimeUnit.MILLISECONDS.sleep(1000 / requestPerSec);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			doneSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double duration = (System.currentTimeMillis() - startTime) / 1000.0;
		System.out.println(totalCnt + " requests processed in " + duration + " seconds. " + "Rate: "
				+ totalCnt / duration + " per second" + " " + threadName);
	}
}

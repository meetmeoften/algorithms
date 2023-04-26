package com.zjava8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureJava {

	public Future<String> calculateAsync() throws InterruptedException, ExecutionException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();

		Executors.newCachedThreadPool().submit(() -> {
			Thread.sleep(500);
			completableFuture.complete("Hello");
			return null;
		});

		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");


		Void combinedFuture = CompletableFuture.allOf(future1, future2, future3).join();
		// combinedFuture.join();



		String combined = Stream.of(future1, future2, future3)
				.map(CompletableFuture::join)
				.collect(Collectors.joining(" "));

		System.out.println(combined);

		CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
			String stringToPrint = "Educative";
			System.out.println("----\nsupplyAsync first future - " + stringToPrint);
			System.out.println("Thread execution - " + Thread.currentThread().getName());
			return stringToPrint;
		});

		CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
			String stringToPrint = "Edpresso";
			System.out.println("----\nsupplyAsync second future - " + stringToPrint);
			System.out.println("Thread execution - " + Thread.currentThread().getName());
			return stringToPrint;
		});

		List<CompletableFuture<String>> completableFutures = Arrays.asList(completableFuture1, completableFuture2);

		CompletableFuture<Void> resultantCf = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));

		CompletableFuture<List<String>> allFutureResults = resultantCf.thenApply(t -> completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList()));

		System.out.println("Result - " + allFutureResults.get());

		return completableFuture;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFutureJava c = new CompletableFutureJava();
		String res = c.calculateAsync().get();
		System.out.println(res);

	}

}

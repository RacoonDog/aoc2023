package io.github.racoondog.aoc.util;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AocInput {
    private static final Path CACHE_DIR = Paths.get(System.getProperty("user.dir"), "advent-of-code", "input-cache");
    private static final String BASE_URL = "https://adventofcode.com/2023/day/%d/input";

    public static List<String> inputOf(int day) {
        Path cachedInput = CACHE_DIR.resolve("day-%d.txt".formatted(day));

        if (Files.exists(cachedInput)) {
            try {
                return Files.readAllLines(cachedInput);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL.formatted(day)))
                    .build();

            String sessionId = System.getenv("aoc-session");
            if (sessionId == null) throw new RuntimeException("You need to set the 'aoc-session' environment variable.");

            HttpCookie sessionCookie = new HttpCookie("session", sessionId);
            sessionCookie.setPath("/");
            sessionCookie.setVersion(0);

            CookieManager cookieManager = new CookieManager();
            cookieManager.getCookieStore().add(URI.create("https://adventofcode.com"), sessionCookie);

            HttpClient client = HttpClient.newBuilder()
                    .cookieHandler(cookieManager)
                    .build();

            List<String> input = new ObjectArrayList<>();

            HttpResponse<Stream<String>> response = client.send(request, HttpResponse.BodyHandlers.ofLines());

            if (response.statusCode() == 400) {
                throw new RuntimeException("Invalid advent of code session id.");
            }

            response.body().forEach(input::add);

            Files.createDirectories(cachedInput.getParent());
            Files.createFile(cachedInput);
            Files.write(cachedInput, input);

            return input;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

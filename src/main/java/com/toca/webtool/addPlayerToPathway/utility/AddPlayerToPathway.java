package com.toca.webtool.addPlayerToPathway.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.toca.webtool.addPlayerToPathway.model.Pathway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AddPlayerToPathway {

    private static String endpoint = "https://dev.tocawebtool.com/api/v2/addPlayerToPathway";
    private static String authentication = "eyJraWQiOiJnUDJodk84UVNOaGpCYl9SZldTVUROZUJmdkcwR0dPeXBibHZVMlpkOWVrIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULmVpaHk3ODlZTW9IVDRzTFRBT01MRkNYaEFBUExwVHcwbzRpLTRKbUtWcUkiLCJpc3MiOiJodHRwczovL2Rldi0zNTE3NzIub2t0YXByZXZpZXcuY29tL29hdXRoMi9hdXNiazU3NXplWFRPaUdpQjBoNyIsImF1ZCI6Imh0dHBzOi8vYXBpLnN0b3JtcGF0aC5jb20vdjEvYXBwbGljYXRpb25zLzJGYmF2Vm9UMjI0aG9EYzBNWDFzUE0iLCJpYXQiOjE2OTI0NjA0MzAsImV4cCI6MTY5MjU0NjgzMCwiY2lkIjoiMG9hYmp4dW52bDNwTHJ2MWwwaDciLCJ1aWQiOiIwMHViazU2NHJlVjQ2UDhWbjBoNyIsInNjcCI6WyJvcGVuaWQiXSwiYXV0aF90aW1lIjoxNjkyNDYwNDMwLCJzdWIiOiJzd2FwbmlsQGsyLWluYy5jb20ifQ.RAKgIcyTRab20prhGZzdao1vUIdgmXEOr6x00Jz5Bq4kMmVFwP3KEqvvNEPAENuGz1C-5ghelCrdyb2RNmF3kztfpZYkMIPyT-WOQ89rSnJoPEaoOgXGXv6ZC-JAkPY-K1mdZmtXglVhskk3hWRqJaq7ZPyXL9dyOErxCi1pq5kz-cua875YEg7AdmyRkQFN49OTnE1dw_edCFNHsOyZyraEDLeTGrdwbQayOElHV7EeyWv8WX8F7SqyCN9F6syDVVdB3UQUpQxXD4NoWc2U7qshsUqcICH8TGrgwEXijhCNPQOSY4EchylKWHd-jpI5vCuFu4z3COKGkxGUHPZWYA";
    private static List<Pathway> failures = new ArrayList<>();

    public static void addPlayer(int record, Pathway pathway) throws IOException {
        Gson gson = new GsonBuilder().create();
        String pathwayString = gson.toJson(pathway);

        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + authentication);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");

        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()){
            byte[] input = pathwayString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        System.out.println("POST Request Response Code: " + responseCode);

        BufferedReader bufferedReader = null;
        if(responseCode != 200){
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        if(bufferedReader != null){
            int i;
            System.out.print(record + ": ");
            while((i = bufferedReader.read()) != -1){
                System.out.print((char) i);
            }
            failures.add(pathway);
        } else {
            System.out.println(record + ". Player Id: " + pathway.getPlayerId() + " with Site Id: " + pathway.getSiteId() + " was added to Pathway Id: " + pathway.getPathwayId() + " with Pathway Name: " + pathway.getPathwayName());
        }
    }

    public static List<Pathway> getFailures() {
        return failures;
    }
}

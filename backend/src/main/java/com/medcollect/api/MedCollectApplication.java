package com.medcollect.api;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MedCollectApplication {
    public static void main(String[] args) throws IOException {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setProjectId(System.getenv("FIREBASE_PROJECT_ID"))
                .build();

        FirebaseApp.initializeApp(options);

        SpringApplication.run(MedCollectApplication.class, args);
    }
}

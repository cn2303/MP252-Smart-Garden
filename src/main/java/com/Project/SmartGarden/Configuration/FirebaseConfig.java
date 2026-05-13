package com.Project.SmartGarden.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initializeFirebase() {
        try {

            InputStream serviceAccount;

            // ===== PRIORITY 1: ENV JSON =====
            String firebaseConfigJson =
                    System.getenv("FIREBASE_CONFIG_JSON");

            if (firebaseConfigJson != null &&
                    !firebaseConfigJson.isBlank()) {

                serviceAccount = new ByteArrayInputStream(
                        firebaseConfigJson.getBytes(StandardCharsets.UTF_8)
                );

                System.out.println(
                        "Using Firebase config from ENV"
                );

            } else {

                // ===== PRIORITY 2: LOCAL FILE =====
                String firebasePath =
                        System.getenv("FIREBASE_CONFIG_PATH");

                if (firebasePath == null || firebasePath.isBlank()) {
                    firebasePath = "./secrets/serviceAccountKey.json";
                }

                serviceAccount =
                        new FileInputStream(firebasePath);

                System.out.println(
                        "Using Firebase config from file: "
                                + firebasePath
                );
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(serviceAccount)
                    )
                    .setDatabaseUrl(
                            "https://mp252-ba91e-default-rtdb.asia-southeast1.firebasedatabase.app/"
                    )
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            System.out.println("Firebase initialized");

        } catch (Exception e) {

            System.out.println(
                    "Error initializing Firebase"
            );

            e.printStackTrace();
        }
    }
}
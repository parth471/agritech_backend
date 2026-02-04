package com.example.agritechbackend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            String firebaseKey = System.getenv("FIREBASE_KEY");

            if (firebaseKey == null || firebaseKey.isBlank()) {
                throw new RuntimeException("FIREBASE_KEY env variable not found");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(
                                    new ByteArrayInputStream(
                                            firebaseKey.getBytes(StandardCharsets.UTF_8)
                                    )
                            )
                    )
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            System.out.println("✅ Firebase initialized");

        } catch (Exception e) {
            throw new RuntimeException("❌ Firebase init failed", e);
        }
    }
}

package com.example.agritechbackend.service;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.example.agritechbackend.dto.IntrusionRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class IntrusionService {

    public String handleIntrusion(IntrusionRequest req) throws Exception {

        Firestore db = FirestoreClient.getFirestore();

        // 1️⃣ Validate device exists
        DocumentSnapshot deviceDoc = db.collection("devices")
                .document(req.getDeviceId())
                .get()
                .get();

        if (!deviceDoc.exists()) {
            return "❌ Unknown device. Registration required.";
        }

        // 2️⃣ Rule Engine (simple for now)
        String severity = "LOW";
        if (Boolean.TRUE.equals(req.getMotionDetected())) {
            severity = "HIGH";
        }

        // 3️⃣ Store Event
        String eventId = UUID.randomUUID().toString();

        Map<String, Object> event = new HashMap<>();
        event.put("eventId", eventId);
        event.put("deviceId", req.getDeviceId());
        event.put("motion", req.getMotionDetected());
        event.put("sensorType", req.getSensorType());
        event.put("severity", severity);
        event.put("timestamp", LocalDateTime.now().toString());

        db.collection("events").document(eventId).set(event);

        // 4️⃣ Return Response
        if ("HIGH".equals(severity)) {
            return "🚨 INTRUSION ALERT! Authorities Notified (simulated)";
        } else {
            return "ℹ️ Motion logged (LOW severity)";
        }
    }
}


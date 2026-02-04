package com.example.agritechbackend.service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.example.agritechbackend.dto.DeviceRegisterRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeviceService {

    public void registerDevice(DeviceRegisterRequest req) throws Exception {

        Firestore db = FirestoreClient.getFirestore();

        Map<String, Object> device = new HashMap<>();
        device.put("deviceId", req.getDeviceId());
        device.put("location", req.getLocation());
        device.put("zone", req.getZone());
        device.put("status", "online");
        device.put("lastSeen", LocalDateTime.now().toString());

        db.collection("devices").document(req.getDeviceId()).set(device);

        System.out.println("📟 DEVICE REGISTERED: " + req.getDeviceId());
    }
}

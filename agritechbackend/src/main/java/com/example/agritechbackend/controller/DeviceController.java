package com.example.agritechbackend.controller;

import com.example.agritechbackend.dto.DeviceRegisterRequest;
import com.example.agritechbackend.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService service;

    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody DeviceRegisterRequest req) throws Exception {
        service.registerDevice(req);
        return "✅ Device Registered";
    }
}

package com.example.agritechbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IntrusionRequest {

    @NotBlank
    private String deviceId;

    @NotNull
    private Boolean motionDetected;

    private String sensorType; // PIR, CAMERA, IR, etc
}


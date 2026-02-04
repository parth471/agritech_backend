package com.example.agritechbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeviceRegisterRequest {

    @NotBlank
    private String deviceId;

    @NotBlank
    private String location;

    @NotBlank
    private String zone;
}

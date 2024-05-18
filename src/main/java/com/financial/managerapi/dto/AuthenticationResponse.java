package com.financial.managerapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.managerapi.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;

    private User user;

}

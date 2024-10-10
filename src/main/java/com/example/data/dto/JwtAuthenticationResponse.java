package com.example.data.dto;


import com.example.data.enitys.Users;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private Users users;
    private boolean mfaEnabled;
    private String secretImageUri;

}
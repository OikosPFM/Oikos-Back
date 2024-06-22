package com.pfm.oikos.jwtpack;

public class AuthenticationResponse {

    private String token;
    /*
     * @JsonProperty("access_token")
     * 
     * private String accessToken;
     * 
     * @JsonProperty("refresh_token")
     * private String refreshToken;
     * 
     * @JsonProperty("message")
     * private String message;
     */

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    /*
     * public String getAccessToken() {
     * return accessToken;
     * }
     * 
     * public String getRefreshToken() {
     * return refreshToken;
     * }
     * 
     * public String getMessage() {
     * return message;
     * }
     */

}

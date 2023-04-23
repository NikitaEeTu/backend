package com.bettingjaws.backend.rest.legacy.request;

public record UserRequest(String first_name, String last_name,
                          String email, String phone_number, String country){
}

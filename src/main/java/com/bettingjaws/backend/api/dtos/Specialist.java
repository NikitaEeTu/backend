package com.bettingjaws.backend.api.dtos;

public record Specialist(String id, String firstName, String lastName,
                         String email, String phoneNumber, String clinicName,
                         String county, SpecialistType type, String website){

}

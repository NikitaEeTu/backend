package com.bettingjaws.backend.api.dtos;

public record SupportGroup(String groupName, String city,
                           SupportGroupType type, String website,
                           String creatorFirstName, String creatorLastName) {
}

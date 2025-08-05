package com.bytebook.userservice.model.user;

public enum Language {
    RU,
    HE,
    EN;

    public static Language fromString(String language) {
        try {
            return valueOf(language.toUpperCase());
        } catch (IllegalArgumentException e) {
            return EN;
        }
    }
}

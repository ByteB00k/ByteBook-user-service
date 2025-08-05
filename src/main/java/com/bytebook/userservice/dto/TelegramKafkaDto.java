package com.bytebook.userservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TelegramKafkaDto {
    private long update_id;
    private TelegramMessage message;

    @Getter @Setter @Builder
    public static class TelegramMessage {
        private TelegramUser from;
        private Integer date;
    }

    @Getter @Setter @Builder
    public static class TelegramUser {
        private Integer id;
        private String first_name;
        private Boolean is_bot;
        private String username;
        private String language_code;
    }
}

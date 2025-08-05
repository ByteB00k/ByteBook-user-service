package com.bytebook.userservice;

import com.bytebook.userservice.dto.TelegramKafkaDto;
import com.bytebook.userservice.dto.UserDto;
import com.bytebook.userservice.model.user.Language;
import com.bytebook.userservice.model.user.Role;
import com.bytebook.userservice.model.user.Status;
import com.bytebook.userservice.model.user.User;

import java.time.Instant;
import java.util.UUID;

public class TestUtils {
    public static String userName = "bennyFast";

    public static TelegramKafkaDto.TelegramUser getValidTelegramUser() {
        return TelegramKafkaDto.TelegramUser.builder()
                .id(894633267)
                .is_bot(false)
                .language_code(Language.EN.toString())
                .username(userName)
                .first_name("Benny")
                .build();
    }

    public static TelegramKafkaDto.TelegramMessage getValidMessage() {
        return TelegramKafkaDto.TelegramMessage.builder()
                .date(1753279549)
                .from(getValidTelegramUser())
                .build();
    }

    public static TelegramKafkaDto getValidTelegramKafkaDto() {
        return TelegramKafkaDto.builder()
                .update_id(358092760)
                .message(getValidMessage())
                .build();
    }

    public static String getValidTelegramMessage() {
        return String.format("""
                {
                	"update_id": 358092760,
                	"message": {
                		"message_id": 27,
                		"from": {
                			"id": 894633267,
                			"first_name": "Benny",
                			"is_bot": false,
                			"username": "%s",
                			"language_code": "en"
                		},
                		"date": 1753279549,
                		"chat": {
                			"id": 894633267,
                			"type": "private",
                			"first_name": "Benny",
                			"username": "%s"
                		},
                		"text": "Hello 1"
                	}
                }""", userName, userName);
    }

    public static User getValidUser() {
        return User.builder()
                .guid(UUID.randomUUID())
                .username(userName)
                .role(Role.USER)
                .status(Status.ACTIVE)
                .first_name("Benny")
                .language(Language.EN)
                .timezone(0)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public static UserDto getValidUserDto() {
        return UserDto.builder()
                .guid(String.valueOf(UUID.randomUUID()))
                .username(userName)
                .role(Role.USER)
                .status(Status.ACTIVE)
                .firstName("Benny")
                .language(Language.EN)
                .timezone(0)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }
}

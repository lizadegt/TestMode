package ru.netology;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserCreation {
    private String login;
    private String password;
    private String status;
}

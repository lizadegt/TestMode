package ru.netology;

import ru.netology.DataGenerator;
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

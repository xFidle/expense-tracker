package com.example.expenseapi.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.expenseapi.pojo.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceIT {
    private final UserServiceImpl underTest;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceIT(UserServiceImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    void testGetAllUsers() {
        List<User> users = underTest.getAll();
        assertThat(users.size()).isEqualTo(3);
        users.forEach(u -> assertThat(u).isNotNull());
    }

    @Test
    void testGetUserByPresentId() {
        User user = underTest.get(1L);
        assertThat(user).isNotNull();
    }

    @Test
    void testGetUserByNotPresentId() {
        User user = underTest.get(1000L);
        assertThat(user).isNull();
    }

    @Test
    void testGetUserByPresentEmail() {
        Optional<User> user = underTest.findByEmail("herkules1@gmail.com");
        assertThat(user).isPresent();
        assertThat(user.get().getEmail()).isEqualTo("herkules1@gmail.com");

    }

    @Test
    void testGetUserByNotPresentEmail() {
        Optional<User> user = underTest.findByEmail("herkules4@gmail.com");
        assertThat(user).isNotPresent();
    }

    @Transactional
    @Test
    void testInsertNewUser() {
        User newUser = new User("Herkules4", "Herkules4", "herkules4@gmail.com", passwordEncoder.encode("456"));
        underTest.save(newUser);
        User retrievedUser = underTest.get(4L);
        assertThat(retrievedUser.getName()).isEqualTo("Herkules4");
        assertThat(retrievedUser.getSurname()).isEqualTo("Herkules4");
        assertThat(retrievedUser.getEmail()).isEqualTo("herkules4@gmail.com");
        assertThat(retrievedUser.getCreationDate().getMonth()).isEqualTo(LocalDate.now().getMonth());
    }
}

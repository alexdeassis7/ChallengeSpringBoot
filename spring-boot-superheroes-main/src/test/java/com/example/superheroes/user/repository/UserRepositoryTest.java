package com.example.superheroes.user.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.superheroes.user.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckWhenUserEmailExists() {
        // give
        String email = "alex@gmail.com";
        UserEntity user = new UserEntity(email, "21398732478");

        underTest.save(user);

        // when
        boolean expected = underTest.selectExistsEmail(email);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckWhenUserEmailDoesNotExists() {
        // given
        String email = "ale@gmail.com";

        // when
        boolean expected = underTest.selectExistsEmail(email);

        // then
        assertThat(expected).isFalse();
    }
}

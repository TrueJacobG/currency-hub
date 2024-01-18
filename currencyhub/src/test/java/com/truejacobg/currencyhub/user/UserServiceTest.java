package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.user.dto.DeleteUserResponseDTO;
import com.truejacobg.currencyhub.user.dto.GetUserResponseDTO;
import com.truejacobg.currencyhub.user.dto.UpdateUserResponseDTO;
import com.truejacobg.currencyhub.user.dto.UserDTO;
import com.truejacobg.currencyhub.user.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void prepareData() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void prepareService() {
        userService = new UserService(userRepository);
    }

    @Test
    void createUserCorrectly() {
        // given
        String name = "testName";
        String firstName = "testFirstName";
        String surName = "testSurName";
        String authCode = "testAuthCode123";
        String email = "testEmail@testEmail.testEmail";
        LocalDateTime creationDate = LocalDateTime.now();

        UserDTO userDTO = new UserDTO(name, firstName, surName, authCode, email, creationDate);

        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setFirstName(firstName);
        userEntity.setSurname(surName);
        userEntity.setAuthCode(authCode);
        userEntity.setEmail(email);
        userEntity.setCreationDate(creationDate);

        given(userRepository.save(userEntity)).willReturn(userEntity);
        given(userRepository.findByEmail(email)).willReturn(Optional.of(userEntity));

        // when
        userService.createUser(userDTO);

        // then
        GetUserResponseDTO getUserResponseDTO = userService.getUser(email);
        UserDTO actualUser = getUserResponseDTO.getUser();

        assertEquals(userDTO.getName(), actualUser.getName());
        assertEquals(userDTO.getFirstname(), actualUser.getFirstname());
        assertEquals(userDTO.getSurname(), actualUser.getSurname());
        assertEquals(userDTO.getEmail(), actualUser.getEmail());
        // i ten test pozwolil nam znalesc bugga bo ponizsza linijka nie przechodzila :DDD
        assertEquals(userDTO.getCreationDate(), actualUser.getCreationDate());
    }

    @Test
    void getUser() {
        // given
        String email = "testEmail@testEmail.testEmail";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        given(userRepository.findByEmail(email)).willReturn(Optional.of(userEntity));

        // when
        GetUserResponseDTO result = userService.getUser(email);

        // then
        assertEquals(email, result.getUser().getEmail());

    }

    @Test
    void updateUser() {
        // given
        String email = "testEmail@testEmail.testEmail";
        UserDTO userDTO = new UserDTO("testName", "testFirstName", "testSurName", "testAuthCode123", email, LocalDateTime.now());
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        given(userRepository.findByEmail(email)).willReturn(Optional.of(userEntity));
        given(userRepository.save(userEntity)).willReturn(userEntity);

        // when
        UpdateUserResponseDTO result = userService.updateUser(userDTO, email);

        // then
        assertEquals("The userEntity has been updated successfully.", result.getMessage());

    }

    @Test
    void deleteUser() {
        // given
        String email = "testEmail@testEmail.testEmail";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        given(userRepository.findByEmail(email)).willReturn(Optional.of(userEntity));

        // when
        DeleteUserResponseDTO result = userService.deleteUser(email);

        // then
        assertEquals("User has been deleted", result.getMessage());

    }

    @Test
    void getUserPasswordByName() {
        // given
        String name = "testName";
        String authCode = "testAuthCode123";
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setAuthCode(authCode);

        given(userRepository.findByName(name)).willReturn(Optional.of(userEntity));

        // when
        String result = userService.getUserPasswordByName(name);

        // then
        assertEquals(authCode, result);


    }
}
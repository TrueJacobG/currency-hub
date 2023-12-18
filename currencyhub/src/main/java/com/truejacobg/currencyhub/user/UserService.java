package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.exception.UserWithThatEmailDoesNotExistException;
import com.truejacobg.currencyhub.exception.UserWithThatNameOrEmailExistException;
import com.truejacobg.currencyhub.user.dto.*;
import com.truejacobg.currencyhub.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public CreateUserResponseDTO createUser(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity(null, userDTO.getName(),userDTO.getFirstname(), userDTO.getSurname(), userDTO.getAuthCode(), userDTO.getEmail(), LocalDateTime.now());
        if (userRepository.findByNameOrEmail(userEntity.getName(), userEntity.getEmail()).isPresent()) {
            throw new UserWithThatNameOrEmailExistException(String.format("User with name: %s or email: %s already exist!", userEntity.getName(), userEntity.getEmail()));
        } else {
            userRepository.save(userEntity);
            return new CreateUserResponseDTO("ok", HttpStatus.ACCEPTED);
        }
    }

    public GetUserResponseDTO getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserWithThatEmailDoesNotExistException(String.format("User with email: %s does not exist!", email)));
        UserDTO userDTO = new UserDTO(userEntity.getName(), userEntity.getFirstName(), userEntity.getSurname(), null, userEntity.getEmail(), userEntity.getCreationDate());
        return new GetUserResponseDTO(userDTO, "has been found", HttpStatus.OK);
    }

    public UpdateUserResponseDTO updateUser(UserDTO userDTO, String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserWithThatEmailDoesNotExistException("There is no such user"));

        userEntity.setFirstName(userDTO.getFirstname());
        userEntity.setName(userDTO.getName());
        userEntity.setSurname(userDTO.getSurname());
        userEntity.setEmail(userDTO.getEmail());

        userRepository.save(userEntity);

        return new UpdateUserResponseDTO("The userEntity has been updated successfully.", HttpStatus.OK, userDTO.toString());
    }

    public DeleteUserResponseDTO deleteUser(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UserWithThatEmailDoesNotExistException(String.format("User with email: %s does not exist in database!", email)));
        userRepository.deleteByEmail(email);
        return new DeleteUserResponseDTO("User has been deleted", HttpStatus.OK);
    }

    public String getUserPasswordByName(String name) {
        UserEntity userEntity = userRepository.findByName(name).orElseThrow(() -> new UserWithThatEmailDoesNotExistException("There is no such user"));
        return userEntity.getAuthCode();
    }
}

package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.security.AuthenticationFilter;
import com.truejacobg.currencyhub.user.dto.CreateUserResponseDTO;
import com.truejacobg.currencyhub.user.dto.UpdateUserResponseDTO;
import com.truejacobg.currencyhub.user.dto.UserDTO;
import com.truejacobg.currencyhub.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
// prawdziwy backend metody walidacje, zabawa z bazą, prawdziwe mięso, tutaj bedzie cały kod
// musi implementować repo, 90% będzie tutaj, autoryzacje, zabezpieczenia, uruchomienia, rzucanie błędów
//

@AllArgsConstructor // sprawia ze nie musimy pisac konstruktora na wszystkie pola
@Service
public class UserService {
    private UserRepository userRepository;
    private static Logger logger = LogManager.getLogger(AuthenticationFilter.class.getName());

    public CreateUserResponseDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity(null, userDTO.getName(), userDTO.getAuthCode(), userDTO.getSurname(), userDTO.getEmail(), LocalDateTime.now());
        userRepository.save(userEntity);

        return new CreateUserResponseDTO("ok", HttpStatus.ACCEPTED);
    }

    public CreateUserResponseDTO getUser(String userEmail) {
        UserEntity userEntity = userRepository.findByEmail(userEmail).orElseThrow(() -> new NoSuchElementException("There is no such user"));
        return new CreateUserResponseDTO("has been found", HttpStatus.OK);
    }

    public String getUserPasswordByName(String name) {
        UserEntity userEntity = userRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("There is no such user"));
        return userEntity.getAuthCode();
    }

    public UpdateUserResponseDTO updateUser(UserDTO userDTO, String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("There is no such user"));

        userEntity.setName(userDTO.getName());
        userEntity.setSurname(userDTO.getSurname());
        userEntity.setEmail(userDTO.getEmail());

        userRepository.save(userEntity);

        return new UpdateUserResponseDTO("The userEntity has been updated successfully.", HttpStatus.OK, "All good");
    }

    public CreateUserResponseDTO deleteUser(String email) {
        userRepository.deleteByEmail(email); // TODO: dodać wyjątek
        return new CreateUserResponseDTO("User has been deleted", HttpStatus.OK);
    }
}

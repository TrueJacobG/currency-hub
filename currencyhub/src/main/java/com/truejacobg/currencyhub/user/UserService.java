package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.user.dto.CreateUserResponseDTO;
import com.truejacobg.currencyhub.user.dto.UserDTO;
import com.truejacobg.currencyhub.user.entity.UserEntity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
// prawdziwy backend metody walidacje, zabawa z bazą, prawdziwe mięso, tutaj bedzie cały kod
// musi implementować repo, 90% będzie tutaj, autoryzacje, zabezpieczenia, uruchomienia, rzucanie błędów
//

@AllArgsConstructor // sprawia ze nie musimy pisac konstruktora na wszystkie pola
@Service
public class UserService {
    private UserRepository userRepository;

    public CreateUserResponseDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity(null,userDTO.getFirstName(), userDTO.getName(), userDTO.getAuthCode(), userDTO.getSurname(), userDTO.getEmail(), LocalDateTime.now());
        userRepository.save(userEntity);

        return new CreateUserResponseDTO("ok", HttpStatus.ACCEPTED);
    }

    public CreateUserResponseDTO getUser(String userEmail) {
        UserEntity user = userRepository.findByEmail(userEmail);
        if (user != null) {
            // found
            return new CreateUserResponseDTO("has been found", HttpStatus.OK);
        } else {
            return new CreateUserResponseDTO("has not been found", HttpStatus.NOT_FOUND);
        }
    }

    public CreateUserResponseDTO updateUser(UserDTO userDTO, String email) {
        // is he in database?
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            return new CreateUserResponseDTO("User has not been in db", HttpStatus.NOT_FOUND);
        } else {
            user.setFirstName(userDTO.getFirstName());
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setEmail(userDTO.getEmail());

            userRepository.save(user);

            return new CreateUserResponseDTO("The user has been updated successfully.", HttpStatus.OK);

        }
    }

      public CreateUserResponseDTO deleteUser(String email) {
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findByEmail(email));
        if(!user.isPresent()){
            return new CreateUserResponseDTO("User has not been in db", HttpStatus.NOT_FOUND);
        }else {
            userRepository.deleteByEmail(email);
            return new CreateUserResponseDTO("User has been deleted", HttpStatus.OK);
        }
    }

}

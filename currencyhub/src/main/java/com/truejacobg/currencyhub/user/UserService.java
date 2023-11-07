package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.user.dto.CreateUserResponseDTO;
import com.truejacobg.currencyhub.user.dto.UserDTO;
import com.truejacobg.currencyhub.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
// prawdziwy backend metody walidacje, zabawa z bazą, prawdziwe mięso, tutaj bedzie cały kod
// musi implementować repo, 90% będzie tutaj, autoryzacje, zabezpieczenia, uruchomienia, rzucanie błędów
//

@AllArgsConstructor // sprawia ze nie musimy pisac konstruktora na wszystkie pola
@Service
public class UserService {
    private UserRepository userRepository;

    public CreateUserResponseDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity(null, userDTO.getName(), userDTO.getAuthCode(), userDTO.getSurname(), userDTO.getEmail(), LocalDateTime.now());
        userRepository.save(userEntity);

        return new CreateUserResponseDTO("ok", HttpStatus.ACCEPTED);
    }

    public CreateUserResponseDTO getUser(String userEmail) {
        UserEntity userEntity = userRepository.findByEmail(userEmail);
        if (userEntity != null) {
            // found
            return new CreateUserResponseDTO("has been found", HttpStatus.OK);
        } else {
            return new CreateUserResponseDTO("has not been found", HttpStatus.NOT_FOUND);
        }
    }

    public String getUserPasswordByName(String name){
        UserEntity userEntity =  userRepository.findByName(name);
        return userEntity.getAuthCode();
//        if(userEntity != null){
//            return userEntity.getAuthCode();
//        }
//        else{
//            //TODO:: ass throw error
//        }
    }

    public CreateUserResponseDTO updateUser(UserDTO userDTO, String email) {
        // is he in database?
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            return new CreateUserResponseDTO("User has not been in db", HttpStatus.NOT_FOUND);
        } else {
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setEmail(userDTO.getEmail());

            userRepository.save(user);

            return new CreateUserResponseDTO("The user has been updated successfully.", HttpStatus.OK);

        }
    }

    public CreateUserResponseDTO deleteUser(String email) {
        userRepository.deleteByEmail(email); // TODO: dodać wyjątek
        return new CreateUserResponseDTO("User has been deleted", HttpStatus.OK);
    }
}

package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.user.dto.CreateUserResponseDTO;
import com.truejacobg.currencyhub.user.dto.UserDTO;
import com.truejacobg.currencyhub.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
// prawdziwy backend metody walidacje, zabawa z bazą, prawdziwe mięso, tutaj bedzie cały kod
// musi implementować repo, 90% będzie tutaj, autoryzacje, zabezpieczenia, uruchomienia, rzucanie błędów
//

@AllArgsConstructor // sprawia ze nie musimy pisac konstruktora na wszystkie pola
@Service
public class UserService {
    private UserRepository userRepository;

    public CreateUserResponseDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity("123124", userDTO.getName(), userDTO.getAuthCode(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getCreationDate());
        userRepository.save(userEntity);

        return new CreateUserResponseDTO("ok", HttpStatus.ACCEPTED);
    }


}

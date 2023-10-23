package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.user.dto.CreateUserResponseDTO;
import com.truejacobg.currencyhub.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// trzyma konkretny endpoint, zarzadzaja co sie kiedy ma zadziac, ktore metody
// jaki typ zwracany itp. te klasy słóżą jaki link ma się zając czym. Kolejność metod staramy sie trzymać CRUDowo
@AllArgsConstructor
@RequestMapping(value = "/api/v1/user")
@RestController
public class UserController {
    private UserService userService;

    @PostMapping("/")
    ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody UserDTO userDTO) {
        // responseEntity -> menedżer odsyłań
        CreateUserResponseDTO response = userService.createUser(userDTO);
        return ResponseEntity.ok(response);
    }

}

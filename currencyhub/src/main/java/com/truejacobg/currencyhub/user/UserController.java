package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.user.dto.CreateUserResponseDTO;
import com.truejacobg.currencyhub.user.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// trzyma konkretny endpoint, zarzadzaja co sie kiedy ma zadziac, ktore metody
// jaki typ zwracany itp. te klasy słóżą jaki link ma się zając czym. Kolejność metod staramy sie trzymać CRUDowo

@AllArgsConstructor
@RequestMapping(value = "/api/v1/user")
@RestController
public class UserController {
    private UserService userService;

    @PostMapping("/")
    ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        // responseEntity -> menedżer odsyłań
        CreateUserResponseDTO response = userService.createUser(userDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{email}")
    ResponseEntity<CreateUserResponseDTO> getUser(@PathVariable String email) {
        CreateUserResponseDTO response = userService.getUser(email);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{email}")
    ResponseEntity<CreateUserResponseDTO> upadateUser(@RequestBody UserDTO userDTO, @PathVariable String email) {
        CreateUserResponseDTO response = userService.updateUser(userDTO, email);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{email}")
    ResponseEntity<CreateUserResponseDTO> deleteUser(@PathVariable String email) {
        CreateUserResponseDTO response = userService.deleteUser(email);
        return ResponseEntity.ok(response);
    }
}

package com.truejacobg.currencyhub.user;

import com.truejacobg.currencyhub.user.dto.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@AllArgsConstructor
@RequestMapping(value = "/api/v1/user")
@RestController
public class UserController {
    private UserService userService;

    @PostMapping("/")
    ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        CreateUserResponseDTO response = userService.createUser(userDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{email}")
    ResponseEntity<GetUserResponseDTO> getUser(@PathVariable String email) {
        GetUserResponseDTO response = userService.getUser(email);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{email}")
    ResponseEntity<UpdateUserResponseDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable String email) {
        UpdateUserResponseDTO response = userService.updateUser(userDTO, email);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{email}")
    ResponseEntity<DeleteUserResponseDTO> deleteUser(@PathVariable String email) {
        DeleteUserResponseDTO response = userService.deleteUser(email);
        return ResponseEntity.ok(response);
    }
}

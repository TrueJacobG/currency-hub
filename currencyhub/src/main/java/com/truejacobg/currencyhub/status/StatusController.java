package com.truejacobg.currencyhub.status;

import com.truejacobg.currencyhub.status.dto.StatusDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
public class StatusController {
    private StatusService statusService;

    @GetMapping("/status")
    ResponseEntity<StatusDTO> getServerStatus() {
        return ResponseEntity.ok(new StatusDTO("All works fine!", HttpStatus.OK));
    }
}

package me.fmesnata.tictactoe.authentication;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationApi {

    @GetMapping("/me")
    public HttpEntity<Void> checkAuthentication(Principal principal) {
        return principal == null ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }
}

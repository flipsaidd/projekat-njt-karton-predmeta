package com.fgc.kartonpredmeta.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO implements DomainDTO{

    @NotBlank(message = "Korisničko ime je obavezno")
    private String username;

    @NotBlank(message = "Lozinka je obavezna")
    private String password;
}

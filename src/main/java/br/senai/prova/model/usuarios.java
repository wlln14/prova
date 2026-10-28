package br.senai.prova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank(message = "Informe o nome")
    private String nome;

    @NotBlank(message = "Informe o login")
    private String login;

    // Em produção, nunca armazene senha em texto puro: use BCrypt.
    @NotBlank(message = "Informe a senha")
    private String senha;
}

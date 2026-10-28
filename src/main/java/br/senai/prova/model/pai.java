package br.senai.prova.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "responsaveis")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class pai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPai;

    @NotBlank(message = "Informe o nome")
    private String nome;

    @NotBlank(message = "Informe o CPF")
    private String cpf;

    @NotBlank(message = "Informe o telefone")
    private String telefone;


    @JsonIgnore
    @OneToMany(mappedBy = "pai")
    private List<paciente> pacientes;
}

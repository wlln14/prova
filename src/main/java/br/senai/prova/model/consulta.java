package br.senai.prova.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "consultas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;

    @NotNull(message = "Informe a data")
    private LocalDate data;

    @NotNull(message = "Informe a hora")
    private LocalTime hora;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_paciente", nullable = false)
    private paciente paciente;
}

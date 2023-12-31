package com.feverdunk.site.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "desempenhos")
public class Desempenho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desempenho_id")
    private Long id;

    @Column(name = "desempenho_data")
    @NotNull
    private LocalDate data;

    @Column(name = "pontos")
    @NotNull
    @Min(value = 0)
    private int pontos;

    @Column(name = "rebotes")
    @NotNull
    @Min(value = 0)
    private int rebotes;

    @Column(name = "assistencias")
    @NotNull
    @Min(value = 0)
    private int assistencias;

    @Column(name = "min_jogados")
    @NotNull
    @Min(value = 0)
    private int minJogados;

    @ManyToOne
    @JoinColumn(name = "desempenho_id_jogador")
    private Jogador jogador;
}

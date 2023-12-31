package com.feverdunk.site.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Managers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    @Column(name = "manager_nome")
    @NotBlank
    private String nome;

    @Column(name = "email", unique = true)
    @NotBlank
    @Email
    private String email;

    @Column(name = "manager_senha")
    @NotBlank
    private String senha;

    @Column(name = "dinheiro")
    @NotNull
    private int dinheiro;

    @Column(name = "premium")
    @NotNull
    private boolean premium;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id_time", referencedColumnName = "time_id")
    private Time time;

    @JsonIgnore
    @OneToMany(mappedBy = "manager")
    private List<Liga> liga;


    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "perfil", nullable = false)
    private Set<Integer> perfis;

    public Set<Perfil> getPerfis(){
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Perfil p){
        this.perfis.add(p.getCodigo());
    }
}

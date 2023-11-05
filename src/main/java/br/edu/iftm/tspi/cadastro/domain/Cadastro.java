package br.edu.iftm.tspi.cadastro.domain;

import lombok.Data;

@Data
public class Cadastro {
    private int id;
    private String nome, email, celular;
    
    public Cadastro(int id, String nome, String email, String celular) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
    }

    public Cadastro(){}
}

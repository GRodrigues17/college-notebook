package br.com.collegenotebook.model;

import java.io.Serializable;

/**
 * Created by GRodrigues17 on 27/09/2016.
 */
public class Materia implements Serializable{
    private static final long serialVersionUID = 1L;
    private long id;
    private String nome;
    private String professor;
    private String pasta;

public Materia(){

}

    public Materia(long id, String nome, String professor,String pasta) {
        this.id = id;
        this.nome = nome;
        this.professor = professor;
        this.pasta = pasta;
    }

    public Materia(String nome, String professor,String pasta) {
        this.nome = nome;
        this.professor = professor;
    }

    @Override
    public String toString() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getPasta() {
        return pasta;
    }

    public void setPasta(String pasta) {
        this.pasta = pasta;
    }


}

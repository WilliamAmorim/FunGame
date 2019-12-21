/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author willi
 */
public class PacotesBean {
    private StringProperty disciplina;
    private StringProperty assunto;
    private StringProperty professor;
    private StringProperty data;
    private StringProperty numeroQuestoes;

    public StringProperty getNumeroQuestoes() {
        return numeroQuestoes;
    }

    public void setNumeroQuestoes(StringProperty numeroQuestoes) {
        this.numeroQuestoes = numeroQuestoes;
    }
    
    public PacotesBean(String disciplina,String assunto,String professor,String data,String numeroQuestoes){
        this.disciplina = new SimpleStringProperty(disciplina);
        this.assunto = new SimpleStringProperty(assunto);
        this.professor = new SimpleStringProperty(professor);
        this.data = new SimpleStringProperty(data);
        this.numeroQuestoes = new SimpleStringProperty(numeroQuestoes);
    }
    public StringProperty getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(StringProperty disciplina) {
        this.disciplina = disciplina;
    }

    public StringProperty getAssunto() {
        return assunto;
    }

    public void setAssunto(StringProperty assunto) {
        this.assunto = assunto;
    }

    public StringProperty getProfessor() {
        return professor;
    }

    public void setProfessor(StringProperty professor) {
        this.professor = professor;
    }

    public StringProperty getData() {
        return data;
    }

    public void setData(StringProperty data) {
        this.data = data;
    }
    
}

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
public class DesempenhoAlunoBean {
    private StringProperty aluno;

    public StringProperty getAluno() {
        return aluno;
    }

    public void setAluno(StringProperty aluno) {
        this.aluno = aluno;
    }
    private StringProperty professor; 
    private StringProperty pacote;
    private StringProperty date;
    private StringProperty pontuacao;
    public DesempenhoAlunoBean(String professor,String pacote,String date,String pontuacao){
        this.professor = new SimpleStringProperty(professor);
        this.pacote = new SimpleStringProperty(pacote);
        this.date = new SimpleStringProperty(date);
        this.pontuacao = new SimpleStringProperty(pontuacao);
        
          
    }
    
    public DesempenhoAlunoBean(String aluno,String professor,String pacote,String date,String pontuacao){
        this.aluno = new SimpleStringProperty(aluno);
        this.professor = new SimpleStringProperty(professor);
        this.pacote = new SimpleStringProperty(pacote);
        this.date = new SimpleStringProperty(date);
        this.pontuacao = new SimpleStringProperty(pontuacao);
        
          
    }

    public StringProperty getProfessor() {
        return professor;
    }

    public void setProfessor(StringProperty professor) {
        this.professor = professor;
    }

    public StringProperty getPacote() {
        return pacote;
    }

    public void setPacote(StringProperty pacote) {
        this.pacote = pacote;
    }

    public StringProperty getDate() {
        return date;
    }

    public void setDate(StringProperty date) {
        this.date = date;
    }

    public StringProperty getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(StringProperty pontuacao) {
        this.pontuacao = pontuacao;
    }
    
}

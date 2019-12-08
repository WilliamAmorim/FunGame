/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.sql;

import java.util.ArrayList;

/**
 *
 * @author willi
 */
public class teste {
    public static void main(String[] args){
        Sql novo = new Sql();
        boolean falso = false;
        ArrayList nulo = new ArrayList();
        nulo.add("virginia");
        nulo.add("123");
        ArrayList array = new ArrayList();
        //novo.executeQuery("INSERT INTO `aluno`(`nome_aluno`, `senha`, `serie`, `turma`, `turno`, `status`) VALUES (?,?,?,?,?,?)",nulo);
        String[] retorno = {"id_professor","nome_professor","senha","disciplina"}; 
        array = novo.executeQuery("SELECT * FROM `professor` WHERE nome_professor = ? AND senha =  ?", nulo, retorno);
        
        for (int i = 0; i <= array.size()-1; i++) {
            String a = array.get(i).toString();
            String b = a.replace("[", "").replace("]", "");
            
          
            String[] tokens = b.split(",");
            System.out.println(tokens[0]+" - "+tokens[1]+" - "+tokens[2]+" - "+tokens[3]);
        }
//        String a = fora.get(0).toString();
//        String b = a.replace("[", "");
//        String[] tokens = b.split(",");
//        System.out.println(tokens[0]);
    }
}

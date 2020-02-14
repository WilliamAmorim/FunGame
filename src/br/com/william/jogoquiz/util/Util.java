/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.util;

import br.com.william.jogoquiz.log.DiretorioLog;
import br.com.william.jogoquiz.sql.Sql;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import javafx.scene.control.ComboBox;



/**
 *
 * @author willi
 */
public class Util {
    
    public static String nome_log() {        
        DiretorioLog diretorio = new DiretorioLog();
        File Diretorio = new File(diretorio.getDiretoriolog());
        if(Diretorio.exists()){
            try {            
                FileInputStream arquivo = arquivo = new FileInputStream(diretorio.getDiretoriolog());;            
                InputStreamReader in = new InputStreamReader(arquivo);
                BufferedReader br = new BufferedReader(in);
                try {
                    String a = br.readLine();
                } catch (IOException ex) {

                }
                try {
                    return br.readLine().substring(4);

                } catch (IOException ex) {

                }
            } catch (FileNotFoundException ex) {

            } finally {

            }
        }
        System.out.println("retornando nada");
        return "nome";

    }
    
    public static String turma(){
        Sql n = new Sql();
        String[] retorno = {"serie","turma"};
        ArrayList values = new ArrayList();
        values.add(nome_log());
        ArrayList v = new ArrayList();
        v = n.executeQuery("SELECT  `serie`, `turma` FROM `aluno` WHERE nome_aluno = ?", values, retorno);
        
        String a = v.get(0).toString();
        String b = a.replace("[", "").replace("]", "");
        String[] tokens = b.split(",");
        System.out.println(v);        
        return tokens[0]+""+tokens[1];
        
    }
    
    public static int ordemCrescente(ArrayList a,int index){
        
        ArrayList b = new ArrayList();
        int posicao =  a.size();
        float maior = 0;     
        //try{
            System.out.println("Size:"+a.size());
            for (int i = 0; i <= a.size()-1; i++) {                    
                if(Float.parseFloat((String) a.get(index)) >= Float.parseFloat((String) a.get(i))){
                    maior = Float.parseFloat((String) a.get(index));
                    posicao--;
                }else{
                    maior = Float.parseFloat((String) a.get(i));
                    //posicao--;
                }
            }                        
            System.out.println("Maior Valor "+maior+" | Posição: "+(posicao));
       // }catch(Exception ex){
       //     return  a.size();
       // }
        return posicao;
                
    }
    
    public static String dividirString(String nome){
        
        String[] tokens = nome.split(":");
        return tokens[0]+":";
        
    }
    
    public static String[] dividirResultado(String resultado){
        if(!resultado.equals("N")){
            String[] tokens = resultado.split("-");
            String[] tokens2 = tokens[2].split(":");
            String[] t = {tokens[1],tokens2[1]};
            return t;
        }else{
            String[] t2 = {"","0"};
            return t2;
        }
        
    }
    public static void listarDisciplinas(ComboBox d){
        String[] disciplinas = {
            "Português",
            "Matemática",
            "Biologia",
            "Física",
            "Química",
            "Filosofia", 
            "Geografia", 
            "História", 
            "Sociologia", 
            "Educação Física", 
            "Artes", 
            "Língua Estrangeira",
            "Literatura"         
        };
        
        for (int i = 0; i < disciplinas.length; i++) {
            d.getItems().add(disciplinas[i]);
        }
    }
    
    public static boolean verificarDesconectado(String variavel){
        String[] resultado = variavel.split("DESCONECTADO");         
        if(resultado.length > 1){            
            return true;
        }else{
            return false;
        }
        
    }
    public static void main(String[] args){
//        ArrayList Array = new ArrayList();        
//        Array.add("8");
//        Array.add("5");
//        Array.add("4");
//        Array.add("11");
//        Array.add("15");
//        Array.add("15");
//        Array.add("17");
//        System.out.println("Valor Retornado: "+ordemCrescente(Array,2));
        //System.out.println(turma());
        //String[] a = dividirResultado("");
        //System.err.println(a[0].);
        //System.err.println(a[1].trim());
        
        System.err.println(verificarDesconectado("aa DESCONECTADO fds"));
    }
  
}

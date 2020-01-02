/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.log;

/**
 *
 * @author willi
 */
public class DiretorioLog {
    //System.getProperty("user.dir")+"\\src\\br\\com\\william\\jogoquiz\\log\\log.txt"
    private String diretoriolog = "C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Local\\logQuiz2\\log.txt";

    public String getDiretoriolog() {
        return diretoriolog;
    }

    public void setDiretoriolog(String diretoriolog) {
        this.diretoriolog = diretoriolog;
    }
    
}

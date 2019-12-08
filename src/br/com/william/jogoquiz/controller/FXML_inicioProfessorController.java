/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.controller;

import br.com.william.jogoquiz.sql.Sql;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author willi
 */
public class FXML_inicioProfessorController implements Initializable {
    
    @FXML
    void BT_sair(ActionEvent event) throws IOException {
        File file = new File("D:\\Projetos-git\\java\\Quiz\\src\\br\\com\\william\\jogoquiz\\log\\log.txt");   
       
        try {
            FileInputStream arquivo = new FileInputStream("D:\\Projetos-git\\java\\Quiz\\src\\br\\com\\william\\jogoquiz\\log\\log.txt");
            InputStreamReader in = new InputStreamReader(arquivo); 
            BufferedReader br = new BufferedReader(in);
            Sql novo = new Sql();
            ArrayList values = new ArrayList();
             String a = br.readLine();
            values.add(br.readLine().substring(4));
            arquivo.close();
            novo.executeQuery("UPDATE `professor` SET `status`='0' WHERE nome_professor  = ?", values);
            if(file.delete()){
                System.out.println("deletando");            
            }
            System.exit(0);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXML_inicioAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.view;

import br.com.william.jogoquiz.controller.FXML_inicioController;
import br.com.william.jogoquiz.log.DiretorioLog;
import br.com.william.jogoquiz.sql.Sql;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author willi
 */
public class Inicio extends Application{
    private static Stage stage;
    private static Scene SceneInicio;
    private static Scene SceneInicioAluno;
    private static Scene SceneInicioProfessor;
    private static Scene ScenePergunta;
    private static Scene SceneAdminGame;
    int inicialize = 0;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Inicio.stage = stage;
        Parent fxmlInicio = FXMLLoader.load(getClass().getResource("FXML_inicio.fxml"));
        SceneInicio = new Scene(fxmlInicio);
        SceneInicio.setFill(Color.TRANSPARENT);
               
        Parent fxmlInicioAluno = FXMLLoader.load(getClass().getResource("FXML_inicioAluno.fxml"));
        SceneInicioAluno = new Scene(fxmlInicioAluno);
        
        Parent fxmlInicioProfessor = FXMLLoader.load(getClass().getResource("FXML_inicioProfessor.fxml"));
        SceneInicioProfessor = new Scene(fxmlInicioProfessor);
        
        Parent fxmlPergunta = FXMLLoader.load(getClass().getResource("FXML_pergunta.fxml"));
        ScenePergunta = new Scene(fxmlPergunta);
        
        Parent fxmlAdminGame = FXMLLoader.load(getClass().getResource("FXML_AdminGame.fxml"));
        SceneAdminGame = new Scene(fxmlAdminGame);
        
        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initStyle(StageStyle.UNIFIED);
        /*
        //ESTE COGIGO E RESPONSAVEL POR IMPEDIR QUE O USUARIO MAXIMIZE A TELA.
        stage.setResizable(false);
        stage.show();
        //***********************************************************************
        */
        verificarLog();
        //stage.setMaximized(true);
        switch(inicialize){
            case 0:stage.setScene(SceneInicio); stage.show();;break;
            case 1:stage.setScene(SceneInicioAluno); stage.show();;break;
            case 2:stage.setScene(SceneInicioProfessor); stage.show();;break;            
        }
            
        
        
        
        
    }

    public void verificarLog(){     
        DiretorioLog pegar = new DiretorioLog();
        File diretorio = new File(pegar.getDiretoriolog());
        Sql novo = new Sql();
        if (diretorio.exists() == true){  
            try {
                FileInputStream arquivo = new FileInputStream(pegar.getDiretoriolog());
                InputStreamReader in = new InputStreamReader(arquivo); 
                BufferedReader br = new BufferedReader(in);
                String query = "";                        
                String nome = br.readLine();
                
                System.out.println("nome:"+nome);
                
                if(nome != null){                                         
                    switch(nome){
                        case "0":query = "SELECT `status` FROM `aluno` where nome_aluno = ? AND status = '1'";break;
                        case "1":query = "SELECT `status` FROM `professor` where nome_professor = ? AND status = '1'";break;
                    }
                    String[] retorno = {"status"};
                    ArrayList valorRetornado = new ArrayList();
                    ArrayList values = new ArrayList();                
                    values.add(br.readLine().substring(4));
                    
                   
                    valorRetornado = novo.executeQuery(query, values, retorno);
                    if(valorRetornado.isEmpty()){
                        System.out.println("nome2:"+values.get(0));
                    }else{
                        System.out.println("nome3:"+values.get(0));                        
                        if(verificarIP((String)values.get(0))){
                        
                            if("1".equals(nome)){
                                //novaTela.abrirScene("inicioProfessor");
                                inicialize = 2;
                            }else if("0".equals(nome)){
                                //novaTela.abrirScene("inicioAluno");
                                 inicialize = 1;
                            }
                        }else{
                            inicialize = 0;
                        }
                    }
                    arquivo.close();  
                }
            } catch (IOException ex) {
                Logger.getLogger(FXML_inicioController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }      
    }

    public boolean verificarIP(String n){
        try{
            ArrayList v = new ArrayList();
            Sql novo = new Sql();
            ArrayList valorRetornado = new ArrayList();
            v.add(n);
            v.add(InetAddress.getLocalHost().getHostAddress());
            String[] retur = {"ip_maquina"};     
            valorRetornado = novo.executeQuery("SELECT `ip_maquina` FROM `log` WHERE nome_aluno = ? AND ip_maquina = ?", v, retur);
            if(!valorRetornado.isEmpty()){
                return true;
            }else{
                return false;
            }  
        }catch(Exception ex){
            
        }
        return false;
    }
    public void abrirScene(String scene){         
        switch(scene){            
            case "inicio":stage.setScene(SceneInicio);break;
            case "inicioAluno":stage.setScene(SceneInicioAluno);break;
            case "inicioProfessor":stage.setScene(SceneInicioProfessor);break;
            case "pergunta":stage.setScene(ScenePergunta);break;
            case "adminGame":stage.setScene(SceneAdminGame);break;
        }                
    }
    /**
     * Metodo que fecha a aplicação
     */
    public static void fechar(){
        System.exit(0);        
    }
    /**
     * Metodos que minimiza a aplicação
     */
    public static void minimizar(){
        stage.setIconified(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

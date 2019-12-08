/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.view;

import br.com.william.jogoquiz.controller.FXML_inicioController;
import br.com.william.jogoquiz.sql.Sql;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    int inicialize = 0;

    
    @Override
    public void start(Stage stage) throws Exception {
        Inicio.stage = stage;
        Parent fxmlInicio = FXMLLoader.load(getClass().getResource("FXML_inicio.fxml"));
        SceneInicio = new Scene(fxmlInicio);
        
        Parent fxmlInicioAluno = FXMLLoader.load(getClass().getResource("FXML_inicioAluno.fxml"));
        SceneInicioAluno = new Scene(fxmlInicioAluno);
        
        Parent fxmlInicioProfessor = FXMLLoader.load(getClass().getResource("FXML_inicioProfessor.fxml"));
        SceneInicioProfessor = new Scene(fxmlInicioProfessor);
        
        Parent fxmlPergunta = FXMLLoader.load(getClass().getResource("FXML_pergunta.fxml"));
        ScenePergunta = new Scene(fxmlPergunta);
        
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
        File diretorio = new File("D:\\Projetos-git\\java\\Quiz\\src\\br\\com\\william\\jogoquiz\\log\\log.txt");
        Sql novo = new Sql();
        if (diretorio.exists() == true){  
            try {
                FileInputStream arquivo = new FileInputStream("D:\\Projetos-git\\java\\Quiz\\src\\br\\com\\william\\jogoquiz\\log\\log.txt");
                InputStreamReader in = new InputStreamReader(arquivo); 
                BufferedReader br = new BufferedReader(in);
                String query = "";                        
                String nome = br.readLine();
                
                System.out.println("nome:"+nome);
                
                if(nome != null){                     
                        //nome = br.readLine().substring(0, 1);

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
//                        switch(nome){;
//                            case "0":novaTela.abrirScene("inicioAluno");System.out.println("nome4:"+values.get(0));break;
//                            case "1":novaTela.abrirScene("inicioProfessor");System.out.println("nome5:"+values.get(0)); break;
//                        }
                        if("1".equals(nome)){
                            //novaTela.abrirScene("inicioProfessor");
                            inicialize = 2;
                        }else if("0".equals(nome)){
                            //novaTela.abrirScene("inicioAluno");
                             inicialize = 1;
                        }
                    }
                    arquivo.close();  
                }
            } catch (IOException ex) {
                Logger.getLogger(FXML_inicioController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }      
    }
    public void abrirScene(String scene){
        switch(scene){
            case "inicio":stage.setScene(SceneInicio);break;
            case "inicioAluno":stage.setScene(SceneInicioAluno);break;
            case "inicioProfessor":stage.setScene(SceneInicioProfessor);break;
            case "pergunta":stage.setScene(ScenePergunta);
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

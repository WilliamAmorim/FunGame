/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.controller;

import br.com.william.jogoquiz.log.DiretorioLog;
import br.com.william.jogoquiz.sql.Sql;
import br.com.william.jogoquiz.view.Inicio;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author willi
 */
public class FXML_inicioController implements Initializable {  
    Inicio novaTela = new Inicio();
    @FXML
    private ImageView imagem_logo;
    @FXML
    private Pane panel_inicio;

    @FXML
    private JFXButton BT_aluno;

    @FXML
    private JFXButton BT_professor;

    @FXML
    private Pane panel_loginProfessor;

    @FXML
    private TextField txt_usuarioProfessor;

    @FXML
    private JFXButton BT_entrarProfessor;

    @FXML
    private JFXButton BT_voltarProfessor;

    @FXML
    private JFXButton BT_cadastreProfessor;

    @FXML
    private PasswordField txt_senhaProfessor;

    @FXML
    private Pane panel_loginAluno;

    @FXML
    private TextField txt_usuarioAluno;

    @FXML
    private JFXButton BT_loginAluno;

    @FXML
    private JFXButton BT_cadastreAluno;

    @FXML
    private JFXButton BT_voltarAluno;

    @FXML
    private PasswordField txt_senhaAluno;

    @FXML
    private Pane panel_cadastroAluno;

    @FXML
    private TextField txt_usuarioCadastroAluno;

    @FXML
    private JFXButton BT_cadastrarAluno;

    @FXML
    private ComboBox combo_serie;

    @FXML
    private ComboBox combo_turma;

    @FXML
    private ComboBox combo_turno;

    @FXML
    private JFXButton BT_voltarCadastro;

    @FXML
    private PasswordField txt_senhaCadastroAluno;

    @FXML
    private Pane panel_cadastroProfessor;

    @FXML
    private TextField txt_usuarioCadastroProfessor;

    @FXML
    private JFXButton BT_cadastrarProfessor;

    @FXML
    private ComboBox combo_disciplina;

    @FXML
    private JFXButton BT_voltarCadastroProfessor;

    @FXML
    private PasswordField txt_senhaCadastroProfessor;

    @FXML
    void loginAlunos(ActionEvent event) {
        panel_inicio.setVisible(false);
        panel_loginAluno.setVisible(true);  
        imagem_logo.setVisible(false);
        limparCampos();
    }

    @FXML
    void loginProfessors(ActionEvent event) {
        panel_inicio.setVisible(false);
        panel_loginProfessor.setVisible(true);
        imagem_logo.setVisible(false);
        limparCampos();
    }
    @FXML
    void BT_entrarCadastrarProfessor(ActionEvent event) {
        panel_loginProfessor.setVisible(false);
        panel_cadastroProfessor.setVisible(true);
        limparCampos();
    }
    @FXML
    void BT_entrarCadastrarAluno(ActionEvent event) {
        panel_loginAluno.setVisible(false);
        panel_cadastroAluno.setVisible(true);
        limparCampos();
    }
    @FXML
    void BT_voltarCadastroAluno(ActionEvent event) {
        panel_cadastroAluno.setVisible(false);
        panel_loginAluno.setVisible(true);
        limparCampos();
       
    }
    @FXML
    void BT_voltarCadastroProfessor(ActionEvent event) {
        panel_loginProfessor.setVisible(true);
        panel_cadastroProfessor.setVisible(false);
        limparCampos();
       
    }
    @FXML
    void BT_voltarLoginAluno(ActionEvent event) {
        panel_loginAluno.setVisible(false);
        panel_inicio.setVisible(true);   
        limparCampos();
        imagem_logo.setVisible(true);
    }
    @FXML
    void BT_voltarLoginProfessor(ActionEvent event) {
        panel_loginProfessor.setVisible(false);
        panel_inicio.setVisible(true);   
        limparCampos();
        imagem_logo.setVisible(true);
    }
    
    @FXML
    void BT_entrarProfessor(ActionEvent event) {
        if(panel_inicio.isVisible() == false){
            Sql novo = new Sql();
            String[] retorno = {"nome_professor","senha"};
            ArrayList valorRetornado = new ArrayList();
            ArrayList values = new ArrayList();
            values.add(txt_usuarioProfessor.getText());
            values.add(txt_senhaProfessor.getText());
            
            if(txt_usuarioProfessor.getText() != null && txt_senhaProfessor.getText() != null){
                valorRetornado = novo.executeQuery("SELECT `nome_professor`, `senha` FROM `professor` WHERE nome_professor = ? AND senha =  ? AND status = '0'", values, retorno);

                if(valorRetornado.isEmpty()){
                    //não existe usuario
                    Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);                
                    dialogoInfo.setHeaderText("Usuario ou senha invalidos");
                    dialogoInfo.setContentText("Preencha os dados novamente");
                    dialogoInfo.showAndWait();
                }else{
                    //existe usuario      
                    values.clear();
                    novo.executeQuery("UPDATE `professor` SET `status`='1' WHERE nome_professor  = '"+txt_usuarioProfessor.getText()+"'", values);    
                    System.setProperty("nome",txt_usuarioProfessor.getText());
                    Criarlog("1\nlog:"+txt_usuarioProfessor.getText(),1);                        
                    novaTela.abrirScene("inicioProfessor");

                }
            }else{
                Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);                
                dialogoInfo.setHeaderText("Preencha todos os campos");
                //dialogoInfo.setContentText("Preencha os dados novamente");
                dialogoInfo.showAndWait();
            }
        }
    }
    
    @FXML
    void BT_cadastrarProfessor(ActionEvent event) {
        if(panel_loginProfessor.isVisible() == false){
            Sql novo = new Sql();
            String[] retorno = {"nome_professor"};
            ArrayList valorRetornado = new ArrayList();
            ArrayList values = new ArrayList();
            values.add(txt_usuarioCadastroProfessor.getText());
            //values.add(txt_senhaProfessor.getText());            
            valorRetornado = novo.executeQuery("SELECT `nome_professor` FROM `professor` WHERE nome_professor = ?", values,retorno);
            
            if(valorRetornado.isEmpty()){
                if(txt_usuarioCadastroProfessor.getText() != null && txt_senhaCadastroProfessor.getText() != null && combo_disciplina.getValue() != null){
                    ArrayList valuess = new ArrayList();
                    valuess.add(txt_usuarioCadastroProfessor.getText());
                    valuess.add(txt_senhaCadastroProfessor.getText());
                    valuess.add(combo_disciplina.getValue()+"");
                    novo.executeQuery("INSERT INTO `professor`(`nome_professor`, `senha`, `disciplina`,`status`) VALUES (?,?,?,1)", valuess);                                 
                    System.setProperty("nome",txt_usuarioCadastroProfessor.getText());
                    Criarlog("1\nlog:"+txt_usuarioCadastroProfessor.getText(),1); 
                    novaTela.abrirScene("inicioProfessor");
                }else{
                     Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);                
                    dialogoInfo.setHeaderText("Preencha todos os campos");
//                    dialogoInfo.setContentText("");
                    dialogoInfo.showAndWait();
                }
                
            }else{
                //não existe usuario
                Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);                
                dialogoInfo.setHeaderText("Usuario já existente");
                dialogoInfo.setContentText("Escolha um usario não existente");
                dialogoInfo.showAndWait();
            }
        }
    }
        
    @FXML
    void BT_entrarAluno(ActionEvent event) {        
        if(panel_inicio.isVisible() == false){
            Sql novo = new Sql();
            String[] retorno = {"nome_aluno","senha"};
            ArrayList valorRetornado = new ArrayList();
            ArrayList values = new ArrayList();
            values.add(txt_usuarioAluno.getText());
            values.add(txt_senhaAluno.getText());
            if(txt_usuarioAluno.getText() != null && txt_senhaAluno.getText() != null){
                valorRetornado = novo.executeQuery("SELECT `nome_aluno`, `senha` FROM `aluno` WHERE nome_aluno = ? AND senha =  ? AND status = '0'", values, retorno);

                if(valorRetornado.isEmpty()){
                    //não existe usuario
                    Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);                
                    dialogoInfo.setHeaderText("Usuario ou senha invalidos");
                    dialogoInfo.setContentText("Preencha os dados novamente");
                    dialogoInfo.showAndWait();
                }else{
                    //existe usuario
                    values.clear();
                    novo.executeQuery("UPDATE `aluno` SET `status`='1' WHERE nome_aluno  = '"+txt_usuarioAluno.getText()+"'", values);
                    System.setProperty("nome",txt_usuarioAluno.getText());                                       
                    Criarlog("0\nlog:"+txt_usuarioAluno.getText(),2);
                    novaTela.abrirScene("inicioAluno");                    
                }
            }else{
                Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);                
                dialogoInfo.setHeaderText("Preencha todos os campos");
                //dialogoInfo.setContentText("Preencha os dados novamente");
                dialogoInfo.showAndWait();
            }
        }
    }
    
    @FXML
    void BT_cadastrarAluno(ActionEvent event) throws IOException {             
        if(panel_loginAluno.isVisible() == false){
            Sql novo = new Sql();
            String[] retorno = {"nome_aluno"};
            ArrayList valorRetornado = new ArrayList();
            ArrayList values = new ArrayList();
            values.add(txt_usuarioCadastroAluno.getText());
            //values.add(txt_senhaProfessor.getText());            
            valorRetornado = novo.executeQuery("SELECT `nome_aluno` FROM `aluno` WHERE nome_aluno = ?", values,retorno);
            
            if(valorRetornado.isEmpty()){
                if(txt_usuarioCadastroAluno.getText() != null && txt_senhaCadastroAluno.getText() != null && combo_serie.getValue() != null && combo_turma.getValue() != null && combo_turno.getValue() != null){
                    ArrayList valuess = new ArrayList();
                    valuess.add(txt_usuarioCadastroAluno.getText());
                    valuess.add(txt_senhaCadastroAluno.getText());
                    valuess.add(combo_serie.getValue());
                    valuess.add(combo_turma.getValue());
                    valuess.add(combo_turno.getValue());
                    novo.executeQuery("INSERT INTO `aluno`(`nome_aluno`, `senha`, `serie`, `turma`, `turno`, `status`) VALUES (?,?,?,?,?,1)", valuess);                                 
                    System.setProperty("nome",txt_usuarioCadastroAluno.getText());                   
                    Criarlog("0\nlog:"+txt_usuarioCadastroAluno.getText(),2);
                    novaTela.abrirScene("inicioAluno");
                }else{
                    Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);                
                    dialogoInfo.setHeaderText("Preencha todos os campos");
                    dialogoInfo.showAndWait();
                }
            }else{
                //não existe usuario
                Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);                
                dialogoInfo.setHeaderText("Usuario já existente");
                dialogoInfo.setContentText("Escolha um usario não existente");
                dialogoInfo.showAndWait();
            }
        }
    }
    
    public void limparCampos(){
        txt_usuarioCadastroAluno.setText("");
        txt_senhaCadastroAluno.setText("");
        txt_senhaCadastroProfessor.setText("");
        txt_usuarioCadastroProfessor.setText("");        
        txt_usuarioAluno.setText("");
        txt_senhaAluno.setText("");
        txt_senhaProfessor.setText("");
        txt_usuarioProfessor.setText("");
        combo_disciplina.getSelectionModel().clearSelection();
        combo_turma.getSelectionModel().clearSelection();
        combo_serie.getSelectionModel().clearSelection();
        combo_turno.getSelectionModel().clearSelection();
        
    }
    
    public void Criarlog(String nome,int n){
        DiretorioLog pegar = new DiretorioLog();
        File file = new File(pegar.getDiretoriolog());
        if (file.delete()) {}
        File diretorio = new File(pegar.getDiretoriolog());
        
        if (diretorio.exists() == false){               
            try {
                FileWriter arquivo = new FileWriter(pegar.getDiretoriolog());
                System.out.println("inserindo");                
                arquivo.write(nome);                     
                Sql novo = new Sql();
                ArrayList values = new ArrayList();
                values.add(System.getProperty("nome")); 
                values.add(InetAddress.getLocalHost().getHostAddress());
                novo.executeQuery("INSERT INTO `log`(`nome_aluno`, `ip_maquina`) VALUES (?,?)", values);
                switch(n){                    
                    case 1:novaTela.abrirScene("inicioProfessor");
                    case 2:novaTela.abrirScene("inicioAluno");
                }
                arquivo.close();  
            } catch (IOException ex) {
              
               System.out.print("Erro no Logo:");
               System.err.print(ex);
            }
        }
    }
    
      @FXML
    void BT_fechar(MouseEvent event) {
        Inicio.fechar();
    }

    @FXML
    void BT_minimizar(MouseEvent event) {
        Inicio.minimizar();
    }
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        //combo_disciplina.getItems().removeAll(combo_disciplina.getItems());
        //combo_disciplina.getItems().addAll("Option A", "Option B", "Option C");                
        
        //combo_disciplina.getSelectionModel().select("Option B");
        combo_disciplina.getItems().add("Matematica");
        combo_disciplina.getItems().add("Portugues");
        combo_disciplina.getItems().add("Quimica");
        
        combo_turma.getItems().add("A");
        combo_turma.getItems().add("B");
        combo_turma.getItems().add("C");
        
        combo_serie.getItems().add("1");
        combo_serie.getItems().add("2");
        combo_serie.getItems().add("3");
        
        combo_turno.getItems().add("Matutino");
        combo_turno.getItems().add("Vespertino");
        combo_turno.getItems().add("Noturno");  
        novaTela.abrirScene("inicioAluno");
        // verificarLog();
        
        final  Stage stage = new Stage();

if (!stage.isShowing()) {
   novaTela.abrirScene("inicioAluno");
} else {
    System.out.println("a janela já esta aberta");
}
        
    }    
    
}

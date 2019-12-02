/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author willi
 */
public class FXML_inicioController implements Initializable {
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
    private ComboBox<?> combo_serie;

    @FXML
    private ComboBox<?> combo_turma;

    @FXML
    private ComboBox<?> combo_turno;

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
    private ComboBox<?> combo_disciplina;

    @FXML
    private JFXButton BT_voltarCadastroProfessor;

    @FXML
    private PasswordField txt_senhaCadastroProfessor;

    @FXML
    void loginAlunos(ActionEvent event) {
        panel_inicio.setVisible(false);
        panel_loginAluno.setVisible(true);
    }

    @FXML
    void loginProfessors(ActionEvent event) {
         panel_inicio.setVisible(false);
        panel_loginProfessor.setVisible(true);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

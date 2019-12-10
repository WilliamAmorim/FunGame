/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.controller;

import br.com.william.jogoquiz.bean.DesempenhoAlunoBean;
import br.com.william.jogoquiz.sql.Sql;
import br.com.william.jogoquiz.util.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author willi
 */
public class FXML_inicioAlunoController implements Initializable {

    @FXML
    private TableView<DesempenhoAlunoBean> tabela_desempenhoAluno;
    
    @FXML
    private TableColumn<DesempenhoAlunoBean, String> coluna_professor;
    
    @FXML
    private TableColumn<DesempenhoAlunoBean, String> coluna_pacote;
    
    @FXML
    private TableColumn<DesempenhoAlunoBean, String> coluna_data;
    
    @FXML
    private TableColumn<DesempenhoAlunoBean, String> coluna_pontuacao;
    
    @FXML
    private DatePicker date_inicio;
    
    @FXML
    private DatePicker date_fim;
    
    @FXML
    private ComboBox combo_disciplina;
    
    @FXML
    private TextField txt_assunto;
    
    private ObservableList<DesempenhoAlunoBean> personData = FXCollections.observableArrayList();
    
    public String nomeAluno;
    
    @FXML
    void BT_sair(ActionEvent event) throws IOException {
        File file = new File("D:\\Projetos-git\\java\\Quiz\\src\\br\\com\\william\\jogoquiz\\log\\log.txt");        
        try {
            FileInputStream arquivo = new FileInputStream("D:\\Projetos-git\\java\\Quiz\\src\\br\\com\\william\\jogoquiz\\log\\log.txt");
            InputStreamReader in = new InputStreamReader(arquivo);            
            BufferedReader br = new BufferedReader(in);
            String a = br.readLine();
            Sql novo = new Sql();
            ArrayList values = new ArrayList();
            values.add(br.readLine().substring(4));
            novo.executeQuery("UPDATE `aluno` SET `status`='0' WHERE nome_aluno  = ?", values);
            if (file.delete()) {
                System.out.println("deletando");                
            }
            System.exit(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXML_inicioAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void BT_buscarDesempenhoAluno(ActionEvent event) {
        //personData.add(new DesempenhoAlunoBean("virginia","po458","12/21/2001","1500"));
        personData.clear();
        Sql novo = new Sql();
        String[] retorno = {"professor", "assunto", "data", "pontuacao"};
        ArrayList values = new ArrayList();
        ArrayList valorRetornado = new ArrayList();
        //values.add("william");
        valorRetornado = novo.executeQuery(criarQuery(), values, retorno);
        System.out.println(criarQuery());
        LocalDate data = date_inicio.getValue();
        for (int i = 0; i <= valorRetornado.size() - 1; i++) {
            String a = valorRetornado.get(i).toString();
            String b = a.replace("[", "").replace("]", "");
            
            String[] tokens = b.split(",");
            
            System.out.println(tokens[0] + " - " + tokens[1] + " - " + tokens[2] + " - " + tokens[3] + " Data:" + data);
            personData.add(new DesempenhoAlunoBean(tokens[0], tokens[1], Data.converterData(tokens[2]), tokens[3]));            
        }
    }

    @FXML
    void BT_limparFiltro(ActionEvent event) {
        combo_disciplina.getSelectionModel().clearSelection();
        
        date_inicio.setValue(null);
        date_fim.setValue(null);
        
        txt_assunto.setText("");
    }
    
    public String criarQuery() {
        LocalDate data;
        FileInputStream arquivo;
        String a = null;
        try {
            arquivo = new FileInputStream("D:\\Projetos-git\\java\\Quiz\\src\\br\\com\\william\\jogoquiz\\log\\log.txt");
            InputStreamReader in = new InputStreamReader(arquivo);            
            BufferedReader br = new BufferedReader(in);
            
            try {
                a = br.readLine();
                a = br.readLine().substring(4);
            } catch (IOException ex) {
                Logger.getLogger(FXML_inicioAlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXML_inicioAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "SELECT * FROM `desempenho` WHERE aluno = '" + a + "' ";
        
        if (combo_disciplina.getValue() != null) {
            query += "AND codigo_pacote  LIKE '%" + combo_disciplina.getValue().toString().substring(0, 3) + "%'";
        }
        
        if (!"".equals(txt_assunto.getText())) {
            query += " AND assunto LIKE '%" + txt_assunto.getText() + "%'";            
        }
        
        if (date_inicio.getValue() != null) {
            data = date_inicio.getValue();
            query += " AND data BETWEEN '" + data + "'";
            if (date_fim.getValue() != null) {
                data = date_fim.getValue();
                query += " AND '" + data + "'";
            }
        }
        
        return query;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combo_disciplina.getItems().add("Matematica");
        combo_disciplina.getItems().add("Portugues");
        combo_disciplina.getItems().add("Quimica");
        coluna_professor.setCellValueFactory(cellData -> cellData.getValue().getProfessor());
        coluna_pacote.setCellValueFactory(cellData -> cellData.getValue().getPacote());
        coluna_data.setCellValueFactory(cellData -> cellData.getValue().getDate());
        coluna_pontuacao.setCellValueFactory(cellData -> cellData.getValue().getPontuacao());
        
        tabela_desempenhoAluno.setItems(personData);
        
    }    
    
}

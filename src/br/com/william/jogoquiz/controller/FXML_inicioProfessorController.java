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
public class FXML_inicioProfessorController implements Initializable {
    
    @FXML
    private TableView<DesempenhoAlunoBean> tabela_desempenho;

    @FXML
    private TableColumn<DesempenhoAlunoBean, String> coluna_aluno;

    @FXML
    private TableColumn<DesempenhoAlunoBean, String> coluna_professor;

    @FXML
    private TableColumn<DesempenhoAlunoBean, String> coluna_assunto;

    @FXML
    private TableColumn<DesempenhoAlunoBean, String> coluna_data;

    @FXML
    private TableColumn<DesempenhoAlunoBean, String> coluna_pontuacao;

    @FXML
    private DatePicker data_inicio;

    @FXML
    private ComboBox combo_disciplina;

    @FXML
    private ComboBox combo_turma;
    
    @FXML
    private ComboBox combo_serie;

    @FXML
    private TextField txt_nome;

    @FXML
    private TextField txt_assunto;

    @FXML
    private DatePicker data_fim;
    
    private ObservableList<DesempenhoAlunoBean> conteudoTabelaDesempenho = FXCollections.observableArrayList();
    
    @FXML
    void BT_buscarDesempenho(ActionEvent event) {
        conteudoTabelaDesempenho.clear();
        Sql novo = new Sql();
        
        String[] retorno = {"aluno","professor","assunto","data","pontuacao"};
        ArrayList values = new ArrayList();
        ArrayList valorRetornado = new ArrayList();    
        valorRetornado = novo.executeQuery(criarQuery(), values, retorno);
        System.out.println(criarQuery());        
        for (int i = 0; i <= valorRetornado.size()-1; i++) {
            String a = valorRetornado.get(i).toString();
            String b = a.replace("[", "").replace("]", "");                       
            String[] tokens = b.split(",");         
            //String datas = tokens[3];//new SimpleDateFormat("dd/MM/yyyy").format(tokens[2]);           
            conteudoTabelaDesempenho.add(new DesempenhoAlunoBean(tokens[0],tokens[1],tokens[2],Data.converterData(tokens[3]),tokens[4]));            
        }
    }
    @FXML
    void BT_limparFiltro(ActionEvent event) {
        combo_disciplina.getSelectionModel().clearSelection();
        combo_turma.getSelectionModel().clearSelection();
        combo_serie.getSelectionModel().clearSelection();
        
        data_inicio.setValue(null);
        data_fim.setValue(null);
        
        txt_nome.setText("");
        txt_assunto.setText("");
    }

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
    
    public  String criarQuery(){
        LocalDate data;
        String query = "SELECT `desempenho`.`aluno`,`desempenho`.`professor`,`desempenho`.`assunto`,`desempenho`.`data`,`desempenho`.`pontuacao` FROM `desempenho`,`aluno` where `desempenho`.`aluno` = `aluno`.`nome_aluno`";
        
        if(combo_disciplina.getValue() != null){
            query += "AND `desempenho`.`codigo_pacote` LIKE '%"+combo_disciplina.getValue().toString().substring(0, 3)+"%'";
        }
        
        if(data_inicio.getValue() != null && data_fim.getValue() != null){
            data = data_inicio.getValue();
            query += " AND `desempenho`.`data` BETWEEN '"+data+"'";            
                data = data_fim.getValue();
                query += " AND '"+data+"'";
            
        }
        if(data_inicio.getValue() != null && data_fim.getValue() == null){
            data = data_inicio.getValue();
            query += " AND `desempenho`.`data` = '"+data+"'";            
               
            
        }
        if(combo_serie.getValue() != null){
            query  += "AND `aluno`.`serie` = '"+combo_serie.getValue()+"'";
        }
        if(combo_turma.getValue() != null){
            query  += "AND `aluno`.`turma` = '"+combo_turma.getValue()+"'";
        }
        
        if(!"".equals(txt_nome.getText())){
            query += " AND `desempenho`.`aluno` = '"+txt_nome.getText()+"'";
        }
         if(!"".equals(txt_assunto.getText())){
            query +=" AND `desempenho`.`assunto` LIKE '%"+txt_assunto.getText()+"%'";            
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
        
        combo_turma.getItems().add("A");
        combo_turma.getItems().add("B");
        combo_turma.getItems().add("C");
        
        combo_serie.getItems().add("1");
        combo_serie.getItems().add("2");
        combo_serie.getItems().add("3");
        
        coluna_aluno.setCellValueFactory(cellData -> cellData.getValue().getAluno());
        coluna_professor.setCellValueFactory(cellData -> cellData.getValue().getProfessor());
        coluna_assunto.setCellValueFactory(cellData -> cellData.getValue().getPacote());
        coluna_data.setCellValueFactory(cellData -> cellData.getValue().getDate());
        coluna_pontuacao.setCellValueFactory(cellData -> cellData.getValue().getPontuacao());
        
        tabela_desempenho.setItems(conteudoTabelaDesempenho);
    }    
    
}

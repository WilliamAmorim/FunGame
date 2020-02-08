/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.controller;

import br.com.william.jogoquiz.bean.DesempenhoAlunoBean;
import br.com.william.jogoquiz.log.DiretorioLog;
import br.com.william.jogoquiz.sql.Sql;
import br.com.william.jogoquiz.util.Data;
import br.com.william.jogoquiz.util.Util;
import br.com.william.jogoquiz.view.Inicio;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;



/**
 * FXML Controller class
 *
 * @author willi
 */
public class FXML_inicioAlunoController implements Initializable {
    
    
    @FXML
    private ImageView image_logo;
  
    //DesempenhoAluno***********************************************************
  
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
    private Label label_nome;
    
    @FXML
    private TextField txt_assunto;
        
    
    private ObservableList<DesempenhoAlunoBean> personData = FXCollections.observableArrayList();
    
    public String nomeAluno;
            
    @FXML
    void BT_buscarDesempenhoAluno(ActionEvent event) {
        buscarDesempenhoAluno();
    }
    public void buscarDesempenhoAluno(){
       buscarDesempenho();
    }
    public void buscarDesempenho(){
         //personData.add(new DesempenhoAlunoBean("virginia","po458","12/21/2001","1500"));
        personData.clear();
        Sql novo = new Sql();
        String[] retorno = {"professor", "assunto", "data", "pontuacao","pontuacao_maxima"};
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
            
            System.out.println(tokens[0] + " - " + tokens[1] + " - " + tokens[2] + " - " + tokens[3] + " Data:" + data+" pontuação maxima: "+tokens[4]);
            personData.add(new DesempenhoAlunoBean(tokens[4], tokens[0],tokens[1],Data.converterData(tokens[2]),tokens[3]));            
        }
        
    }

    @FXML
    void BT_limparFiltro(ActionEvent event) {
        combo_disciplina.getSelectionModel().clearSelection();
        
        date_inicio.setValue(null);
        date_fim.setValue(null);
        
        txt_assunto.setText("");
        buscarDesempenho();
    }
    
    public String criarQuery() {
        LocalDate data;
        //FileInputStream arquivo;
        String a = null;
//        try {;
//            DiretorioLog pegar = new DiretorioLog();
//            arquivo = new FileInputStream(pegar.getDiretoriolog());
//            InputStreamReader in = new InputStreamReader(arquivo);            
//            BufferedReader br = new BufferedReader(in);
//            
//            try {
//                a = br.readLine();
//                a = br.readLine().substring(4);
//            } catch (IOException ex) {
//                Logger.getLogger(FXML_inicioAlunoController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(FXML_inicioAlunoController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        String query = "SELECT `desempenho`.`professor`,`desempenho`.`assunto`, `desempenho`.`data`, `desempenho`.`pontuacao`,`pacote_pergunta`.`pontuacao_maxima` FROM `desempenho`,`pacote_pergunta` WHERE `pacote_pergunta`.`codigo_pacote` = `desempenho`.`codigo_pacote` AND `desempenho`.`aluno` = '" +Util.nome_log() + "' ";
        
        if (combo_disciplina.getValue() != null) {
            query += "AND `desempenho`.`codigo_pacote`  LIKE '%" + combo_disciplina.getValue().toString().substring(0, 3) + "%'";
        }
        
        if (!"".equals(txt_assunto.getText())) {
            query += " AND `desempenho`.`assunto` LIKE '%" + txt_assunto.getText() + "%'";            
        }        
        
        if (date_inicio.getValue() != null && date_fim.getValue() != null) {
            data = date_inicio.getValue();
            query += " AND `desempenho`.`data` BETWEEN '" + data + "'";
            data = date_fim.getValue();
            query += " AND '" + data + "'";

        }
        if (date_inicio.getValue() != null && date_fim.getValue() == null) {
            data = date_inicio.getValue();
            query += " AND `desempenho`.`data` = '" + data + "'";

        }
        
        return query;
    }
    
      @FXML
    private Pane pane_desempenho;
      @FXML
    void BT_AbrirDesempenho(ActionEvent event) {
        image_logo.setVisible(false);
        label_nome.setText(Util.nome_log());
        buscarDesempenhoAluno();
        pane_desempenho.setVisible(true);        
    }
    
    @FXML
    void BT_fecharDesempenho(MouseEvent event) {
        image_logo.setVisible(true);
        pane_desempenho.setVisible(false);
        combo_disciplina.getSelectionModel().clearSelection();
        
        date_inicio.setValue(null);
        date_fim.setValue(null);
        
        txt_assunto.setText("");
        
        System.out.println("Diretorio: "+System.getProperty("user.dir"));
    }
    //**************************************************************************
    
    
    
    @FXML
    void BT_sair(ActionEvent event) throws IOException {
        DiretorioLog pegar = new DiretorioLog();
        File file = new File(pegar.getDiretoriolog());        
        try {
            FileInputStream arquivo = new FileInputStream(pegar.getDiretoriolog());
            InputStreamReader in = new InputStreamReader(arquivo);            
            BufferedReader br = new BufferedReader(in);
            String a = br.readLine();
            Sql novo = new Sql();
            ArrayList values = new ArrayList();
            values.add(br.readLine().substring(4));
            arquivo.close();
            novo.executeQuery("UPDATE `aluno` SET `status`='0' WHERE nome_aluno  = ?", values);
            novo.executeQuery("DELETE FROM `log` WHERE nome_aluno  = ?", values);
            if (file.delete()) {
                System.out.println("deletando");                
            }
            System.exit(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXML_inicioAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    void BT_jogar(ActionEvent event) {
        label_nome.setText(Util.nome_log());
        Inicio abrir = new Inicio();
        abrir.abrirScene("pergunta");
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
        // TODO
        label_nome.setText(Util.nome_log());
        
//        combo_disciplina.getItems().add("Matematica");
//        combo_disciplina.getItems().add("Portugues");
//        combo_disciplina.getItems().add("Quimica");
        Util.listarDisciplinas(combo_disciplina);        
        coluna_professor.setCellValueFactory(cellData -> cellData.getValue().getProfessor());
        coluna_pacote.setCellValueFactory(cellData -> cellData.getValue().getPacote());
        coluna_data.setCellValueFactory(cellData -> cellData.getValue().getDate());
        coluna_pontuacao.setCellValueFactory(cellData -> cellData.getValue().getPontuacao());
        
        tabela_desempenhoAluno.setRowFactory(tv -> new TableRow<DesempenhoAlunoBean>() {
        float  m;
        float p;
        
    public void updateItem(DesempenhoAlunoBean item, boolean empty) {
        super.updateItem(item, empty) ;
            if(item != null){
                m = Float.parseFloat(item.getPontuacaoMaxima().getValue());
                p = Float.parseFloat(item.getPontuacao().getValue());
            }
           
            if(item == null){
                setStyle("");                
            }else if(p < (m/2)){
                setStyle("-fx-background-color:#EE3B3B;");
                setTextFill(Color.RED);
                
                
            }else if(p > (m/2) && p < ((m/2)+((m/2)/2))){
                 setStyle("-fx-background-color:#FF7F24;");
                 setTextFill(Color.YELLOW);
                  
            }else if(p > ((m/2)+((m/2)/2))){
               setStyle("-fx-background-color:#43CD80;");
                setTextFill(Color.GREEN);
                
            }
    }
});
        tabela_desempenhoAluno.setItems(personData);
        
        
        
     
    }    
    
}

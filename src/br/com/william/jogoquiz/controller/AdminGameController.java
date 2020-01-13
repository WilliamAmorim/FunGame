/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.william.jogoquiz.controller;

import br.com.william.jogoquiz.sql.Sql;
import br.com.william.jogoquiz.util.CodigoPacote;
import br.com.william.jogoquiz.util.Util;
import br.com.william.jogoquiz.view.Inicio;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author willi
 */
public class AdminGameController implements Initializable {

    public static String nome;

    @FXML
    private Label Label_enunciado;

    @FXML
    private Label label_numeroPerguntas;
    @FXML
    private Label label_ip;

    @FXML
    private Label label_nome;

    @FXML
    private Label label_clock;

    @FXML
    private Label label_alunosConectados;

    @FXML
    private ListView list_alunosConectados;
    ObservableList<String> alunosConectados = FXCollections.observableArrayList();

    @FXML
    private JFXButton BT_proximaPergunta;

    public void adicionar(String nome) {
        try {
            alunosConectados.add(nome);
        } catch (Exception ex) {
            System.err.println("-------------------------Erro ao adicionar Aluno no list View");
        }
    }
    CodigoPacote P = new CodigoPacote();

    @FXML
    void BT_voltar(ActionEvent event) {
        try {
             esperar = true;
            if(!esperarConexao.isInterrupted()){
                esperarConexao.interrupt();
            }
            list_alunosConectados.setItems(alunosConectados);
            label_nome.setText(Util.nome_log());
            label_alunosConectados.setText(" ");
            label_ip.setText(" ");
            BT_proximaPergunta.setText("Iniciar Servidor");
//            Platform.runLater(()->label_ip.setText(""));
//            Platform.runLater(()->label_alunosConectados.setText(""));
//            Platform.runLater(()->BT_proximaPergunta.setText("Iniciar Servidor"));    
            if(!clientes.isEmpty()){
                for (int j = 0; j <= clientes.size(); j++) {
                    clientes.get(j).close();
                }
            }
            clientes.clear();
            System.out.println("Parando servidor");
            if(!serverSocket.isClosed()){
                serverSocket.close();
                System.out.println("Servidor parado");
            }
            //serverSocket.close();
            
            alunosConectados.clear();
            Inicio n = new Inicio();
            n.abrirScene("inicioProfessor");
        } catch (IOException ex) {
            System.err.println("Erro ao parar servidor Mensagem:"+ex);
        }
    }

    @FXML
    void BT_proximaPergunta(ActionEvent event) throws IOException {
        if (BT_proximaPergunta.getText().equals("Iniciar Servidor")) {
            iniciarServidor(5555);
            pegarPacote();
        } else {
            if (cont == atualClientes) {
                //parar de receber conexoes   
                if (p == Nperguntas) {
                    try{
                        finalizarGame();
                    }catch(Exception ex){

                    }
                }else{
                    esperarConexao.interrupt();
                    esperar = false;
                    BT_proximaPergunta.setText("Proxima Pergunta");
                    label_alunosConectados.setText("Alunos Conectados");
                    enviarMensagens("1," + P.getCodigoPacote());
                    if(p == Nperguntas){
                        BT_proximaPergunta.setText("Finalizar Jogo");
                    }
                }
            } else {
                System.out.println("Espere todos os alunos enviarem");
            }
            
        }
    }

    int p = 0;

    public void proximaPerguntas() {
//        if (p == Nperguntas) {
//            try{
//                finalizarGame();
//            }catch(Exception ex){
//                
//            }
//        } else {
            cronometro(true);
            String a = perguntas.get(p).toString();
            String b = a.replace("[", "").replace("]", "");
            String[] tokens = b.split(",");
            Platform.runLater(() -> Label_enunciado.setText(tokens[0].trim()));
            Platform.runLater(() -> label_numeroPerguntas.setText(p + "/" + Nperguntas));
        //}
        p++;

    }

    public void finalizarGame() {
        ObservableList<String> alunosConectadosOrdem = FXCollections.observableArrayList();
        //enviarMensagens("finalizado,andre,roger,antonio");        
        int todos = 0;
        int ordem = 0;
        int aa = 0;
        while (todos != alunosConectados.size()) {
            for (int j = 0; j < alunosConectados.size(); j++) {                
                if(clientes.get(j).isConnected()){
                    int i = Util.ordemCrescente(pontos, j);
                    if (i == ordem) {
                        aa++;
                        alunosConectadosOrdem.add(aa+" - "+alunosConectados.get(j));
                    }
                }
            }
            ordem++;
            todos++;
        }
        Platform.runLater(() -> list_alunosConectados.setItems(alunosConectadosOrdem));
        System.out.println("Listando");
        for (int j = 0; j < alunosConectadosOrdem.size(); j++) {
            System.out.print("|-"+alunosConectadosOrdem.get(j)+"-|");
            
        }
        cronometro(false);
        Platform.runLater(() -> label_alunosConectados.setText("Jogo Finalizado"));
        
        switch(alunosConectadosOrdem.size()){
            case 1:enviarMensagens("finalizado," + alunosConectadosOrdem.get(0)+ ","+" " + "," +" "+","+Util.nome_log());break;
            case 2:enviarMensagens("finalizado," + alunosConectadosOrdem.get(0)+ "," + alunosConectadosOrdem.get(1)+" N" + ","+" "+","+Util.nome_log()); break;
            default:enviarMensagens("finalizado," + alunosConectadosOrdem.get(0) + "," + alunosConectadosOrdem.get(1)+ "," + alunosConectadosOrdem.get(2)+","+Util.nome_log());break;
        }
        System.out.println("JOGO FINALIZADO");
        //enviarMensagens("finalizado," + alunosConectadosOrdem.get(0)+" N" + "," + alunosConectadosOrdem.get(1)+" N" + "," + alunosConectadosOrdem.get(2)+" N"+","+Util.nome_log());
        
    }

    ArrayList perguntas = new ArrayList();
    public int Nperguntas = 0;

    public void pegarPacote() {
        CodigoPacote pacoteEscolhido = new CodigoPacote();
        Sql novo = new Sql();
        ArrayList values = new ArrayList();
        ArrayList r = new ArrayList();
        values.add(pacoteEscolhido.getCodigoPacote());
        String[] retur = {"id_pergunta", "pontos", "resposta", "enunciado", "a", "b", "c", "d"};
        r = novo.executeQuery("SELECT * FROM `pergunta` WHERE codigo_pacote = ?", values, retur);

        for (int i = 0; i <= r.size() - 1; i++) {
            ArrayList pergunta = new ArrayList();
            String a = r.get(i).toString();
            String b = a.replace("[", "").replace("]", "");
            String[] tokens = b.split(",");
            pergunta.add(tokens[3]);
            pergunta.add(tokens[4]);
            pergunta.add(tokens[5]);
            pergunta.add(tokens[6]);
            pergunta.add(tokens[7]);
            pergunta.add(tokens[2]);
            pergunta.add(tokens[1]);
            pergunta.add(tokens[0]);
            perguntas.add(pergunta);
            Nperguntas++;
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
        
        list_alunosConectados.setItems(alunosConectados);
        label_nome.setText(Util.nome_log());
        label_alunosConectados.setText(" ");
        label_ip.setText(" ");
        BT_proximaPergunta.setText("Iniciar Servidor");
        CodigoPacote P = new CodigoPacote();
    }

    //SERVIDOR******************************************************************    
    ArrayList<Socket> clientes = new ArrayList();
    ArrayList pontos = new ArrayList();
    private ServerSocket serverSocket;
    Socket cliente;
    private int cont = 0;
    int atualClientes;
    boolean esperar = true;

    private void iniciarServidor(int porta) {
        try {            
            serverSocket = new ServerSocket(porta);                        
            System.out.println("SERVIDOR INICIADO!!!");
            esperarConexao.start();
            String ipDaMaquina = InetAddress.getLocalHost().getHostAddress();
            Platform.runLater(() -> label_ip.setText(ipDaMaquina));
            Platform.runLater(() -> label_alunosConectados.setText("Aguardando Alunos..."));
            Platform.runLater(() -> BT_proximaPergunta.setText("Começar Jogo"));
            verificarConexao.start();
        } catch (IOException ex) {
            System.err.println("----ERRO AO INICIAR O SERVIDOR----" + ex);
        }
    }

    Thread esperarConexao = new Thread() {
        public void run() {
            try {
                do {
                    System.out.println("ESPERANDO CONEXÃO");
                    
                    clientes.add(serverSocket.accept());
                    System.out.println("CLIENTE CONECTOU!!!!!");
                    receberMensagem(clientes.get(cont), cont);
                    pontos.add(0);
                    cont++;
                } while (esperar);
                System.out.println("----PARANDO DE ESPERAR CONEXÕES");
            } catch (Exception ex) {
                System.err.println("----ERRO ESPERAR CONEXÃO----");
            }
        }
    };
    
    Thread verificarConexao = new Thread(){
        public void run(){
            while(true){
                for (int i = 0; i < clientes.size(); i++) {
                    if(clientes.get(i).isClosed()){
                        alunosConectados.set(i,"Desconectado");
                    }
                }
            }
            
        }
    };
    
    private void enviarMensagem(Socket socket, String msg, int i) {
        ObjectOutputStream output;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeUTF(msg);
            output.flush();//cambio do rádio amador
            System.out.println("MENSAGEM ENVIADA COM SUCESSO!!!");
            Platform.runLater(() -> alunosConectados.set(i, alunosConectados.get(i)));
            receberMensagem(clientes.get(i), i);
        } catch (IOException ex) {
            Platform.runLater(() -> alunosConectados.set(i, alunosConectados.get(i) + " Desconectado"));
            --cont;
            System.err.println("----ERRO AO ENVIAR MENSAGEM----");
        }

    }

    private void enviarMensagens(String msg) {
        for (int i = 0; i < clientes.size(); i++) {
            enviarMensagem(clientes.get(i), msg, i);
        }
        atualClientes = 0;
        proximaPerguntas();
    }
    int i;

    private void receberMensagem(Socket socket, int a) {
        new Thread() {
            public void run() {
                try {
                    System.out.println("RECEBENDO MENSAGEM...");
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    String m = input.readUTF();
                    atualClientes++;
                    System.out.println("Mesagem: " + m);
                    tratarMensagem(m, a);
                } catch (IOException ex) {
                    Platform.runLater(() -> alunosConectados.set(a, alunosConectados.get(a) + " Desconectado"));
                    --cont;
                    System.err.println("----ERRO AO RECEBER MENSAGEM---- " + a);
                }
            }
        }.start();
    }

    public void tratarMensagem(String msg, int a) {
        String[] tokens = msg.split(",");
        if (tokens[0].equals("iniciar")) {
            Platform.runLater(() -> alunosConectados.add(tokens[1]));
            System.out.println("tokens:" + tokens[1]);
        } else {            
            Platform.runLater(() -> alunosConectados.set(a, alunosConectados.get(a) + " " + tokens[1]));
            System.out.println("tokens:" + tokens[1]);
            if (tokens[0].equals("resposta")) {
                pontos.set(a, tokens[1]);
            }
        }
    }

    private void receberMensagens() {
        System.out.println("numero de clientes no array:" + clientes.size());
        for (int i = 0; i < clientes.size(); i++) {
            receberMensagem(clientes.get(i), i);
        }
    }

    //**************************************************************************
    public void ListaPontos() {
        for (int j = 0; j < pontos.size(); j++) {
            System.out.println(alunosConectados.get(j) + ": " + pontos.get(j));
        }
    }

    //CRONOMETRO****************************************************************
    final SimpleDateFormat format = new SimpleDateFormat("ss");
    Timer timer = null;
    int s = 00, m = 00;
    String zero = "0", zero2 = "0";

    public void cronometro(boolean fluxo) {
        if(fluxo){
            s = 0;
            m = 0;
            zero = "0";
            zero2 = "0";
            if (timer == null) {
                timer = new Timer();
                TimerTask tarefa = new TimerTask() {
                    public void run() {
                        try {
                            s++;
                            if (s == 60) {
                                s = 0;
                                zero = "0";
                                m++;
                            }
                            if (s > 9) {
                                zero = "";
                            }
                            if (m > 9) {
                                zero2 = "";
                            }
                            Platform.runLater(() -> label_clock.setText(zero2 + m + ":" + zero + s));
                            //System.out.println("Hora: "+format.format(new Date().getTime()));      
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                };

                timer.scheduleAtFixedRate(tarefa, 0, 1000);
            }
        }else{        
            timer.cancel();
            Platform.runLater(() -> label_clock.setText("00:00"));
        }
    }    
}

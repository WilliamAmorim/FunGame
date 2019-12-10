/*
 * Todos os metodos relacionados a data serão feitos nessa classe
 */
package br.com.william.jogoquiz.util;

/**
 *
 * @author willi
 */
public class Data {
    /**
     * Esse metodo recebe uma data no formato 0000-00-00 e retorna ela como 00-00-0000 
     * @param data
     * @return 
     */
    public static String converterData(String data){
       String[] dividir = data.split("-");
       return dividir[2]+"/"+dividir[1]+"/"+dividir[0];
    }
}

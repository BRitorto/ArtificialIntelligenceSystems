package ar.edu.itba.sia.Utils;

import ar.edu.itba.sia.Engine.Items;
import ar.edu.itba.sia.Game.Item;

import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class ReadExcel {

    public List<Item> getItems(String path) throws IOException {
        List<Item> rta = new LinkedList<>();

        File excelFile = new File(path);
        BufferedReader TSVFile = new BufferedReader(new FileReader(excelFile));

        String firstLine = TSVFile.readLine(); //ignora primera linea
        String currLine = TSVFile.readLine();
        String[] aux;
        while(currLine != null){
            aux = currLine.split("\t");
            rta.add(new Item(Integer.valueOf(aux[0]).toString(),Double.parseDouble(aux[1]),Double.parseDouble(aux[2]),
                    Double.parseDouble(aux[3]),Double.parseDouble(aux[4]),Double.parseDouble(aux[5])));
            currLine=TSVFile.readLine();
        }
        return rta;
    }
}
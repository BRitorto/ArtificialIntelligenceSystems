package ar.edu.itba.sia.Utils;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.util.ArrayList;

public class DiversionGraph {
    private SwingWrapper<XYChart> sw;
    private XYChart diversionChart;
    private ArrayList<Double> div1;//buen ataque
    private ArrayList<Double> div2;//buena defensa
    private ArrayList<Double> div3;//ambos buenos
    private ArrayList<Double> div4;//resto
    private ArrayList<Double> generations;


    public DiversionGraph() {
        div1 = new ArrayList<>();
        div2 = new ArrayList<>();
        div3 = new ArrayList<>();
        div4 = new ArrayList<>();
        generations=new ArrayList<>();
        generations.add(0.0);
        div1.add(0.0);
        div2.add(0.0);
        div3.add(0.0);
        div4.add(0.0);


        diversionChart=new XYChartBuilder().width(800).height(600).title("Real time Diversity").xAxisTitle("Generations").yAxisTitle("Population Size").build();
        diversionChart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        diversionChart.getStyler().setMarkerSize(10);


        double[] div1Arr = div1.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] div2Arr = div2.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] div3Arr = div3.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] div4Arr = div4.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] genArr = generations.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference

        XYSeries series;
        series = (XYSeries) diversionChart.addSeries("Buen Ataque", genArr, div1Arr);

        series = (XYSeries) diversionChart.addSeries("Buena Defensa", genArr, div2Arr);
        series = (XYSeries) diversionChart.addSeries("Buen Ataque y Defensa", genArr, div3Arr);
        series = (XYSeries) diversionChart.addSeries("Resto", genArr, div4Arr);



        sw=new SwingWrapper<XYChart>(diversionChart);
        sw.displayChart();
    }
    public void onGenerationDiversion(int aux1,int aux2,int aux3,int aux4,long generation){
        div1.add((double) aux1);
        div2.add((double) aux2);
        div3.add((double) aux3);
        div4.add((double) aux4);
        generations.add((double) generation);

        double[] div1Arr = div1.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] div2Arr = div2.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] div3Arr = div3.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] div4Arr = div4.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] genArr = generations.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference

        addToDiversionGraph(div1Arr,div2Arr,div3Arr,div4Arr,genArr);

    }
    private void addToDiversionGraph(double[] div1, double[] div2, double[] div3, double[] div4 , double[] generations) {
        diversionChart.updateXYSeries("Buen Ataque", generations, div1, null);
        diversionChart.updateXYSeries("Buena Defensa", generations, div2, null);
        diversionChart.updateXYSeries("Buen Ataque y Defensa", generations, div3, null);
        diversionChart.updateXYSeries("Resto", generations, div4, null);

        sw.repaintChart();

    }


}

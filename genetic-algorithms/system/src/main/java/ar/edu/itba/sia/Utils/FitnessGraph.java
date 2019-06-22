package ar.edu.itba.sia.Utils;

import ar.edu.itba.sia.Generics.Selector;
import org.json.simple.JSONObject;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.util.ArrayList;

public class FitnessGraph {
    //MySwingWorker mySwingWorker;
    private SwingWrapper<XYChart> sw;
    private XYChart fitnessChart;
    private ArrayList<Double> maxFit;
    private ArrayList<Double> minFit;
    private ArrayList<Double> avgFit;
    private ArrayList<Double> generations;

    public FitnessGraph() {
        maxFit = new ArrayList<>();
        avgFit = new ArrayList<>();
        minFit = new ArrayList<>();
        generations = new ArrayList<>();
        generations.add(0.0);
        maxFit.add(0.0);
        minFit.add(0.0);
        avgFit.add(0.0);
        // Create Chart
        fitnessChart = new XYChartBuilder().width(800).height(600).title("Real time Fitness").xAxisTitle("Generaciones").yAxisTitle("Fitness").build();

        double[] maxArr = maxFit.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] genArr = generations.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] avgArr = avgFit.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] minArr = minFit.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference

        XYSeries series;
        series = (XYSeries) fitnessChart.addSeries("maxFitness", genArr, maxArr);
        series.setLineColor(Color.red);
        series.setLineWidth(1);
        series.setMarker(SeriesMarkers.NONE);

        series = fitnessChart.addSeries("minFitness", generations, minFit);
        series.setLineColor(Color.blue);
        series.setLineWidth(1);
        series.setMarker(SeriesMarkers.NONE);
        series = fitnessChart.addSeries("avgFitness", generations, avgFit);
        series.setLineColor(Color.green);
        series.setLineWidth(1);
        series.setMarker(SeriesMarkers.NONE);


        // Show it
        sw = new SwingWrapper<XYChart>(fitnessChart);
        sw.displayChart();
//


    }

    public void onGeneration(double max, double avg, double min, long generation) {
        maxFit.add(max);
        avgFit.add(avg);
        minFit.add(min);
        generations.add((double) generation);
        double[] maxArr = maxFit.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] genArr = generations.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] minArr = minFit.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference
        double[] avgArr = avgFit.stream().mapToDouble(Double::doubleValue).toArray(); //via method reference

        /*
        System.out.print("MAX: ");
        for (Double d : maxFit) {
            System.out.print(d + ",");
        }
        System.out.println();
        System.out.print("Min: ");
        for (Double d : minFit) {
            System.out.print(d + ",");
        }
        System.out.println();
        System.out.println();
        */
        //System.out.printf(". ");

        addToGraph(maxArr,avgArr,minArr,genArr);
    }

    private void addToGraph(double[] maxFit, double[] avgFit, double[] minFit, double[] generations) {
        fitnessChart.updateXYSeries("maxFitness", generations, maxFit, null);
        fitnessChart.updateXYSeries("avgFitness", generations, avgFit, null);

        fitnessChart.updateXYSeries("minFitness", generations, minFit, null);
        sw.repaintChart();
    }


}

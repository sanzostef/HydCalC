package HydCalC;

import javafx.collections.ObservableList;
import org.jetbrains.annotations.Contract;

import java.util.HashMap;

public class DeltaP {

    private double DeltaP; // Bar
    private double masseVolumique;  // Kg/m3
    private double vitEcoulement;  //m/s
    private double coeff; //1.12 coude 90 - 3 epingle - 0.1 tes tout droit
   // private double Re;
   //private double visco;
    private double D; //mm
    private double Q;  //l/min

    public void setD(double d){this.D = d;}
    public void setQ(double q){this.Q = q;}

   // private void calculerRE(){
   //     this.Re = (this.vitEcoulement*this.D)/this.visco;
   // }

    public void setVitEcoulement(double vitecoulement){}
    public double getVitEcoulement(){ return this.vitEcoulement; }
    public void calculerVitEcoulement(){
        if (this.D!=0 & this.Q!=0){ this.vitEcoulement = this.Q / (6*Math.PI*Math.pow(this.D/20,2));}
    }

    public void setMasseVolumique(double m){}
    public double getMasseVolumique(){ return this.masseVolumique;}

    public void setCoeff(double c){}
    public static HashMap<String, Double> getCoeff(){
        return new HashMap<>();}

    public void setDeltaP(double deltap){this.DeltaP = deltap; }
    public double getDeltaP(){ return this.DeltaP;}
    public void calculerDeltaP(){
        this.DeltaP = this.coeff * (this.masseVolumique * Math.pow(this.vitEcoulement,2)) / 200000;
    }
/*
    public static ObservableList<Double> getPlanetList() {
        Planet mercury = new Planet("MER", "Mercury");
        Planet venus = new Planet("VEN", "Venus");
        Planet earth = new Planet("EAR", "Earth");

        ObservableList<Planet> list //
                = FXCollections.observableArrayList(mercury, venus, earth);

        return list;
    }
    */

}


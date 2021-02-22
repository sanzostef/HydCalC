package HydCalC.Class;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeltaPSing {

    private double DeltaP; // Bar
    private double masseVol;  // Kg/m3
    private double vitEcoulement;  //m/s
    private double coeff; //1.12 coude 90 - 3 epingle - 0.1 tes tout droit
    private double diam; //mm

    public void setdiam(double d){this.diam = d;}
    public double getdiam(){ return this.diam;}
    public void calculerdiam(){}

    public void setVitEcoulement(double vitecoulement){ this.vitEcoulement = vitecoulement;}
    public double getVitEcoulement(){ return this.vitEcoulement; }
    public void calculerVitEcoulement(double Q){
        if (this.diam!=0){ this.vitEcoulement = Q / (6*Math.PI*Math.pow(this.diam/20,2));}
    }

    public void setmasseVol(double m){ this.masseVol = m;}
    public double getmasseVol(){ return this.masseVol;}
    public void calculermasseVol(){}

    public void setCoeff(double c){ this.coeff = c;}

    public void setDeltaP(double deltap){this.DeltaP = deltap; }
    public double getDeltaP(){ return this.DeltaP;}
    public void calculerDeltaP(){
        this.DeltaP = this.coeff * (this.masseVol * Math.pow(this.vitEcoulement,2)/2) / 100000;
    }

    public class Incident{
        String nom;
        public double coeff;
    	public Incident(String n,double c){
    		this.nom = n;
    		this.coeff = c;
    	}
        @Override
        public String toString(){ return this.nom + " - " + coeff; }
    }
    public ObservableList<Incident> getIncidentList() {
        Incident coude90 = new Incident("coude 90°", 1.12);
        Incident coude45 = new Incident("coude épingle 45°", 3.0);
        Incident red10x = new Incident("Réduction 10 x Ø", 0.4);
        Incident red5x = new Incident("Réduction 5 x Ø", 0.38);
        Incident red2x = new Incident("Réduction 2 x Ø", 0.24);
        Incident red10pourcent = new Incident("Réduction 10% du Ø", 0.015);
        Incident vannepapillon5 = new Incident("Vanne papillon fermée 5°", 0.24);
        Incident vannepapillon45 = new Incident("Vanne papillon fermée 45°", 19);
        Incident vannepapillon70 = new Incident("Vanne papillon fermée 70°", 750);
        Incident vanneBoisseau5 = new Incident("Vanne boisseau fermée 5°", 0.05);
        Incident vanneBoisseau15 = new Incident("Vanne boisseau fermée 15°", 0.75);
        Incident vanneBoisseau45 = new Incident("Vanne boisseau fermée 45°", 31);
        return FXCollections.observableArrayList(coude90, coude45, red10x,red5x,red2x,red10pourcent,vannepapillon5,vannepapillon45,vannepapillon70,vanneBoisseau5,vanneBoisseau15,vanneBoisseau45);
    }
}

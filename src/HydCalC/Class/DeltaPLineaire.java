package HydCalC.Class;

public class DeltaPLineaire {

    private double deltaP; // Bar
    private double masseVol;  // Kg/m3
    private double vitEcoulement;  //m/s
    private double diam; //mm
    private double L; //m
    private double visco; //cst
    private double Re;
    private double lambda;

    public void setdiam(double d){this.diam = d;}
    public double getdiam(){ return this.diam;}
    public void calculerdiam(){}

    public void setL(double l){this.L = l;}
    public double getL(){ return this.L;}
    public void calculerL(){}

    public void setVitEcoulement(double vitecoulement){ this.vitEcoulement = vitecoulement;}
    public double getVitEcoulement(){ return this.vitEcoulement; }
    public void calculerVitEcoulement(double Q){
        if (this.diam!=0){ this.vitEcoulement = Q / (6*Math.PI*Math.pow(this.diam/20,2));}
    }
    public void setmasseVolL(double m){ this.masseVol = m;}
    public double getmasseVolL(){ return this.masseVol;}
    public void calculermasseVolL(){}

    public void setDeltaP(double dp) { this.deltaP = dp;}
    public double getDeltaP() { return this.deltaP;}
    public void calculerDeltaP(){
        if (Re<1200) //laminaire
            this.lambda = 64/this.Re;
        if (Re>=1200) //turbulent
            this.lambda = 0.316/Math.pow(Re,0.25);
        this.deltaP = (lambda*L*masseVol*Math.pow(vitEcoulement,2)/(diam/1000*2))/100000;
    }
    public void setRE(double re){this.Re = re;}
    public double getRE(){ return this.Re;}
    public void calculerRE(double Q){
        //this.Re = (this.vitEcoulement*this.D)/this.visco;
        this.Re = 21.2*(Q/(diam/100*visco/100));
    }
    public void setvisco(double v) {this.visco = v;}
    public double getvisco() {return this.visco;}
    public void calculervisco(){}
}
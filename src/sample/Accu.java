package sample;

public class Accu {

    private double V0;
    private double V1;
    private double V2;
    private double P0;
    private double P1;
    private double P2;
    private double DeltaV;
    private double n;

    public void setV0(double v) {this.V0 = v;}
    public double getV0() {return this.V0;}
    public void calculerV0() {}

    public void setV1(double v) {this.V1 = v;}
    public double getV1() {return this.V1;}
    public void calculerV1() {}

    public void setV2(double v) {this.V2 = v;}
    public double getV2() {return this.V2;}
    public void calculerV2() {}

    public void setP0(double v) {this.P0 = v;}
    public double getP0() {return this.P0;}
    public void calculerP0() {}

    public void setP1(double v) {this.P1 = v;}
    public double getP1() {return this.P1;}
    public void calculerP1() {}

    public void setP2(double v) {this.P2 = v;}
    public double getP2() {return this.P2;}
    public void calculerP2() {}

    public void setDeltaV(double v) {this.P1 = v;}
    public double getDeltaV() {return this.P1;}
    public void calculerDeltaV() {
        this.DeltaV = (this.P0*this.V0/this.P1)*(1-Math.pow((this.P1/this.P2),1/this.n));
    }

    public void setn(double v) {this.P2 = v;}
    public double getn() {return this.P2;}
    public void calculern() {}



    public static void main(String[] args) {

    }
}

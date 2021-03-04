package HydCalC.Class;

public class Accu {

    private double V0;
    private double V1;
    private double V2;
    private double P0;
    private double P1;
    private double P2;
    private double DeltaV;
    private double n = 1.4;
    private double cste;
    private double cste1;
    private double cste2;
    private double cste3;
    public void Cste(int raz){
        this.cste = raz;
    }
    public void Cste(){
        if (this.V0!=0 & this.P0!=0){ cste1 = this.P0 * Math.pow(this.V0,this.n); }
        if (this.V1!=0 & this.P1!=0){ cste2 = this.P1 * Math.pow(this.V1,this.n); }
        if (this.V2!=0 & this.P2!=0){ cste3 = this.P2 * Math.pow(this.V2,this.n); }
        if (cste1!=0 & cste2!=0 & cste3!=0){
        	if (cste1!=0){
                this.cste = cste1;
                return;
            }
        	if (cste2!=cste3)
        		return;
        	this.cste = cste1;
        }
        else if(cste1!=0) this.cste = cste1;
        else if(cste2!=0) this.cste = cste2;
        else if(cste3!=0) this.cste = cste3;
    }

    public void setV0(double v0) {this.V0 = v0;}
    public double getV0() {return this.V0;}
    public void calculerV0() {
        if ( this.P1!=0 & this.P0!=0 & this.P2!=0 & this.DeltaV!=0) {
            this.V0 = ((this.DeltaV * this.P1) / (this.P0 * (1 - (Math.pow((this.P1 / this.P2), 1 / this.n)))));
            return;
        }
        if (this.cste!=0 & this.P0!=0){ this.V0 = Math.pow(this.cste/this.P0,1/this.n); }
    }

    public void setV1(double v1) {this.V1 = v1;}
    public double getV1() {return this.V1;}
    public void calculerV1() {
        if (this.DeltaV!=0 & this.V2!=0) {
            this.V1 = this.DeltaV + this.V2;
            return;
        }
        if (this.cste!=0 & this.P1!=0){ this.V1 = Math.pow(this.cste/this.P1,1/this.n); }
    }

    public void setV2(double v2) {this.V2 = v2;}
    public double getV2() {return this.V2;}
    public void calculerV2() {
        if (this.DeltaV!=0 & this.V1!=0) {
            this.V2 = this.V1 - this.DeltaV;
            return;
        }
        if (this.cste!=0 & this.P2!=0){ this.V2 = Math.pow(this.cste/this.P2,1/this.n); }
    }

    public void setP0(double p0) {this.P0 = p0;}
    public double getP0() {return this.P0;}
    public void calculerP0() {
      //  if (this.P1!=0)
      //      this.P0 = 0.9*this.P1;
        if (this.P0==0 & this.DeltaV!=0 & this.P1!=0 & this.V0!=0 & this.P1!=0 & this.P2!=0) {
            this.P0 = ((this.DeltaV * this.P1) / (this.V0 * (1 - (Math.pow((this.P1 / this.P2), 1 / this.n)))));
            return;
        }
        if (this.cste!=0 & this.V0!=0){ this.P0 = this.cste/Math.pow(this.V0,this.n);  }
    }

    public void setP1(double p1) {
        this.P1 = p1;
        this.P0 = 0.9 * this.P1;
    }
    public double getP1() {return this.P1;}
    public void calculerP1() {
        if (this.cste!=0 & this.V1!=0){ this.P1 = this.cste/Math.pow(this.V1,this.n);  }
    }

    public void setP2(double p2) {this.P2 = p2;}
    public double getP2() {return this.P2;}
    public void calculerP2() {
        if (this.P1!=0 & this.DeltaV!=0 & this.P0!=0 & this.V0!=0)
            this.P2 = this.P1/(Math.pow(1-((this.DeltaV*this.P1)/(this.P0*this.V0)),this.n));
        if (this.cste!=0 & this.V2!=0){ this.P2 = this.cste/Math.pow(this.V2,this.n); }
    }

    public void setDeltaV(double deltaV) {this.DeltaV = deltaV;}
    public double getDeltaV() {return this.DeltaV;}
    public void calculerDeltaV() {
        if (this.P0 != 0 & this.P1!=0 & this.P2!=0 & this.V0!=0)
            this.DeltaV = (this.P0*this.V0/this.P1)*(1-Math.pow((this.P1/this.P2),1/this.n));
        if (this.V1!=0 & this.V2!=0)
            this.DeltaV = this.V1 - this.V2;
    }

    public void setn(double n) {this.n = n;}
    public double getn() {return this.n;}
    public void calculern() {}

}

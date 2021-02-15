package HydCalC.Class;

public class Pump {
    private double cyl;
    private double pression;
    private double debit;
    private double vitDeRotation;
    private double pwrHyd;
    private double pwrMeca;
    private double rendement;

    public void setCyl(double q){	this.cyl = q; }
    public double getCyl(){	return this.cyl; }
    public void calculerCyl() {
        if (this.debit != 0.0d & this.vitDeRotation != 0.0d)
            this.cyl = (1000 * this.debit) / this.vitDeRotation;
    }

    public void setDebit(double Q){	this.debit = Q;	}
    public double getDebit(){	return this.debit; }
    public void calculerDebit() {
        if (this.pression != 0.0d & this.pwrHyd != 0.0d){
            this.debit = (600 * this.pwrHyd) / this.pression;
        }
        if (this.cyl != 0.0d & this.vitDeRotation != 0.0d){
            this.debit = (this.vitDeRotation * this.cyl) / 1000;
        }
    }
    public void calculerDebit(double S, double vit){
        if (S != 0.0d & vit != 0.0d)
            this.debit = (6 * vit * S);
    }

    public void setPression(double P){	this.pression = P; }
    public double getPression(){	return this.pression; }
    public void calculerPression(){
        if (this.pwrHyd != 0.0d & this.debit != 0.0d)
            this.pression = (600 * this.pwrHyd) / this.debit;
    }
    public void calculerPression(double S, double F){
        if (F != 0.0d & S != 0.0d)
            this.pression = F*1000 / S;
    }

    public void setVitDeRotation(double n){	this.vitDeRotation = n;	}
    public double getVitDeRotation(){	return this.vitDeRotation; }
    public void calculerVitDeRotation(){
        if (this.cyl != 0.0d & this.debit != 0.0d){
            this.vitDeRotation = (1000 * this.debit) / this.cyl;
        }
    }

    public void setPwrHyd(double W){	this.pwrHyd = W; }
    public double getPwrHyd(){	return this.pwrHyd;	}
    public void calculerPwrHyd() {
        if (this.pression != 0.0d & this.debit != 0.0d) {
            this.pwrHyd = (this.pression * this.debit) / 600;
        }
        if (this.pwrMeca != 0.0d & this.rendement != 0.0d)
            this.pwrHyd = this.pwrMeca * this.rendement / 100;
    }

    public void setPwrMeca(double pwr){	this.pwrMeca = pwr;	}
    public double getPwrMeca(){	return this.pwrMeca;	}
    public void calculerPwrMeca() {
        if (this.pwrHyd != 0.0d & this.rendement != 0.0d) {
            this.pwrMeca = this.pwrHyd / this.rendement * 100;
        }
    }

    public void setRendement(double n){ this.rendement = n;	}
    public double getRendement(){	return this.rendement;	}
    public void calculerRendement(){
        if (this.pwrHyd != 0.0d & this.pwrMeca != 0.0d)
            this.rendement = this.pwrHyd / this.pwrMeca * 100;
    }
}

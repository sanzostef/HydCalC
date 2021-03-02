package HydCalC.Class;

public class Moteur {
    private double couple;
    private double cyl;
    private double vitDeRotation;
    private double pwrMeca;
    private double pwrHyd;
    private double ηhm = 0.85;
    private double ηvol = 0.97;
    private double ηtot = ηvol * ηhm;
    private double debit;
    private double pression;

    public void setCouple(double c) {
        this.couple = c;
    }
    public double getCouple() {
        return this.couple;
    }
    public void calculerCouple() {
        if (this.cyl != 0.0d & this.pression != 0.0d) {
            this.couple = ((this.cyl * this.pression / 200 * Math.PI * ηhm));
        }
    }

    public void setCyl(double q) {
        this.ηvol = q;
    }
    public double getCyl() {        return this.cyl;    }
    public void calculerCyl() {
        if (this.debit != 0.0d & this.vitDeRotation != 0.0d)
            this.cyl = (1000 * this.debit) / (this.vitDeRotation * ηvol);
    }

    public void setVitDeRotation(double n) {        this.vitDeRotation = n;    }
    public double getVitDeRotation() {        return this.vitDeRotation;    }
    public void calculerVitDeRotation() {
        if (this.cyl != 0.0d & this.debit != 0.0d) {
            this.vitDeRotation = ((1000 * this.debit) / this.cyl) * ηvol;
        }
    }

    public void setPwrMeca(double pwr) {        this.pwrMeca = pwr;    }
    public double getPwrMeca() {        return this.pwrMeca;    }
    public void calculerPwrMeca() {
        if (this.pwrHyd != 0.0d & this.ηtot != 0.0d) {
            this.pwrMeca = this.pwrHyd * this.ηtot;
        }
    }
    public void setPwrHyd(double W) {        this.pwrHyd = W;    }
    public double getPwrHyd() {        return this.pwrHyd;    }
    public void calculerPwrHyd() {
        if (this.pression != 0.0d & this.debit != 0.0d) {
            this.pwrHyd = ((this.pression * this.debit) / 600);
        }
        if (this.pwrMeca != 0.0d & this.ηtot != 0.0d)
            this.pwrHyd = this.pwrMeca / this.ηtot;
    }
    public void setηtot(double n) {        this.ηtot = n;    }
    public double getηtot() {        return this.ηtot;    }
    public void calculerηtot() {
        if (this.ηvol != 0.0d & this.ηhm != 0.0d)
            this.ηtot = this.ηvol * this.ηhm;
    }

    public void setηvol(double n) {        this.ηvol = n;    }
    public double getηvol() {        return this.ηvol;    }
    public void calculerηvol() {
    }

    public void setηhm(double n) {        this.ηhm = n;    }
    public double getηhm() {        return this.ηhm;    }
    public void calculerηhm() {
    }

    public void setPression(double P) {        this.pression = P;    }
    public double getPression() {        return this.pression;    }
    public void calculerPression() {
        if (this.pwrMeca != 0.0d & this.debit != 0.0d)
            this.pression = (600 * this.pwrMeca / ηtot) / this.debit;
    }
}


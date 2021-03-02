package HydCalC.Class;

public class Gicleur {

    private double deltaP; // Bar
    private double diamOrifice;  // Kg/m3
    private double debit;

    public double getdebit() {
        return this.debit;
    }
    public void calculerDebit() {
        if (this.diamOrifice != 0 & this.deltaP != 0){ this.debit = (0.5 * diamOrifice * diamOrifice) * (Math.sqrt(deltaP)); }
    }
    public void setDebit(double Q) { this.debit = Q; }

    public double getDiamOrifice() {
        return this.diamOrifice;
    }

    public void calculerDiamOrifice() {
        if (this.debit != 0 & this.deltaP != 0)
            this.diamOrifice = Math.sqrt(this.debit / (0.5 * (Math.sqrt(deltaP))));
    }

    public void setDiamOrifice(double diamOrifice) {
        this.diamOrifice = diamOrifice;
    }

    public double getDeltaPOrifice() {
        return this.deltaP;
    }

    public void calculerDeltaPOrifice() {
        if (this.debit != 0 & this.diamOrifice != 0)
            this.deltaP = (this.debit / (0.5 * this.diamOrifice * this.diamOrifice)) * (this.debit / (0.5 * this.diamOrifice * this.diamOrifice));
    }

    public void setDeltaPOrifice(double deltaP) {
        this.deltaP = deltaP;
    }
}
package sample;

public class Verin {
    private double DFond, DTige, course;
    private double r, rInv;
    private double SFond, SAnnulaire, SDeLaTige;
    private double VFond, VTige, VDeLaTige;
    private double forceSortie, forceRentree, forceDiff;
    private double vitSortie, vitRentree, vitDiff;
    private double tpsSortie, tpsRentree, tpsDiff;

    public void setDFond(double DFond) {    this.DFond = DFond; }
    public double getDFond() {  return this.DFond;  }
    public void calculerDFond() {
        this.DFond = 20*(Math.sqrt(this.SFond/Math.PI));
    }

    public void setDTige(double DTige) {    this.DTige = DTige; }
    public double getDTige() {  return  this.DTige; }
    public void calculerDTige() {
        if (this.SFond != 0.0d & SAnnulaire != 0){
            //    SFond = (Math.PI * Math.pow((this.DFond/20), 2.0));
            this.SDeLaTige = this.SFond - this.SAnnulaire;
            this.DTige = 20*(Math.sqrt(this.SDeLaTige/Math.PI));
            return;
        }
        if (this.r != 0.0d){
            this.SFond = this.SAnnulaire*r;
            this.SDeLaTige = this.SFond - this.SAnnulaire;
            this.DTige = 20*(Math.sqrt(this.SDeLaTige/Math.PI));
            return;
        }
        if (this.rInv != 0.0d){
            this.SFond = this.SAnnulaire/this.rInv;
            this.SDeLaTige = this.SFond - this.SAnnulaire;
            this.DTige = 20*(Math.sqrt(this.SDeLaTige/Math.PI));
            return;
        }
        if (this.VFond != 0.0d & this.course != 0.0d){
            SFond = this.VFond/this.course*10000;
            if (SAnnulaire != 0.0d) {
                this.SDeLaTige = this.SFond - this.SAnnulaire;
                this.DTige = 20*(Math.sqrt(this.SDeLaTige/Math.PI));
            }
        }
    }

    public void setSFond(double SFond) {    this.SFond = SFond; }
    public double getSFond() {  return this.SFond;  }
    public void calculerSFond() {
        if (this.DFond != 0.0d){
            this.SFond = (Math.PI * Math.pow((this.DFond/20), 2.0));
            return;
        }
        if (this.SAnnulaire != 0.0d & this.r != 0.0d){
            this.SFond = this.getSAnnulaire() * r;
            return;
        }
        if (this.SAnnulaire != 0.0d & this.rInv != 0.0d){
            this.SFond = this.getSAnnulaire() / rInv;
            return;
        }
        if (this.VFond != 0.0d & this.course != 0.0d){
            this.SFond = this.VFond/this.course*10000;
        }
    }
    public void calculerSFond(double P) {
        if (this.forceSortie != 0)
            this.SFond = this.forceSortie * 1000 / P;
    }

    public void setSAnnulaire(double SAnnulaire)  { this.SAnnulaire = SAnnulaire; }
    public double getSAnnulaire() { return this.SAnnulaire; }
    public void calculerSAnnulaire() {
        if (this.DTige !=0 & this.DFond != 0){
            this.SDeLaTige = (Math.PI * Math.pow((this.DTige/20), 2.0));
            this.SFond = (Math.PI * Math.pow((this.DFond/20), 2.0));
            this.SAnnulaire = this.SFond - this.SDeLaTige;
            return;
        }
        if (this.SFond != 0.0d & r != 0){
            this.SAnnulaire = this.SFond/r;
            return;
        }
        if (this.VTige != 0.0d & this.course != 0.0d){  this.SAnnulaire = this.VTige/this.course*10000; }
    }
    public void calculerSAnnulaire(double P) {
        if (this.forceRentree != 0)
            this.SAnnulaire = this.forceRentree * 1000 / P;
    }

    public double getSDeLaTige() {  return this.SDeLaTige; }
    public void calculerSDeLaTige() {
        if (this.SFond != 0.0d & this.SAnnulaire != 0.0d) {
            this.SDeLaTige = SFond - SAnnulaire;
            return;
        }
        if (this.SFond != 0.0d & this.r != 0.0d) {
            this.SDeLaTige = SFond - SFond / r;
            return;
        }
        if (this.SAnnulaire != 0.0d & this.r != 0.0d) {
            this.SDeLaTige = SAnnulaire * 2 - SAnnulaire;
            return;
        }
        if (this.forceDiff != 0.0d & this.forceSortie != 0.0d & this.SFond != 0.0d){
            double P = this.forceSortie*1000/this.SFond;
            this.SDeLaTige = this.forceDiff*1000/P;
        }
    }

    public void setRap(double rap){ if (this.rInv != 0.0d) this.r = rap; }
    public void setRInv(double rInv) { if (this.r != 0.0d) this.rInv = rInv; }
    public double getRap(){return this.r;}
    public double getRInv(){return this.rInv;}
    public void calculerRap(){
        if (this.SAnnulaire != 0.0d & this.SFond!=0.0d) {
            this.r = this.SFond / this.SAnnulaire;
            this.rInv = 1 / this.r;
            return;
        }
        if (this.r != 0.0d) {
            this.rInv = 1/this.r;
            return;
        }
        if (this.rInv != 0.0d) {
            this.r = 1/this.rInv;
            return;
        }
        if (this.VFond !=0 & this.VTige != 0){
            this.r = this.VFond / this.VTige;
            return;
        }
        if (this.tpsSortie !=0 & this.tpsRentree != 0){
            this.r = this.tpsSortie / this.tpsRentree;
        }
        if (this.forceSortie !=0 & this.forceRentree != 0){
            this.r = this.forceSortie / this.forceRentree;
        }
    }
    public void calculerRInv(){ if (this.r != 0.0d) this.rInv = 1 / r; }

    public void setVFond(double VFond){ this.VFond = VFond;}
    public double getVFond(){return this.VFond;}
    public void calculerVFond(){
        if (this.SFond != 0.0d & this.course != 0.0d){
            this.VFond = this.SFond/100 * this.course/100;
        }
    }
    public void calculerVFond(double Q){
        if (this.tpsSortie !=0){
            this.VFond = Q * this.tpsSortie/60;
        }
    }

    public void setVTige(double VTige){ this.VTige = VTige;}
    public double getVTige(){return this.VTige;}
    public void calculerVTige(){
        if (this.DTige != 0.0d & this.course != 0.0d){
            this.VDeLaTige = this.SDeLaTige/100 * this.course/100;
            if (this.VFond != 0.0d){
                this.VTige = this.VFond - VDeLaTige;
            }
        }
        if (this.SAnnulaire != 0){
            this.VTige = this.SAnnulaire/100 * this.course/100;
        }
    }
    public void calculerVTige(double Q){
        if (this.tpsRentree !=0){
            this.VTige = Q * this.tpsRentree/60;
        }
    }

    public double getCourse() { return this.course;  }
    public void setCourse(double course) {  this.course = course;  }
    public void calculerCourse() {
        if (this.SFond != 0.0d & this.VFond != 0.0d) {
            this.course = this.VFond * 10000 / this.SFond;
            return;
        }
        if (this.SAnnulaire != 0.0d & this.VTige != 0.0d) {
            this.course = this.VTige * 10000 / this.SAnnulaire;
            return;
        }
        if(this.vitRentree != 0.0d & this.tpsRentree != 0.0d){
            this.course = this.tpsRentree*1000 * this.vitRentree;
            return;
        }
        if(this.vitSortie != 0.0d & this.tpsSortie != 0.0d){
            this.course = this.tpsSortie*1000 * this.vitSortie;
        }
    }

    public void setForceSortie(double F){ this.forceSortie = F; }
    public double getForceSortie(){ return this.forceSortie; }
    public void calculerForceSortie(){
        if (this.forceRentree != 0 & this.forceDiff != 0)
            this.forceSortie = this.forceRentree - this.forceDiff;
    }
    public void calculerForceSortie(double P){
        if(this.SFond != 0)
            this.forceSortie = (P * this.SFond)/1000;
    }

    public void setForceRentree(double F){  this.forceRentree = F;  }
    public double getForceRentree(){ return this.forceRentree;}
    public void calculerForceRentree(){
        if (this.forceSortie != 0 & this.forceDiff != 0)
            this.forceRentree = this.forceSortie - this.forceDiff;
    }
    public void calculerForceRentree(double P){
        if (this.SAnnulaire != 0)
            this.forceRentree = (P * this.SAnnulaire)/1000;
    }

    public void setForceDiff(double F){  this.forceDiff = F;  }
    public double getForceDiff(){ return this.forceDiff;}
    public void calculerForceDiff(){
        if (this.forceRentree != 0 & this.forceSortie != 0)
            this.forceDiff = this.forceSortie - this.forceRentree;
    }
    public void calculerForceDiff(double P){
        if ( this.SDeLaTige != 0)
            this.forceDiff = (P * this.SDeLaTige)/1000;
    }

    public void setVitSortie(double n){ this.vitSortie = n; }
    public double getVitSortie(){ return this.vitSortie;  }
    public void calculerVitSortie(){
        if (this.tpsSortie !=  0.0d) { this.vitSortie = this.course / this.tpsSortie / 1000; }
    }public void calculerVitSortie(double Q){
        if (this.SFond !=0)
            this.vitSortie = Q / (6 * this.SFond);
    }

    public void setVitRentree(double n){ this.vitRentree = n; }
    public double getVitRentree(){ return this.vitRentree;  }
    public void calculerVitRentree(){
        if (this.tpsRentree !=  0.0d) { this.vitRentree = this.course / this.tpsRentree / 1000; }
    }public void calculerVitRentree(double Q){
        if (this.SAnnulaire != 0)
            this.vitRentree = Q / (6 * this.SAnnulaire);
    }

    public void setVitDiff(double n){ this.vitDiff = n; }
    public double getVitDiff(){ return this.vitDiff;  }
    public void calculerVitDiff(){
        if (this.tpsDiff !=  0.0d) { this.vitDiff = this.course / this.tpsDiff / 1000; }
    }
    public void calculerVitDiff(double Q){
        if (this.SDeLaTige != 0)
            this.vitDiff = Q / (6 * this.SDeLaTige);
    }

    public void setTpsSortie(double t){ this.tpsSortie = t; }
    public double getTpsSortie(){ return this.tpsSortie;  }
    public void calculerTpsSortie() {
        if (this.vitSortie != 0.0d) { this.tpsSortie = this.course / this.vitSortie / 1000; }
    }
    public void calculerTpsSortie(double Q){
        if (this.VFond != 0.0d){    this.tpsSortie =  VFond*60/Q;   }
    }


    public void setTpsRentree(double t){ this.tpsRentree = t; }
    public double getTpsRentree(){ return this.tpsRentree;  }
    public void calculerTpsRentree(){
        if (this.vitRentree != 0.0d) { this.tpsRentree = this.course / this.vitRentree/1000; }
    }
    public void calculerTpsRentree(double Q){
        if (this.VTige != 0.0d){    this.tpsRentree =  VTige*60/Q;   }
    }

    public void setTpsDiff(double t){ this.tpsDiff = t; }
    public double getTpsDiff(){ return this.tpsDiff;  }
    public void calculerTpsDiff(){
        if (this.vitDiff != 0.0d) { this.tpsDiff = this.course / this.vitDiff/1000; }
    }
    public void calculerTpsDiff(double Q){
        if (this.VDeLaTige != 0.0d){    this.tpsDiff =  VDeLaTige*60/Q;   }
    }
}


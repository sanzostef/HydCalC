package HydCalC.Class;

public class Client {

    private String raisonSociale;
    private String contact;
    private String telephone;
    private String mail;
    private String adresse;
    private String telephoneContact;

	/*public Client(String raison) {
		this.raisonSociale = raison;
	}*/

    public void setContact(String contact){ this.contact = contact;}
    public String getContact(){ return this.contact;}

    public void setRaisonSociale(String raison){ this.raisonSociale = raison;}
    public String getRaisonSociale(){ return this.raisonSociale;}

    public void setTelephone(String tel){ this.telephone = tel;}
    public String getTelephone(){ return this.telephone;}

    public void setMail(String email){ this.mail = email;}
    public String getMail(){ return this.mail;}

    public void setAdresse(String adress){ this.adresse = adress;}
    public String getAdresse(){ return this.adresse;}

    public void setTelephoneContact(String tel){ this.telephoneContact = tel;}
    public String getTelephoneContact(){ return this.telephoneContact;}
}
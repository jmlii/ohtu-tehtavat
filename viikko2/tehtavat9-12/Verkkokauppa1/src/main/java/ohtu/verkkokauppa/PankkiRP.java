package ohtu.verkkokauppa;
    
public interface PankkiRP {

    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
    
}

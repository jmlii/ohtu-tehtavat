package ohtu.verkkokauppa;

public class Pankki implements PankkiRP {

// getInstance-metodi ja staattinen instance-muuttuja poistetaan:
//    private static Pankki instanssi;
//
//    public static Pankki getInstance() {
//        if (instanssi == null) {
//            instanssi = new Pankki();
//        }
//
//        return instanssi;
//    }
    
    //Muutetaan riippuvuus Kirjanpito-luokkaan riippuvuudeksi rajapintaan 
    private KirjanpitoRP kirjanpito;

    // injektoidaaan kirjanpitorajapinta:
    public Pankki(KirjanpitoRP kirjanpito) {
        this.kirjanpito = kirjanpito;
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}

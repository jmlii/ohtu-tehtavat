
package ohtu.verkkokauppa;

import java.util.ArrayList;

public class Kirjanpito implements KirjanpitoRP {

// getInstance-metodi ja staattinen instance-muuttuja poistetaan:
//    private static Kirjanpito instance;
//    
//    public static Kirjanpito getInstance() {
//        if ( instance==null) {
//            instance = new Kirjanpito();
//        }
//        
//        return instance;
//    }
    
    private ArrayList<String> tapahtumat;

// private-konstruktori muutetaan julkiseksi:
    public Kirjanpito() {
        tapahtumat = new ArrayList<String>();
    }
    
    @Override
    public void lisaaTapahtuma(String tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    @Override
    public ArrayList<String> getTapahtumat() {
        return tapahtumat;
    }       
}

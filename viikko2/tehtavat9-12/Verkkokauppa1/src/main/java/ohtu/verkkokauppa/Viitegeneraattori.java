package ohtu.verkkokauppa;

public class Viitegeneraattori implements ViitegeneraattoriRP {

// getInstance-metodi ja staattinen instance-muuttuja poistetaan:
//    private static Viitegeneraattori instanssi;
//
//    public static Viitegeneraattori getInstance() {
//        if (instanssi == null) {
//            instanssi = new Viitegeneraattori();
//        }
//
//        return instanssi;
//    }
    
    private int seuraava;
    
    // private-konstruktori muutetaan julkiseksi:
    public Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}

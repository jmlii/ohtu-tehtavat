
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        luoTaulukko(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (tarkistaLukuPositiivinen(kapasiteetti) == true) {
            luoTaulukko(kapasiteetti, OLETUSKASVATUS);
        }
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (tarkistaLukuPositiivinen(kapasiteetti) == true &&
                tarkistaLukuPositiivinen(kasvatuskoko) == true) { 
            luoTaulukko(kapasiteetti, kasvatuskoko);
        }
    }
    
    private boolean tarkistaLukuPositiivinen(int luku) {
        if (luku < 0) {
            return false;
        } else return true;
    }
    
    private void luoTaulukko(int kapasiteetti, int kasvatuskoko){
        this.taulukko = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
        alkioidenLkm = 0;
    }
    
    public boolean lisaa(int luku) { 
        if (!kuuluu(luku)) {
            if (alkioidenLkm == taulukko.length) {
                taulukko = luoIsompiTaulukko();
            }
            taulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            return true;
        }
        return false;
    }
    
    public boolean kuuluu(int luku) {
        boolean kuuluu = false;
        if (getKohta(luku) != -1) {
            kuuluu = true;
        }
        return kuuluu;
    }
    
    private int getKohta(int luku) {
        int kohta = -1;
        if (alkioidenLkm > 0) {
            for (int i = 0; i < alkioidenLkm; i++) {
                if (luku == taulukko[i]) {
                    kohta = i;
                }
            }
        }
        return kohta;
    }
    
    private int[] luoIsompiTaulukko() {
        int[] uusi = kopioiTaulukko(taulukko.length + kasvatuskoko);
        return uusi;
    }
    
    private int[] kopioiTaulukko(int uusiKoko) {
        int[] uusi = new int[uusiKoko];
        for (int i = 0; i < alkioidenLkm; i++) {
            uusi[i] = taulukko[i];
        }
        return uusi;
    }

    public boolean poista(int luku) {
        boolean poistettu = false;
        int kohta = getKohta(luku); 
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                taulukko[j] = taulukko[j+1];
            }
            taulukko[alkioidenLkm-1] = 0;
            alkioidenLkm--;
            poistettu = true;
        }
        return poistettu;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + taulukko[0] + "}";
        } else {
            String tuloste = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuloste += taulukko[i];
                tuloste += ", ";
            }
            tuloste += taulukko[alkioidenLkm - 1];
            tuloste += "}";
            return tuloste;
        }
    }

    public int[] toIntArray() {
        int[] taulukkona = kopioiTaulukko(alkioidenLkm);
        return taulukkona;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkaus;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(bTaulu[i]);
        }
        return erotus;
    }
        
}

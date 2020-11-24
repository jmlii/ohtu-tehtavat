package laskin;

import javafx.scene.control.TextField;

public class Erotus extends LaskuKomento {

    public Erotus(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, sovellus);
    }

    @Override
    protected void laske() {
        sovellus.miinus(syote);
    }
    
}

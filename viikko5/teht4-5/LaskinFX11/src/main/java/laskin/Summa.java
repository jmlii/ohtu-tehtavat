package laskin;

import javafx.scene.control.TextField;

public class Summa extends LaskuKomento {

    public Summa(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, sovellus);
    }
    
    @Override
    protected void laske() {
        sovellus.plus(syote);
    }

}

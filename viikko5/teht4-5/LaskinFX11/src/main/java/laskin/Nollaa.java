package laskin;

import javafx.scene.control.TextField;

public class Nollaa extends LaskuKomento {

    public Nollaa(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, sovellus);
    }   
    
    @Override
    public void suorita() {
        edellinenTulos = tuloskentta.getText();
        laske();
        int uusiTulos = sovellus.tulos();
        
        String uusiTuloskentta = String.valueOf(uusiTulos);
        tuloskentta.setText(uusiTuloskentta);
        syotekentta.setText(""); 
    }

    @Override
    protected void laske() {
        sovellus.nollaa();
    }
    
}

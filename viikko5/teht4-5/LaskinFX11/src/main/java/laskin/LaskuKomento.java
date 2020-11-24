package laskin;

import javafx.scene.control.TextField;

public abstract class LaskuKomento extends Komento {

    protected int syote;
    protected String edellinenTulos;
      
    public LaskuKomento(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, sovellus);
    }
     
    @Override
    public void suorita() {
        edellinenTulos = tuloskentta.getText();
        if (syotekentta.getText().isEmpty()) {
            return;
        } else syote = Integer.valueOf(syotekentta.getText());
        laske();
        int uusiTulos = sovellus.tulos();
        
        String uusiTuloskentta = String.valueOf(uusiTulos);
        tuloskentta.setText(uusiTuloskentta);
        syotekentta.setText(""); 
    }
    
    protected abstract void laske();
    
    @Override
    public void peru() {
        sovellus.palauta(Integer.valueOf(edellinenTulos));
        tuloskentta.setText(edellinenTulos);
        
    }
    
}

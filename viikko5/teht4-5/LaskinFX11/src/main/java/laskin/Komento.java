package laskin;

import javafx.scene.control.TextField;

public abstract class Komento {
    
    TextField tuloskentta;
    TextField syotekentta;
    Sovelluslogiikka sovellus;
    
    public Komento(TextField tuloskentta, TextField syotekentta, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;
    }
    
    public abstract void suorita();
    
    public abstract void peru();
    
}

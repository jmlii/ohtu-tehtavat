package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {

    private Button undo;
    private Sovelluslogiikka sovellus;
    
    private Map<Button, Komento> komennot;
    private Komento edellinen = null;
    
//    private TextField tuloskentta; 
//    private TextField syotekentta; 
//    private Button plus;
//    private Button miinus;
//    private Button nollaa;
//    private Button undo;
//    private Sovelluslogiikka sovellus;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Summa(tuloskentta, syotekentta, sovellus) );
        this.komennot.put(miinus, new Erotus(tuloskentta, syotekentta, sovellus) );
        this.komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, sovellus) );
    }
    

    
    @Override
    public void handle(Event event) {
        if ( event.getTarget() != undo ) {
            Komento komento = this.komennot.get((Button)event.getTarget());
            komento.suorita();
            this.edellinen = komento;
        } else {
            this.edellinen.peru();
            this.edellinen = null;
        }                  
        
        
        // Tehtäväpohjan metodi :
//        int arvo = 0;
// 
//        try {
//            arvo = Integer.parseInt(syotekentta.getText());
//        } catch (Exception e) {
//        }
//        
//        if (event.getTarget() == plus) {
//            sovellus.plus(arvo);
//        } else if (event.getTarget() == miinus) {
//            sovellus.miinus(arvo);
//        } else if (event.getTarget() == nollaa) {
//            sovellus.nollaa();
//        } else {
//            System.out.println("undo pressed");
//        }
//        
//        int laskunTulos = sovellus.tulos();
//        
//        syotekentta.setText("");
//        tuloskentta.setText("" + laskunTulos);
//        
//        if ( laskunTulos==0) {
//            nollaa.disableProperty().set(true);
//        } else {
//            nollaa.disableProperty().set(false);
//        }
//        undo.disableProperty().set(false);
    }

}

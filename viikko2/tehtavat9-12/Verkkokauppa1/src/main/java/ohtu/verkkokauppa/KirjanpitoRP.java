package ohtu.verkkokauppa;

import java.util.ArrayList;


public interface KirjanpitoRP {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}

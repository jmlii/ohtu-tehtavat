package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    Kauppa k;
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    
    
    // Kaikille testeille yhteiset alustukset: 
    // kauppa, mock-oliot ja muut testeissä tarvittavat ominaisuudet
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        
        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa ensin viitteen 42
        // ja kasvattaa viitettä seuraavilla kutsukerroilla
        when(viite.uusi())
                .thenReturn(42)
                .thenReturn(43)
                .thenReturn(44);

        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        // määritellään tuote numero 2 on omenamehu, hinta 7, saldo 6
        when(varasto.saldo(2)).thenReturn(6);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "omenamehu", 7));
        // määritellään tuote numero 3 on limonadi, hinta 10, saldo 0;
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "limonadi", 10));
        
        // sitten testattava kauppa 
        k = new Kauppa(varasto, pankki, viite);  
    }

    // asioinnin aloitus, varastossa olevan tuotteen lisääminen koriin, 
    // oston suorittaminen varmistaen että on oikea asiakas, tilinumero ja summa
    // (tehtävänannossa annettu esimerkki muokattuna)
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu 
        // oikealla asiakkaalla, tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
        // pankin tilisiirron parametrit: nimi, viite, (asiakkaan) tiliNumero, kaupanTili, summa
    }

    // asioinnin aloitus, varastossa olevien kahden eri tuotteen lisääminen koriin, 
    // oston suorittaminen varmistaen että on oikea asiakas, tilinumero ja summa
    @Test
    public void kaksiEriVarastotuotettaVoiOstaaJaPankinMetodiaTilisiirtoKutsutaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("liisa", "67890");
        
        verify(pankki).tilisiirto(eq("liisa"), anyInt(), eq("67890"), anyString(), eq(12));
    }
    
    // asioinnin aloitus, varastossa olevien kahden saman tuotteen lisääminen koriin, 
    // oston suorittaminen varmistaen että on oikea asiakas, tilinumero ja summa
    @Test
    public void kaksiSamaaVarastotuotettaVoiOstaaJaPankinMetodiaTilisiirtoKutsutaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.tilimaksu("liisa", "67890");
        
        verify(pankki).tilisiirto(eq("liisa"), anyInt(), eq("67890"), anyString(), eq(14));
    }
    
    // asioinnin aloitus, varastossa olevan tuotteen ja varastosta loppuneen tuotteen lisääminen koriin, 
    // oston suorittaminen varmistaen että on oikea asiakas, tilinumero ja summa
    @Test
    public void vainVarastossaOlevanTuotteenVoiOstaaJaPankinMetodiaTilisiirtoKutsutaan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        k.tilimaksu("liisa", "67890");
        
        verify(pankki).tilisiirto(eq("liisa"), anyInt(), eq("67890"), anyString(), eq(7));
    }
    
    // varmistetaan että aloitaAsiointi nollaa edellisen ostoksen tiedot
    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksen() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("liisa", "67890");
        
        verify(pankki).tilisiirto(eq("liisa"), anyInt(), eq("67890"), anyString(), eq(5));
    }
    
    // varmistetaan, että kauppa pyytää uuden viitenumeron jokaiselle maksutapahtumalle
    @Test
    public void kauppaPyytaaUudenViitenumeronJokaiselleMaksutapahtumalle() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("liisa", "67890");
        
        verify(pankki).tilisiirto(eq("liisa"), eq(42), eq("67890"), anyString(), eq(7));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("leena", "67899");
        
        verify(pankki).tilisiirto(eq("leena"), eq(43), eq("67899"), anyString(), eq(5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("leena", "67899");
        
        verify(pankki).tilisiirto(eq("leena"), eq(44), eq("67899"), anyString(), eq(5));
    }
    
    
    // varmistetaan, että korista poistetut tuotteet eivät ole mukana ostossa
    @Test
    public void koristaPoistetutEivatOleMukanaOstoksessa() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.poistaKorista(1);
        k.tilimaksu("liisa", "67890");
        
        verify(pankki).tilisiirto(eq("liisa"), anyInt(), eq("67890"), anyString(), eq(12));
        
    }

}

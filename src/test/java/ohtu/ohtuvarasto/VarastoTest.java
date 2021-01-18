package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(100,10);
       
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaAlkusaldoVarastollaOikeaTilavuus() {
        assertEquals(100, varasto2.getTilavuus(), vertailuTarkkuus);
    }


    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiLisaaLiikaaTavaraa() {
        varasto.lisaaVarastoon(12);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusKonstruktorissaLuoOikeanSaldonVaraston() {
        Varasto varasto3 = new Varasto(-5);
        
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
        
    }
    
    @Test
    public void konstruktoriLuoVarastonOikeallaAlkuSaldolla() {
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    } 
    
    @Test
    public void negatiivinenAlkuSaldoMuuttuuNollaksi() {
        Varasto varasto3 = new Varasto(100, -5);
        assertEquals(0, varasto3.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoOnSuurempiKuinTilavuusJolloinAlkusaldoMuuttuuSamaksiKuinTilavuus() {
        Varasto varasto3 = new Varasto(10,100);
        assertEquals(10, varasto3.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuusEiOlePositiivinen() {
        Varasto varasto3 = new Varasto(-1, 1);
        assertEquals(0, varasto3.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoEiVahennaSaldoa() {
        varasto.otaVarastosta(-2);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanKaikkiMitaSaadaanJosMaaraOnSuurempiKuinSaldo() {
        varasto2.otaVarastosta(100);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tulostaaOikein() {
        assertEquals("saldo = 0.0, vielä tilaa 100.0", varasto.toString());
    }
    

}
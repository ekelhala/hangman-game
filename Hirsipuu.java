import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hirsipuu {

    private final List<String> sanat;
    private int arvaustenMaara;
    private String arvattavaSana;
    private final Random satunnaisLuku;
    private final List<Character> arvaukset;

    /*Parametrillinen konstruktori, asettaa annetut arvot ja luo tarvitut oliot. Lopuksi
    * käyttää Random-oliota arvattavan sanan valintaan.*/
    public Hirsipuu(Sanalista sanat, int arvaustenMaara) {
        this.sanat = sanat.annaSanat();
        this.arvaustenMaara = arvaustenMaara;
        arvaukset = new ArrayList<>();
        satunnaisLuku = new Random();
        setArvattavaSana();
    }

    //Metodi, jolla asetetaan arvattava sana. Käyttää Javan Random-satunnaislukugeneraattorin nextInt-metodia
    private void setArvattavaSana() {
        int sanojenMaara = sanat.size();
        int indeksi = satunnaisLuku.nextInt(sanojenMaara - 1);
        arvattavaSana = sanat.get(indeksi);
    }

    /*Arvataan parametrina saatu merkki. Lisätään aluksi arvattava merkki arvauslistaan ja sen jälkeen tarkistetaan, onko
    * arvattava merkki valitussa sanassa. Lopuksi palautetaan arvauksen totuusarvo ja vähennetään arvauksia yhdellä, mikäli
    * arvaus meni väärin.*/
    public boolean arvaa(Character merkki) {
        arvaukset.add(merkki);
        for(char tarkistettavaMerkki : arvattavaSana.toCharArray()) {
            if (tarkistettavaMerkki == merkki) {
                    return true;
            }
        }
        arvaustenMaara -= 1;
        return false;
    }

    public List<Character> getArvaukset() {
        return arvaukset;
    }

    //Palauttaa jäljellä olevien arvausten määrän
    public int arvauksiaOnJaljella() {
        return arvaustenMaara;
    }

    //Palauttaa arvattavaksi valitun sanan
    public String sana() {
        return arvattavaSana;
    }

    /*Tämän metodin palauttama totuusarvo ilmaisee, onko peli loppu sen kutsumisajanhetkellä. Ensin alustetaan arvattavien
    * merkkien ja arvattujen merkkien määrät. Sitten käydään läpi jokainen arvattavan sanan merkki ja jos arvattujen merkkien
    * listassa oleva merkki löytyy sanasta, lisätään arvattujen merkkien määrään yksi. Lopuksi tutkitaan, onko kaikki merkit arvattu
    * ja palautetaan tämän vertailun totuusarvo.*/
    public boolean onLoppu() {
        int arvatutMerkit = 0;
        for(char merkki : arvattavaSana.toCharArray()) {
            if(arvaukset.contains(merkki)) {
                arvatutMerkit += 1;
            }
        }
        return arvattavaSana.length() == arvatutMerkit;
    }

}

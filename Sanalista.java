import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sanalista {

    private final List<String> sanat;

    public Sanalista(String tiedostoNimi) {
        sanat = new ArrayList<>();
        try(BufferedReader lukija = new BufferedReader(new FileReader(tiedostoNimi))) {
            String rivi;
            while((rivi = lukija.readLine()) != null) {
                sanat.add(rivi.toLowerCase());
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Sanalista(List<String> sanat) {
        this.sanat = new ArrayList<>();
        this.sanat.addAll(sanat);
    }

    public List<String> annaSanat() {
        return sanat;
    }

    //Bonustehtävässä haluttu metodi
    public Sanalista sanatJoidenPituusOn(int pituus) {
        List<String> tulos = new ArrayList<>();
        //Käydään läpi kaikki tämän luokan sanat-listan sisältämät merkkijonot
        for(String sana : sanat) {
            //Jos läpi käytävän merkkijonon pituus on sama kuin haluttu pituus, lisätään se tuloslistaan
            if(sana.length() == pituus) {
                tulos.add(sana);
            }
        }
        //Palautetaan uusi Sanalista, joka sisältää vain tuloksena saadut sanat
        return new Sanalista(tulos);
    }

    //Bonustehtävässä haluttu metodi
    public Sanalista sanatJoissaMerkit(String mjono) {
        List<String> tulos = new ArrayList<>();
        //Haetaan vain mjono:n pituisia sanoja, käytetään tähän aikaisemmin toteutettua sanatJoidenPituusOn-metodia
        List<String> hakuLista = sanatJoidenPituusOn(mjono.length()).annaSanat();
        //mjono:n listaesitys:
        char[] mArray = mjono.toLowerCase().toCharArray();
        //mjono:ssa olevien sanan määrittävien kirjainten määrä:
        int kirjaimet = 0;
        for(char c : mArray) {
            if (c != '_') {
                kirjaimet += 1;
            }
        }
        //Käydään läpi kaikki sanat ja niiden sisältämät kirjaimet
        for(String sana : hakuLista) {
            //Samojen ja samalla paikalla olevien kirjainten määrä:
            int samanlaisuudet = 0;
            for (int j = 0; j < sana.length(); j++) {
                //Tarkastetaan jotta vältytään IndexOutOfBoundsExceptionilta
                if (j < mArray.length) {
                    //Jos sanassa oleva kirjain paikalla j on sama kuin annetussa merkkijonossa
                    if (sana.charAt(j) == mArray[j]) {
                        //On löydetty samanlaisuus
                        samanlaisuudet += 1;
                    }
                }
            }
            //Jos samoja kirjaimia samoilla paikoilla haluttu määrä, on löydetty halutunlainen sana
            if (samanlaisuudet == kirjaimet) {
                tulos.add(sana);
            }
        }
        //Palautetaan uusi Sanalista joka sisältää vain tuloksena saadut merkkijonot
        return new Sanalista(tulos);
    }

}

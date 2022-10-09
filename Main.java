import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner lukija = new Scanner(System.in);

    public static void main(String[] args) {
        Sanalista lista = new Sanalista("sanat.txt");
        int arvaustenMaara = lueLuku("Anna arvausten määrä>");
        Hirsipuu hirsipuu = new Hirsipuu(lista, arvaustenMaara);
            while (!hirsipuu.onLoppu()) {
                System.out.printf("Arvattava sana: %s%n", tulostaArvattavaSana(hirsipuu));
                System.out.printf("Arvatut merkit: %s%n", tulostaArvatutMerkit(hirsipuu));
                char arvaus = lueMerkki("Arvaa merkki>");
                System.out.println();
                if (hirsipuu.arvaa(arvaus)) {
                    System.out.printf("Arvattu merkki on sanassa. %nArvauksia jäljellä: %d%n", hirsipuu.arvauksiaOnJaljella());
                }
                //Merkki ei ollut sanassa
                else {
                    System.out.println("Arvattu merkki ei ole sanassa.");
                    //Jos uusia vääriä arvauksia ei tämän väärän arvauksen jälkeen voida tehdä, peli on hävitty
                    if (hirsipuu.arvauksiaOnJaljella() == 0) {
                        System.out.printf("Arvaukset loppuivat, hävisit pelin! Arvattava sana oli: %s%n", hirsipuu.sana());
                        return;
                    } else {
                        System.out.printf("Arvauksia jäljellä: %d%n", hirsipuu.arvauksiaOnJaljella());
                    }
                }
            }
            System.out.printf("Arvattava sana oli: %s%nVoitit pelin!", hirsipuu.sana());
    }

    //Lukee kokonaisluku-tyyppisen syötteen käyttäjältä, hyväksyy vain positiiviset kokonaisluvut
    private static int lueLuku(String viesti) {
        while (true) {
            System.out.print(viesti);
            try {
                int tulos = lukija.nextInt();
                if(tulos > 0) {
                    return tulos;
                }
            }
            catch (InputMismatchException e) {
                lukija.nextLine();
            }
        }
    }

    /*Lukee kirjainsyötteen käyttäjältä. Jos useampi kirjain (=merkkijono) syötetään, vain ensimmäinen merkki huomioidaan.
    * Metodi ei myöskään hyväksy syötteeksi muita kuin aakkosia eli esimerkiksi numerot ja erikoismerkit hylätään*/
    private static char lueMerkki(String viesti) {
        while(true) {
            System.out.print(viesti);
            String merkkijono = lukija.nextLine();
            char[] merkit = merkkijono.toCharArray();
            if((!merkkijono.isBlank()) && (Character.isLetter(merkit[0]))) {
                return merkit[0];
            }
        }
    }

    /*Palauttaa merkkijonon, jossa arvattava sana on ruudulle tulostamisen kannalta valmiissa muodossa.
    * Tarkastelee arvattavan sanan kirjaimia ja jos arvattava kirjain sisältyy arvattujen kirjainten listaan, se
    * palautetaan tulostettavaksi normaalissa muodossa. Muuten kirjaimen kohdalle tullee _-merkki*/
    private static String tulostaArvattavaSana(Hirsipuu hirsipuu) {
        StringBuilder tulos = new StringBuilder();
        String arvattavaSana = hirsipuu.sana();
        List<Character> arvaukset = hirsipuu.getArvaukset();
        for(Character kirjain : arvattavaSana.toCharArray()) {
            if(arvaukset.contains(kirjain)) {
                tulos.append(String.format("%s ", kirjain));
            }
            else {
                tulos.append("_ ");
            }
        }
        return tulos.toString();
    }

    //Palauttaa arvatut merkit-listan sisällön näytölle tulostettavaksi valmiissa muodossa
    private static String tulostaArvatutMerkit(Hirsipuu hirsipuu) {
        StringBuilder tulos = new StringBuilder();
        for(Character merkki : hirsipuu.getArvaukset()) {
            tulos.append(merkki).append(" ");
        }
        return tulos.toString();
    }
}

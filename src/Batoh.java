import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Batoh {

    private double celkovaCenaBatoha;
    private double cenaBatoha;
    private double vahaBatoha = 0;
    private double pocetPredmetov = 0;

    private List<Integer> ceny;
    private List<Integer> vahy;
    private List<Double> koeficienty;

    private InputStream vahyFile = ClassLoader.getSystemResourceAsStream("H2_a.txt");
    private InputStream cenyFile = ClassLoader.getSystemResourceAsStream("H2_c.txt");
    private Scanner vahaScanner = new Scanner(vahyFile);
    private Scanner cenaScanner = new Scanner(cenyFile);
    private PrintWriter cenyOut = new PrintWriter("files\\cost.txt");
    private PrintWriter vahyOut = new PrintWriter("files\\weight.txt");

    public Batoh() throws IOException {
        this.vahy = new ArrayList<>();
        this.ceny = new ArrayList<>();
        this.koeficienty = new ArrayList<>();

    }

    public void getHodnoty() {
        var aktualnaVaha = 0;
        var aktualnaCena = 0;

        while (vahaScanner.hasNext() && cenaScanner.hasNext()) {
            aktualnaVaha = vahaScanner.nextInt();
            aktualnaCena = cenaScanner.nextInt();
            double koeficient = (float) aktualnaCena / aktualnaVaha;
            this.ceny.add(aktualnaCena);
            this.cenaBatoha += aktualnaVaha;
            this.celkovaCenaBatoha += aktualnaCena;
            this.vahy.add(aktualnaVaha);
            this.koeficienty.add(koeficient);
            this.pocetPredmetov++;
        }
        //this.vahaBatoha = 0;//(int) this.cenaBatoha;

    }

    public void getVysledok() {
        for (int n = 0; n < this.koeficienty.size(); n++) {
            int i = this.koeficienty.indexOf(Collections.max(this.ceny));

            this.vahaBatoha += this.vahy.get(i);
            this.ceny.remove(i);
            this.vahy.remove(i);
            this.koeficienty.remove(i);
            this.pocetPredmetov--;


            n--;
            double K = 11500;
            double r = 350;
            if (this.vahaBatoha >= K && this.pocetPredmetov >= r) {
                break;
            }
        }
    }


    public void zapisDoSuboru() {
        int celkovaVaha = 0;
        int celkovaCena = 0;
        for (int i = 0; i < this.vahy.size(); i++) {
            celkovaVaha += this.vahy.get(i);
            celkovaCena += this.ceny.get(i);
            vahyOut.println(this.vahy.get(i).toString());
            cenyOut.println(this.ceny.get(i).toString());
        }
        System.out.println("Celkova cena : " + celkovaCena);
        System.out.println("Celkova vaha : " + celkovaVaha);

        System.out.println("Pociatocna vaha batoha : " + this.cenaBatoha);
        System.out.println("Pociatocna cena batoha : " + this.celkovaCenaBatoha);
        cenyOut.close();
        vahyOut.close();
    }
}

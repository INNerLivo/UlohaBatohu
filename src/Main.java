import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Batoh batoh = new Batoh();
        batoh.getHodnoty();
        batoh.getVysledok();
        batoh.zapisDoSuboru();
    }
}
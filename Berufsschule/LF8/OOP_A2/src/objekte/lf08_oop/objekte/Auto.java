package objekte.lf08_oop.objekte;

public class Auto {
    String marke;
    String farbe;
    int geschwindigkeit;

    void beschleunigen(int wert) {
        geschwindigkeit = geschwindigkeit + wert;
        System.out.println(marke + " beschleunigt auf " + geschwindigkeit + " km/h.");
    }

    void bremsen(int wert) {
        geschwindigkeit = geschwindigkeit - wert;
        if (geschwindigkeit < 0) {
            geschwindigkeit = 0;
        }
        System.out.println(marke + " bremst auf " + geschwindigkeit + " km/h.");
    }

    void anzeigen() {
        System.out.println("Marke: " + marke);
        System.out.println("Farbe: " + farbe);
        System.out.println("Geschwindigkeit: " + geschwindigkeit + " km/h");
    }

    public static void main(String[] args) {
        Auto meinAuto = new Auto();

        meinAuto.marke = "VW";
        meinAuto.farbe = "blau";
        meinAuto.geschwindigkeit = 0;

        meinAuto.anzeigen();
        System.out.println();

        meinAuto.beschleunigen(50);
        meinAuto.beschleunigen(30);
        meinAuto.bremsen(20);
        System.out.println();

        meinAuto.anzeigen();

        Auto meinAuto2 = new Auto();

        meinAuto2.marke = "Audi";
        meinAuto2.farbe = "rot";
        meinAuto2.geschwindigkeit = 0;

        meinAuto2.anzeigen();

        Auto meinAuto3 = new Auto();

        meinAuto3.marke = "Mercedes";
        meinAuto3.farbe = "pink";
        meinAuto3.geschwindigkeit = 0;

        meinAuto3.anzeigen();


    }
}

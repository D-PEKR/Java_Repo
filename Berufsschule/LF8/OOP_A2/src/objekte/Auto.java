package objekte;

public class Auto { // 1. Zeile definiert die Klasse
    String marke; // 2. Attribute deklariert - erkennbar an: Datentyp + Variablenname
    String farbe; // 2. Attribute deklariert
    int geschwindigkeit; // 2. Attribute deklariert

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
        Auto meinAuto = new Auto(); // 3. Erzeugt ein Objekt - 'new' erstellt neuen Speicherplatz für dieses Objekt

        meinAuto.marke = "VW"; // 4. Attribut setzen - Schreibweise: objektname.attributname = wert;
        meinAuto.farbe = "blau"; // 4. Attribut setzen
        meinAuto.geschwindigkeit = 0; // 4. Attribut setzen

        meinAuto.anzeigen();
        System.out.println();

        meinAuto.beschleunigen(50);
        meinAuto.beschleunigen(30);
        meinAuto.bremsen(20);
        System.out.println();

        meinAuto.anzeigen();
    }
}
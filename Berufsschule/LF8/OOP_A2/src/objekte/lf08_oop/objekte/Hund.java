package objekte.lf08_oop.objekte;

public class Hund {
    String marke;
    String name;
    int anzahlBeine;
    boolean bellen;

    void setBellen(boolean wert) {
        if (bellen == false) {
            bellen = true;
        } else {
            bellen = false;
        }
    }

    void rufNamen(String wert){
        System.out.println(name);
    }

    void anzeigen() {
        System.out.println("Name: " + name);
        System.out.println("Marke: " + marke);
        System.out.println("Anzahl Beine: " + anzahlBeine);
        System.out.println("Bellen: " + bellen);
    }


    public static void main(String[] args) {
        Hund meinHund = new Hund();

        meinHund.anzeigen();
        System.out.println();

        meinHund.marke = "Pitbull";
        meinHund.name = "Ben";
        meinHund.anzahlBeine = 4;
        meinHund.bellen = false;
        meinHund.anzeigen();
    }

}
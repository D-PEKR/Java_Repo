package objekte.lf08_oop.objekte;

public class Fahrrad {
    String marke;
    int gaenge;
    int aktuelerGang;
    double geschwindigkeit;

    void ganghochschalten() {
        if (aktuelerGang < gaenge) {
            aktuelerGang++;
            System.out.println("Gang hochgeschaltet auf: " + aktuelerGang);
        } else {
            System.out.println("Bereits im höchsten Gang.");
        }
    }

    void gangrunterschalten() {
        if (aktuelerGang > 1) {
            aktuelerGang--;
            System.out.println("Gang runtergeschaltet auf: " + aktuelerGang);
        } else {
            System.out.println("Bereits im niedrigsten Gang.");
        }
    }

    void beschleunigen(double wert) {
        geschwindigkeit += wert;
        System.out.println("Fahrrad beschleunigt auf " + geschwindigkeit + " km/h.");
    }

    void anzeigen() {
        System.out.println("Marke: " + marke);
        System.out.println("Gänge: " + gaenge);
        System.out.println("Aktueller Gang: " + aktuelerGang);
        System.out.println("Geschwindigkeit: " + geschwindigkeit + " km/h");
    }

    public static void main(String[] args) {
        Fahrrad meinFahrrad = new Fahrrad();

        meinFahrrad.marke = "Trek";
        meinFahrrad.gaenge = 21;
        meinFahrrad.aktuelerGang = 1;
        meinFahrrad.geschwindigkeit = 0;

        meinFahrrad.anzeigen();
        System.out.println();

        meinFahrrad.beschleunigen(15);
        meinFahrrad.ganghochschalten();
        meinFahrrad.beschleunigen(10);
        meinFahrrad.gangrunterschalten();
        System.out.println();

        meinFahrrad.anzeigen();
    }

}

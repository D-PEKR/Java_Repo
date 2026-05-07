import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Verbindet die Spiellogik ({@link GameLogic}) mit der GUI ({@link GameBoard}).
 *
 * Der Controller:
 * - reagiert auf Button-Klicks und leitet Züge an die Logik weiter
 * - aktualisiert die Anzeige nach jedem Zug
 * - benachrichtigt das Hauptfenster ({@link GameWindow}) über Spielereignisse
 */
public class GameController implements ActionListener {

    // Felder

    /** Die Spiellogik – verwaltet Zustand und Regelprüfung */
    private final GameLogic logik;

    /** Das Spielfeld-Panel – zeigt den aktuellen Zustand an */
    private final GameBoard spielfeld;

    /** Das Hauptfenster – empfängt Benachrichtigungen über Spielereignisse */
    private final GameWindow fenster;

    // Konstruktor

    /**
     * Erstellt den Controller und verbindet alle Komponenten.
     *
     * @param logik     die Spiellogik-Instanz
     * @param spielfeld das Spielfeld-Panel
     * @param fenster   das Hauptfenster für Statusmeldungen
     */
    public GameController(GameLogic logik, GameBoard spielfeld, GameWindow fenster) {
        this.logik     = logik;
        this.spielfeld = spielfeld;
        this.fenster   = fenster;

        // Controller als Listener an allen Spielfeld-Buttons registrieren
        spielfeld.setzeButtonListener(this);
    }

    // ActionListener-Implementierung

    /**
     * Wird aufgerufen, wenn ein Spielfeld-Button geklickt wird.
     *
     * Der ActionCommand hat das Format "zeile,spalte" (z. B. "1,2").
     *
     * @param event das ausgelöste Klick-Ereignis
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // Position aus dem ActionCommand parsen
        String[] teile = event.getActionCommand().split(",");
        int zeile  = Integer.parseInt(teile[0]);
        int spalte = Integer.parseInt(teile[1]);

        // Zug in der Logik ausführen – schlägt fehl, wenn Feld belegt ist
        boolean zugGueltig = logik.zugAusfuehren(zeile, spalte);

        if (!zugGueltig) {
            return; // Ungültiger Zug – nichts tun
        }

        // Button-Darstellung aktualisieren
        spielfeld.aktualisiereButton(zeile, spalte, logik.getZelle(zeile, spalte));

        // Spielende prüfen und entsprechend reagieren
        if (logik.istSpielBeendet()) {
            spielfeld.alleButtonsDeaktivieren();

            if (logik.istUnentschieden()) {
                // Unentschieden
                fenster.zeigeSpielEnde(null);
            } else {
                // Gewinner ist der zuletzt aktive Spieler (noch nicht gewechselt)
                fenster.zeigeSpielEnde(logik.getAktuellerSpieler());
            }
        } else {
            // Statuszeile im Fenster aktualisieren
            fenster.aktualisiereStatus(logik.getAktuellerSpieler());
        }
    }

    // Öffentliche Methoden

    /**
     * Setzt Logik und GUI zurück und startet ein neues Spiel.
     */
    public void neuesSpiel() {
        logik.spielZuruecksetzen();
        spielfeld.felderZuruecksetzen();
        fenster.aktualisiereStatus(logik.getAktuellerSpieler());
    }
}

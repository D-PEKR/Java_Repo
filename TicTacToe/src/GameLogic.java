/**
 * Enthält die gesamte Spiellogik für Tic-Tac-Toe.
 *
 * Diese Klasse verwaltet:
 * - den Zustand aller 9 Felder (3×3-Gitter)
 * - den aktuellen Spieler
 * - die Gewinnerprüfung nach jedem Zug
 * - die Unentschieden-Erkennung
 *
 * Sie hat keinerlei GUI-Abhängigkeiten und kann unabhängig getestet werden.
 */
public class GameLogic {

    // -----------------------------------------------------------------------
    // Konstanten
    // -----------------------------------------------------------------------

    /** Anzahl der Zeilen und Spalten des quadratischen Spielfelds */
    public static final int GITTER_GROESSE = 3;

    // -----------------------------------------------------------------------
    // Felder
    // -----------------------------------------------------------------------

    /**
     * Das 3×3-Spielfeld.
     * brett[zeile][spalte] enthält das Symbol der jeweiligen Zelle.
     */
    private PlayerSymbol[][] brett;

    /** Der Spieler, der gerade an der Reihe ist */
    private PlayerSymbol aktuellerSpieler;

    /** Anzahl der bereits gesetzten Züge (0–9) */
    private int zugAnzahl;

    /** Gibt an, ob das Spiel bereits beendet ist */
    private boolean spielBeendet;

    // -----------------------------------------------------------------------
    // Konstruktor
    // -----------------------------------------------------------------------

    /**
     * Erstellt eine neue Spiellogik und setzt das Brett zurück.
     * Spieler X beginnt immer.
     */
    public GameLogic() {
        brett = new PlayerSymbol[GITTER_GROESSE][GITTER_GROESSE];
        spielZuruecksetzen();
    }

    // -----------------------------------------------------------------------
    // Öffentliche Methoden
    // -----------------------------------------------------------------------

    /**
     * Setzt das Spielfeld vollständig zurück und startet eine neue Runde.
     * Spieler X beginnt die neue Runde.
     */
    public void spielZuruecksetzen() {
        // Alle Felder auf LEER setzen
        for (int zeile = 0; zeile < GITTER_GROESSE; zeile++) {
            for (int spalte = 0; spalte < GITTER_GROESSE; spalte++) {
                brett[zeile][spalte] = PlayerSymbol.LEER;
            }
        }
        aktuellerSpieler = PlayerSymbol.X; // X startet immer
        zugAnzahl = 0;
        spielBeendet = false;
    }

    /**
     * Führt einen Zug für den aktuellen Spieler auf dem angegebenen Feld aus.
     *
     * Ein Zug ist nur gültig, wenn:
     * - das Spiel noch nicht beendet ist
     * - die Zelle leer ist
     *
     * @param zeile   Zeilenindex (0–2)
     * @param spalte  Spaltenindex (0–2)
     * @return {@code true}, wenn der Zug erfolgreich ausgeführt wurde
     */
    public boolean zugAusfuehren(int zeile, int spalte) {
        // Ungültige Züge ablehnen
        if (spielBeendet) return false;
        if (brett[zeile][spalte] != PlayerSymbol.LEER) return false;

        // Zug setzen
        brett[zeile][spalte] = aktuellerSpieler;
        zugAnzahl++;

        // Spielende prüfen – nach diesem Zug wird kein Spielerwechsel mehr
        // benötigt, wenn das Spiel vorbei ist
        if (pruefeGewinner(aktuellerSpieler) || zugAnzahl == GITTER_GROESSE * GITTER_GROESSE) {
            spielBeendet = true;
        } else {
            // Spieler wechseln
            aktuellerSpieler = aktuellerSpieler.andererSpieler();
        }

        return true;
    }

    /**
     * Prüft, ob das angegebene Symbol gewonnen hat.
     *
     * Ein Sieg liegt vor, wenn ein Symbol eine komplette Zeile,
     * Spalte oder Diagonale belegt.
     *
     * @param symbol das zu prüfende Spielersymbol
     * @return {@code true}, wenn dieses Symbol gewonnen hat
     */
    public boolean pruefeGewinner(PlayerSymbol symbol) {
        // --- Zeilen prüfen ---
        for (int zeile = 0; zeile < GITTER_GROESSE; zeile++) {
            if (brett[zeile][0] == symbol
                    && brett[zeile][1] == symbol
                    && brett[zeile][2] == symbol) {
                return true;
            }
        }

        // --- Spalten prüfen ---
        for (int spalte = 0; spalte < GITTER_GROESSE; spalte++) {
            if (brett[0][spalte] == symbol
                    && brett[1][spalte] == symbol
                    && brett[2][spalte] == symbol) {
                return true;
            }
        }

        // --- Hauptdiagonale prüfen (oben-links → unten-rechts) ---
        if (brett[0][0] == symbol && brett[1][1] == symbol && brett[2][2] == symbol) {
            return true;
        }

        // --- Nebendiagonale prüfen (oben-rechts → unten-links) ---
        if (brett[0][2] == symbol && brett[1][1] == symbol && brett[2][0] == symbol) {
            return true;
        }

        return false;
    }

    /**
     * Prüft, ob das Spiel unentschieden ist.
     * Ein Unentschieden liegt vor, wenn alle Felder belegt sind und niemand gewonnen hat.
     *
     * @return {@code true} bei Unentschieden
     */
    public boolean istUnentschieden() {
        return spielBeendet
                && !pruefeGewinner(PlayerSymbol.X)
                && !pruefeGewinner(PlayerSymbol.O);
    }

    /**
     * Gibt das Symbol einer bestimmten Zelle zurück.
     *
     * @param zeile   Zeilenindex (0–2)
     * @param spalte  Spaltenindex (0–2)
     * @return das Symbol in der Zelle
     */
    public PlayerSymbol getZelle(int zeile, int spalte) {
        return brett[zeile][spalte];
    }

    /**
     * Gibt den aktuell aktiven Spieler zurück.
     *
     * @return das Symbol des aktuellen Spielers
     */
    public PlayerSymbol getAktuellerSpieler() {
        return aktuellerSpieler;
    }

    /**
     * Gibt an, ob das Spiel beendet ist (Sieg oder Unentschieden).
     *
     * @return {@code true}, wenn das Spiel vorbei ist
     */
    public boolean istSpielBeendet() {
        return spielBeendet;
    }
}

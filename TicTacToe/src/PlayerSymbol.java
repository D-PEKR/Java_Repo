/**
 * Aufzählungstyp für die möglichen Zustände einer Spielfeldzelle.
 * Jede Zelle kann leer sein oder das Symbol von Spieler X bzw. O enthalten.
 */
public enum PlayerSymbol {

    /** Leere Zelle – noch kein Zug gesetzt */
    LEER(" "),

    /** Symbol für Spieler X */
    X("X"),

    /** Symbol für Spieler O */
    O("O");

    // -----------------------------------------------------------------------
    // Felder
    // -----------------------------------------------------------------------

    /** Textdarstellung des Symbols (für die Anzeige auf den Buttons) */
    private final String anzeige;

    // -----------------------------------------------------------------------
    // Konstruktor
    // -----------------------------------------------------------------------

    /**
     * Erstellt ein Symbol mit der zugehörigen Textdarstellung.
     *
     * @param anzeige der angezeigte Text
     */
    PlayerSymbol(String anzeige) {
        this.anzeige = anzeige;
    }

    // -----------------------------------------------------------------------
    // Methoden
    // -----------------------------------------------------------------------

    /**
     * Liefert die Textdarstellung des Symbols.
     *
     * @return Textdarstellung ("X", "O" oder " ")
     */
    public String getAnzeige() {
        return anzeige;
    }

    /**
     * Gibt das Symbol des jeweils anderen Spielers zurück.
     * Kann nur für X und O aufgerufen werden, nicht für LEER.
     *
     * @return das Symbol des Gegenspielers
     * @throws IllegalStateException wenn auf LEER aufgerufen
     */
    public PlayerSymbol andererSpieler() {
        if (this == X) return O;
        if (this == O) return X;
        throw new IllegalStateException("LEER hat keinen Gegenspieler.");
    }
}

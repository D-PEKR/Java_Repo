import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * GUI-Komponente, die das 3×3-Spielfeld als Raster aus Buttons darstellt.
 *
 * GameBoard ist rein für die Darstellung zuständig.
 * Die Spiellogik und das Reagieren auf Klicks übernimmt der {@link GameController}.
 */
public class GameBoard extends JPanel {

    // -----------------------------------------------------------------------
    // Konstanten – Farben und Schriften
    // -----------------------------------------------------------------------

    /** Hintergrundfarbe des Spielfelds */
    private static final Color FARBE_HINTERGRUND  = new Color(30, 30, 46);

    /** Hintergrundfarbe eines leeren Buttons */
    private static final Color FARBE_BUTTON        = new Color(49, 50, 68);

    /** Hover-Farbe (nicht genutzt bei Standard-Swing, Platzhalter) */
    private static final Color FARBE_BUTTON_HOVER  = new Color(69, 71, 90);

    /** Textfarbe für Spieler X */
    private static final Color FARBE_X             = new Color(226, 14, 78); // Rosa

    /** Textfarbe für Spieler O */
    private static final Color FARBE_O             = new Color(20, 185, 221); // Hellblau

    /** Standardtextfarbe (leer) */
    private static final Color FARBE_LEER          = new Color(88, 91, 112);

    /** Schriftart für die Symbole auf den Buttons */
    private static final Font SCHRIFT_SYMBOL = new Font("Monospaced", Font.BOLD, 64);

    // -----------------------------------------------------------------------
    // Felder
    // -----------------------------------------------------------------------

    /**
     * Die 9 Buttons des Spielfelds, indiziert als [zeile][spalte].
     */
    private final JButton[][] buttons;

    // -----------------------------------------------------------------------
    // Konstruktor
    // -----------------------------------------------------------------------

    /**
     * Erstellt das Spielfeld-Panel mit einem 3×3-Grid aus Buttons.
     */
    public GameBoard() {
        // GridLayout für gleichmäßige 3×3-Aufteilung mit kleinen Lücken
        setLayout(new GridLayout(GameLogic.GITTER_GROESSE, GameLogic.GITTER_GROESSE, 8, 8));
        setBackground(new Color(18, 18, 27)); // Lückenfarbe = Hintergrund

        buttons = new JButton[GameLogic.GITTER_GROESSE][GameLogic.GITTER_GROESSE];

        // Jeden der 9 Buttons initialisieren und hinzufügen
        for (int zeile = 0; zeile < GameLogic.GITTER_GROESSE; zeile++) {
            for (int spalte = 0; spalte < GameLogic.GITTER_GROESSE; spalte++) {
                buttons[zeile][spalte] = erstelleButton();
                add(buttons[zeile][spalte]);
            }
        }
    }

    // -----------------------------------------------------------------------
    // Öffentliche Methoden
    // -----------------------------------------------------------------------

    /**
     * Registriert einen ActionListener an allen 9 Buttons.
     * Der Controller kann so auf Klicks reagieren.
     * Der ActionCommand ist im Format "zeile,spalte" (z. B. "0,2").
     *
     * @param listener der zu registrierende Listener
     */
    public void setzeButtonListener(ActionListener listener) {
        for (int zeile = 0; zeile < GameLogic.GITTER_GROESSE; zeile++) {
            for (int spalte = 0; spalte < GameLogic.GITTER_GROESSE; spalte++) {
                // ActionCommand codiert die Position als "zeile,spalte"
                buttons[zeile][spalte].setActionCommand(zeile + "," + spalte);
                buttons[zeile][spalte].addActionListener(listener);
            }
        }
    }

    /**
     * Aktualisiert die Beschriftung und Farbe eines einzelnen Buttons.
     *
     * @param zeile   Zeilenindex (0–2)
     * @param spalte  Spaltenindex (0–2)
     * @param symbol  das anzuzeigende Symbol
     */
    public void aktualisiereButton(int zeile, int spalte, PlayerSymbol symbol) {
        JButton button = buttons[zeile][spalte];
        button.setText(symbol.getAnzeige());

        // Symbolfarbe je nach Spieler setzen
        switch (symbol) {
            case X:
                button.setForeground(FARBE_X);
                button.setEnabled(false); // Belegtes Feld sperren
                break;
            case O:
                button.setForeground(FARBE_O);
                button.setEnabled(false); // Belegtes Feld sperren
                break;
            default:
                button.setForeground(FARBE_LEER);
                button.setEnabled(true);
                break;
        }
    }

    /**
     * Setzt alle Buttons auf ihren Ausgangszustand zurück (leer, aktiv).
     */
    public void felderZuruecksetzen() {
        for (int zeile = 0; zeile < GameLogic.GITTER_GROESSE; zeile++) {
            for (int spalte = 0; spalte < GameLogic.GITTER_GROESSE; spalte++) {
                aktualisiereButton(zeile, spalte, PlayerSymbol.LEER);
            }
        }
    }

    /**
     * Deaktiviert alle Buttons (z. B. nach Spielende).
     */
    public void alleButtonsDeaktivieren() {
        for (int zeile = 0; zeile < GameLogic.GITTER_GROESSE; zeile++) {
            for (int spalte = 0; spalte < GameLogic.GITTER_GROESSE; spalte++) {
                buttons[zeile][spalte].setEnabled(false);
            }
        }
    }

    // -----------------------------------------------------------------------
    // Private Hilfsmethoden
    // -----------------------------------------------------------------------

    /**
     * Erstellt einen einzelnen, stilisierten Spielfeld-Button.
     *
     * @return der fertig konfigurierte Button
     */
    private JButton erstelleButton() {
        JButton button = new JButton(" ");
        button.setFont(SCHRIFT_SYMBOL);
        button.setForeground(FARBE_LEER);
        button.setBackground(FARBE_BUTTON);
        button.setFocusPainted(false);   // Keinen Fokusrahmen anzeigen
        button.setBorderPainted(false);  // Keinen Rahmen anzeigen
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover-Effekt über MouseListener simulieren
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(FARBE_BUTTON_HOVER);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(FARBE_BUTTON);
                }
            }
        });

        return button;
    }
}

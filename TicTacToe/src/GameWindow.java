import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Das Hauptfenster des Tic-Tac-Toe-Spiels.
 *
 * Dieses Fenster enthält:
 * - eine Titelzeile mit dem Spielnamen
 * - das Spielfeld ({@link GameBoard})
 * - eine Statusleiste mit dem aktuellen Spieler
 * - einen "Neues Spiel"-Button
 *
 * Die eigentliche Spiellogik liegt in {@link GameLogic},
 * die Verbindung übernimmt {@link GameController}.
 */
public class GameWindow extends JFrame {

    // -----------------------------------------------------------------------
    // Konstanten – Farben und Schriften
    // -----------------------------------------------------------------------

    /** Hintergrundfarbe des gesamten Fensters */
    private static final Color FARBE_HINTERGRUND      = new Color(30, 30, 46);

    /** Farbe der Titelleiste */
    private static final Color FARBE_KOPFZEILE        = new Color(18, 18, 27);

    /** Farbe des Neustartbuttons */
    private static final Color FARBE_BUTTON           = new Color(137, 180, 250); // Blau

    /** Textfarbe des Neustartbuttons */
    private static final Color FARBE_BUTTON_TEXT      = new Color(18, 18, 27);

    /** Textfarbe der Statusleiste */
    private static final Color FARBE_STATUS           = new Color(205, 214, 244);

    /** Farbe für das X-Symbol im Statustext */
    private static final Color FARBE_X                = new Color(243, 139, 168);

    /** Farbe für das O-Symbol im Statustext */
    private static final Color FARBE_O                = new Color(137, 220, 235);

    /** Schriftart für den Titel */
    private static final Font SCHRIFT_TITEL           = new Font("Monospaced", Font.BOLD, 28);

    /** Schriftart für den Statustext */
    private static final Font SCHRIFT_STATUS          = new Font("Monospaced", Font.PLAIN, 16);

    /** Schriftart für den Neustartbutton */
    private static final Font SCHRIFT_BUTTON          = new Font("Monospaced", Font.BOLD, 14);

    /** Bevorzugte Fenstergröße */
    private static final Dimension FENSTER_GROESSE    = new Dimension(480, 580);

    // -----------------------------------------------------------------------
    // Felder
    // -----------------------------------------------------------------------

    /** Panel mit dem 3×3-Spielfeld */
    private final GameBoard spielfeld;

    /** Statuslabel mit dem aktuellen Spieler */
    private final JLabel statusLabel;

    /** Controller, der Logik und GUI verbindet */
    private GameController controller;

    // -----------------------------------------------------------------------
    // Konstruktor
    // -----------------------------------------------------------------------

    /**
     * Baut das komplette Fenster auf und macht es sichtbar.
     */
    public GameWindow() {
        super("Tic‑Tac‑Toe");

        // Spielfeld und Statuslabel initialisieren
        spielfeld   = new GameBoard();
        statusLabel = new JLabel("Spieler X ist dran", SwingConstants.CENTER);

        // Fenster konfigurieren
        konfiguriereFenster();

        // Alle Komponenten aufbauen
        getContentPane().setBackground(FARBE_HINTERGRUND);
        getContentPane().setLayout(new BorderLayout(0, 0));

        getContentPane().add(erstelleKopfzeile(),    BorderLayout.NORTH);
        getContentPane().add(erstelleSpielfeldPanel(), BorderLayout.CENTER);
        getContentPane().add(erstelleFusszeile(),    BorderLayout.SOUTH);

        // Controller erzeugen (verbindet Logik ↔ GUI ↔ Fenster)
        GameLogic logik = new GameLogic();
        controller = new GameController(logik, spielfeld, this);

        // Fenster anzeigen
        setVisible(true);

        // Initialen Status setzen
        aktualisiereStatus(logik.getAktuellerSpieler());
    }

    // -----------------------------------------------------------------------
    // Öffentliche Methoden (für Controller-Rückmeldungen)
    // -----------------------------------------------------------------------

    /**
     * Aktualisiert die Statusleiste mit dem Namen des aktiven Spielers.
     *
     * @param spieler der Spieler, der als nächstes zieht
     */
    public void aktualisiereStatus(PlayerSymbol spieler) {
        statusLabel.setText("Spieler " + spieler.getAnzeige() + " ist dran");

        // Textfarbe je nach Spieler anpassen
        if (spieler == PlayerSymbol.X) {
            statusLabel.setForeground(FARBE_X);
        } else {
            statusLabel.setForeground(FARBE_O);
        }
    }

    /**
     * Zeigt einen Dialog am Spielende an und bietet einen Neustart an.
     *
     * @param gewinner der siegreiche Spieler, oder {@code null} bei Unentschieden
     */
    public void zeigeSpielEnde(PlayerSymbol gewinner) {
        // Nachricht formulieren
        String nachricht;
        if (gewinner == null) {
            nachricht = "Unentschieden! Niemand gewinnt.";
            statusLabel.setText("Unentschieden!");
            statusLabel.setForeground(FARBE_STATUS);
        } else {
            nachricht = "Spieler " + gewinner.getAnzeige() + " hat gewonnen! 🎉";
            statusLabel.setText("Spieler " + gewinner.getAnzeige() + " gewinnt!");
            statusLabel.setForeground(gewinner == PlayerSymbol.X ? FARBE_X : FARBE_O);
        }

        // Dialog anzeigen
        int antwort = JOptionPane.showConfirmDialog(
                this,
                nachricht + "\n\nMöchtest du nochmal spielen?",
                "Spielende",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        // Bei Ja: neues Spiel starten
        if (antwort == JOptionPane.YES_OPTION) {
            controller.neuesSpiel();
        }
    }

    // -----------------------------------------------------------------------
    // Private Hilfsmethoden – Aufbau der GUI-Komponenten
    // -----------------------------------------------------------------------

    /**
     * Konfiguriert die Grundeigenschaften des JFrame.
     */
    private void konfiguriereFenster() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(FENSTER_GROESSE);
        setMinimumSize(FENSTER_GROESSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null); // Fenster auf Bildschirm zentrieren
    }

    /**
     * Erstellt die Kopfzeile mit dem Spieltitel.
     *
     * @return fertiges Kopfzeilen-Panel
     */
    private JPanel erstelleKopfzeile() {
        JPanel kopf = new JPanel(new BorderLayout());
        kopf.setBackground(FARBE_KOPFZEILE);
        kopf.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titel = new JLabel("Tic‑Tac‑Toe", SwingConstants.CENTER);
        titel.setFont(SCHRIFT_TITEL);
        titel.setForeground(new Color(205, 214, 244)); // Helles Grau-Weiß

        kopf.add(titel, BorderLayout.CENTER);
        return kopf;
    }

    /**
     * Erstellt das Panel, das das Spielfeld aufnimmt.
     *
     * @return fertiges Spielfeld-Panel mit Innenabstand
     */
    private JPanel erstelleSpielfeldPanel() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(FARBE_HINTERGRUND);
        wrapper.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));
        wrapper.add(spielfeld, BorderLayout.CENTER);
        return wrapper;
    }

    /**
     * Erstellt die Fußzeile mit Statuslabel und Neustartbutton.
     *
     * @return fertiges Fußzeilen-Panel
     */
    private JPanel erstelleFusszeile() {
        JPanel fuss = new JPanel();
        fuss.setLayout(new BoxLayout(fuss, BoxLayout.Y_AXIS));
        fuss.setBackground(FARBE_HINTERGRUND);
        fuss.setBorder(BorderFactory.createEmptyBorder(0, 24, 24, 24));

        // Statuslabel konfigurieren
        statusLabel.setFont(SCHRIFT_STATUS);
        statusLabel.setForeground(FARBE_STATUS);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Neustartbutton erstellen
        JButton neustartButton = erstelleNeustartButton();

        fuss.add(statusLabel);
        fuss.add(Box.createRigidArea(new Dimension(0, 12)));
        fuss.add(neustartButton);

        return fuss;
    }

    /**
     * Erstellt den "Neues Spiel"-Button mit Stil und Aktion.
     *
     * @return der fertig konfigurierte Button
     */
    private JButton erstelleNeustartButton() {
        JButton button = new JButton("↺  Neues Spiel");
        button.setFont(SCHRIFT_BUTTON);
        button.setBackground(FARBE_BUTTON);
        button.setForeground(FARBE_BUTTON_TEXT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 40));
        button.setPreferredSize(new Dimension(200, 40));

        // Hover-Effekt
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(116, 199, 236)); // etwas dunkler
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(FARBE_BUTTON);
            }
        });

        // Aktion: neues Spiel über den Controller starten
        button.addActionListener((ActionEvent e) -> controller.neuesSpiel());

        return button;
    }
}

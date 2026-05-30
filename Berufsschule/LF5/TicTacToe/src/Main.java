import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Startpunkt des Tic-Tac-Toe-Programms.
 *
 * Diese Klasse enthält ausschließlich die {@code main}-Methode,
 * die das Hauptfenster auf dem Swing Event-Dispatch-Thread startet.
 */
public class Main {

    /**
     * Einstiegspunkt der Anwendung.
     *
     * Das Fenster wird über {@link SwingUtilities#invokeLater} gestartet,
     * um Thread-Sicherheit für die Swing-GUI zu gewährleisten.
     *
     * @param args Kommandozeilenargumente (werden nicht verwendet)
     */
    public static void main(String[] args) {
        // Systemweites Look-and-Feel setzen (nativer Stil des Betriebssystems)
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            // Standard-Look-and-Feel beibehalten, falls ein Fehler auftritt
            System.err.println("Look-and-Feel konnte nicht gesetzt werden: " + e.getMessage());
        }

        // Fenster auf dem Event-Dispatch-Thread erstellen und anzeigen
        SwingUtilities.invokeLater(() -> new GameWindow());
    }
}

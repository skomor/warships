import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Application {

    private JFrame frame;

    private JPanel mainMenuScreen, gameScreen ;
    private int GameState;
    private boolean isAdmin;
    private boolean playersTurn;
     private Server ser;
     private String serverIP;


    /**
     * @param frame
     * konstruktor kalsy
     */
    public Application(JFrame frame) {
        this.frame = frame;
        ser= new Server(this);
        setGameState(0);

    }


    /**
     * @return
     * zwraca ramke (JFrame)
     */
    JFrame getFrame() {
        return frame;
    }

    /**
     * @param screen
     * wyswietla podany JPanel
     */
    private void showScreen(JPanel screen) {
        dismissWindow();
        this.frame.setContentPane(screen);
        showWindow();
    }

    /**
     * pokzuje okno
     */
    private void showWindow() {
        if(frame.isVisible())
            return;

        frame.setVisible(true);
    }

    /**
     * ukrywa okno
     */
    private void dismissWindow() {
        if(!frame.isVisible())
            return;

        frame.setVisible(false);
    }

    /**
     * pokazuje MainMenu
     */
    void showMainMenu() {
        if(mainMenuScreen == null)
            mainMenuScreen = new MainMenuScreen(this );
        showScreen(mainMenuScreen);
        frame.setMinimumSize(new Dimension(800, 600));


    }

    /**
     * Pokazuje BattleScreen
     */
    void showBattleScreen() {
        setGameState(2);
        if(gameScreen == null)
            gameScreen = new GameScreen(this , ser);
        showScreen(gameScreen);
        frame.setMinimumSize(new Dimension(600, 1000));

    }


    /**
     * @return
     * zwraca stan gry
     */
    int getGameState() {
        return GameState;
    }

    /**
     * @param gameState
     * ustawia stan gry
     */
    synchronized void setGameState(int gameState) {
        GameState = gameState;
    }

    /**
     * @return
     * Zwraca true jesli admin jest true
     */
    boolean isAdmin() {
        return isAdmin;
    }

    /**
     * @param admin
     */
    void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * @return
     * Ustawia true jesli jest kolej gracza
     */
    boolean isPlayersTrun() {
        return playersTurn;
    }

    /**
     * @param playersTurn
     * Ustawia true jesli jest kolej gracza
     */
    void setPlayersTrun(boolean playersTurn) {
        System.out.println("Czy kolej gracza: " + playersTurn);
        this.playersTurn = playersTurn;
    }


    /**
     * @return
     * zwraca ustowione przez uzytkownika ip servera
     */
    String getServerIP() {
        return serverIP;
    }

    /**
     * @param serverIP
     * ustawia wartosc sereverIp na tę podaną w nawiasie
     */
    void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }


}

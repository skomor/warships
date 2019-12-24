/*
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GameScreen extends JPanel {
    private Thread thread;
    private JLabel statusBar;
    private int k = 0;

    private Integer[] shipsSizes = {5, 4, 4, 3, 3, 3, 2, 2, 2, 1, 1, 1};
    //private Integer[] shipsSizes = {5, 4,3,2};
    private String[] ok = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};

    private static final int SHIFT_W = 50, SHIFT_H = 70, SHIFT_W2 = SHIFT_W;
    private int CoordH, CoordW, SHIFT_H2;
    private int sizeW, sizeH;

    private Graphics2D g2d;

    private Graphics g;

    private Application app;

    private Ship[] ships;
    private Server server;
    boolean changeSides;


    private List<Shape> shapes = new ArrayList<>();
    private List<Shape> shapes2 = new ArrayList<>();
    private List<Shape> shapes3 = new ArrayList<>();
    private List<Shape> shapesE1 = new ArrayList<>();
    private List<Shape> shapesE2 = new ArrayList<>();
    private List<Shape> shapesODWR = new ArrayList<>();
    private List<Shape> shapes2ODWR = new ArrayList<>();
    private List<Shape> shapes3ODWR = new ArrayList<>();
    private List<Shape> shapesE1ODWR = new ArrayList<>();
    private List<Shape> shapesE2ODWR = new ArrayList<>();

    private final int CellsXCells = 10;
    private Boolean[][] alreadyAttacked;

    private Boolean isEnemyReady = false;


    *//**
     * @param app
     * @param server konstruktor
     *               inicjalizuje zmienne
     *               inicjalizuje instancje innych obiektow
     *//*
    public GameScreen(Application app, Server server) {

        changeSides = true;
        this.server = server;
        this.app = app;
        ships = new Ship[15];
        alreadyAttacked = new Boolean[CellsXCells][CellsXCells];
        for (int i = 0; i < CellsXCells; i++) {
            for (int j = 0; j < CellsXCells; j++) {
                alreadyAttacked[i][j] = false;
            }
        }

        Ship.init();
        server.init();
        BorderLayout borderLayout = new BorderLayout(15, 15);

        this.setLayout(borderLayout);
        HandlerClass handler = new HandlerClass();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
        statusBar = new JLabel(BorderLayout.SOUTH);
        statusBar.setVisible(false);

        this.add(statusBar);


    }



    *//**
     * @param g Metoda odpowiada za malowanie
     *//*
    public void paint(Graphics g) {

        this.g = g;

        CoordW = (getSize().width - 40) / 14;
        CoordH = (getSize().height - 40) / 22;
        sizeW = getSize().width / 14;
        sizeH = getSize().height / 22;
        SHIFT_H2 = getSize().height / 2 + 60;

        super.paint(g);
        g2d = (Graphics2D) g;
        Font font = new Font("Serif", Font.BOLD, 18);
        g2d.setFont(font);

        g2d.drawString("Przeciwnik " + (isEnemyReady ? "JEST GOTOW" : "NIE JEST GOTOW"), CellsXCells * CoordH, 15);
        g2d.setPaint(app.isPlayersTrun() ? Color.GREEN : Color.RED);
        g2d.drawString("Kolej " + (app.isPlayersTrun() ? "TWOJA" : "PRZRCIWNIKA"), CellsXCells * CoordH + 40, 34);
        g2d.setPaint(Color.BLACK);


        if(!changeSides) {
            g2d.drawString("PLANSZA PRZECIWNIKA: ", 0, 25);
            g2d.drawString("TWOJA PLANSZA: ", 0, SHIFT_H2 - 25);


            this.paintGrid(CoordW, CoordH, SHIFT_W, SHIFT_H, sizeW, sizeH, Theme.CELLS_BACKGROUND_COLOR, Theme.CELLS_BORDER_COLOR);
            this.paintGrid(CoordW, CoordH, SHIFT_W2, SHIFT_H2, sizeW, sizeH, Theme.COLOR_SHADOW_PRE_BATTLE, Theme.SCREEN_BACKGROUND_COLOR);


            for (Shape s : shapes) {
                g2d.fill(s);
            }
            g2d.setPaint(Theme.VERY_DARK_RED);
            for (Shape s : shapes2) {
                g2d.fill(s);
            }
            g2d.setPaint(Theme.VERY_DARK_GREEN);
            for (Shape s : shapes3) {
                g2d.fill(s);
            }
            g2d.setPaint(Color.GREEN);
            for (Shape s : shapesE1) {
                g2d.fill(s);
            }
            g2d.setPaint(Color.RED);
            for (Shape s : shapesE2) {
                g2d.fill(s);
            }
        }else {
            g2d.drawString("TWOJA PLANSZA: ", 0, 25);
            g2d.drawString("PLANSZA PRZECIWNIKA: ", 0, SHIFT_H2 - 25);


            this.paintGrid(CoordW, CoordH, SHIFT_W2, SHIFT_H2, sizeW, sizeH, Theme.CELLS_BACKGROUND_COLOR, Theme.CELLS_BORDER_COLOR);
            this.paintGrid(CoordW, CoordH, SHIFT_W, SHIFT_H, sizeW, sizeH, Theme.COLOR_SHADOW_PRE_BATTLE, Theme.SCREEN_BACKGROUND_COLOR);


            for (Shape s : shapesODWR) {
                g2d.fill(s);
            }
            g2d.setPaint(Theme.VERY_DARK_RED);
            for (Shape s : shapes2ODWR) {
                g2d.fill(s);
            }
            g2d.setPaint(Theme.VERY_DARK_GREEN);
            for (Shape s : shapes3ODWR) {
                g2d.fill(s);
            }
            g2d.setPaint(Color.GREEN);
            for (Shape s : shapesE1ODWR) {
                g2d.fill(s);
            }
            g2d.setPaint(Color.RED);
            for (Shape s : shapesE2ODWR) {
                g2d.fill(s);
            }

        }

    }


    public void changeTiles() {
        changeSides= !changeSides;

    }


    *//**
     * @param a
     * @param b
     * @return Metoda odpowada za zwrocenie tablicy dwoch znakow gdy nacisnie sie na kafelek z dolnej planszy
     *//*
    private Integer[] GetPlace2(double a, double b) {


        String[] s = new String[2];
        Integer[] place = new Integer[2];


        for (int i = 0; i < CellsXCells; i++) {
            if (a < (SHIFT_W) || a > (CellsXCells * CoordW + SHIFT_W2) || b < SHIFT_H || b > CellsXCells * CoordH + SHIFT_H)
                return null;
            if (a > (i * CoordW + SHIFT_W) && a <= ((i + 1) * CoordW + SHIFT_W)) {
                s[1] = ok[i];
                place[0] = i;
            }
            if (b > i * CoordH + SHIFT_H && b <= (i + 1) * CoordH + SHIFT_H) {
                s[0] = Integer.toString(i + 1);
                place[1] = i;
            }
        }
        System.out.println(s[0] + s[1]);

        return (place);


    }

    *//**
     * @param a
     * @param b
     * @return Metodaodpowada za zwrocenie tablicy dwoch znakow gdy nacisnie sie na kafelek z gornej planszy
     *//*
    private Integer[] GetPlace3(double a, double b) {


        String[] s = new String[2];
        Integer[] place = new Integer[2];


        for (int i = 0; i < CellsXCells; i++) {
            if (a < (SHIFT_W2) || a > (CellsXCells * CoordW + SHIFT_W2) || b < SHIFT_H2 || b > CellsXCells * CoordH + SHIFT_H2)
                return null;
            if (a >= (i * CoordW + SHIFT_W2) && a <= ((i + 1) * CoordW + SHIFT_W2)) {
                s[1] = ok[i];
                place[0] = i;
            }
            if (b >= i * CoordH + SHIFT_H2 && b <= (i + 1) * CoordH + SHIFT_H2) {
                s[0] = Integer.toString(i + 1);
                place[1] = i;
            }
        }
        System.out.println(s[0] + s[1]);

        return (place);


    }


    *//**
     * @param CoordW
     * @param CoordH
     * @param SHIFT_W
     * @param SHIFT_H
     * @param SizeW
     * @param SizeH
     * @param p1
     * @param p2      Metoda rysuje kafelki do gry
     *//*
    private void paintGrid(int CoordW, int CoordH, int SHIFT_W, int SHIFT_H, int SizeW, int SizeH, Color p1, Color p2) {
        g2d.setPaint(p2);
        for (int i = 0; i < CellsXCells; i++) {
            for (int j = 0; j < CellsXCells; j++) {


                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    g2d.setPaint(p2);
                } else {
                    g2d.setPaint(p1);
                }
                g2d.fillRect((j * CoordW + SHIFT_W), (i * CoordH + SHIFT_H), SizeW, SizeH);

            }
        }////for nr1
        Font font2 = new Font("Serif", Font.PLAIN, 35);
        g2d.setFont(font2);
        for (int i = 0; i < CellsXCells; i++) {
            g2d.setColor(Color.BLACK);
            g2d.drawString(ok[i], i * CoordW + SHIFT_W, SHIFT_H);

        }

        for (int i = 0; i < CellsXCells; i++) {
            g2d.setColor(Color.BLACK);
            g2d.drawString(Integer.toString(i + 1), 15, i * CoordH + SHIFT_H + 25);
        }

    }

    *//**
     * Metoda rysuje statki po stawianiu ich na swojej planszy
     *//*
    private void paintShips() {
        Boolean[][] CellsState;
        CellsState = Ship.getCellsState();

        for (int i = 0; i < CellsXCells; i++) {
            for (int j = 0; j < CellsXCells; j++) {
                if (CellsState[i][j]) {
                    paintOneCellMyBoard(i, j);
                    repaint();
                }
            }
        }


    }

    *//**
     * @param x
     * @param y Metoda zamalowuje komorki na dolnej planszy
     *          <p>
     *          uzywana przy stawianu statkow
     *//*
    private void paintOneCellMyBoard(int x, int y) {


        shapes.add(new Rectangle(x * CoordW + SHIFT_W2, y * CoordH + SHIFT_H2, sizeW, sizeH));
        shapesODWR.add(new Rectangle(x * CoordW + SHIFT_W, y * CoordH + SHIFT_H, sizeW, sizeH));

    }

    *//**
     * @param a
     * @param b Metoda zamalowuje trafione komorki na dolnej planszy
     *//*
    private void paintHitedOneCellMyBoard(int a, int b) {


        shapes2.add(new Rectangle(a * CoordW + SHIFT_W2, b * CoordH + SHIFT_H2, sizeW, sizeH));
        shapes2ODWR.add(new Rectangle(a * CoordW + SHIFT_W2, b * CoordH + SHIFT_H, sizeW, sizeH));

        repaint();
    }

    *//**
     * @param a
     * @param b Metoda zamalowuje nietrafione komorki na dolnej planszy
     *//*
    private void paintMissedOneCellMyBoard(int a, int b) {


        shapes3.add(new Rectangle(a * CoordW + SHIFT_W2, b * CoordH + SHIFT_H2, sizeW, sizeH));
        shapes3ODWR.add(new Rectangle(a * CoordW + SHIFT_W2, b * CoordH + SHIFT_H, sizeW, sizeH));
        repaint();
    }

    *//**
     * @param x
     * @param y Metoda zamalowuje trafione komorki na gornej planszy
     *//*
    private void HITEDOneCellOnEnemyBoard(int x, int y) {


        shapesE1.add(new Rectangle(x * CoordW + SHIFT_W, y * CoordH + SHIFT_H, sizeW, sizeH));
        shapesE1ODWR.add(new Rectangle(x * CoordW + SHIFT_W2, y * CoordH + SHIFT_H2, sizeW, sizeH));
        repaint();
    }

    *//**
     * @param x
     * @param y Metoda zamalowuje nietrafione komorki na gornej planszy
     *//*
    private void NOT_HITEDOneCellOnEnemyBoard(int x, int y) {


        shapesE2.add(new Rectangle(x * CoordW + SHIFT_W, y * CoordH + SHIFT_H, sizeW, sizeH));
        shapesE2ODWR.add(new Rectangle(x * CoordW + SHIFT_W2, y * CoordH + SHIFT_H2, sizeW, sizeH));
        repaint();
    }

    *//**
     * Klasa obsługująca kliknięcia myszą
     *//*
    private class HandlerClass implements MouseListener, MouseMotionListener {

        *//**
         * @param event
         *//*
        @Override
        public void mouseMoved(MouseEvent event) {
            statusBar.setText("you moved  the mouse  X:" + event.getX() + "Y:" + event.getY());

        }

        *//**
         * @param event
         *//*
        @Override
        public void mouseDragged(MouseEvent event) {

            statusBar.setText("you are dragging  the mouse  X:" + event.getX() + "Y:" + event.getY());


        }


        *//**
         * @param e gdy klikniety jest przycisk myszy uruchamiana jest ta funkcja
         *//*
        @Override
        public void mouseClicked(MouseEvent e) {
            int a = e.getX(), b = e.getY();
            if (k == 1) receiveMessageAndProcessIfBoolORCOORDINATES();

                if (app.getGameState() == 2) {
                    GameState2ODW(e, a, b);

                } else {

                    GameState3ODW(e, a, b);
                }

        }

        *//**
         * uruchamia watek ktory pobiera wiadomosci od oponenta gdy ten je wysyła
         * po otrzymaniu odpowiedzi użytkownika że się nie trafiło w jego statek
         * Players turn ustawiany jest na false
         *//*
        private void receiveMessageAndProcessIfBoolORCOORDINATES() {
            thread = new Thread(() -> {
                while (true) {

                    String string;

                    string = server.getMessage();

                    String[] hitORmiss;
                    int coordofHit1;
                    int coordofHit2;


                    hitORmiss = string.split("\\.");
                    if (hitORmiss[0].equals("HIT")) {
                        coordofHit1 = Integer.valueOf(hitORmiss[1]);
                        coordofHit2 = Integer.valueOf(hitORmiss[2]);

                        HITEDOneCellOnEnemyBoard(coordofHit1, coordofHit2);

                    }
                    if (hitORmiss[0].equals("MISSED")) {
                        coordofHit1 = Integer.valueOf(hitORmiss[1]);
                        coordofHit2 = Integer.valueOf(hitORmiss[2]);
                        app.setPlayersTrun(false);
                        changeSides=true;
                        NOT_HITEDOneCellOnEnemyBoard(coordofHit1, coordofHit2);

                    }
                    if (hitORmiss[0].equals("TORPEDO")) {
                        coordofHit1 = Integer.valueOf(hitORmiss[1]);
                        coordofHit2 = Integer.valueOf(hitORmiss[2]);
                        if (Ship.isCellAlive(coordofHit1, coordofHit2)) {
                            Ship.setCellDead(coordofHit1, coordofHit2);
                            paintHitedOneCellMyBoard(coordofHit1, coordofHit2);
                            server.sendString("HIT." + coordofHit1 + "." + coordofHit2);

                        } else {
                            paintMissedOneCellMyBoard(coordofHit1, coordofHit2);
                            server.sendString("MISSED." + coordofHit1 + "." + coordofHit2);
                            app.setPlayersTrun(true);
                            changeSides=false;
                        }
                    }
                    if (hitORmiss[0].equals("READY")) {
                        isEnemyReady = true;
                        repaint();

                    }
                    if (hitORmiss[0].equals("iLOST")) {
                        server.sendString("SORRY." + 1 + "." + 1);
                        VictoryScreen();
                        server.closeConnection();


                    }
                    if (hitORmiss[0].equals("SORRY")) {
                        LoosersScreen();
                        server.closeConnection();


                    }


                }
            });
            thread.setDaemon(true);
            thread.start();
//            while (!app.isPlayersTrun()) {
//
//                Integer[] g;
//
//                g = server.getShoot();
//
//                if (Ship.isCellAlive(g[0], g[1])) {
//                    Ship.setCellDead(g[0], g[1]);
//                    server.sendResponse(true);
//                    paintHitedOneCellMyBoard(g);
//                    repaint();
////                    if (Ship.isLost()) {
////                        System.out.println("przegrałęs");
////                        server.closeConnection();
////                    }
//
//                } else {
//
//                    app.setPlayersTrun(true);
//                    server.sendResponse(false);
//                    paintMissedOneCellMyBoard(g);
//                    repaint();
//                }
//
//            }
        }

        *//**
         * wyswietla powiadomienie o przegranej
         *//*
        private void LoosersScreen() {
            JOptionPane.showMessageDialog(app.getFrame(),
                    "Przegrałeś :( (mozesz zamknąć grę)",
                    "KAPITANIE, TWOJE STATKI ZATOPIONE",
                    JOptionPane.ERROR_MESSAGE);
        }


        *//**
         * wyswietla powiadomienie o wygranej
         *//*
        private void VictoryScreen() {
            JOptionPane.showMessageDialog(app.getFrame(),
                    "GRATULACJE!! WYGRALES!! (mozesz zamknąć grę)",
                    "WYGRANA!!",
                    JOptionPane.ERROR_MESSAGE);
        }


        *//**
         * @param g wysyla koordynaty strzalu do oponenta
         *//*
        private void sendShotCoordinatesToEnemy(Integer[] g) {

            if (pointCanBeAttacked(g)) {
                alreadyAttacked[g[0]][g[1]] = true;
                server.sendString("TORPEDO." + g[0] + "." + g[1]);
            }

        }

        *//**
         * wysyla wiadomosc o gotowosci
         *//*
        private void sendMessageThatYouAreReady() {

            server.sendString("READY." + 0 + "." + 0);

        }

        *//**
         * wysyla wiadomosc o przegranej
         *//*
        private void sendThatILost() {
            server.sendString("iLOST." + 0 + "." + 0);
        }


        *//**
         * @param e
         * @param a
         * @param b Metoda wykonuje sie gdy stan gry jest rowny 3
         *          Uzywana jest przy kliknieciu mysza
         *          wysyła koordynaty strzalu
         *//*
        private void GameState3(MouseEvent e, int a, int b) {
            if (app.getGameState() == 3) {
                if (isEnemyReady) {


                    if (app.isPlayersTrun()) {

                        if (GetPlace2(a, b) != null) {

                            Integer[] g;
                            g = GetPlace2(a, b);
                            if (Ship.isLost()) {
                                sendThatILost();
                            }
                            sendShotCoordinatesToEnemy(g);


                        }
                    } else {
                        JOptionPane.showMessageDialog(app.getFrame(),
                                "Prosze czekac na swoja kolej ",
                                "Prosze czekac!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(app.getFrame(),
                            "Proszę czekać az przeciwnik będzie gotów ",
                            "Prosze czekac!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }


        }
        private void GameState3ODW(MouseEvent e, int a, int b) {
            if (app.getGameState() == 3) {
                if (isEnemyReady) {


                    if (app.isPlayersTrun()) {

                        if (GetPlace2(a, b) != null) {

                            Integer[] g;
                            g = GetPlace2(a, b);
                            if (Ship.isLost()) {
                                sendThatILost();
                            }
                            sendShotCoordinatesToEnemy(g);


                        }
                    } else {
                        JOptionPane.showMessageDialog(app.getFrame(),
                                "Prosze czekac na swoja kolej ",
                                "Prosze czekac!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(app.getFrame(),
                            "Proszę czekać az przeciwnik będzie gotów ",
                            "Prosze czekac!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }


        }
        private void GameState2ODW(MouseEvent e, int a, int b) {
            if (app.getGameState() == 2) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (GetPlace3(a, b) != null) {
                        JOptionPane.showMessageDialog(app.getFrame(),
                                "Proszę stawiać statki na swojej planszy (TWOJA PLANSZA).",
                                "Złe pole!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    if (GetPlace2(a, b) != null) {
                        Integer[] t;
                        t = GetPlace2(a, b);
                        if (Ship.CanBePlaced(t, shipsSizes[k], true)) {

                            Ship ship = new Ship(shipsSizes[k], true, t);

                            ships[k] = ship;
                            k++;
                            paintShips();
                            if (k >= shipsSizes.length) {
                                app.setGameState(3);
                                sendMessageThatYouAreReady();
                                if (app.isAdmin()) {
                                    changeSides= false;
                                    repaint();
                                    JOptionPane.showMessageDialog(app.getFrame(),
                                            "Ustawiono wszystkie statki, Teraz pora atakować.",
                                            "Do ataku!",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    changeSides= true;
                                    repaint();
                                    JOptionPane.showMessageDialog(app.getFrame(),
                                            "Zaczekaj aż Administrator ustawi statki i Cię zaatakuje.",
                                            "Do ataku!",
                                            JOptionPane.INFORMATION_MESSAGE);



                                }


                            }


                        } else {
                            JOptionPane.showMessageDialog(app.getFrame(),
                                    "Nie można tu postawić statku(styka się z innym / nie mieści się w ten sposób.",
                                    "Złe pole!",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                if (SwingUtilities.isRightMouseButton(e)) {
                    if (GetPlace3(a, b) != null) {
                        JOptionPane.showMessageDialog(app.getFrame(),
                                "Proszę stawiać statki na swojej planszy (TWOJA PLANSZA).",
                                "Złe pole!",
                                JOptionPane.ERROR_MESSAGE);

                    }
                    if (GetPlace2(a, b) != null) {
                        Integer[] g;
                        g = GetPlace2(a, b);

                        if (Ship.CanBePlaced(g, shipsSizes[k], false)) {
                            Ship ship = new Ship(shipsSizes[k], false, g);
                            ships[k] = ship;
                            k++;


                            paintShips();
                            if (k >= shipsSizes.length) {
                                app.setGameState(3);
                                sendMessageThatYouAreReady();
                                if (app.isAdmin()) {
                                    changeSides= false;
                                    repaint();
                                    JOptionPane.showMessageDialog(app.getFrame(),
                                            "Ustawiono wszystkie statki, Teraz pora atakować.",
                                            "Do ataku!",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    changeSides= true;
                                    repaint();
                                    JOptionPane.showMessageDialog(app.getFrame(),
                                            "Zaczekaj aż Administrator ustawi statki i Cię zaatakuje.",
                                            "Do ataku!",
                                            JOptionPane.INFORMATION_MESSAGE);



                                }


                            }


                        } else {
                            JOptionPane.showMessageDialog(app.getFrame(),
                                    "Nie można tu postawić statku(styka się z innym / nie mieści się w ten sposób.",
                                    "Złe pole!",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }


        }




        *//**
         * @param e
         * @param a
         * @param b * Metoda wykonuje sie gdy stan gry jest rowny 3
         *          Uzywana jest przy kliknieciu mysza
         *          używana dla kafelkow gracza
         *          rozroznia przyciski myszy
         *          tworzy nowe statki
         *//*
        private void GameState2(MouseEvent e, int a, int b) {
            if (app.getGameState() == 2) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (GetPlace2(a, b) != null) {
                        JOptionPane.showMessageDialog(app.getFrame(),
                                "Proszę stawiać statki na swojej planszy (TWOJA PLANSZA).",
                                "Złe pole!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    if (GetPlace3(a, b) != null) {
                        Integer[] t;
                        t = GetPlace3(a, b);
                        if (Ship.CanBePlaced(t, shipsSizes[k], true)) {

                            Ship ship = new Ship(shipsSizes[k], true, t);

                            ships[k] = ship;
                            k++;
                            paintShips();
                            if (k >= shipsSizes.length) {
                                app.setGameState(3);
                                sendMessageThatYouAreReady();
                                if (app.isAdmin()) {
                                    changeSides= false;
                                    repaint();
                                    JOptionPane.showMessageDialog(app.getFrame(),
                                            "Ustawiono wszystkie statki, Teraz pora atakować.",
                                            "Do ataku!",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    changeSides= true;
                                    repaint();
                                    JOptionPane.showMessageDialog(app.getFrame(),
                                            "Zaczekaj aż Administrator ustawi statki i Cię zaatakuje.",
                                            "Do ataku!",
                                            JOptionPane.INFORMATION_MESSAGE);



                                }

                            }


                        } else {
                            JOptionPane.showMessageDialog(app.getFrame(),
                                    "Nie można tu postawić statku(styka się z innym / nie mieści się w ten sposób.",
                                    "Złe pole!",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                if (SwingUtilities.isRightMouseButton(e)) {
                    if (GetPlace2(a, b) != null) {
                        JOptionPane.showMessageDialog(app.getFrame(),
                                "Proszę stawiać statki na swojej planszy (TWOJA PLANSZA).",
                                "Złe pole!",
                                JOptionPane.ERROR_MESSAGE);

                    }
                    if (GetPlace3(a, b) != null) {
                        Integer[] g;
                        g = GetPlace3(a, b);

                        if (Ship.CanBePlaced(g, shipsSizes[k], false)) {
                            Ship ship = new Ship(shipsSizes[k], false, g);
                            ships[k] = ship;
                            k++;


                            paintShips();
                            if (k >= shipsSizes.length) {
                                app.setGameState(3);
                                sendMessageThatYouAreReady();

                                if (app.isAdmin()) {
                                    changeSides= false;
                                    repaint();
                                    JOptionPane.showMessageDialog(app.getFrame(),
                                            "Ustawiono wszystkie statki, Teraz pora atakować.",
                                            "Do ataku!",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    changeSides= true;
                                    repaint();
                                    JOptionPane.showMessageDialog(app.getFrame(),
                                            "Zaczekaj aż Administrator ustawi statki i Cię zaatakuje.",
                                            "Do ataku!",
                                            JOptionPane.INFORMATION_MESSAGE);



                                }


                            }


                        } else {
                            JOptionPane.showMessageDialog(app.getFrame(),
                                    "Nie można tu postawić statku(styka się z innym / nie mieści się w ten sposób.",
                                    "Złe pole!",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }


        }

        *//**
         * @param g
         * @return sprawdza czy mozna zaatakowac dany kwadrat
         *//*
        private boolean pointCanBeAttacked(Integer[] g) {
            return !alreadyAttacked[g[0]][g[1]];

        }


        *//**
         * @param arg0
         *//*
        @Override
        public void mouseEntered(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        *//**
         * @param arg0
         *//*
        @Override
        public void mouseExited(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        *//**
         * @param arg0
         *//*
        @Override
        public void mousePressed(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        *//**
         * @param arg0
         *//*
        @Override
        public void mouseReleased(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }


    }
}*/
import javax.microedition.sensor;
        import com.shimmerresearch.bluetooth.ShimmerBluetooth;
import com.shimmerresearch.driver.Configuration.Shimmer3;
import com.shimmerresearch.pcdriver.CallbackObject;
import com.shimmerresearch.pcdriver.ShimmerPC;
        import sun.management.Sensor;

        import javax.swing.*;
import javax.vecmath.Matrix3d;
import javax.vecmath.Quat4d;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class SimpleExample9DoF extends BasicProcessWithCallBack {
    sensor

    private JFrame frame;
    ShimmerPC mShimmer = new ShimmerPC("ShimmerDevice", true);
    ShimmerPC mShimmer2 = new ShimmerPC("ShimmerDevice2", true);
    private JTextField textFieldQW;
    private JTextField textFieldQX;
    private JTextField textFieldQY;
    private JTextField textFieldQZ;
    private boolean mFirstTime = true;
    private boolean mFirstTime2 = true;
    private JTextField textFieldm20;
    private JTextField textFieldm10;
    private JTextField textFieldm00;
    private JTextField textFieldm01;
    private JTextField textFieldm11;
    private JTextField textFieldm21;
    private JTextField textFieldm02;
    private JTextField textFieldm12;
    private JTextField textFieldm22;
    private JTextField textField2QW;
    private JTextField textField2QX;
    private JTextField textField2QY;
    private JTextField textField2QZ;
    private JTextField textField2m22;
    private JTextField textField2m12;
    private JTextField textField2m02;
    private JTextField textField2m01;
    private JTextField textField2m11;
    private JTextField textField2m21;
    private JTextField textField2m20;
    private JTextField textField2m10;
    private JTextField textField2m00;
    private JLabel label;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JTextField textField_13;
    private JTextField textField_14;
    private JTextField textField_15;
    private JLabel lblQuaternionDev;
    private JLabel lblQuaternionDev_1;
    private JLabel lblRotMatrixDev;
    private JLabel lblRotMatrixDev_1;
    Matrix3d m3d1 = new Matrix3d();
    Matrix3d m3d2 = new Matrix3d();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SimpleExample9DoF window = new SimpleExample9DoF();
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * Create the application.
     */
    public SimpleExample9DoF() {
        initialize();
        frame.getContentPane().setLayout(null);
        setWaitForData(mShimmer);
        setWaitForData(mShimmer2);
        JButton btnNewButton = new JButton("Start Streaming");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                mShimmer.startStreaming();
                mShimmer2.startStreaming();
            }
        });
        btnNewButton.setBounds(10, 11, 107, 23);
        frame.getContentPane().add(btnNewButton);

        JLabel lblW = new JLabel("w");
        lblW.setBounds(138, 51, 46, 14);
        frame.getContentPane().add(lblW);

        JLabel lblX = new JLabel("x");
        lblX.setBounds(138, 76, 46, 14);
        frame.getContentPane().add(lblX);

        JLabel lblY = new JLabel("y");
        lblY.setBounds(138, 101, 46, 14);
        frame.getContentPane().add(lblY);

        JLabel lblZ = new JLabel("z");
        lblZ.setBounds(138, 126, 46, 14);
        frame.getContentPane().add(lblZ);

        textFieldQW = new JTextField();
        textFieldQW.setBounds(158, 48, 86, 20);
        frame.getContentPane().add(textFieldQW);
        textFieldQW.setColumns(10);

        textFieldQX = new JTextField();
        textFieldQX.setColumns(10);
        textFieldQX.setBounds(158, 73, 86, 20);
        frame.getContentPane().add(textFieldQX);

        textFieldQY = new JTextField();
        textFieldQY.setColumns(10);
        textFieldQY.setBounds(158, 98, 86, 20);
        frame.getContentPane().add(textFieldQY);

        textFieldQZ = new JTextField();
        textFieldQZ.setColumns(10);
        textFieldQZ.setBounds(158, 126, 86, 20);
        frame.getContentPane().add(textFieldQZ);

        textFieldm20 = new JTextField();
        textFieldm20.setColumns(10);
        textFieldm20.setBounds(158, 222, 86, 20);
        frame.getContentPane().add(textFieldm20);

        textFieldm10 = new JTextField();
        textFieldm10.setColumns(10);
        textFieldm10.setBounds(158, 194, 86, 20);
        frame.getContentPane().add(textFieldm10);

        textFieldm00 = new JTextField();
        textFieldm00.setColumns(10);
        textFieldm00.setBounds(158, 169, 86, 20);
        frame.getContentPane().add(textFieldm00);

        textFieldm01 = new JTextField();
        textFieldm01.setColumns(10);
        textFieldm01.setBounds(273, 169, 86, 20);
        frame.getContentPane().add(textFieldm01);

        textFieldm11 = new JTextField();
        textFieldm11.setColumns(10);
        textFieldm11.setBounds(273, 194, 86, 20);
        frame.getContentPane().add(textFieldm11);

        textFieldm21 = new JTextField();
        textFieldm21.setColumns(10);
        textFieldm21.setBounds(273, 222, 86, 20);
        frame.getContentPane().add(textFieldm21);

        textFieldm02 = new JTextField();
        textFieldm02.setColumns(10);
        textFieldm02.setBounds(384, 169, 86, 20);
        frame.getContentPane().add(textFieldm02);

        textFieldm12 = new JTextField();
        textFieldm12.setColumns(10);
        textFieldm12.setBounds(384, 194, 86, 20);
        frame.getContentPane().add(textFieldm12);

        textFieldm22 = new JTextField();
        textFieldm22.setColumns(10);
        textFieldm22.setBounds(384, 222, 86, 20);
        frame.getContentPane().add(textFieldm22);

        textField2QW = new JTextField();
        textField2QW.setColumns(10);
        textField2QW.setBounds(516, 45, 86, 20);
        frame.getContentPane().add(textField2QW);

        textField2QX = new JTextField();
        textField2QX.setColumns(10);
        textField2QX.setBounds(516, 73, 86, 20);
        frame.getContentPane().add(textField2QX);

        textField2QY = new JTextField();
        textField2QY.setColumns(10);
        textField2QY.setBounds(516, 98, 86, 20);
        frame.getContentPane().add(textField2QY);

        textField2QZ = new JTextField();
        textField2QZ.setColumns(10);
        textField2QZ.setBounds(516, 126, 86, 20);
        frame.getContentPane().add(textField2QZ);

        textField2m22 = new JTextField();
        textField2m22.setColumns(10);
        textField2m22.setBounds(741, 222, 86, 20);
        frame.getContentPane().add(textField2m22);

        textField2m12 = new JTextField();
        textField2m12.setColumns(10);
        textField2m12.setBounds(741, 194, 86, 20);
        frame.getContentPane().add(textField2m12);

        textField2m02 = new JTextField();
        textField2m02.setColumns(10);
        textField2m02.setBounds(741, 169, 86, 20);
        frame.getContentPane().add(textField2m02);

        textField2m01 = new JTextField();
        textField2m01.setColumns(10);
        textField2m01.setBounds(630, 169, 86, 20);
        frame.getContentPane().add(textField2m01);

        textField2m11 = new JTextField();
        textField2m11.setColumns(10);
        textField2m11.setBounds(630, 194, 86, 20);
        frame.getContentPane().add(textField2m11);

        textField2m21 = new JTextField();
        textField2m21.setColumns(10);
        textField2m21.setBounds(630, 222, 86, 20);
        frame.getContentPane().add(textField2m21);

        textField2m20 = new JTextField();
        textField2m20.setColumns(10);
        textField2m20.setBounds(515, 222, 86, 20);
        frame.getContentPane().add(textField2m20);

        textField2m10 = new JTextField();
        textField2m10.setColumns(10);
        textField2m10.setBounds(515, 194, 86, 20);
        frame.getContentPane().add(textField2m10);

        textField2m00 = new JTextField();
        textField2m00.setColumns(10);
        textField2m00.setBounds(515, 169, 86, 20);
        frame.getContentPane().add(textField2m00);

        label = new JLabel("w");
        label.setBounds(500, 48, 46, 14);
        frame.getContentPane().add(label);

        label_1 = new JLabel("x");
        label_1.setBounds(500, 73, 46, 14);
        frame.getContentPane().add(label_1);

        label_2 = new JLabel("y");
        label_2.setBounds(500, 98, 46, 14);
        frame.getContentPane().add(label_2);

        label_3 = new JLabel("z");
        label_3.setBounds(500, 123, 46, 14);
        frame.getContentPane().add(label_3);

        textField_13 = new JTextField();
        textField_13.setColumns(10);
        textField_13.setBounds(576, 339, 86, 20);
        frame.getContentPane().add(textField_13);

        textField_14 = new JTextField();
        textField_14.setColumns(10);
        textField_14.setBounds(465, 339, 86, 20);
        frame.getContentPane().add(textField_14);

        textField_15 = new JTextField();
        textField_15.setColumns(10);
        textField_15.setBounds(350, 339, 86, 20);
        frame.getContentPane().add(textField_15);

        lblQuaternionDev = new JLabel("Quaternion Dev1");
        lblQuaternionDev.setBounds(158, 26, 98, 14);
        frame.getContentPane().add(lblQuaternionDev);

        lblQuaternionDev_1 = new JLabel("Quaternion Dev2");
        lblQuaternionDev_1.setBounds(516, 26, 98, 14);
        frame.getContentPane().add(lblQuaternionDev_1);

        lblRotMatrixDev = new JLabel("Rot Matrix Dev1");
        lblRotMatrixDev.setBounds(158, 151, 98, 14);
        frame.getContentPane().add(lblRotMatrixDev);

        lblRotMatrixDev_1 = new JLabel("Rot Matrix Dev2");
        lblRotMatrixDev_1.setBounds(516, 151, 98, 14);
        frame.getContentPane().add(lblRotMatrixDev_1);
        mShimmer.connect("COM19", "");
        mShimmer.enable3DOrientation(true);
        mShimmer2.connect("COM16", "");
        mShimmer2.enable3DOrientation(true);

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1643, 592);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    protected void processMsgFromCallback(ShimmerMsg shimmerMSG) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        int ind = shimmerMSG.mIdentifier;
        Object object = shimmerMSG.mB;
        if (ind == ShimmerPC.MSG_IDENTIFIER_STATE_CHANGE) {
            CallbackObject callbackObject = (CallbackObject)object;
            int state = callbackObject.mIndicator;
            String bAdd = callbackObject.bluetoothAddress;
            if (state == ShimmerBluetooth.STATE_CONNECTING) {

            } else if (state == ShimmerBluetooth.STATE_CONNECTED) {
                if(mFirstTime && bAdd.equals(mShimmer.getBluetoothAddress())){
                    mShimmer.writeSamplingRate(10.1);
                    mShimmer.writeEnabledSensors(ShimmerObject.SENSOR_ACCEL|ShimmerObject.SENSOR_GYRO|ShimmerObject.SENSOR_MAG);
                    mFirstTime = false;
                }
                if(mFirstTime2 && bAdd.equals(mShimmer2.getBluetoothAddress())){
                    mShimmer.writeSamplingRate(10.1);
                    mShimmer.writeEnabledSensors(ShimmerObject.SENSOR_ACCEL|ShimmerObject.SENSOR_GYRO|ShimmerObject.SENSOR_MAG);
                    mFirstTime2 = false;
                }
            }  else {

            }
        }
        else if (ind == ShimmerPC.MSG_IDENTIFIER_DATA_PACKET) {
            ObjectCluster objectCluster = (ObjectCluster)object;

            if (objectCluster!=null){
                if (objectCluster.mMyName.equals("ShimmerDevice")){
                    Collection<FormatCluster> accelXFormats = objectCluster.mPropertyCluster.get(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_W);  // first retrieve all the possible formats for the current sensor device
                    float q0 = 0,x = 0,y=0,z=0;
                    if (accelXFormats != null){
                        FormatCluster formatCluster = ((FormatCluster)ObjectCluster.returnFormatCluster(accelXFormats,"CAL")); // retrieve the calibrated data
                        q0 = (float) formatCluster.mData;
                        textFieldQW.setText(Double.toString(q0));
                    }
                    Collection<FormatCluster> accelYFormats = objectCluster.mPropertyCluster.get(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_X);  // first retrieve all the possible formats for the current sensor device
                    if (accelYFormats != null){
                        FormatCluster formatCluster = ((FormatCluster)ObjectCluster.returnFormatCluster(accelYFormats,"CAL")); // retrieve the calibrated data
                        x=(float) formatCluster.mData;
                        textFieldQX.setText(Double.toString(x));
                    }
                    Collection<FormatCluster> accelZFormats = objectCluster.mPropertyCluster.get(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_Y);  // first retrieve all the possible formats for the current sensor device
                    if (accelZFormats != null){
                        FormatCluster formatCluster = ((FormatCluster)ObjectCluster.returnFormatCluster(accelZFormats,"CAL")); // retrieve the calibrated data
                        y=(float) formatCluster.mData;
                        textFieldQY.setText(Double.toString(y));
                    }
                    Collection<FormatCluster> aaFormats = objectCluster.mPropertyCluster.get(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_Z);  // first retrieve all the possible formats for the current sensor device
                    if (aaFormats != null){
                        FormatCluster formatCluster = ((FormatCluster)ObjectCluster.returnFormatCluster(aaFormats,"CAL")); // retrieve the calibrated data
                        z=(float) formatCluster.mData;
                        textFieldQZ.setText(Double.toString(z));
                    }
                    Quat4d q = new Quat4d(x, y,z,q0);

                    m3d1.set(q);
                    textFieldm00.setText(Double.toString(m3d1.m00));
                    textFieldm01.setText(Double.toString(m3d1.m01));
                    textFieldm02.setText(Double.toString(m3d1.m02));
                    textFieldm10.setText(Double.toString(m3d1.m10));
                    textFieldm11.setText(Double.toString(m3d1.m11));
                    textFieldm12.setText(Double.toString(m3d1.m12));
                    textFieldm20.setText(Double.toString(m3d1.m20));
                    textFieldm21.setText(Double.toString(m3d1.m21));
                    textFieldm22.setText(Double.toString(m3d1.m22));
                }


                if (objectCluster.mMyName.equals("ShimmerDevice2")){
                    Collection<FormatCluster> accelXFormats = objectCluster.mPropertyCluster.get(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_W);  // first retrieve all the possible formats for the current sensor device
                    float q0 = 0,x = 0,y=0,z=0;
                    if (accelXFormats != null){
                        FormatCluster formatCluster = ((FormatCluster)ObjectCluster.returnFormatCluster(accelXFormats,"CAL")); // retrieve the calibrated data
                        q0 = (float) formatCluster.mData;
                        textField2QW.setText(Double.toString(q0));
                    }
                    Collection<FormatCluster> accelYFormats = objectCluster.mPropertyCluster.get(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_X);  // first retrieve all the possible formats for the current sensor device
                    if (accelYFormats != null){
                        FormatCluster formatCluster = ((FormatCluster)ObjectCluster.returnFormatCluster(accelYFormats,"CAL")); // retrieve the calibrated data
                        x=(float) formatCluster.mData;
                        textField2QX.setText(Double.toString(x));
                    }
                    Collection<FormatCluster> accelZFormats = objectCluster.mPropertyCluster.get(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_Y);  // first retrieve all the possible formats for the current sensor device
                    if (accelZFormats != null){
                        FormatCluster formatCluster = ((FormatCluster)ObjectCluster.returnFormatCluster(accelZFormats,"CAL")); // retrieve the calibrated data
                        y=(float) formatCluster.mData;
                        textField2QY.setText(Double.toString(y));
                    }
                    Collection<FormatCluster> aaFormats = objectCluster.mPropertyCluster.get(Shimmer3.ObjectClusterSensorName.QUAT_MADGE_9DOF_Z);  // first retrieve all the possible formats for the current sensor device
                    if (aaFormats != null){
                        FormatCluster formatCluster = ((FormatCluster)ObjectCluster.returnFormatCluster(aaFormats,"CAL")); // retrieve the calibrated data
                        z=(float) formatCluster.mData;
                        textField2QZ.setText(Double.toString(z));
                    }
                    Quat4d q = new Quat4d(x, y,z,q0);

                    m3d2.set(q);
                    textField2m00.setText(Double.toString(m3d2.m00));
                    textField2m01.setText(Double.toString(m3d2.m01));
                    textField2m02.setText(Double.toString(m3d2.m02));
                    textField2m10.setText(Double.toString(m3d2.m10));
                    textField2m11.setText(Double.toString(m3d2.m11));
                    textField2m12.setText(Double.toString(m3d2.m12));
                    textField2m20.setText(Double.toString(m3d2.m20));
                    textField2m21.setText(Double.toString(m3d2.m21));
                    textField2m22.setText(Double.toString(m3d2.m22));
                }



            }
        }

    }
}

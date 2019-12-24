import com.sun.xml.internal.bind.v2.TODO;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class Ship {
    private final int Type;
    private boolean Horizontal;
    private static Boolean[][] CellsState;
    private static final int CellsXCells =10;


    /**
     * @param type
     * @param horizontal
     * @param a
     * konstruktor Ship przyjmuje czy statek bedzie pionowo, typ, i dlugosc
     */
    public Ship(int type, boolean horizontal, Integer[] a) {
        this.Type = type;
        this.Horizontal = horizontal;
        setPlace(a);
    }

    /**
     * @return
     * sprawcza czy gracz wygral
     */
    public static Boolean isLost(){
        for (int i = 0; i < CellsXCells; i++) {
            for (int j = 0; j < CellsXCells; j++) {
                if(CellsState[i][j]) return false;

            }
        }
        return true;
    }


    /**
     * @param a
     * @param Type
     * @param hor
     * @return
     * sprawdza czy statek moze byc postawiony
     */
    public static boolean CanBePlaced(Integer[] a, int Type, Boolean hor) {
        if (hor) {


            if ((Type + a[0] >= CellsXCells)) {
                if (!(AreCellAlive(CellsXCells - Type, a[1], CellsXCells -1, a[1]))) {
                    if (isSpaceBetween(CellsXCells - Type, a[1], CellsXCells -1, a[1], hor)) {

                        return true;
                    }
                }


            } else if ((Type + a[0] < CellsXCells)) {
                if (!AreCellAlive(a[0], a[1], a[0] + Type, a[1])) {
                    if (isSpaceBetween(a[0], a[1], a[0] + Type - 1, a[1], hor)) {
                        return true;

                    }
                }
            }
        } else {
            if (Type + a[1] >= CellsXCells){
                if(!AreCellAlive(a[0], CellsXCells - Type, a[0], CellsXCells -1)){
                    if (isSpaceBetween(a[0], CellsXCells - Type, a[0], CellsXCells -1, hor)){
                        return true;
                    }
                }
            }else if ((Type + a[1] < CellsXCells)){
                if(!AreCellAlive(a[0], a[1], a[0], a[1] + Type))
                    if(isSpaceBetween(a[0], a[1], a[0], a[1] + Type-1, hor)){
                        return true;
                    }
            }
        }

        return false;
    }


    /**
     * inicjalizacja pol jako pustych
     */
    public static void init() {
        CellsState = new Boolean[CellsXCells][CellsXCells];
        for (int i = 0; i < CellsXCells; i++) {
            for (int j = 0; j < CellsXCells; j++) {
                CellsState[i][j] = false;
            }
        }
    }

    /**
     * @return
     * zwraca dwuwymiarowa tablice na ktorej sa statki
     */
    public static Boolean[][] getCellsState() {
        return CellsState;
    }

    /**
     * @param x
     * @param y
     * @return
     * sprawdza czy na danej komorce jest czesc statku
     */
    public static Boolean isCellAlive(int x, int y) {
        if (CellsState[x][y]) return true;
        else
            return false;
    }


    /**
     * @param x
     * @param y
     * @return
     * sprawdza czy obok danej komorki jest juz komorka i jesli jest to zwraca true
     */
    public static Boolean isNotNextToSmth(int x, int y) {
        if (x == 0) {
            System.out.println("1");
            if (y == 0) {
                System.out.println("2");
                if (isCellAlive(x + 1, y)) return false;
                if (isCellAlive(x, y + 1)) return false;

            } else if (y == (CellsXCells -1)) {
                System.out.println("3");
                if (isCellAlive(x + 1, y)) return false;
                if (isCellAlive(x, y - 1)) return false;

            } else {
                System.out.println("4");
                if (isCellAlive(x + 1, y)) return false;
                if (isCellAlive(x, y - 1)) return false;
                if (isCellAlive(x, y + 1)) return false;

            }
        }
        if (x == (CellsXCells -1)) {
            if (y == CellsXCells -1) {
                if (isCellAlive(x - 1, y)) return false;
                if (isCellAlive(x, y - 1)) return false;

            } else if (y == 0) {
                if (isCellAlive(x - 1, y)) return false;
                if (isCellAlive(x, y + 1)) return false;
            } else if(y!=0 && y!=(CellsXCells -1)){
                if (isCellAlive(x - 1, y)) return false;
                if (isCellAlive(x, y - 1)) return false;
                if (isCellAlive(x, y + 1)) return false;

            }

        }
        if (x != 0 && x != (CellsXCells -1)) {
            if (y == 0) {

                if (isCellAlive(x, y + 1)) return false;
                if (isCellAlive(x - 1, y)) return false;
                if (isCellAlive(x + 1, y)) return false;


            } else if (y == (CellsXCells -1)) {

                if (isCellAlive(x, y - 1)) return false;
                if (isCellAlive(x - 1, y)) return false;
                if (isCellAlive(x + 1, y)) return false;

            } else {

                if (isCellAlive(x - 1, y)) return false;
                if (isCellAlive(x + 1, y)) return false;
                if (isCellAlive(x, y - 1)) return false;
                if (isCellAlive(x, y + 1)) return false;

            }


        }
        return true;
    }


    /**
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @param horizontal
     * @return
     * sprawdza czy jest wolna przestrzen pomiedzy komorkami
     */
    public static Boolean isSpaceBetween(int x, int y, int x2, int y2, Boolean horizontal) {
        if (horizontal) {
            for (int i = x; i <= x2; i++) {
                if (!isNotNextToSmth(i, y)) return false;

            }
        } else {
            for (int i = y; i <= y2; i++) {
                if (!isNotNextToSmth(x, i)) return false;
            }
        }
        return true;
    }

    /**
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return
     * sprawdza czy komorki sa zywe
     */
    public static Boolean AreCellAlive(int x, int y, int x2, int y2) {


        if (x == x2) {
            for (int i = y; i < y2; i++) {
                if (isCellAlive(x, i)) {
                    return true;
                }

            }
        }
        if (y == y2) {
            for (int i = x; i < x2; i++) {
                if (isCellAlive(i, y)) {
                    return true;
                }
            }
        } else return false;
        return false;
    }


    /**
     * @param x
     * @param y
     * ustawia dana komórke jako zywa(zaludniona przez statek
     */
    public static void setCellAlive(int x, int y) {
        if (CellsState[x][y]) System.out.println("ERROR - SET CELL ALIVE ALREADY ");
        else CellsState[x][y] = true;

    }


    /**
     * @param x
     * @param y
     * ustawia wartosc false dla danej komorki (gdy statek jest trafiony)
     */
    public static void setCellDead(int x, int y) {
        if (!CellsState[x][y]) System.out.println("ERROR - SET CELL DEAD  ALREADY");
        else CellsState[x][y] = false;
    }


    /**
     * @param a
     *
     * ustawia miejsce statku
     */
    public void setPlace(Integer[] a) {
        if (isHorizontal()) {
            if (Type + a[0] > CellsXCells) {

                for (int i = 0; i < Type; i++) {
                    setCellAlive(CellsXCells - Type + i, a[1]);


                }


            } else {
                for (int i = 0; i < Type; i++) {
                    setCellAlive(a[0] + i, a[1]);
                }

            }

        } else {
            if (Type + a[1] > CellsXCells) {
                for (int i = 0; i < Type; i++) {
                    setCellAlive(a[0], CellsXCells - Type + i);
                }
            } else {
                for (int i = 0; i < Type; i++) {

                    setCellAlive(a[0], a[1] + i);
                }
            }

        }
    }


    /**
     * @return
     * zwraca typ statku
     */
    public int getType() {
        return Type;
    }


    /**
     * @return
     * zwraca czy statek jest poziomo
     */
    public boolean isHorizontal() {
        return Horizontal;
    }

    /**
     * @param horizontal
     * ustawia statek poziomo dla true i pionowo dla false
     */
    public void setHorizontal(boolean horizontal) {
        Horizontal = horizontal;
    }


}

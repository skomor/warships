import java.awt.*;

public class Theme {
    public static Color TRANSPARENT = new Color(0xFF, 0xFF, 0xFF, 0x00);
    public static Color SCREEN_BACKGROUND_COLOR;
    public static Color BORDER_COLOR_DEFAULT,
            VERY_DARK_RED,
            VERY_DARK_GREEN,
                        BACKGROUND_COLOR_PRESSED,
                        COLOR_SHADOW_PRE_BATTLE;
    public static Color CELLS_BORDER_COLOR,
                        CELLS_BACKGROUND_COLOR;
    static {
        SCREEN_BACKGROUND_COLOR = new Color(204, 233, 255);
        BORDER_COLOR_DEFAULT = new Color(0, 26, 178);
        VERY_DARK_RED = new Color(153, 0, 0);
        VERY_DARK_GREEN = new Color(0, 102, 0);
        BACKGROUND_COLOR_PRESSED = new Color(230, 255, 255);
        
        CELLS_BORDER_COLOR = new Color(204, 204, 204);
        CELLS_BACKGROUND_COLOR = new Color(237, 247, 255);
        
        COLOR_SHADOW_PRE_BATTLE = new Color(32, 32, 32, 128);
    } 
    
}

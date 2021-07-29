package view;

import model.*;
import java.awt.*;


public interface ResultDisplay extends ModelObserver {

    public static final Color DISPLAY_COLOR = Color.CYAN;
    public abstract Label getOldDisplay();
    public abstract Label getDisplayEntry();

}

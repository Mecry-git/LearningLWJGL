package Engine.io.OutputPrint;

import java.awt.*;

public interface Terminal {
    static void init() {
        printWelcomeWords();
        printHelperWords();
    }
    static void printWelcomeWords() {
        System.out.println("Welcome to this app!");
    }
    static void printHelperWords() {
        System.out.println("Press \"F3 + Control + Alt + H\" to open" +
                " the helper panel. ");
    }
    static void printHelperPanel() {
        System.out.println("""
                            You pressed "F3 + Control + Alt + H"! Help menu:\s
                            \s
                            Press F3 to open CMD print service.\s
                            Press F3 and press other key to print their scancode and
                             states.\s
                            Press F3 and press mouse buttons to print their states.\s
                            Press F3 and move mouse to print mouse position.\s
                            Press F3 and turn mouseScroll to print mouseScroll state.\s
                            \s
                            Press "F8 + C" to change background color(black or red).\s
                            \s
                            Press "Control + Alt + P" to change the way to change that
                             camera rotation(mouse or key).\s
                            Mouse: Move mouse to change camera rotation.\s
                            Key: Press "U", "J", "H", "K" to change camera rotation.\s
                            \s
                            Release F3 to close CMD print service.\s
                            \s
                            \s
                            \s
                            \s""");
    }
    static void printCloseWords() {
        System.out.println("Closing app. Goodbye! ");
    }

    static void printBackgroundChanged(String color) {
        System.out.println("Change background color to " + color + "!");
    }

    static void printKeysState(int key, boolean state) {
        if (state) System.out.println("The key which scancode " + key + " was pressed!");
        else System.out.println("The key which scancode " + key + " was released!");
    }
    static void printKeyF3State(boolean state) {
        if (state)
            System.out.println("F3 was pressed! Help to press " +
                "\"F3 + Control + Alt + H\" " +
                "to open F3 help menu!");
        else System.out.println("F3 was released!");
    }
    static void printKeyF8State(boolean state) {
        if (state)
            System.out.println("F8 was Pressed! Press \"F8 + C\" to " +
                    "change background color!(black or red)!");
        else System.out.println("F8 was Released!");
    }
    static void printKeyEscPressed() {
        System.out.println("Pressed ESC! Release it to close game!");
    }

    static void printMousePositionState(Point mousePos) {
        System.out.println("MousePosition: " + mousePos.x + " | " + mousePos.y);
    }
    static void printMouseScrollState(Point mouseScrollPos) {
        if (mouseScrollPos.y > 0)
            System.out.println("MouseScroll turned up " +
                    mouseScrollPos.y + " point(s)!");
        if (mouseScrollPos.y < 0)
            System.out.println("MouseScroll turned down " +
                    -mouseScrollPos.y + " point(s)!");
    }
    static void printMouseButtonsState(int but, boolean state) {
        switch (but) {
            case 0:
                //mouse left
                if (state)
                    System.out.println("Mouse left button was pressed!");
                else
                    System.out.println("Mouse left button was released!");
                break;
            case 1:
                //mouse middle
                if (state)
                    System.out.println("Mouse middle button was pressed!");
                else
                    System.out.println("Mouse middle button was released!");
                break;
            case 2:
                //mouse right
                if (state)
                    System.out.println("Mouse right button was pressed!");
                else
                    System.out.println("Mouse right button was released!");
                break;
        }
    }
}

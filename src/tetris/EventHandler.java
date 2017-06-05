package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class EventHandler implements KeyListener {
    private final GameField gameField;
    private final GameGraphics gameGraphics;

    EventHandler(GameField field, GameGraphics graphics) {
        gameField = field;
        gameGraphics = graphics;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 40: {
                gameField.rotateCurrentFigureLeft();
                break;
            }
            case 38: {
                gameField.rotateCurrentFigureRight();
                break;
            }
            case 37: {
                gameField.moveCurrentFigureLeft();
                break;
            }
            case 39: {
                gameField.moveCurrentFigureRight();
                break;
            }
        }
        gameGraphics.render();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

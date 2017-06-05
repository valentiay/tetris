package tetris;

import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;

class GameField {
    private final static int FIELD_WIDTH = 10;
    private final static int FIELD_HEIGHT = 20;

    private ArrayList<ArrayList<Color>> field;
    private Figure currentFigure;

    GameField() {
        field = new ArrayList<>(FIELD_WIDTH);
        for (int i = 0; i < FIELD_WIDTH; i++) {
            field.add(i, new ArrayList<>(FIELD_HEIGHT));
            for (int j = 0; j < FIELD_HEIGHT; j++) {
                field.get(i).add(j, null);
            }
        }
    }

    void update() {
        if (currentFigure != null) {
            currentFigure.posY--;
            if (figureCollides(currentFigure)) {
                currentFigure.posY++;
                frozeFigure(currentFigure);
                currentFigure = null;
            }
        }
        if (currentFigure == null) {
            currentFigure = Figure.makeRandomFigure();
        }
        refreshField();
    }

    static int getFieldWidth() {
        return FIELD_WIDTH;
    }

    static int getFieldHeight() {
        return FIELD_HEIGHT;
    }

    Color getColor(int x, int y) {
        return field.get(x).get(y);
    }

    Figure getCurrentFigure() {
        return currentFigure;
    }

    void rotateCurrentFigureLeft() {
        Figure newCurrentFigure = currentFigure.rotateLeft();
        if (!figureCollides(newCurrentFigure)) {
            currentFigure = newCurrentFigure;
        }
    }

    void rotateCurrentFigureRight() {
        Figure newCurrentFigure = currentFigure.rotateRight();
        if (!figureCollides(newCurrentFigure)) {
            currentFigure = newCurrentFigure;
        }
    }

    void moveCurrentFigureLeft() {
        currentFigure.posX--;
        if (figureCollides(currentFigure))
            currentFigure.posX++;
    }

    void moveCurrentFigureRight() {
        currentFigure.posX++;
        if (figureCollides(currentFigure))
            currentFigure.posX--;
    }

    boolean isGameOver() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (field.get(i).get(FIELD_HEIGHT - 5) != null)
                return true;
        }
        return false;
    }

    private boolean figureCollides(Figure figure) {
        for (Pair<Integer, Integer> i : figure.cells) {
            if (i.getValue() + figure.posY < 0)
                return true;
            if (figure.posX + i.getKey() < 0 || figure.posX + i.getKey() >= FIELD_WIDTH)
                return true;
            if (field.get(i.getKey() + figure.posX).get(i.getValue() + figure.posY) != null)
                return true;
        }
        return false;
    }

    private void frozeFigure(Figure figure) {
        for (Pair<Integer, Integer> i : figure.cells) {
            field.get(i.getKey() + figure.posX).set(i.getValue() + figure.posY, figure.color);
        }
    }

    private void refreshField() {
        for (int j = 0; j < FIELD_HEIGHT; j++) {
            boolean rowToRemove = true;
            for (int i = 0; i < FIELD_WIDTH; i++) {
                if (field.get(i).get(j) == null) {
                    rowToRemove = false;
                    break;
                }
            }
            if (rowToRemove)
                removeRow(j);
        }
    }

    private void removeRow(int index) {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = index; j < FIELD_HEIGHT - 1; j++) {
                field.get(i).set(j, field.get(i).get(j + 1));
            }
        }
    }
}
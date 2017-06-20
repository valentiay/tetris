package tetris;

import javafx.util.Pair;

import java.awt.*;
import java.util.LinkedList;

class Figure implements Cloneable {
    int posX = GameField.getFieldWidth() / 2 - 1;
    int posY = GameField.getFieldHeight() - 3;
    Color color;

    LinkedList<Pair<Integer, Integer>> cells = new LinkedList<>();

    static Figure makeRandomFigure() {
        int c = (int)Math.floor(7 * Math.random());
        switch (c) {
            case 0:
                return makeLeftL();
            case 1:
                return makeLeftZ();
            case 2:
                return makeLine();
            case 3:
                return makeRightL();
            case 4:
                return makeRightZ();
            case 5:
                return makeSquare();
            case 6:
                return makeT();
        }
        return null;
    }

    private Figure() {}

    private Figure(Figure src) {
        posX = src.posX;
        posY = src.posY;
        color = src.color;
        cells.addAll(src.cells);
    }

    Figure rotateLeft() {
        Figure result = new Figure(this);
        for (int i = 0; i < result.cells.size(); i++)
            result.cells.set(i, new Pair<>(-cells.get(i).getValue(), cells.get(i).getKey()));
        return result;
    }

    Figure rotateRight() {
        Figure result = new Figure(this);
        for (int i = 0; i < result.cells.size(); i++)
            result.cells.set(i, new Pair<>(cells.get(i).getValue(), -cells.get(i).getKey()));
        return result;
    }

    private static Figure makeLine() {
        Figure figure = new Figure();
        figure.color = new Color(22, 201, 108);
        figure.cells.add(new Pair<>(0, 1));
        figure.cells.add(new Pair<>(0, 0));
        figure.cells.add(new Pair<>(0, -1));
        figure.cells.add(new Pair<>(0, -2));
        return figure;
    }

    private static Figure makeSquare() {
        Figure figure = new Figure();
        figure.color = new Color(201, 130, 13);
        figure.cells.add(new Pair<>(0, 0));
        figure.cells.add(new Pair<>(0, -1));
        figure.cells.add(new Pair<>(1, 0));
        figure.cells.add(new Pair<>(1, -1));
        return figure;
    }

    private static Figure makeT() {
        Figure figure = new Figure();
        figure.color = new Color(201, 32, 17);
        figure.cells.add(new Pair<>(0, 0));
        figure.cells.add(new Pair<>(-1, -1));
        figure.cells.add(new Pair<>(0, -1));
        figure.cells.add(new Pair<>(1, -1));
        return figure;
    }

    private static Figure makeLeftL() {
        Figure figure = new Figure();
        figure.color = new Color(201, 81, 146);
        figure.cells.add(new Pair<>(0, 0));
        figure.cells.add(new Pair<>(-1, 0));
        figure.cells.add(new Pair<>(0, 1));
        figure.cells.add(new Pair<>(0, 2));
        return figure;
    }

    private static Figure makeRightL() {
        Figure figure = new Figure();
        figure.color = new Color(130, 66, 201);
        figure.cells.add(new Pair<>(0, 0));
        figure.cells.add(new Pair<>(1, 0));
        figure.cells.add(new Pair<>(0, 1));
        figure.cells.add(new Pair<>(0, 2));
        return figure;
    }

    private static Figure makeRightZ() {
        Figure figure = new Figure();
        figure.color = new Color(94, 167, 201);
        figure.cells.add(new Pair<>(-1, 0));
        figure.cells.add(new Pair<>(0, 0));
        figure.cells.add(new Pair<>(0, 1));
        figure.cells.add(new Pair<>(1, 1));
        return figure;
    }

    private static Figure makeLeftZ(){
        Figure figure = new Figure();
        figure.color = new Color(42, 201, 23);
        figure.cells.add(new Pair<>(0, 0));
        figure.cells.add(new Pair<>(-1, 1));
        figure.cells.add(new Pair<>(0, 1));
        figure.cells.add(new Pair<>(1, 0));
        return figure;
    }

}
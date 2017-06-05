package tetris;

import javax.swing.*;

public class Game {
    private double FPS = 2.0;
    private double dt = 1000.0 / FPS;

    private final JFrame frame = new JFrame("Tetris");
    private final GameField gameField = new GameField();
    private final GameGraphics gameGraphics = new GameGraphics(gameField);

    public Game() {}

    public void run() {
        System.out.println("run");
        frame.addKeyListener(new EventHandler(gameField, gameGraphics));
        javax.swing.SwingUtilities.invokeLater(() -> gameGraphics.showGUI(frame));
        double lastUpdateTime = System.currentTimeMillis();
        boolean wasUpdated = false;
        while (!gameField.isGameOver()) {
            while (System.currentTimeMillis() - lastUpdateTime > dt) {
                gameField.update();
                lastUpdateTime += dt;
                FPS += 0.005;
                dt = 1000.0 / FPS;
                wasUpdated = true;
            }
            if (wasUpdated) {
                gameGraphics.render();
                wasUpdated = false;
            }
        }
        gameGraphics.renderGameOverMsg();
    }
}
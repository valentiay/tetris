package tetris;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class GameGraphics {
    static final private int WIDTH = 300;
    static final private int HEIGHT = 600;

    private BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    private JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Creating a copy of the Graphics
            // so any reconfiguration we do on
            // it doesn't interfere with what
            // Swing is doing.
            Graphics2D g2 = (Graphics2D) g.create();
            // Drawing the image.
            int w = img.getWidth();
            int h = img.getHeight();
            g2.drawImage(img, 0, 0, w, h, null);
            // Drawing a swatch.
            // At the end, we dispose the
            // Graphics copy we've created
            g2.dispose();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(img.getWidth(), img.getHeight());
        }
    };
    private Graphics2D canvas = img.createGraphics();

    private final GameField gameField;
    private Dimension blockSize = new Dimension(WIDTH / GameField.getFieldWidth(),
                                                HEIGHT / GameField.getFieldHeight());

    GameGraphics(GameField field) {
        gameField = field;
    }

    void render() {
        canvas.clearRect(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < GameField.getFieldWidth(); i++) {
            for (int j = 0; j < GameField.getFieldHeight(); j++) {
                if (gameField.getColor(i, j) == null)
                    continue;
                canvas.setColor(gameField.getColor(i, j));
                canvas.fillRect(i * blockSize.width,
                                HEIGHT - (j + 1) * blockSize.height,
                                blockSize.width,
                                blockSize.height);
            }
        }

        Figure currentFigure = gameField.getCurrentFigure();
        if (currentFigure != null) {
            canvas.setColor(currentFigure.color);
            for (Pair<Integer, Integer> i : currentFigure.cells) {
                canvas.fillRect((currentFigure.posX + i.getKey()) * blockSize.width,
                                HEIGHT - (currentFigure.posY + 1 + i.getValue()) * blockSize.height,
                                blockSize.width,
                                blockSize.height);
            }
        }

        panel.repaint();
    }

    void renderGameOverMsg() {
        canvas.setColor(Color.white);
        canvas.drawString("Game Over", 100, 100);
        panel.repaint();
    }

    void showGUI(JFrame frame) {
        panel.setBackground(Color.white);
        frame.setContentPane(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
package org.snakeinc.snake;

import javax.swing.JFrame;
import org.snakeinc.snake.ui.GamePanel;
import org.snakeinc.snake.ui.PlayerSelectionPanel;

public class SnakeApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Inc");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final PlayerSelectionPanel[] selectionPanelHolder = new PlayerSelectionPanel[1];
        PlayerSelectionPanel selectionPanel = new PlayerSelectionPanel(e -> {
            var selectedPlayer = selectionPanelHolder[0].getSelectedPlayer();
            String playerName = selectedPlayer.getName();
            int playerId = selectedPlayer.getId();
            frame.getContentPane().removeAll();
            GamePanel gamePanel = new GamePanel(playerName, playerId);
            frame.add(gamePanel);
            frame.revalidate();
            frame.repaint();
            gamePanel.requestFocusInWindow();
        });
        selectionPanelHolder[0] = selectionPanel;

        frame.add(selectionPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
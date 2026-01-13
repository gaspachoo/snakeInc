package org.snakeinc.snake.ui;

import lombok.Getter;
import org.snakeinc.snake.dto.PlayerDTO;
import org.snakeinc.snake.service.ApiService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PlayerSelectionPanel extends JPanel {
    private final ApiService apiService;
    private final JList<PlayerDTO> playerList;
    private final DefaultListModel<PlayerDTO> listModel;
    private final JButton startButton;
    private final JButton addNewPlayerButton;
    @Getter
    private PlayerDTO selectedPlayer;

    public PlayerSelectionPanel(ActionListener onStartGame) {
        this.apiService = new ApiService();
        this.listModel = new DefaultListModel<>();
        this.playerList = new JList<>(listModel);
        this.startButton = new JButton("Start game");
        this.addNewPlayerButton = new JButton("Add new player");

        setupUI();
        loadPlayers();
        setupListeners(onStartGame);
    }

    private void setupUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(GamePanel.GAME_PIXEL_WIDTH, GamePanel.GAME_PIXEL_HEIGHT));

        JLabel titleLabel = new JLabel("Sélectionner un joueur", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        playerList.setFont(new Font("Arial", Font.PLAIN, 16));
        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        playerList.setBackground(Color.DARK_GRAY);
        playerList.setForeground(Color.WHITE);
        playerList.setSelectionBackground(Color.GREEN);
        playerList.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(playerList);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonsPanel.setBackground(Color.BLACK);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));

        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.BLACK);
        startButton.setEnabled(false);

        addNewPlayerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        addNewPlayerButton.setBackground(Color.GRAY);
        addNewPlayerButton.setForeground(Color.WHITE);
        addNewPlayerButton.setEnabled(false); // Inactif pour l'instant

        buttonsPanel.add(startButton);
        buttonsPanel.add(addNewPlayerButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void loadPlayers() {
        listModel.clear();
        List<PlayerDTO> players = apiService.getAllPlayers();

        for (PlayerDTO player : players) {
            listModel.addElement(player);
        }
    }

    private void setupListeners(ActionListener onStartGame) {
        playerList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedPlayer = playerList.getSelectedValue();
                startButton.setEnabled(selectedPlayer != null);
            }
        });

        startButton.addActionListener(e -> {
            if (selectedPlayer != null && onStartGame != null) {
                onStartGame.actionPerformed(e);
            }
        });

        addNewPlayerButton.addActionListener(e -> {});
    }
}


package org.snakeinc.snake.ui;

import lombok.Getter;
import org.snakeinc.snake.dto.PlayerDTO;
import org.snakeinc.snake.service.ApiService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PlayerSelectionPanel extends JPanel {
    private final ApiService apiService;
    private final JList<PlayerDTO> playerList;
    private final DefaultListModel<PlayerDTO> listModel;
    private final JButton startButton;
    private final JTextField usernameField;
    private final JTextField ageField;
    private final JButton newPlayerButton;
    private static final String NAME_PLACEHOLDER = "Your name";
    private static final String AGE_PLACEHOLDER  = "Your age";
    @Getter
    private PlayerDTO selectedPlayer;

    public PlayerSelectionPanel(ActionListener onStartGame) {
        this.apiService = new ApiService();
        this.listModel = new DefaultListModel<>();
        this.playerList = new JList<>(listModel);
        this.startButton = new JButton("Start game");
        this.usernameField = new JTextField();
        this.ageField = new JTextField();
        this.newPlayerButton = new JButton("Add new player");

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

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));

        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.BLACK);
        startButton.setEnabled(false);

        JPanel newPlayerPanel = new JPanel();
        newPlayerPanel.setLayout(new FlowLayout());
        configureTextField(usernameField, NAME_PLACEHOLDER);
        configureTextField(ageField, AGE_PLACEHOLDER);

        newPlayerButton.setFont(new Font("Arial", Font.PLAIN, 14));
        newPlayerButton.setBackground(Color.CYAN);
        newPlayerButton.setForeground(Color.BLACK);
        newPlayerButton.setEnabled(false);

        newPlayerPanel.add(usernameField);
        newPlayerPanel.add(ageField);
        newPlayerPanel.add(newPlayerButton);

        bottomPanel.add(startButton);
        bottomPanel.add(newPlayerPanel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void configureTextField(JTextField textField, String FieldPlaceHolder) {
        textField.setText(FieldPlaceHolder);
        textField.setMargin(new Insets(5, 5, 5, 5));
        textField.setPreferredSize(new Dimension(150, 30));
        textField.setBackground(Color.LIGHT_GRAY);
        textField.setForeground(Color.DARK_GRAY);
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(FieldPlaceHolder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setText(FieldPlaceHolder);
                    textField.setForeground(Color.DARK_GRAY);
                }
            }
        });
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

        DocumentListener fieldListener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { checkFields(); }
            public void removeUpdate(DocumentEvent e) { checkFields(); }
            public void changedUpdate(DocumentEvent e) { checkFields(); }
        };

        usernameField.getDocument().addDocumentListener(fieldListener);
        ageField.getDocument().addDocumentListener(fieldListener);

        newPlayerButton.addActionListener(e -> {
            String name = usernameField.getText().trim();
            String ageText = ageField.getText().trim();
            if (name.equals(NAME_PLACEHOLDER) || ageText.equals(AGE_PLACEHOLDER)) return;
            int age;

            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PlayerDTO newPlayer = apiService.createPlayer(name, age);
            if (newPlayer != null) {
                listModel.addElement(newPlayer);
                resetPlaceholders();
                newPlayerButton.setEnabled(false);
            } else {
                String errorMsg = apiService.getLastErrorMessage();
                if (errorMsg != null && !errorMsg.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Error: " + errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Unable to create player", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void checkFields() {
        String name = usernameField.getText().trim();
        String ageText = ageField.getText().trim();
        boolean nameValid = !name.isEmpty() && !name.equals("Your name");
        boolean ageValid = ageText.matches("\\d+");

        newPlayerButton.setEnabled(nameValid && ageValid);
    }

    private void resetPlaceholders() {
        usernameField.setText("Your name");
        usernameField.setForeground(Color.DARK_GRAY);
        ageField.setText("Your age");
        ageField.setForeground(Color.DARK_GRAY);
    }

}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame extends JFrame implements ActionListener {
    private int low = 1;
    private int high = 100;
    private int randNum;

    private int mxAttempts ;
    private int attempts = 0;
    private int score = 0;

    private JLabel titleLabel;
    private JLabel instrnL;
    private JTextField guessTf;
    private JButton guessBtn;
    private JTextArea resultTA;
    private JLabel attemptsL3;
    private JLabel scoreL4;

    public GuessingGame() {
        setTitle("Guess the Number Game");
        setLayout(null);

        Random random = new Random();
        randNum = random.nextInt(high - low + 1) + low;

        titleLabel = new JLabel("Welcome to Guess the Number Game!");
        titleLabel.setFont(new Font("System", Font.BOLD, 16));
        titleLabel.setBounds(320, 50, 400, 35);
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel);

        String attempt = JOptionPane.showInputDialog(this, "Enter the number of attempts:");
        try {
            mxAttempts = Integer.parseInt(attempt);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for attempts. Using default value of 5.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            mxAttempts = 5;
        }


        instrnL = new JLabel("I have selected a random number between " + low + " and " + high);
        instrnL.setFont(new Font("System", Font.BOLD, 16));
        instrnL.setBounds(280, 145, 400, 35);
        instrnL.setForeground(Color.BLACK);
        add(instrnL);

        guessTf = new JTextField();
        guessTf.setBounds(280, 200, 270, 30);
        guessTf.setFont(new Font("Arial", Font.BOLD, 14));
        add(guessTf);

        guessBtn = new JButton("Guess");
        guessBtn.setBackground(Color.BLACK);
        guessBtn.setForeground(Color.WHITE);
        guessBtn.setFont(new Font("Arial", Font.BOLD, 14));
        guessBtn.setBounds(550, 200, 100, 29);
        guessBtn.addActionListener(this);
        add(guessBtn);

        resultTA = new JTextArea();
        resultTA.setFont(new Font("Raleway", Font.BOLD, 14));
        resultTA.setBounds(280, 250, 450, 300);
        add(resultTA);



        attemptsL3 = new JLabel("Attempts left: " + (mxAttempts - attempts));
        attemptsL3.setFont(new Font("System", Font.BOLD, 16));
        attemptsL3.setBounds(300, 650, 150, 35);
        attemptsL3.setForeground(Color.BLACK);
        add(attemptsL3);

        scoreL4 = new JLabel("Your score: " + score);
        scoreL4.setFont(new Font("System", Font.BOLD, 16));
        scoreL4.setBounds(600, 650, 150, 35);
        scoreL4.setForeground(Color.BLACK);
        add(scoreL4);


        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(500, 120);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String enteredText = guessTf.getText();
        resultTA.append(enteredText + "\n");

        if (attempts <mxAttempts) {
            int userGuess;
            try {
                userGuess = Integer.parseInt(guessTf.getText());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            attempts++;

            if (userGuess == randNum) {

                resultTA.append("Congratulations! You guessed the correct number.\n");
                score += (mxAttempts - attempts + 1);
                guessBtn.setEnabled(false);
            } else if (userGuess < randNum) {
                resultTA.append("Try a higher number.\n");
//                resultTA.append(Integer.toString(randNum) + "\n");

            } else {
                resultTA.append("Try a lower number.\n");
//                resultTA.append(Integer.toString(randNum) + "\n");
            }

            attemptsL3.setText("Attempts left: " + (mxAttempts - attempts));
            scoreL4.setText("Your score: " + score);

            if (attempts == mxAttempts && userGuess != randNum) {
                resultTA.append("Sorry, you've run out of attempts. The correct number was " + randNum + "\n");
                guessBtn.setEnabled(false);
            }
        }

    }


    public static void main(String[] args) {
            GuessingGame game = new GuessingGame();
            game.setVisible(true);
    }
}


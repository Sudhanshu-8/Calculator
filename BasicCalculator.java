import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BasicCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Basic Calculator");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input and output field
        JTextField textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 30)); // Increased font size for better visibility
        textField.setPreferredSize(new Dimension(400, 70));  // Increased height for more space

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        // Variables for calculation logic
        final String[] operator = {""};
        final double[] num1 = {0};

        for (String label : buttons) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            buttonPanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();

                    try {
                        if ("0123456789".contains(cmd)) {
                            textField.setText(textField.getText() + cmd);
                        } else if ("/+-*".contains(cmd)) {
                            operator[0] = cmd;
                            num1[0] = Double.parseDouble(textField.getText());
                            textField.setText("");
                        } else if ("=".equals(cmd)) {
                            double num2 = Double.parseDouble(textField.getText());
                            double result;

                            switch (operator[0]) {
                                case "+" -> result = num1[0] + num2;
                                case "-" -> result = num1[0] - num2;
                                case "*" -> result = num1[0] * num2;
                                case "/" -> {
                                    if (num2 == 0) throw new ArithmeticException("Cannot divide by zero");
                                    result = num1[0] / num2;
                                }
                                default -> throw new IllegalStateException("Invalid operator");
                            }

                            textField.setText(String.valueOf(result));
                        } else if ("C".equals(cmd)) {
                            textField.setText("");
                            num1[0] = 0;
                            operator[0] = "";
                        }
                    } catch (Exception ex) {
                        textField.setText("Error");
                    }
                }
            });
        }

        // Add components to frame
        frame.add(textField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Show frame
        frame.setVisible(true);
    }
}

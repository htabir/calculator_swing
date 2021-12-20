//==========================================
// Title:  Calculator using Java Swing
// Author: htabir
// Date:   20 December 2021
//==========================================
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Calculator extends JFrame {

    Double number = 0.0;
    boolean dot = false, div_z = false;
    int cnt_o = 0, cnt_n = 0;
    JTextField input, output;

    // Constructor containing all components
    Calculator() {

        setTitle("Calculator");

        // init-ing all buttons
        JButton button_AC = new JButton("AC");
        button_AC.setBounds(0, 260, 100, 76);
        setButtonStyle(button_AC);
        button_AC.setFont(new Font("Calibri", Font.BOLD, 26));
        button_AC.setForeground(new Color(197, 102, 76));
        add(button_AC);

        JButton button_DEL = new JButton("DEL");
        button_DEL.setBounds(100, 260, 100, 76);
        setButtonStyle(button_DEL);
        add(button_DEL);

        JButton button_DIV = new JButton("/");
        button_DIV.setBounds(200, 260, 100, 76);
        setButtonStyle(button_DIV);
        add(button_DIV);

        JButton button_MUL = new JButton("*");
        button_MUL.setBounds(300, 260, 100, 76);
        setButtonStyle(button_MUL);
        add(button_MUL);

        JButton button_7 = new JButton("7");
        button_7.setBounds(0, 336, 100, 76);
        setButtonStyle(button_7);
        add(button_7);

        JButton button_8 = new JButton("8");
        button_8.setBounds(100, 336, 100, 76);
        setButtonStyle(button_8);
        add(button_8);

        JButton button_9 = new JButton("9");
        button_9.setBounds(200, 336, 100, 76);
        setButtonStyle(button_9);
        add(button_9);

        JButton button_SUB = new JButton("-");
        button_SUB.setBounds(300, 336, 100, 76);
        setButtonStyle(button_SUB);
        add(button_SUB);

        JButton button_4 = new JButton("4");
        button_4.setBounds(0, 412, 100, 76);
        setButtonStyle(button_4);
        add(button_4);

        JButton button_5 = new JButton("5");
        button_5.setBounds(100, 412, 100, 76);
        setButtonStyle(button_5);
        add(button_5);

        JButton button_6 = new JButton("6");
        button_6.setBounds(200, 412, 100, 76);
        setButtonStyle(button_6);
        add(button_6);

        JButton button_ADD = new JButton("+");
        button_ADD.setBounds(300, 412, 100, 76);
        setButtonStyle(button_ADD);
        add(button_ADD);

        JButton button_1 = new JButton("1");
        button_1.setBounds(0, 488, 100, 76);
        setButtonStyle(button_1);
        add(button_1);

        JButton button_2 = new JButton("2");
        button_2.setBounds(100, 488, 100, 76);
        setButtonStyle(button_2);
        add(button_2);

        JButton button_3 = new JButton("3");
        button_3.setBounds(200, 488, 100, 76);
        setButtonStyle(button_3);
        add(button_3);

        JButton button_EQT = new JButton("=");
        button_EQT.setBounds(300, 488, 100, 76 * 2);
        setButtonStyle(button_EQT);
        add(button_EQT);

        JButton button_0 = new JButton("0");
        button_0.setBounds(0, 564, 100, 76);
        setButtonStyle(button_0);
        add(button_0);

        JButton button_00 = new JButton("00");
        button_00.setBounds(100, 564, 100, 76);
        setButtonStyle(button_00);
        add(button_00);

        JButton button_DOT = new JButton(".");
        button_DOT.setBounds(200, 564, 100, 76);
        setButtonStyle(button_DOT);
        add(button_DOT);


        // init-ing text fields
        input = new JTextField();
        input.setHorizontalAlignment(SwingConstants.RIGHT);
        input.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        input.setBounds(24, 32, 352, 48);
        input.setFont(new Font("Calibri", Font.PLAIN, 28));
        input.setForeground(new Color(117, 100, 130));
        input.setBackground(Color.WHITE);
        input.setEditable(false);
        input.setText("10 + 5");
        add(input);

        output = new JTextField("15");
        output.setHorizontalAlignment(SwingConstants.RIGHT);
        output.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        output.setBounds(24, 96, 352, 72);
        output.setFont(new Font("Calibri", Font.PLAIN, 56));
        output.setForeground(new Color(92, 67, 110));
        output.setBackground(Color.WHITE);
        output.setEditable(false);
        add(output);


        // background image setup
        final ImageIcon icon = new ImageIcon("D:\\kodes\\Java\\LU_Java_Labs\\Calculator\\src\\bg.png");
        JTextArea text = new JTextArea() {
            Image img = icon.getImage();

            // instance initializer
            {
                setOpaque(false);
            }

            public void paintComponent(Graphics graphics) {
                graphics.drawImage(img, 0, 0, 400, 640, this);
                super.paintComponent(graphics);
            }

        };
        JScrollPane pane = new JScrollPane(text);
        Container content = getContentPane();
        text.setEditable(false);
        content.add(pane, BorderLayout.CENTER);

        // default frame options
        setDefaultCloseOperation(3);
        setSize(400, 680);
        setVisible(true);
        setLayout(null);
        setResizable(false);

        clear();
    }

    public static void main(String[] args) {
        new Calculator();
    }

    // setting common button style with a function
    public void setButtonStyle(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Calibri", Font.PLAIN, 24));
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trigger(button.getText());
            }
        });
    }

    // trigger actions when button clicked
    void trigger(String t) {
        String val = input.getText();
        if (t == "AC") {
            clear();
        } else if (t == "DEL") {
            div_z = false;
            try {
                // if any operator (which contains space)
                if (val.charAt(val.length() - 1) == ' ') {
                    input.setText(val.substring(0, val.length() - 3));
                    // to check if the previous number is double
                    List<String> list = new ArrayList<String>();
                    list = List.of(input.getText().split(" "));
                    number = Double.parseDouble(list.get(list.size() - 1));
                    if (list.get(list.size() - 1).contains(".")) {
                        dot = true;
                    }
                    cnt_o--;
                } else {
                    if (val.charAt(val.length() - 1) == '.') {
                        dot = false;
                    }
                    input.setText(val.substring(0, val.length() - 1));
                    List<String> list = new ArrayList<String>();
                    list = List.of(input.getText().split(" "));
                    if (input.getText().charAt(input.getText().length() - 1) == ' ') {
                        number = 0.0;
                        cnt_n--;
                    } else {
                        number = Double.parseDouble(list.get(list.size() - 1));
                    }

                }
            } catch (Exception e) {
                clear();
            }
        } else if (t == "+") {
            // regular usage of addition(+)
            if (cnt_o < cnt_n & number != 0.0) {
                defaultOperatorAction();
                input.setText(val + " + ");
            } else if (val.length() > 0 && val.charAt(val.length() - 1) == '0') {
                // if 0 gets input, that counts as a number too
                defaultOperatorAction();
                input.setText(val + " + ");
            }
        } else if (t == "-") {
            if (cnt_o < cnt_n) {
                defaultOperatorAction();
                input.setText(val + " - ");
            } else if (val.length() > 0 && val.charAt(val.length() - 1) == '0') {
                defaultOperatorAction();
                input.setText(val + " - ");
            }
        } else if (t == "*") {
            if (cnt_o < cnt_n & number != 0.0) {
                defaultOperatorAction();
                input.setText(val + " * ");
            } else if (val.length() > 0 && val.charAt(val.length() - 1) == '0') {
                defaultOperatorAction();
                input.setText(val + " * ");
            }
        } else if (t == "/") {
            if (cnt_o < cnt_n & number != 0.0) {
                defaultOperatorAction();
                input.setText(val + " / ");
            } else if (val.length() > 0 && val.charAt(val.length() - 1) == '0') {
                defaultOperatorAction();
                input.setText(val + " / ");
            }
        } else if (t == "=") {
            // stripping out last operator if exists
            if (val.length() > 0 && val.charAt(val.length() - 1) == ' ') {
                val = val.substring(0, val.length() - 3);
            }
            // adding extra 0 if starts with neg(-)
            if (val.length() > 0 && val.charAt(0) == ' ') {
                val = "0" + val;
            }
            output.setText(calculate(val));

        } else if (t == ".") {
            if (dot == false) {
                dot = true;
                if (val.length() == 0 || number == 0.0 && val.charAt(val.length() - 1) != '0') {
                    input.setText(val + "0.");
                } else {
                    input.setText(val + ".");
                }
            }
        } else if (t == "00") {
            if (number != 0.0 || dot == true) {
                input.setText(val + t);
            }
            number *= 100;
        } else if (t == "0") {
            if (number != 0.0 || dot == true) {
                input.setText(val + t);
            } else if (val.length() == 0 || number == 0.0 && val.charAt(val.length() - 1) != '0') {
                cnt_n++;
                input.setText(val + t);
            }
            number *= 10;
        } else {
            if (cnt_o == cnt_n) {
                cnt_n++;
            }
            input.setText(val + t);
            number = (number * 10) + Integer.parseInt(t);
        }
    }

    // clears the calculator to its initial state
    void clear() {
        input.setText("");
        output.setText("0");
        number = 0.0;
        dot = false;
        div_z = false;
        cnt_o = 0;
        cnt_n = 1;
    }

    // perform common tasks when an operation clicked
    void defaultOperatorAction() {
        String val = input.getText();
        if (val.length() > 0 && val.charAt(val.length() - 1) == '.') {
            input.setText(val + "0");
        }
        cnt_o++;
        dot = false;
        div_z = false;
        number = 0.0;
    }

    // final calculation
    String calculate(String str) {
        List<String> list = new ArrayList<String>();
        String[] splited = str.split(" ");

        // making a mutable array list
        for (int i = 0; i < splited.length; i++) {
            list.add(splited[i]);
        }

        // performing the multiply and division tasks first
        for (int i = 0; i < list.size(); ) {
            if (list.get(i).contains("*") || list.get(i).contains("/")) {
                Double r = 0.0;
                if (list.get(i).contains("*")) {
                    r = Double.parseDouble(list.get(i - 1)) * Double.parseDouble(list.get(i + 1));
                } else {
                    r = Double.parseDouble(list.get(i - 1)) / Double.parseDouble(list.get(i + 1));
                }
                list.set(i - 1, String.valueOf(r));
                list.remove(i);
                list.remove(i);
                i--;
            }
            i++;
        }

        // rest of the addition and subtraction tasks
        while (list.size() != 1) {
            Double r = 0.0;
            if (list.get(1).contains("+")) {
                r = Double.parseDouble(list.get(0)) + Double.parseDouble(list.get(2));
            } else {
                r = Double.parseDouble(list.get(0)) - Double.parseDouble(list.get(2));
            }
            list.set(0, String.valueOf(r));
            list.remove(1);
            list.remove(1);
        }
        return list.get(0);
    }
}

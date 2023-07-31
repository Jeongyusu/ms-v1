package exceltokakao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class InputForm extends JFrame {

    private JPanel panel;
    private JLabel cnameLabel;
    private JLabel phoneNumberLabel;
    private JFormattedTextField phoneNumberTextField;
    private JTextField cnameTextField;
    private MaskFormatter formatter;

    public InputForm() {

        makePanel();
        makeCnameLabel();
        makePhoneNumberLabel();
        makeCnameTextField();
        makePhoneNumberTextField();
        makeDefaultSetting();

    }

    public static void main(String[] args) {

        InputForm form = new InputForm();

    }

    public void makePanel() {
        panel = new JPanel();
        panel.setBackground(Color.pink);
        panel.setLayout(null);
        add(panel);

    }

    public void makeCnameLabel() {
        cnameLabel = new JLabel("고객명: ");
        Font labelFont = new Font(cnameLabel.getFont().getName(), Font.BOLD, 15);
        cnameLabel.setFont(labelFont);
        cnameLabel.setBounds(20, 50, 60, 30);
        panel.add(cnameLabel);

    }

    public void makePhoneNumberLabel() {
        phoneNumberLabel = new JLabel("휴대폰 번호: ");
        Font labelFont = new Font(phoneNumberLabel.getFont().getName(), Font.BOLD, 15);
        phoneNumberLabel.setFont(labelFont);
        phoneNumberLabel.setBounds(20, 100, 100, 30);
        panel.add(phoneNumberLabel);

    }

    public void makeCnameTextField() {
        cnameTextField = new JTextField();
        cnameTextField.setBounds(80, 50, 70, 30);
        panel.add(cnameTextField);

    }

    public void makePhoneNumberTextField() {
        try {
            formatter = new MaskFormatter("###-####-####");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "형식에 맞지 않습니다.", "에러", JOptionPane.ERROR_MESSAGE);
        }
        phoneNumberTextField = new JFormattedTextField(formatter);
        phoneNumberTextField.setHorizontalAlignment(JTextField.CENTER);
        phoneNumberTextField.setBounds(120, 100, 100, 30);
        panel.add(phoneNumberTextField);

    }

    public void makeDefaultSetting() {
        setSize(600, 800);
        setDefaultCloseOperation(3);
        setVisible(true);

    }

}

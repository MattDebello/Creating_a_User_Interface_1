import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UserInterfaceExample {
    private JFrame frame;
    private JTextArea textBox;

    private float initialHue;  
    private boolean isGreen = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserInterfaceExample().createAndShowGUI());
    }

    private void createAndShowGUI() {
        frame = new JFrame("User Interface Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        createMenuBar();
        createTextBox();
        initialHue = generateRandomGreenHue();

        frame.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem item1 = new JMenuItem("Print Date and Time");
        JMenuItem item2 = new JMenuItem("Write to File");
        JMenuItem item3 = new JMenuItem("Change Background Color");
        JMenuItem item4 = new JMenuItem("Exit");

        item1.addActionListener(e -> printDateAndTime());
        item2.addActionListener(e -> writeToFile());
        item3.addActionListener(e -> changeBackgroundColor());
        item4.addActionListener(e -> System.exit(0));

        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
    }

    private void createTextBox() {
        textBox = new JTextArea();
        textBox.setEditable(false);
        frame.add(new JScrollPane(textBox), BorderLayout.CENTER);
    }

    private void printDateAndTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(new Date());
        textBox.append(dateTime + "\n");
    }

    private void writeToFile() {
        String content = textBox.getText();
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(content);
            JOptionPane.showMessageDialog(frame, "Content written to log.txt");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error writing to file");
        }
    }
    private void changeBackgroundColor() {
        if (isGreen) {

            frame.getContentPane().setBackground(UIManager.getColor("Panel.background"));
        } else {

            float hue = initialHue;
            frame.getContentPane().setBackground(Color.getHSBColor(hue, 0.7f, 0.9f));
        }
        isGreen = !isGreen;
    }

    private float generateRandomGreenHue() {
        Random rand = new Random();
        return rand.nextFloat() * 0.4f + 0.3f;
    }
}
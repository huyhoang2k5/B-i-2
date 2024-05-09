import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.*;

public class WorldClock {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("World Clock");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JLabel clockLabel = new JLabel();
            clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
            frame.add(clockLabel, BorderLayout.CENTER);

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new FlowLayout());

            JTextField timeZoneField = new JTextField(5);
            inputPanel.add(timeZoneField);

            JButton addButton = new JButton("Add Clock");
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String timeZoneId = timeZoneField.getText().trim();
                    createClockFrame(timeZoneId);
                }
            });
            inputPanel.add(addButton);

            frame.add(inputPanel, BorderLayout.SOUTH);

            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            startClock(clockLabel);
        });
    }

    private static void startClock(JLabel clockLabel) {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                clockLabel.setText(sdf.format(cal.getTime()));
            }
        });
        timer.start();
    }

    private static void createClockFrame(String timeZoneId) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("World Clock - " + timeZoneId);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JLabel clockLabel = new JLabel();
            clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
            frame.add(clockLabel, BorderLayout.CENTER);

            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            startClock(clockLabel, timeZoneId);
        });
    }

    private static void startClock(JLabel clockLabel, String timeZoneId) {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
                cal.setTimeZone(timeZone);
                clockLabel.setText(sdf.format(cal.getTime()));
            }
        });
        timer.start();
    }
}

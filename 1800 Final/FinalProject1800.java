import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DailyLog {
    private int dayNumber;
    private int caloriesConsumed;
    private int caloriesBurned;

    public DailyLog(int dayNumber, int caloriesConsumed, int caloriesBurned) {
        this.dayNumber = dayNumber;
        this.caloriesConsumed = caloriesConsumed;
        this.caloriesBurned = caloriesBurned;
    }

    public int getCaloriesConsumed() { return caloriesConsumed; }
    public int getCaloriesBurned() { return caloriesBurned; }
    
    public int getNetCalories() {
        return caloriesConsumed - caloriesBurned;
    }
}

public class FinalProject1800 extends JFrame {
    
    private final int DAYS_IN_MONTH = 31;
    private DailyLog[] monthData = new DailyLog[DAYS_IN_MONTH];
    private int targetCalories = 2000;

    private JTabbedPane tabbedPane;
    private JTextField targetInput;
    private JLabel currentTargetLabel;
    
    private JComboBox<String> dayDropdown;
    private JTextField consumedInput;
    private JTextField burnedInput;
    
    private JLabel lblTargetDisplay;
    private JLabel lblAvgConsumed;
    private JLabel lblAvgBurned;
    private JLabel lblFormula;
    private JLabel lblNetAvg;
    private JTextArea txtInsights;

    public FinalProject1800() {
        for (int i = 0; i < DAYS_IN_MONTH; i++) {
            monthData[i] = new DailyLog(i + 1, 0, 0);
        }

        setTitle("Athlete Training Log & Dashboard");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        createSetupTab();
        createDataInputTab();
        createAnalyticsTab();

        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 2) {
                calculateAndRefreshAnalytics();
            }
        });

        add(tabbedPane);
    }

    private void createSetupTab() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Welcome to Your Athlete Training Log", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel descLabel = new JLabel("Enter your daily target calories (e.g., 2000) below to get started.", JLabel.CENTER);
        gbc.gridy = 1;
        panel.add(descLabel, gbc);

        JLabel inputLabel = new JLabel("Target Daily Calories:");
        gbc.gridy = 2; gbc.gridwidth = 1;
        panel.add(inputLabel, gbc);

        targetInput = new JTextField("2000", 10);
        gbc.gridx = 1;
        panel.add(targetInput, gbc);

        JButton saveBtn = new JButton("Lock Goal & Proceed");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(saveBtn, gbc);

        currentTargetLabel = new JLabel("Current Target: 2000 kcal", JLabel.CENTER);
        currentTargetLabel.setForeground(Color.GRAY);
        gbc.gridy = 4;
        panel.add(currentTargetLabel, gbc);

        saveBtn.addActionListener(e -> {
            try {
                int target = Integer.parseInt(targetInput.getText().trim());
                if (target <= 0) {
                    throw new IllegalArgumentException("Target must be greater than zero.");
                }
                targetCalories = target;
                currentTargetLabel.setText("Current Target: " + targetCalories + " kcal");
                
                tabbedPane.setSelectedIndex(1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                        "Please enter a valid whole number greater than 0 for target calories.", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        tabbedPane.addTab("1. Target Setup", panel);
    }

    private void createDataInputTab() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Select Day:"), gbc);

        String[] days = new String[DAYS_IN_MONTH];
        for (int i = 0; i < DAYS_IN_MONTH; i++) {
            days[i] = "Day " + (i + 1);
        }
        dayDropdown = new JComboBox<>(days);
        gbc.gridx = 1;
        panel.add(dayDropdown, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Calories Consumed (In):"), gbc);

        consumedInput = new JTextField(10);
        gbc.gridx = 1;
        panel.add(consumedInput, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Calories Burned (Out):"), gbc);

        burnedInput = new JTextField(10);
        gbc.gridx = 1;
        panel.add(burnedInput, gbc);

        JButton logBtn = new JButton("Save Entry to Log");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(logBtn, gbc);

        JLabel statusLabel = new JLabel(" ", JLabel.CENTER);
        statusLabel.setForeground(new Color(46, 125, 50));
        gbc.gridy = 4;
        panel.add(statusLabel, gbc);

        dayDropdown.addActionListener(e -> {
            int idx = dayDropdown.getSelectedIndex();
            consumedInput.setText(String.valueOf(monthData[idx].getCaloriesConsumed()));
            burnedInput.setText(String.valueOf(monthData[idx].getCaloriesBurned()));
            statusLabel.setText("Editing record for Day " + (idx + 1));
        });

        logBtn.addActionListener(e -> {
            try {
                int dayIndex = dayDropdown.getSelectedIndex();
                int consumed = Integer.parseInt(consumedInput.getText().trim());
                int burned = Integer.parseInt(burnedInput.getText().trim());

                if (consumed < 0 || burned < 0) {
                    throw new IllegalArgumentException("Values cannot be negative.");
                }

                monthData[dayIndex] = new DailyLog(dayIndex + 1, consumed, burned);
                statusLabel.setText("Successfully saved metrics for Day " + (dayIndex + 1) + "!");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                        "Please enter valid whole integers for calories.", 
                        "Data Entry Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Data Entry Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        tabbedPane.addTab("2. Daily Tracking Logs", panel);
    }

    private void createAnalyticsTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel cardPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        cardPanel.setBorder(BorderFactory.createTitledBorder("Monthly Balance Framework"));

        lblTargetDisplay = new JLabel("Your Daily Target Goal: 2000 kcal");
        lblAvgConsumed   = new JLabel("Average Calories Consumed: 0.00 kcal");
        lblAvgBurned     = new JLabel("Average Calories Burned: 0.00 kcal");
        lblFormula       = new JLabel("Formula (Consumed - Burned): 0.00 - 0.00");
        lblNetAvg        = new JLabel("Net Average Daily Calories: 0.00 kcal");
        lblNetAvg.setFont(new Font("Arial", Font.BOLD, 13));
        lblNetAvg.setForeground(Color.BLUE);

        cardPanel.add(lblTargetDisplay);
        cardPanel.add(lblAvgConsumed);
        cardPanel.add(lblAvgBurned);
        cardPanel.add(lblFormula);
        cardPanel.add(lblNetAvg);

        panel.add(cardPanel, BorderLayout.NORTH);

        txtInsights = new JTextArea(4, 30);
        txtInsights.setEditable(false);
        txtInsights.setLineWrap(true);
        txtInsights.setWrapStyleWord(true);
        txtInsights.setBackground(panel.getBackground());
        txtInsights.setBorder(BorderFactory.createTitledBorder("Actionable Fitness Evaluation"));
        txtInsights.setText("Navigate to tab 2 to log data. Your evaluation will compile here automatically.");

        panel.add(new JScrollPane(txtInsights), BorderLayout.CENTER);

        tabbedPane.addTab("3. Performance Summary", panel);
    }
    
    private void calculateAndRefreshAnalytics() {
        int totalConsumed = 0;
        int totalBurned = 0;

        for (DailyLog log : monthData) {
            totalConsumed += log.getCaloriesConsumed();
            totalBurned += log.getCaloriesBurned();
        }

        double avgConsumed = (double) totalConsumed / DAYS_IN_MONTH;
        double avgBurned = (double) totalBurned / DAYS_IN_MONTH;
        double netAverage = avgConsumed - avgBurned;

        lblTargetDisplay.setText("Your Daily Target Goal: " + targetCalories + " kcal");
        lblAvgConsumed.setText(String.format("Average Calories Consumed: %.2f kcal", avgConsumed));
        lblAvgBurned.setText(String.format("Average Calories Burned: %.2f kcal", avgBurned));
        lblFormula.setText(String.format("Formula (Consumed - Burned): %.2f - %.2f", avgConsumed, avgBurned));
        lblNetAvg.setText(String.format("Net Average Daily Calories: %.2f kcal", netAverage));

        StringBuilder insights = new StringBuilder();
        
        if (avgConsumed > targetCalories) {
            insights.append(String.format("On average, you were over your intake goal by %.2f kcal. Consider eating less and exercising more.\n\n", (avgConsumed - targetCalories)));
        } else if (avgConsumed < targetCalories) {
            insights.append(String.format("On average, you were under your intake goal by %.2f kcal. Make sure you're eating enough!\n\n", (targetCalories - avgConsumed)));
        } else {
            insights.append("Spot on! On average, you hit your calorie target perfectly.\n\n");
        }

        if (netAverage < targetCalories) {
            insights.append("Your net calorie balance is below your target limit. This is good for weight loss/deficit goals.");
        } else {
            insights.append("Your net calorie balance is above your target limit. This will support muscle gain/surplus goals. Consider lowering your daily calories if you're trying to lose weight.");
        }

        txtInsights.setText(insights.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FinalProject1800 app = new FinalProject1800();
            app.setVisible(true);
        });
    }
}

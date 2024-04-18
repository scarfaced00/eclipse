package WorkoutTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WorkoutTracker extends JFrame implements ActionListener {
    private JTextField exerciseField, setsField, repsField, weightField;
    private JTextArea workoutHistoryArea;
    private JButton addExerciseButton, viewHistoryButton;
    private ArrayList<String> workoutHistory;

    public WorkoutTracker() {
        setTitle("Workout Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create components
        JLabel exerciseLabel = new JLabel("Exercise:");
        exerciseField = new JTextField(20);
        JLabel setsLabel = new JLabel("Sets:");
        setsField = new JTextField(5);
        JLabel repsLabel = new JLabel("Reps:");
        repsField = new JTextField(5);
        JLabel weightLabel = new JLabel("Weight (kg):");
        weightField = new JTextField(5);
        workoutHistoryArea = new JTextArea(10, 30);
        workoutHistoryArea.setEditable(false); // Workout history area is not editable
        JScrollPane scrollPane = new JScrollPane(workoutHistoryArea);
        addExerciseButton = new JButton("Add Exercise");
        viewHistoryButton = new JButton("View History");

        // Set layout
        setLayout(new BorderLayout());

        // Panel for input components
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // 4 rows, 2 columns, gaps between components
        inputPanel.add(exerciseLabel);
        inputPanel.add(exerciseField);
        inputPanel.add(setsLabel);
        inputPanel.add(setsField);
        inputPanel.add(repsLabel);
        inputPanel.add(repsField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addExerciseButton);
        buttonPanel.add(viewHistoryButton);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Register action listeners for buttons
        addExerciseButton.addActionListener(this);
        viewHistoryButton.addActionListener(this);

        // Initialize workout history
        workoutHistory = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addExerciseButton) {
            addExercise();
        } else if (e.getSource() == viewHistoryButton) {
            viewHistory();
        }
    }

    private void addExercise() {
        String exercise = exerciseField.getText().trim();
        String sets = setsField.getText().trim();
        String reps = repsField.getText().trim();
        String weight = weightField.getText().trim();

        if (exercise.isEmpty() || sets.isEmpty() || reps.isEmpty() || weight.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String exerciseInfo = exercise + ": " + sets + " sets x " + reps + " reps @ " + weight + " lbs";
        workoutHistory.add(exerciseInfo);
        workoutHistoryArea.append(exerciseInfo + "\n");
        clearInputFields();
    }

    private void viewHistory() {
        if (workoutHistory.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No workout history available.", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder history = new StringBuilder();
            for (String exercise : workoutHistory) {
                history.append(exercise).append("\n");
            }
            JOptionPane.showMessageDialog(this, history.toString(), "Workout History", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearInputFields() {
        exerciseField.setText("");
        setsField.setText("");
        repsField.setText("");
        weightField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WorkoutTracker tracker = new WorkoutTracker();
            tracker.setVisible(true);
        });
    }
}
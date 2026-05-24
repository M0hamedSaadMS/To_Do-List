import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

public class GUI {

    private JFrame frame;
    private JTextField input;
    private JButton button,toggleButton,deleteButton;
    private JLabel label,textLabel;
    private DefaultListModel<Task> model;
    private JList<Task> list;
    private TaskManager taskManager;
    FileManager fileManager = new FileManager();

    public void todoGui() {
        frame = new JFrame("ToDo List");
        input = new JTextField();
        button = new JButton("Add Task");
        toggleButton = new JButton ("Done");
        deleteButton = new JButton ("Delete Task");
        label = new JLabel("Task Name : ");
        model = new DefaultListModel<>();
        list = new JList<>(model);
        taskManager = new TaskManager();
        textLabel = new JLabel ("To_Do List");


        ArrayList<Task> loadedTasks = fileManager.loadTasks();
        for (Task t : loadedTasks) {
            model.addElement(t);
        }


        textLabel.setBounds(150,10,150,30);
        textLabel.setFont(new Font("Arial",Font.BOLD,20));
        label.setBounds(10, 50, 100, 30);
        input.setBounds(100, 50, 150, 30);
        button.setBounds(260, 50, 100, 30);
        toggleButton.setBounds(90, 225, 100, 30);
        deleteButton.setBounds(210, 225, 110, 30);
        list.setBounds(50, 100, 300, 120);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = input.getText();

                if (!text.isEmpty()) {
                    Task task = new Task(text);
                    model.addElement(task);
                    fileManager.saveTasks(getTasksFromModel());
                    JOptionPane.showMessageDialog(null, "Task Added.");
                } else {
                    JOptionPane.showMessageDialog(null, "No Text Written.");
                }
                input.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task selected = list.getSelectedValue();
                if (selected == null){
                    JOptionPane.showMessageDialog(null,"Select Task.");
                }else{
                    model.removeElement(selected);
                    fileManager.saveTasks(getTasksFromModel());
                    JOptionPane.showMessageDialog(null,"Text Deleted.");
                }

            }
        });

        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task selected = list.getSelectedValue();
                if (selected == null){
                    JOptionPane.showMessageDialog(null,"Select Task.");
                }else{
                    selected.toggleIsDone();
                    list.repaint();
                    fileManager.saveTasks(getTasksFromModel());
                }

            }
        });

        list.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Task selected = list.getSelectedValue();
                    if (selected != null) {
                        selected.toggleIsDone();
                        list.repaint();
                        fileManager.saveTasks(getTasksFromModel());
                    }
                }
            }
        });

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(30, 30, 30));
        label.setForeground(Color.WHITE);
        textLabel.setForeground(Color.WHITE);
        input.setBackground(new Color(50, 50, 50));
        input.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.WHITE);
        toggleButton.setBackground(new Color(70, 70, 70));
        toggleButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(70, 70, 70));
        deleteButton.setForeground(Color.WHITE);
        list.setBackground(new Color(40, 40, 40));
        list.setForeground(Color.WHITE);
        frame.add(input);
        frame.add(button);
        frame.add(label);
        frame.add(list);
        frame.add(toggleButton);
        frame.add(deleteButton);
        frame.add(textLabel);
        frame.setVisible(true);
    }

    private ArrayList<Task> getTasksFromModel() {
        ArrayList<Task> tasks = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            tasks.add(model.getElementAt(i));
        }

        return tasks;
    }
}
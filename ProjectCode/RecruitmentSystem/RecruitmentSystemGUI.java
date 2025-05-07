import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.*;



public class RecruitmentSystemGUI/* extends JFrame */{
    private JFrame RecruitmentSystem = new JFrame();;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private String[] filter = {"A", "N", "N"}; // Default filter
    private JTable staffTable;
    private DefaultTableModel tableModel;
    
    DefaultTableModel fullTimeModel;
    DefaultTableModel partTimeModel;

    ArrayList<StaffHire> SHObj = new ArrayList<>(); 
    public RecruitmentSystemGUI() {
        
        RecruitmentSystem.setTitle("Recruitment System");
        RecruitmentSystem.setSize(1500, 800);
        RecruitmentSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RecruitmentSystem.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        JPanel viewStaffPage = createViewStaffPage();

        mainPanel.add(createTitlePage(), "TitlePage");
        mainPanel.add(createContentsPage(), "ContentsPage");
        mainPanel.add(addFullTimeStaffWindow(), "Add Full Time Staff");
        mainPanel.add(addPartTimeStaffWindow(), "Add Part Time Staff");
        mainPanel.add(viewStaffPage, "ViewStaffPage");
        mainPanel.add(editStaffOptionsWindow(),"Edit Staff Options Window"); 
        mainPanel.add(fullTimeEditWindow(),"Edit Full Time Staff Window");
        mainPanel.add(partTimeEditWindow(),"Edit Part Time Staff Window");
        mainPanel.add(terminateStaffWindow(),"Terminate Staff Window");
        
        
        RecruitmentSystem.add(mainPanel);
        cardLayout.show(mainPanel, "TitlePage");
        RecruitmentSystem.setVisible(true);
    }

    private JPanel createTitlePage() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Recruitment_System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Exit");

        startButton.addActionListener(e -> cardLayout.show(mainPanel, "ContentsPage"));
        exitButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createContentsPage() {
        JPanel panel = new JPanel(new GridLayout(8, 1, 5, 5));

        JButton viewStaffBtn = new JButton("View Current Staff");
        JButton addFullTimeBtn = new JButton("Add Full Time Staff");
        JButton addPartTimeBtn = new JButton("Add Part Time Staff");
        JButton editStaffBtn = new JButton("Edit Staff");
        JButton terminateBtn = new JButton("Terminate Staff");
        JButton exitBtn = new JButton("Exit");

        
        viewStaffBtn.addActionListener(e -> {
            updateTable(); // refresh data
            cardLayout.show(mainPanel, "ViewStaffPage");
        });
        
        terminateBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "Terminate Staff Window");
        });
        
        editStaffBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "Edit Staff Options Window");
        });
        
        
        exitBtn.addActionListener(e -> System.exit(0));

        addFullTimeBtn.addActionListener(e -> cardLayout.show(mainPanel,"Add Full Time Staff"));
        addPartTimeBtn.addActionListener(e -> cardLayout.show(mainPanel,"Add Part Time Staff"));
        
        panel.add(viewStaffBtn);
        panel.add(addFullTimeBtn);
        panel.add(addPartTimeBtn);
        panel.add(editStaffBtn);
        panel.add(terminateBtn);
        panel.add(exitBtn);

        return panel;
    }
    
    
    
    private JPanel addFullTimeStaffWindow() {
        JPanel FullTimeStaffWindow = new JPanel(new GridLayout(13, 2, 5, 5));
        FullTimeStaffWindow.setSize(400, 500);

        JTextField vacancyField = new JTextField();
        JTextField desField = new JTextField();
        JTextField jTypeField = new JTextField();
        JTextField sNameField = new JTextField();
        JTextField jDateField = new JTextField();
        JTextField qualField = new JTextField();
        JTextField appointByField = new JTextField();
        JTextField joinedField = new JTextField(); // Use "true"/"false"
        JTextField salaryField = new JTextField();
        JTextField wkHrsField = new JTextField();

        FullTimeStaffWindow.add(new JLabel("Vacancy No:"));
        FullTimeStaffWindow.add(vacancyField);
        FullTimeStaffWindow.add(new JLabel("Designation:"));
        FullTimeStaffWindow.add(desField);
        FullTimeStaffWindow.add(new JLabel("Job Type:"));
        FullTimeStaffWindow.add(jTypeField);
        FullTimeStaffWindow.add(new JLabel("Staff Name:"));
        FullTimeStaffWindow.add(sNameField);
        FullTimeStaffWindow.add(new JLabel("Joining Date:"));
        FullTimeStaffWindow.add(jDateField);
        FullTimeStaffWindow.add(new JLabel("Qualification:"));
        FullTimeStaffWindow.add(qualField);
        FullTimeStaffWindow.add(new JLabel("Appointed By:"));
        FullTimeStaffWindow.add(appointByField);
        FullTimeStaffWindow.add(new JLabel("Joined (true/false):"));
        FullTimeStaffWindow.add(joinedField);
        FullTimeStaffWindow.add(new JLabel("Salary:"));
        FullTimeStaffWindow.add(salaryField);
        FullTimeStaffWindow.add(new JLabel("Weekly Hours:"));
        FullTimeStaffWindow.add(wkHrsField);

        JButton clearBtn = new JButton("Clear");
        JButton confirmBtn = new JButton("Confirm");

        clearBtn.addActionListener(e -> {
            vacancyField.setText("");
            desField.setText("");
            jTypeField.setText("");
            sNameField.setText("");
            jDateField.setText("");
            qualField.setText("");
            appointByField.setText("");
            joinedField.setText("");
            salaryField.setText("");
            wkHrsField.setText("");
        });

        confirmBtn.addActionListener(e -> {
            try {
                
            int vacancy = Integer.parseInt(vacancyField.getText());

                
            for (StaffHire s : SHObj) {
                if (s.getVacancyNo() == vacancy) {
                    JOptionPane.showMessageDialog(FullTimeStaffWindow, "Vacancy number already exists. Please enter a unique vacancy number.");
                    return;
                }
            }

            String joinedText = joinedField.getText().toLowerCase();
            if (!joinedText.equals("true") && !joinedText.equals("false")) {
                JOptionPane.showMessageDialog(FullTimeStaffWindow, "Please enter 'true' or 'false' in the 'Joined' field.");
                return;
            }
                
                String des = desField.getText();
                String jType = jTypeField.getText();
                String sName = sNameField.getText();
                String jDate = jDateField.getText();
                String qual = qualField.getText();
                String appointBy = appointByField.getText();
                boolean joined = Boolean.parseBoolean(joinedField.getText());
                double salary = Double.parseDouble(salaryField.getText());
                int wkHrs = Integer.parseInt(wkHrsField.getText());

                FullTimeStaffHire staff = new FullTimeStaffHire(vacancy, des, jType, sName, jDate, qual, appointBy, joined, salary, wkHrs);
                SHObj.add(staff);
                JOptionPane.showMessageDialog(FullTimeStaffWindow, "Full Time Staff Added Successfully");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(FullTimeStaffWindow, "Please enter valid number");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(FullTimeStaffWindow, "Error: " + ex.getMessage());
            }
        });

        FullTimeStaffWindow.add(clearBtn);
        FullTimeStaffWindow.add(confirmBtn);
        
        JButton bButton = new JButton("back");
        JButton exitButton = new JButton("exit");
        
        bButton.addActionListener(e -> cardLayout.show(mainPanel, "ContentsPage"));
        exitButton.addActionListener(e -> System.exit(0));
        
        FullTimeStaffWindow.add(bButton);
        FullTimeStaffWindow.add(exitButton);
    
        return FullTimeStaffWindow;
    }
    
    
    
    private JPanel addPartTimeStaffWindow() {
        JPanel PartTimeWindow = new JPanel();
        PartTimeWindow.setSize(400, 550);
        PartTimeWindow.setLayout(new GridLayout(13, 2, 5, 5));
    
        JTextField vacancyField = new JTextField();
        JTextField desField = new JTextField();
        JTextField jTypeField = new JTextField();
        JTextField sNameField = new JTextField();
        JTextField jDateField = new JTextField();
        JTextField qualField = new JTextField();
        JTextField appointByField = new JTextField();
        JTextField joinedField = new JTextField(); // true/false
        JTextField wageField = new JTextField();
        JTextField workHrField = new JTextField();
        JTextField shiftField = new JTextField();

        PartTimeWindow.add(new JLabel("Vacancy No:"));
        PartTimeWindow.add(vacancyField);
        PartTimeWindow.add(new JLabel("Designation:"));
        PartTimeWindow.add(desField);
        PartTimeWindow.add(new JLabel("Job Type:"));
        PartTimeWindow.add(jTypeField);
        PartTimeWindow.add(new JLabel("Staff Name:"));
        PartTimeWindow.add(sNameField);
        PartTimeWindow.add(new JLabel("Joining Date:"));
        PartTimeWindow.add(jDateField);
        PartTimeWindow.add(new JLabel("Qualification:"));
        PartTimeWindow.add(qualField);
        PartTimeWindow.add(new JLabel("Appointed By:"));
        PartTimeWindow.add(appointByField);
        PartTimeWindow.add(new JLabel("Joined (true/false):"));
        PartTimeWindow.add(joinedField);
        PartTimeWindow.add(new JLabel("Wage per Hour:"));
        PartTimeWindow.add(wageField);
        PartTimeWindow.add(new JLabel("Working Hours:"));
        PartTimeWindow.add(workHrField);
        PartTimeWindow.add(new JLabel("Shift:"));
        PartTimeWindow.add(shiftField);

        JButton clearBtn = new JButton("Clear");
        JButton confirmBtn = new JButton("Confirm");

        clearBtn.addActionListener(e -> {
            vacancyField.setText("");
            desField.setText("");
            jTypeField.setText("");
            sNameField.setText("");
            jDateField.setText("");
            qualField.setText("");
            appointByField.setText("");
            joinedField.setText("");
            wageField.setText("");
            workHrField.setText("");
            shiftField.setText("");
        });

        confirmBtn.addActionListener(e -> {
            try {
            
                int vacancy = Integer.parseInt(vacancyField.getText());

                
                for (StaffHire s : SHObj) {
                    if (s.getVacancyNo() == vacancy) {
                        JOptionPane.showMessageDialog(PartTimeWindow, "Vacancy number already exists. Please enter a unique vacancy number.");
                        return;
                    }
                }

                String joinedText = joinedField.getText().toLowerCase();
                if (!joinedText.equals("true") && !joinedText.equals("false")) {
                    JOptionPane.showMessageDialog(PartTimeWindow, "Please enter 'true' or 'false' in the 'Joined' field.");
                    return;
                }
            
            
                String des = desField.getText();
                String jType = jTypeField.getText();
                String sName = sNameField.getText();
                String jDate = jDateField.getText();
                String qual = qualField.getText();
                String appointBy = appointByField.getText();
                boolean joined = Boolean.parseBoolean(joinedField.getText());
                double wage = Double.parseDouble(wageField.getText());
                int workHr = Integer.parseInt(workHrField.getText());
                String shift = shiftField.getText();

                PartTimeStaffHire staff = new PartTimeStaffHire(vacancy, des, jType, sName, jDate, qual, appointBy, joined, wage, workHr, shift);
                SHObj.add(staff);
                JOptionPane.showMessageDialog(PartTimeWindow, "Part Time Staff Added Successfully");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PartTimeWindow, "Please enter valid numeric values where required.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(PartTimeWindow, "Error: " + ex.getMessage());
            }
        });

        PartTimeWindow.add(clearBtn);
        PartTimeWindow.add(confirmBtn);
        PartTimeWindow.setVisible(true);
    
        JButton bButton = new JButton("back");
        JButton exitButton = new JButton("exit");
        
        bButton.addActionListener(e -> cardLayout.show(mainPanel, "ContentsPage"));
        exitButton.addActionListener(e -> System.exit(0));
        
        PartTimeWindow.add(bButton);
        PartTimeWindow.add(exitButton);
    
        return PartTimeWindow;
    }
    


    private JPanel createViewStaffPage() {
    JPanel panel = new JPanel(new BorderLayout());

    JButton filterBtn = new JButton("Filter");
    JButton backBtn = new JButton("Back");
     JButton clearBtn = new JButton("Clear");

    filterBtn.addActionListener(e -> openFilterWindow());

    backBtn.addActionListener(e -> cardLayout.show(mainPanel, "ContentsPage"));

    clearBtn.addActionListener(e -> {this.filter = new String[]{"A", "N", "N"};
    updateTable();
});
    
    JPanel topPanel = new JPanel();
    topPanel.add(filterBtn);

    JButton searchBtn = new JButton("Search by Vacancy No");
    JTextField searchField = new JTextField(10);
    topPanel.add(searchField);
    topPanel.add(searchBtn);
    topPanel.add(clearBtn);

    /*String[] columnNames = {
        "Vacancy No", "Designation", "Job Type", "Name", "Join Date",
        "Qualification", "Appointed By", "Joined", "Extra Info"
    };
    tableModel = new DefaultTableModel(columnNames, 0);
    staffTable = new JTable(tableModel);*/
    
     String[] fullTimeCols = {
        "Vacancy No", "Designation", "Job Type", "Staff Name", "Join Date",
        "Qualification", "Appointed By", "Joined", "Salary", "Working Hours", "Terminated"
    };
    this.fullTimeModel = new DefaultTableModel(fullTimeCols, 0);
    JTable fullTimeTable = new JTable(fullTimeModel);

    // Part-Time Table
    String[] partTimeCols = {
        "Vacancy No", "Designation", "Job Type", "Staff Name", "Join Date",
        "Qualification", "Appointed By", "Joined", "Wage/hr", "Working Hours", "Shift", "Terminated"
    };
    this.partTimeModel = new DefaultTableModel(partTimeCols, 0);
    JTable partTimeTable = new JTable(partTimeModel);

    searchBtn.addActionListener(e -> {
        try {
            int searchVacancy = Integer.parseInt(searchField.getText());
            updateTable(searchVacancy,partTimeModel,fullTimeModel);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(RecruitmentSystem, "Invalid vacancy number.");
        }
    });

    //JScrollPane scrollPane = new JScrollPane(staffTable);
    
    JScrollPane fullTimeScroll = new JScrollPane(fullTimeTable);
    JScrollPane partTimeScroll = new JScrollPane(partTimeTable);
    
    panel.add(topPanel, BorderLayout.NORTH);
    //panel.add(scrollPane, BorderLayout.CENTER);
    
    JPanel tablePanel = new JPanel(new GridLayout(2, 1));
    tablePanel.add(new JLabel("Full-Time Staff", SwingConstants.CENTER));
    tablePanel.add(fullTimeScroll);
    tablePanel.add(new JLabel("Part-Time Staff", SwingConstants.CENTER));
    tablePanel.add(partTimeScroll);
    panel.add(tablePanel, BorderLayout.CENTER);
    panel.add(backBtn, BorderLayout.SOUTH);

    
    return panel;
}



private void updateTable() {
    updateTable(-1,this.partTimeModel,this.fullTimeModel);
}

private void updateTable(int vacancySearch, DefaultTableModel partTimeModel,
DefaultTableModel fullTimeModel) {
    partTimeModel.setRowCount(0);
    fullTimeModel.setRowCount(0);
    
    for (StaffHire s : SHObj) {
            if (vacancySearch != -1 && s.getVacancyNo() != vacancySearch) continue;
            if (!matchesFilter(s)) continue;

            if (s instanceof FullTimeStaffHire f) {
                fullTimeModel.addRow(new Object[]{
                    f.getVacancyNo(), f.getDesignation(), f.getJobType(), f.getStaffName(), f.getJoinDate(),
                    f.getQualification(), f.getAppointedBy(), f.getJoined(), f.getSalary(), f.getWkFrcHrs(),
                    f.getTerminated()
                });
            } else if (s instanceof PartTimeStaffHire p) {
                partTimeModel.addRow(new Object[]{
                    p.getVacancyNo(), p.getDesignation(), p.getJobType(), p.getStaffName(), p.getJoinDate(),
                    p.getQualification(), p.getAppointedBy(), p.getJoined(), p.getWageHr(), p.getWorkHr(),
                    p.getShifts(), p.getTerminated()
                });
        
    
    }
}}

private boolean matchesFilter(StaffHire s) {
    if (filter[0].equals("P") && !(s instanceof PartTimeStaffHire)) {return false;};
    if (filter[0].equals("F") && !(s instanceof FullTimeStaffHire)) {return false;};

    if (filter[1].equals("Y") && !s.getJoined()){ return false;};

    if (filter[2].equals("Y")) {
        if (s instanceof FullTimeStaffHire && ((FullTimeStaffHire) s).getTerminated()){ return false;};
        if (s instanceof PartTimeStaffHire && ((PartTimeStaffHire) s).getTerminated()) {return false;};
    }

    return true;
}



private void openFilterWindow() {
    JFrame filterFrame = new JFrame("Filter");
    filterFrame.setSize(600, 600);
    filterFrame.setLayout(new GridLayout(10, 1));
    filterFrame.setLocationRelativeTo(RecruitmentSystem);
    
    JLabel emptyLabel1 = new JLabel("");
    JLabel emptyLabel2 = new JLabel("");
    JLabel emptyLabel3 = new JLabel("");
    JLabel emptyLabel4 = new JLabel("");
    JLabel emptyLabel5 = new JLabel("");
    JLabel emptyLabel6 = new JLabel("");
    
    JLabel typeLabel = new JLabel("Full Time or Part Time:");
    JButton partTimeBtn = new JButton("Part Time Staff");
    JButton fullTimeBtn = new JButton("Full Time Staff");
    JButton allBtn = new JButton("All");

    JLabel joinedLabel = new JLabel("Only show joined staff:");
    JButton yesJoinedBtn = new JButton("Yes");
    JButton noJoinedBtn = new JButton("No");

    JLabel termLabel = new JLabel("Don't display terminated staff:");
    JButton yesTermBtn = new JButton("Yes");
    JButton noTermBtn = new JButton("No");

    JButton confirmBtn = new JButton("Confirm");
    JButton exitBtn = new JButton("Exit");

    ActionListener toggleGroup = e -> {
        JButton src = (JButton) e.getSource();
        Color green = Color.GREEN;
        Color def = UIManager.getColor("Button.background");

        if (src == partTimeBtn || src == fullTimeBtn || src == allBtn) {
            partTimeBtn.setBackground(def);
            fullTimeBtn.setBackground(def);
            allBtn.setBackground(def);
            src.setBackground(green);
        }

        if (src == yesJoinedBtn || src == noJoinedBtn) {
            yesJoinedBtn.setBackground(def);
            noJoinedBtn.setBackground(def);
            src.setBackground(green);
        }

        if (src == yesTermBtn || src == noTermBtn) {
            yesTermBtn.setBackground(def);
            noTermBtn.setBackground(def);
            src.setBackground(green);
        }
    };

    partTimeBtn.addActionListener(toggleGroup);
    fullTimeBtn.addActionListener(toggleGroup);
    allBtn.addActionListener(toggleGroup);
    yesJoinedBtn.addActionListener(toggleGroup);
    noJoinedBtn.addActionListener(toggleGroup);
    yesTermBtn.addActionListener(toggleGroup);
    noTermBtn.addActionListener(toggleGroup);

    confirmBtn.addActionListener(e -> {
        if (partTimeBtn.getBackground() == Color.GREEN) filter[0] = "P";
        else if (fullTimeBtn.getBackground() == Color.GREEN) filter[0] = "F";
        else filter[0] = "A";

        filter[1] = (yesJoinedBtn.getBackground() == Color.GREEN) ? "Y" : "N";
        filter[2] = (yesTermBtn.getBackground() == Color.GREEN) ? "Y" : "N";

        updateTable();
        filterFrame.dispose();
    });

    exitBtn.addActionListener(e -> filterFrame.dispose());

    filterFrame.add(typeLabel);
    
    filterFrame.add(emptyLabel1);
    
    filterFrame.add(partTimeBtn);
    filterFrame.add(fullTimeBtn);
    filterFrame.add(allBtn);
    
    filterFrame.add(emptyLabel2);
    
    filterFrame.add(joinedLabel);
    
    filterFrame.add(emptyLabel3);
    
    filterFrame.add(yesJoinedBtn);
    filterFrame.add(noJoinedBtn);
    
    filterFrame.add(termLabel);
    
    filterFrame.add(emptyLabel4);
    
    filterFrame.add(yesTermBtn);
    filterFrame.add(noTermBtn);
    
    filterFrame.add(emptyLabel5);
    filterFrame.add(emptyLabel6);

    filterFrame.add(confirmBtn);
    filterFrame.add(exitBtn);

    filterFrame.setVisible(true);
}


    private JPanel editStaffOptionsWindow() {
        JPanel editStaffOptionsWindow = new JPanel(new GridLayout(2, 2, 5, 5));
        editStaffOptionsWindow.setSize(400, 500);
        
        JButton fullTimeEditBtn = new JButton("edit Full Time Staff Hire");
        JButton partTimeEditBtn = new JButton("edit part Time Staff Hire");
        
        fullTimeEditBtn.addActionListener(e -> cardLayout.show(mainPanel, "Edit Full Time Staff Window"));
        partTimeEditBtn.addActionListener(e -> cardLayout.show(mainPanel, "Edit Part Time Staff Window"));
        
        JButton bButton = new JButton("back");
        JButton exitButton = new JButton("exit");
        
        bButton.addActionListener(e -> cardLayout.show(mainPanel, "ContentsPage"));
        exitButton.addActionListener(e -> System.exit(0));
        
        editStaffOptionsWindow.add(fullTimeEditBtn);
        editStaffOptionsWindow.add(partTimeEditBtn);
        editStaffOptionsWindow.add(bButton);
        editStaffOptionsWindow.add(exitButton);
    
        return editStaffOptionsWindow;
    }


    private JPanel fullTimeEditWindow() {
        JPanel editStaffWindow = new JPanel(new GridLayout(14, 2, 5, 5));
        editStaffWindow.setSize(400, 500);
        
        JPanel editStaffOptionsWindow = new JPanel(new GridLayout(1, 2, 5, 5));
        editStaffWindow.setSize(400, 500);
        
        JTextField vacancyField = new JTextField();
        JTextField desField = new JTextField();
        JTextField jTypeField = new JTextField();
        JTextField sNameField = new JTextField();
        JTextField qualField = new JTextField();
        JTextField joinedField = new JTextField(); // Use "true"/"false"
        JTextField salaryField = new JTextField();
        JTextField wkHrsField = new JTextField();
    
        editStaffWindow.add(new JLabel("type in the data to be edited"));
        editStaffWindow.add(new JLabel("leave blank if you want to keep it the same"));
        editStaffWindow.add(new JLabel("Vacancy No of the staff to edit:"));
        editStaffWindow.add(vacancyField);
        editStaffWindow.add(new JLabel("Designation:"));
        editStaffWindow.add(desField);
        editStaffWindow.add(new JLabel("Job Type:"));
        editStaffWindow.add(jTypeField);
        editStaffWindow.add(new JLabel("Staff Name:"));
        editStaffWindow.add(sNameField);
        editStaffWindow.add(new JLabel("Qualification:"));
        editStaffWindow.add(qualField);
        editStaffWindow.add(new JLabel("Joined (true/false):"));
        editStaffWindow.add(joinedField);
        editStaffWindow.add(new JLabel("Salary:"));
        editStaffWindow.add(salaryField);
        editStaffWindow.add(new JLabel("Weekly Hours:"));
        editStaffWindow.add(wkHrsField);

        JButton clearBtn = new JButton("Clear");
        JButton confirmBtn = new JButton("Confirm");

        clearBtn.addActionListener(e -> {
            vacancyField.setText("");
            desField.setText("");
            jTypeField.setText("");
            sNameField.setText("");
            qualField.setText("");
            joinedField.setText("");
            salaryField.setText("");
            wkHrsField.setText("");
        });

        confirmBtn.addActionListener(e -> {
            try {
                
            int vacancy = Integer.parseInt(vacancyField.getText());

            int x = 0;
            for (StaffHire sh : SHObj) {
                if (sh.getVacancyNo() == vacancy) {
                    if(sh instanceof FullTimeStaffHire){
                        x = 1;
                        FullTimeStaffHire s = (FullTimeStaffHire) sh;
                        String joinedText = joinedField.getText().toLowerCase();
                        if (!joinedText.equals("true") && !joinedText.equals("false")&& !joinedText.equals("")) {
                            JOptionPane.showMessageDialog(editStaffWindow, "Please enter 'true' or 'false' in the 'Joined' field.");
                            return;
                        }
            
                        if (!desField.getText().equals("")){s.setDesignation(desField.getText());}
                        if (!jTypeField.getText().equals("")){s.setJobType(jTypeField.getText());}
                        if (!sNameField.getText().equals("")){s.setStaffName(sNameField.getText());}
                        if (!qualField.getText().equals("")){s.setQualification(qualField.getText());}
                        if (!joinedField.getText().equals("")){s.setJoined(Boolean.parseBoolean(joinedField.getText()));}
                    
                        if (!salaryField.getText().equals("")){s.setSalary(Double.parseDouble(salaryField.getText()));}
                        if (!wkHrsField.getText().equals("")){s.setWkFrcHrs(Integer.parseInt(wkHrsField.getText()));}

                        JOptionPane.showMessageDialog(editStaffWindow, "Staff edited Successfully");
                    }
                }
            }
            if  (x==0)  {
                JOptionPane.showMessageDialog(editStaffWindow, "Vacancy number does not exist. Please enter a valid vacancy number.");
                    return;
                }

            
                
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editStaffWindow, "Please enter valid number");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(editStaffWindow, "Error: " + ex.getMessage());
            }
        });

        editStaffWindow.add(clearBtn);
        editStaffWindow.add(confirmBtn);
        
        JButton bButton = new JButton("back");
        JButton exitButton = new JButton("exit");
        
        bButton.addActionListener(e -> cardLayout.show(mainPanel, "Edit Staff Options Window"));
        exitButton.addActionListener(e -> System.exit(0));
        
        editStaffWindow.add(bButton);
        editStaffWindow.add(exitButton);
    
        return editStaffWindow;
    }


    private JPanel partTimeEditWindow() {
        JPanel editStaffWindow = new JPanel(new GridLayout(14, 2, 5, 5));
        editStaffWindow.setSize(400, 500);
        
        JPanel editStaffOptionsWindow = new JPanel(new GridLayout(1, 2, 5, 5));
        editStaffWindow.setSize(400, 500);

        JTextField vacancyField = new JTextField();
        JTextField desField = new JTextField();
        JTextField jTypeField = new JTextField();
        JTextField sNameField = new JTextField();
        JTextField qualField = new JTextField();
        JTextField joinedField = new JTextField(); // Use "true"/"false"
        
        JTextField wageField = new JTextField();
        JTextField workHrField = new JTextField();
        JTextField shiftField = new JTextField();

        editStaffWindow.add(new JLabel("type in the data to be edited"));
        editStaffWindow.add(new JLabel("leave blank if you want to keep it the same"));
        editStaffWindow.add(new JLabel("Vacancy No of the staff to edit:"));
        editStaffWindow.add(vacancyField);
        editStaffWindow.add(new JLabel("Designation:"));
        editStaffWindow.add(desField);
        editStaffWindow.add(new JLabel("Job Type:"));
        editStaffWindow.add(jTypeField);
        editStaffWindow.add(new JLabel("Staff Name:"));
        editStaffWindow.add(sNameField);
        editStaffWindow.add(new JLabel("Qualification:"));
        editStaffWindow.add(qualField);
        editStaffWindow.add(new JLabel("Joined (true/false):"));
        editStaffWindow.add(joinedField);
        editStaffWindow.add(new JLabel("Wage per Hour:"));
        editStaffWindow.add(wageField);
        editStaffWindow.add(new JLabel("Working Hours:"));
        editStaffWindow.add(workHrField);
        editStaffWindow.add(new JLabel("Shift:"));
        editStaffWindow.add(shiftField);

        JButton clearBtn = new JButton("Clear");
        JButton confirmBtn = new JButton("Confirm");

        clearBtn.addActionListener(e -> {
            vacancyField.setText("");
            desField.setText("");
            jTypeField.setText("");
            sNameField.setText("");
            qualField.setText("");
            joinedField.setText("");
            wageField.setText("");
            workHrField.setText("");
            shiftField.setText("");
        });

        confirmBtn.addActionListener(e -> {
            try {
                    
                int vacancy = Integer.parseInt(vacancyField.getText());
    
                int x = 0;
                for (StaffHire sh : SHObj) {
                    if (sh.getVacancyNo() == vacancy) {
                        if(sh instanceof PartTimeStaffHire){
                            x = 1;
                            PartTimeStaffHire s = (PartTimeStaffHire) sh;
                            String joinedText = joinedField.getText().toLowerCase();
                            if (!joinedText.equals("true") && !joinedText.equals("false")&& !joinedText.equals("")) {
                                JOptionPane.showMessageDialog(editStaffWindow, "Please enter 'true' or 'false' in the 'Joined' field.");
                                return;
                            }
            
                            if (!desField.getText().equals("")){s.setDesignation(desField.getText());}
                            if (!jTypeField.getText().equals("")){s.setJobType(jTypeField.getText());}
                            if (!sNameField.getText().equals("")){s.setStaffName(sNameField.getText());}
                            if (!qualField.getText().equals("")){s.setQualification(qualField.getText());}
                            if (!joinedField.getText().equals("")){s.setJoined(Boolean.parseBoolean(joinedField.getText()));}
                       
                            if (!wageField.getText().equals("")){s.setWageHr(Double.parseDouble(wageField.getText()));}
                            if (!workHrField.getText().equals("")){s.setWorkHr(Integer.parseInt(workHrField.getText()));}
                            if (!shiftField.getText().equals("")){s.setShifts(shiftField.getText());}
                        
                            JOptionPane.showMessageDialog(editStaffWindow, "Staff edited Successfully");
                            }
                    }
                }
                if  (x==0)  {
                    JOptionPane.showMessageDialog(editStaffWindow, "A PartTimeStaffHire with the Vacancy number does not exist. Please enter a valid vacancy number.");
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editStaffWindow, "Please enter valid number");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(editStaffWindow, "Error: " + ex.getMessage());
            }
        });

        editStaffWindow.add(clearBtn);
        editStaffWindow.add(confirmBtn);
        
        JButton bButton = new JButton("back");
        JButton exitButton = new JButton("exit");
        
        bButton.addActionListener(e -> cardLayout.show(mainPanel, "Edit Staff Options Window"));
        exitButton.addActionListener(e -> System.exit(0));
        
        editStaffWindow.add(bButton);
        editStaffWindow.add(exitButton);
    
        return editStaffWindow;
    }
    
    private JPanel terminateStaffWindow() {
        
        JPanel terminateStaffWindow = new JPanel(new BorderLayout(10, 10));
    
        // Warning label at the top
        JLabel warning = new JLabel("The staff designated by the user below will be terminated", JLabel.CENTER);
        warning.setFont(new Font("Arial", Font.BOLD, 25));
        terminateStaffWindow.add(warning, BorderLayout.NORTH);
    
        // Center panel for input fields
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        
        JLabel vacancyLabel = new JLabel("Vacancy No:");
        vacancyLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        
        JTextField vacancyField = new JTextField(10);
        centerPanel.add(vacancyLabel);
        centerPanel.add(vacancyField);
        terminateStaffWindow.add(centerPanel, BorderLayout.CENTER);
    
        // Bottom panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirmButton = new JButton("Confirm");
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "ContentsPage"));
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(backButton);
        terminateStaffWindow.add(buttonPanel, BorderLayout.SOUTH);
    
        confirmButton.addActionListener(e -> {
            try {
                    
                int vacancy = Integer.parseInt(vacancyField.getText());
    
                int x = 0;
                for (StaffHire sh : SHObj) {
                    if (sh.getVacancyNo() == vacancy) {
                        if(sh instanceof PartTimeStaffHire){
                            x = 1;
                            PartTimeStaffHire s = (PartTimeStaffHire) sh;
                            s.setTerminated(true);
                            JOptionPane.showMessageDialog(terminateStaffWindow, "Staff terminated Successfully");
                        }
                        if(sh instanceof FullTimeStaffHire){
                            x = 1;
                            FullTimeStaffHire s = (FullTimeStaffHire) sh;
                            s.setTerminated(true);
                            JOptionPane.showMessageDialog(terminateStaffWindow, "Staff terminated Successfully");
                        }
                    }
                }
                if  (x==0)  {
                    JOptionPane.showMessageDialog(terminateStaffWindow, "A StaffHire with the Vacancy number provided does not exist. Please enter a valid vacancy number.");
                    return;
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(terminateStaffWindow, "Please enter valid number");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(terminateStaffWindow, "Error: " + ex.getMessage());
            }
        });
        
        
        return terminateStaffWindow;
    }
    
    
    public static void main(String[] args) {
        RecruitmentSystemGUI apple = new RecruitmentSystemGUI();
    }
}


//*******************************___________________**************************

    


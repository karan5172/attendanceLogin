package attandanceDetails;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;


import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.sql.Statement;


public class AttendenceDetails {

	private JFrame frame;
	private JTextField empid;
	private JTextField name;
	private JTextField in_time;
	private JTextField out_time;
	private JTextField status;
	private JTable tableData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendenceDetails window = new AttendenceDetails();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AttendenceDetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 255, 102));
		frame.setBounds(100, 100, 846, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEmpAtten = new JLabel("EMPLOYEE ATTENDENCE");
		lblEmpAtten.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblEmpAtten.setBounds(230, 22, 441, 30);
		frame.getContentPane().add(lblEmpAtten);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 153));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), "ADD ATTENDENCE", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBounds(10, 84, 810, 158);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMP ID");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(77, 35, 86, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("IN_TIME");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_1.setBounds(358, 35, 66, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NAME");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(221, 35, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("OUT_TIME");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_3.setBounds(501, 35, 66, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("STATUS");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_4.setBounds(668, 35, 57, 14);
		panel.add(lblNewLabel_4);
		
		empid = new JTextField();
		empid.setBounds(38, 71, 125, 20);
		panel.add(empid);
		empid.setColumns(10);
		
		name = new JTextField();
		name.setBounds(189, 71, 130, 20);
		panel.add(name);
		name.setColumns(10);
		
		in_time = new JTextField();
		in_time.setBounds(329, 71, 131, 20);
		panel.add(in_time);
		in_time.setColumns(10);
		
		out_time = new JTextField();
		out_time.setBounds(470, 71, 141, 20);
		panel.add(out_time);
		out_time.setColumns(10);
		
		status = new JTextField();
		status.setBounds(644, 71, 125, 20);
		panel.add(status);
		status.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 58, 790, 2);
		panel.add(separator);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = empid.getText();
				String ename = name.getText();
				String intime = in_time.getText();
				String outtime = out_time.getText();
				String stat = status.getText();
				Connection con1;
				PreparedStatement insert;
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kit_attandance", "root", "karan@kit");
					insert = con1.prepareStatement("INSERT INTO emp_atten (emp_id, emp_name, in_time, out_time, status) VALUES (?, ?, ?, ?, ?)");
					insert.setString(1, id);
					insert.setString(2, ename);
					insert.setString(3, intime);
					insert.setString(4, outtime);
					insert.setString(5, stat);
					insert.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "RECORD ADDED !!");
				} catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}  
				
			}
		});
		btnSave.setBackground(new Color(0, 255, 255));
		btnSave.setBounds(77, 113, 89, 23);
		panel.add(btnSave);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setBackground(new Color(0, 255, 255));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empid.setText(null);
				name.setText(null);
				in_time.setText(null);
				out_time.setText(null);
				status.setText(null);
				
			}
		});
		btnReset.setBounds(210, 113, 89, 23);
		panel.add(btnReset);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit","Attendence Details",
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setBackground(new Color(0, 255, 255));
		btnExit.setBounds(661, 113, 89, 23);
		panel.add(btnExit);
		
		JButton btnView = new JButton("View Details");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/kit_attandance","root","karan@kit");
					Statement st = connect.createStatement();
					String query = "select * from emp_atten";
					ResultSet result = st.executeQuery(query);
					ResultSetMetaData metadata = result.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tableData.getModel();
					
					model.setRowCount(0);
					int cols = metadata.getColumnCount();
					String[] colName= new String[cols];
					for(int i=0; i<cols; i++) {
						colName[i] = metadata.getColumnName(i+1);
						model.setColumnIdentifiers(colName);
						
						String id, name, inTime, outTime, status;
						while(result.next()) {
							id=result.getString(1);
							name=result.getString(2);
							inTime=result.getString(3);
							outTime=result.getString(4);
							status = result.getString(5);
							String[] row = {id, name, inTime, outTime, status};
							model.addRow(row);
						}
						result.close();
						st.close();
						connect.close();
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
				
			
		});
		btnView.setBounds(350, 113, 110, 23);
		panel.add(btnView);
		
		JButton btnNewButton = new JButton("Clear Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableData.setModel(new DefaultTableModel());
			}
		});
		btnNewButton.setBounds(501, 113, 113, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 153, 255));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ATTENDENCE DETAILS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBounds(10, 253, 810, 183);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 790, 150);
		panel_1.add(scrollPane);
		
		tableData = new JTable();
		scrollPane.setViewportView(tableData);
		tableData.setCellSelectionEnabled(true);
		tableData.setColumnSelectionAllowed(true);
		tableData.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
	}
}

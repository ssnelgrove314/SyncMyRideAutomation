package com.automation.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.automation.customer.Customer;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class CustomerDataWindow extends JFrame {


	private CustomerData customer;
	private JTextField txtFirstName;
	private JTextField txtVin;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtPassword;
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtZip;
	private JTextField txtLastName;
	private JTextField CustomerConstructor;
	private JTextField lblCustomerToString;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Customer custom;

	public CustomerDataWindow() {
		setTitle("Sync Automation");
		setResizable(false);
		customer = new CustomerData();
		customer.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 12, 57, 16);
		customer.add(lblName);

		txtFirstName = new JTextField();
		txtFirstName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtFirstName.setText("");
			}
		});
		txtFirstName.setText("First Name");
		txtFirstName.setBounds(80, 10, 114, 20);
		customer.add(txtFirstName);
		txtFirstName.setColumns(10);

		JLabel lblVin = new JLabel("VIN:");
		lblVin.setBounds(12, 40, 55, 16);
		customer.add(lblVin);

		txtVin = new JTextField();
		txtVin.setBounds(80, 38, 114, 20);
		customer.add(txtVin);
		txtVin.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 68, 55, 16);
		customer.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(80, 66, 114, 20);
		customer.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(12, 96, 55, 16);
		customer.add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setBounds(80, 94, 114, 20);
		customer.add(txtPhone);
		txtPhone.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(12, 124, 74, 16);
		customer.add(lblPassword);

		txtPassword = new JTextField();
		txtPassword.setBounds(80, 122, 114, 20);
		customer.add(txtPassword);
		txtPassword.setColumns(10);

		JCheckBox chckbxGeneratePassword = new JCheckBox("Generate Password?");
		chckbxGeneratePassword.setBounds(221, 120, 151, 24);
		customer.add(chckbxGeneratePassword);



		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(12, 152, 55, 16);
		customer.add(lblAddress);

		txtStreet = new JTextField();
		txtStreet.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtStreet.setText("");
			}
		});
		txtStreet.setText("Street");
		txtStreet.setBounds(80, 152, 114, 20);
		customer.add(txtStreet);
		txtStreet.setColumns(10);

		txtCity = new JTextField();
		txtCity.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtCity.setText("");
			}
		});
		txtCity.setText("City");
		txtCity.setBounds(200, 152, 114, 20);
		customer.add(txtCity);
		txtCity.setColumns(10);

		txtState = new JTextField();
		txtState.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtState.setText("");
			}
		});
		txtState.setText("State");
		txtState.setBounds(80, 184, 114, 20);
		customer.add(txtState);
		txtState.setColumns(10);

		txtZip = new JTextField();
		txtZip.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtZip.setText("");
			}
		});
		txtZip.setText("Zip");
		txtZip.setBounds(200, 184, 114, 20);
		customer.add(txtZip);
		txtZip.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtLastName.setText("");
			}
		});
		txtLastName.setText("Last Name");
		txtLastName.setBounds(200, 10, 114, 20);
		customer.add(txtLastName);
		txtLastName.setColumns(10);

		CustomerConstructor = new JTextField();
		CustomerConstructor.setBounds(12, 289, 613, 16);
		customer.add(CustomerConstructor);
		CustomerConstructor.setColumns(10);

		lblCustomerToString = new JTextField();
		lblCustomerToString.setBounds(12, 216, 613, 61);
		customer.add(lblCustomerToString);
		lblCustomerToString.setColumns(10);

		JCheckBox chckbxIsThisVehicle = new JCheckBox("Is this Vehicle a Lincoln?");
		chckbxIsThisVehicle.setBounds(369, 183, 193, 23);
		customer.add(chckbxIsThisVehicle);
		setupFrame();

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Customer customer = new Customer(txtFirstName.getText(), txtLastName.getText(), 
						txtEmail.getText(), txtStreet.getText(), txtCity.getText(), txtState.getText(), 
						txtZip.getText(), txtPassword.getText(), txtVin.getText(), !(chckbxIsThisVehicle.isSelected()) );

				if( chckbxGeneratePassword.isSelected() ) {
					customer.setPassword( txtVin.getText().substring(9, 17).toLowerCase() );
				}
				lblCustomerToString.setText(customer.toString());
				CustomerConstructor.setText(customer.toString());
			}
		});
		btnSubmit.setBounds(539, 329, 98, 26);
		customer.add(btnSubmit);
	}

	private void setupFrame() {
		this.setContentPane(customer);
		//		this.setBounds(x, y, 660, 394);

		this.setBounds(100, 100 , 660, 394);
		this.setVisible(true);
	}

	public Customer getCustomer() {
		return custom;
	}


}


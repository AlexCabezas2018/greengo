package presentation.rental.forms;

import business.rental.TRental;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDropRental extends JDialog {
	/*Attributes*/

	private JTextField idText;

	public FormDropRental(){
		setTitle("Drop rental");
		setResizable(false);
		Util.addEscapeListener(this);
		initGUI();
	}

	private void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		mainPanel.add(fieldsPanel());
		mainPanel.add(buttonsPanel());

		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel fieldsPanel(){
		JPanel ret = ViewHelpers.createFieldPanel(1);

		//id
		JLabel idLabel = new JLabel("Id");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		return ret;
	}

	private JPanel buttonsPanel() {
		JPanel ret = new JPanel(new FlowLayout());

		JButton drop = ViewHelpers.buttonsForms("DROP");

		drop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				TRental rent = new TRental();
				try{
					rent.setId(Util.parseNoNegativeInt(idText.getText()));
					AppController.getInstance().execute(new LightContext(Event.DROP_RENTAL, rent));
					dispose();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(getRootPane(), e.getMessage(), "ERROR IN DROP RENTAL",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = ViewHelpers.buttonsForms("CANCEL");

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				dispose();
			}
		});

		ret.add(drop);
		ret.add(cancel);

		return ret;
	}

	public static void main(String[] Args){
		FormDropRental f = new FormDropRental();
		f.setVisible(true);
	}
}

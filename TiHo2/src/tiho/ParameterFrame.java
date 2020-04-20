package tiho;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ParameterFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private String[] parameters;
	private ParameterChooserPanel parameterChooser;

	private JPanel mainPanel;
	private JButton confirmButton;
	private JButton backButton;

	public ParameterFrame(String[] parameters)
	{
		this.parameters = parameters;
		parameterChooser = new ParameterChooserPanel(this.parameters);
		confirmButton = new JButton("OK");
		backButton = new JButton("Zurück");

		confirmButton.setSize(backButton.getPreferredSize());

		mainPanel = new JPanel();
		Border innerBorder = BorderFactory.createEtchedBorder();
		Border outerBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		mainPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ParameterFrame.this.dispose();
				new MainFrame();
			}
		});

		setFrame();
		setComponentLayout();
	}

	private void setComponentLayout()
	{
		mainPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 1;
		gc.weighty = 1;

		gc.fill = GridBagConstraints.NONE;

		// FIRST LINE
		gc.gridy = 0;

		gc.fill = GridBagConstraints.BOTH;

		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(parameterChooser, gc);

		// NEXT LINE
		gc.gridy++;

		gc.weighty = 0.1;

		gc.fill = GridBagConstraints.NONE;

		gc.gridwidth = 1;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 15, 0, 15);
		mainPanel.add(confirmButton, gc);

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		mainPanel.add(backButton, gc);

		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
	}

	private void setFrame()
	{
		setSize(190, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}

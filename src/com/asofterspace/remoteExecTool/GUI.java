/**
 * Unlicensed code created by A Softer Space, 2019
 * www.asofterspace.com/licenses/unlicense.txt
 */
package com.asofterspace.remoteExecTool;

import com.asofterspace.toolbox.gui.Arrangement;
import com.asofterspace.toolbox.gui.GuiUtils;
import com.asofterspace.toolbox.gui.MainWindow;
import com.asofterspace.toolbox.gui.ProgressDialog;
import com.asofterspace.toolbox.io.Directory;
import com.asofterspace.toolbox.io.File;
import com.asofterspace.toolbox.io.IoUtils;
import com.asofterspace.toolbox.io.Record;
import com.asofterspace.toolbox.io.SimpleFile;
import com.asofterspace.toolbox.utils.Callback;
import com.asofterspace.toolbox.utils.ProgressIndicator;
import com.asofterspace.toolbox.Utils;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;


public class GUI extends MainWindow {

	private Integer id;

	private String name;

	private String sharePath;

	private List<Record> commands;


	public GUI(Integer id, String name, String sharePath, List<Record> commands) {

		this.id = id;

		this.name = name;

		this.sharePath = sharePath;

		this.commands = commands;
	}

	@Override
	public void run() {

		super.create();

		createMainPanel(mainFrame);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Stage everything to be shown
				mainFrame.pack();

				// Actually display the whole jazz
				mainFrame.setVisible(true);
			}
		});
	}

	private JPanel createMainPanel(JFrame parent) {

		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(800, 500));
		GridBagLayout mainPanelLayout = new GridBagLayout();
		mainPanel.setLayout(mainPanelLayout);

		final JTextField inputField = new JTextField();
		mainPanel.add(inputField, new Arrangement(0, 0, 1.0, 0.0));

		// listen to the enter key being pressed (which does not create text updates)
		inputField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exec(inputField.getText());
				inputField.setText("");
			}
		});

		parent.add(mainPanel, BorderLayout.CENTER);

		return mainPanel;
	}

	private void exec(String commandStr) {

		for (Record command : commands) {
			if (commandStr.equals(command.getString("shorthand"))) {
				exec(command);
			}
		}
	}

	private void exec(Record command) {

		System.out.println("Executing " + command.getString("name") + " command...");

		if ("shellexec".equals(command.getString("kind"))) {
			IoUtils.execute(command.getString("name"), new Directory(command.getString("path")));
		}
	}

}

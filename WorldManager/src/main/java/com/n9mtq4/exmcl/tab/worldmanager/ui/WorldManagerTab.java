package com.n9mtq4.exmcl.tab.worldmanager.ui;

import com.n9mtq4.logwindow.BaseConsole;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by will on 8/31/15 at 11:42 PM.
 */
public final class WorldManagerTab extends JSplitPane {
	
	private final LauncherTabPanel parent;
	private final BaseConsole baseConsole;
	
	private JScrollPane scrollPane;
	private WorldTable table;
	private JPanel buttonArea;
	
	private JButton refresh;
	private JButton openSaveFolder;
	private JButton rename;
	private JButton duplicate;
	private JButton moreInfo;
	
	public WorldManagerTab(LauncherTabPanel parent, BaseConsole baseConsole) {
		super();
		this.parent = parent;
		this.baseConsole = baseConsole;
		gui();
	}
	
	private void gui() {
		
		table = new WorldTable(parent.getMinecraftLauncher());
		scrollPane = new JScrollPane(table);
		setRightComponent(scrollPane);
		
		ButtonListener listener = new ButtonListener();
		this.buttonArea = new JPanel(new GridLayout(10, 1));
		
		this.refresh = new JButton("Refresh");
		buttonArea.add(refresh);
		refresh.addActionListener(listener);
		
		this.openSaveFolder = new JButton("Open saves");
		buttonArea.add(openSaveFolder);
		openSaveFolder.addActionListener(listener);
		
		this.rename = new JButton("Rename");
		buttonArea.add(rename);
		rename.addActionListener(listener);
		
		this.duplicate = new JButton("Duplicate");
		buttonArea.add(duplicate);
		duplicate.addActionListener(listener);
		
		this.moreInfo = new JButton("More Info");
		buttonArea.add(moreInfo);
		moreInfo.addActionListener(listener);
		
		setLeftComponent(buttonArea);
		
	}
	
	private class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String buttonText = ((JButton) e.getSource()).getText();
			
			if (buttonText.equalsIgnoreCase("refresh")) {
				table.refresh();
			}else if (buttonText.equalsIgnoreCase("open saves")) {
				
				try {
					Desktop.getDesktop().open(table.getWorldTableModel().getSavesDir());
				}catch (IOException e1) {
					e1.printStackTrace();
				}
				
			/*}else if (buttonText.equalsIgnoreCase("rename")) {
				String newName = JOptionPane.showInputDialog(table, "What should be the new World Name?", "Enter name");
				try {
					WorldManagerUtils.renameWorld(table.getSelectedWorld(), newName);
				}catch (IOException e1) {
					e1.printStackTrace();
				}
				table.refresh();*/
			}else {
				JOptionPane.showMessageDialog(table, "This feature is coming soon.", "Not yet ready", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
	}
	
}

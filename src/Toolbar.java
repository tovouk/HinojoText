import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.*;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultEditorKit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.swing.JFileChooser;


public class Toolbar extends JMenuBar implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu File;
	private JMenu Preferences;
	private JMenu Edit;
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem saveAs;
	private JMenuItem incFont;
	private JMenuItem decFont;
	private JMenuItem copy;
	private JMenuItem cut;
	private JMenuItem paste;
	private StringListener textListener;
	private JTextArea textarea;
	private JFileChooser chooser = new JFileChooser();
	private BufferedReader reader;
	private File file;
	private File currentFile;
	private Insets inset = new Insets(0,0,0,0);
	Clipboard clpbrd;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		open = new JMenuItem("Open File");
		save = new JMenuItem("Save Current File");
		saveAs = new JMenuItem("Save File As");
		incFont = new JMenuItem("Inc. Font Size");
		decFont = new JMenuItem("Dec. Font Size");
		
		open.setFont(new Font("Arial",Font.PLAIN,20));
		save.setFont(new Font("Arial",Font.PLAIN,20));
		saveAs.setFont(new Font("Arial",Font.PLAIN,20));
		incFont.setFont(new Font("Arial",Font.PLAIN,20));
		decFont.setFont(new Font("Arial",Font.PLAIN,20));
		
	 	cut = new JMenuItem(new DefaultEditorKit.CutAction());
        cut.setText("Cut");
        cut.setMnemonic(KeyEvent.VK_T);

        copy = new JMenuItem(new DefaultEditorKit.CopyAction());
        copy.setText("Copy");
        copy.setMnemonic(KeyEvent.VK_C);

        paste = new JMenuItem(new DefaultEditorKit.PasteAction());
        paste.setText("Paste");
        paste.setMnemonic(KeyEvent.VK_P);
        
        copy.setFont(new Font("Arial",Font.PLAIN,20));
		cut.setFont(new Font("Arial",Font.PLAIN,20));
		paste.setFont(new Font("Arial",Font.PLAIN,20));
		
		open.setOpaque(false);
		open.setContentAreaFilled(false);
		open.setBorderPainted(false);
		
		save.setOpaque(false);
		save.setContentAreaFilled(false);
		save.setBorderPainted(false);
		
		saveAs.setOpaque(false);
		saveAs.setContentAreaFilled(false);
		saveAs.setBorderPainted(false);
		
		incFont.setOpaque(false);
		incFont.setContentAreaFilled(false);
		incFont.setBorderPainted(false);
		
		decFont.setOpaque(false);
		decFont.setContentAreaFilled(false);
		decFont.setBorderPainted(false);
		
		copy.setOpaque(false);
		copy.setContentAreaFilled(false);
		copy.setBorderPainted(false);
		
		cut.setOpaque(false);
		cut.setContentAreaFilled(false);
		cut.setBorderPainted(false);
		
		paste.setOpaque(false);
		paste.setContentAreaFilled(false);
		paste.setBorderPainted(false);
		
		open.setMargin(inset);
		save.setMargin(inset);
		saveAs.setMargin(inset);
		incFont.setMargin(inset);
		decFont.setMargin(inset);
		copy.setMargin(inset);
		cut.setMargin(inset);
		paste.setMargin(inset);
		
		open.addActionListener(this);
		save.addActionListener(this);
		saveAs.addActionListener(this);
		incFont.addActionListener(this);
		decFont.addActionListener(this);
		copy.addActionListener(this);
		cut.addActionListener(this);
		paste.addActionListener(this);
		
		menuBar = new JMenuBar();
		
		File = new JMenu("File");
		File.add(open);
		File.add(save);
		File.add(saveAs);
		menuBar.add(File);
		
		Edit = new JMenu("Edit");
		Edit.add(copy);
		Edit.add(cut);
		Edit.add(paste);
		menuBar.add(Edit);
		
		Preferences = new JMenu("Preferences");
		Preferences.add(incFont);
		Preferences.add(decFont);
		menuBar.add(Preferences);
		
		
		add(menuBar);
		
		
		
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}
	
	public void setTextArea(JTextArea textarea) {
		this.textarea= textarea;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem clicked = (JMenuItem)e.getSource();
		chooser.setPreferredSize(new Dimension(900,700));
		chooser.setFont(new Font("Arial",Font.TRUETYPE_FONT,80));
		
		if(clicked == open) {
			chooser.setDialogTitle("Open File");
			chooser.setApproveButtonText("Open");
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				if(currentFile != file) {
					textarea.setText("");
				}
				try {
					reader = new BufferedReader(new FileReader(file));
					String line;
					try {
						while((line = reader.readLine())!= null) {
							textarea.append(line + "\n");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					reader.close();
					currentFile = file;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		}else if(clicked == save) {
			
			if(currentFile == null) {
				JOptionPane.showMessageDialog(null, "No File to save");
			}else if(currentFile == file) {
					try (
					        BufferedReader reader = new BufferedReader(new StringReader(textarea.getText()));
					        PrintWriter writer = new PrintWriter(new FileWriter(file));
					    ) {
					        reader.lines().forEach(line -> writer.println(line));
							writer.close();
					    }
				catch(IOException e1) {
					JOptionPane.showMessageDialog(null, "No File to save");
					e1.printStackTrace();
				}
				
			}
			
		}else if(clicked == saveAs) {
			chooser.setDialogTitle("Save File As");
			chooser.setApproveButtonText("Save As");
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				if(currentFile == file) {
					textarea.setText("");
				}
				try {
					BufferedReader reader = new BufferedReader(new StringReader(textarea.getText()));
					
					try(FileWriter fw = new FileWriter(chooser.getSelectedFile())){
						reader.lines().forEach(line -> {
							try {
								fw.append(line);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						});
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					reader.close();
					currentFile = file;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(clicked == incFont) {
			int pastFont = textarea.getFont().getSize();
			pastFont += 5;
			textarea.setFont(new Font("Arial",Font.PLAIN,pastFont));
		}else if(clicked == decFont) {
			int pastFont = textarea.getFont().getSize();
			pastFont -= 5;
			textarea.setFont(new Font("Arial",Font.PLAIN,pastFont));
		}
		
	
	}

}

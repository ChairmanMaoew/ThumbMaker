import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class ThumbnailGUI extends JFrame implements ActionListener{
	
	JPanel pane = new JPanel(new BorderLayout());
	JButton create = new JButton("Create");
	JButton browse = new JButton("Browse");
	JLabel label = new JLabel();
	JTextField fileInput = new JTextField();
	String file = new String();
	String fileName = new String();
	JFileChooser c = new JFileChooser();
	
	/**
	 * Create the GUI to ask for file w/ file browser option
	 */
	ThumbnailGUI(){
		
		super("PDF Thumbnail Creator");
		setBounds(100,100,600,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		Container container = this.getContentPane();
		container.add(pane);
		
		label.setText("Please enter File: ");
		fileInput.setSize(10, 100);
		
		create.addActionListener(this);
		browse.addActionListener(this);
		
		pane.add(label, BorderLayout.LINE_START);
		pane.add(fileInput, BorderLayout.CENTER);
		pane.add(create, BorderLayout.PAGE_END);
		pane.add(browse, BorderLayout.LINE_END);
		
	}
	
	/**
	 * Implement ActionListner for GUI buttons: browse and create.
	 * Create button creates a instance of ThumbGen with the argument in the textBox.
	 * Browse Button calls a JFileChooser object to facilitate a file selection
	 * on selection applying the Absolute file path to the JTextBox
	 */
	public void actionPerformed(ActionEvent event) {
	
		if(event.getSource() == create) {
			System.out.println("<Create button clicked>");
			file = fileInput.getText();
			new ThumbGen(file, fileName);
			
		}else if(event.getSource() == browse) {
			System.out.println("<Browse button clicked>");
			
			int rVal = c.showOpenDialog(ThumbnailGUI.this);
		      if(rVal == JFileChooser.APPROVE_OPTION) {
		        fileInput.setText(c.getSelectedFile().getAbsolutePath());
		        fileName = c.getSelectedFile().getName();
		      }
		      if(rVal == JFileChooser.CANCEL_OPTION) {
		        fileInput.setText("You pressed cancel");
		      
		      }
		    
			}
		
		}

	}
	



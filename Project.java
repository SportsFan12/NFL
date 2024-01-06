import javax.swing.JFrame; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JOptionPane;

public class Project extends JFrame implements ItemListener {

	private String teamName; 
	private ArrayList<Player> players = new ArrayList<Player>(); 
	private JLabel introLabel; 
	private JLabel introLabel2; 
	private JLabel introLabel3;
	private JLabel nfl;
	private JLabel teamNameLabel;
	private JLabel playerLabel;
	private JPanel introPanel;
	private JComboBox teamComboBox;
	//buttons
	private JButton passing_yards_plus;
	private JButton passing_yards_negative;
	private JButton completions_plus;
	private JButton completions_negative;
	private JButton attempts_plus;
	private JButton attempts_negative;
	private JButton passing_touchdown_plus;
	private JButton passing_touchdown_negative;
	private JButton interception_plus;
	private JButton interception_negative;
	private JButton carries_plus;
	private JButton carries_negative;
	private JButton rushing_yards_plus;
	private JButton rushing_yards_negative;
	private JButton rushing_touchdowns_plus;
	private JButton rushing_touchdowns_negative;
	private JButton catches_plus;
	private JButton catches_negative;
	private JButton receiving_yards_plus;
	private JButton receiving_yards_negative;
	private JButton receiving_touchdowns_plus;
	private JButton receiving_touchdowns_negative;
	//buttons
	private int removeFile = 0;
	private int currentPlayerIndex = 0; 
	
	public Project() {
		super("NFL Box Score ( Offense )");
		setLayout(new FlowLayout());
		setSize(750,650);
		setUpMenu();
		setUpIntroLabel();
		setUpIntroPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void setUpIntroLabel () {
		Font font = new Font("Courier", Font.BOLD, 16); 
		introLabel = new JLabel("Welcome, to begin please select a text file via the menubar, Options (top");
		introLabel.setVisible(true);
		introLabel.setFont(font);
		introLabel2 = new JLabel("left). File format must have the team name on the first line and player");
		introLabel2.setVisible(true);
		introLabel2.setFont(font);
		introLabel3 = new JLabel("names for following lines; one entry per line and at least two entries.");
		introLabel3.setVisible(true);
		introLabel3.setFont(font);
		add(introLabel);
		add(introLabel2);
		add(introLabel3);
		revalidate();
	}
	
	public void setUpIntroPanel () {
		introPanel = new JPanel();
		introPanel.setPreferredSize(new Dimension(500, 500)); 
		introPanel.setBackground(Color.WHITE);
		nfl = new JLabel("", JLabel.CENTER);
		ImageIcon icon =  new ImageIcon(getClass().getResource("Madden09.png")); 
		Image img = icon.getImage(); 
		img = img.getScaledInstance(500, 500, Image.SCALE_DEFAULT); 
		icon.setImage(img); 
		nfl.setIcon(icon); 
		nfl.setSize(500, 500); 
		introPanel.add(nfl); 
		add(introPanel);
		
	}
	
	public void setUpMenu () {
		JMenuBar menuBar = new JMenuBar(); 
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("Options"); 
		menuBar.add(fileMenu);
		JMenuItem fileMenuOpen = new JMenuItem("Open Text File");
		fileMenu.add(fileMenuOpen); 
		JMenuItem fileQuit = new JMenuItem("Quit");
		fileMenu.add(fileQuit);
		fileMenuOpen.addActionListener( 
				new ActionListener() { 
					public void actionPerformed (ActionEvent e) {
							remove(introLabel);
							remove(introLabel2);
							remove(introLabel3);
							remove(introPanel);
							revalidate();
							repaint();
							removeFile++;
							JFileChooser fileChooser = new JFileChooser("."); 
							int retval = fileChooser.showOpenDialog(Project.this); 
							if (retval == JFileChooser.APPROVE_OPTION) { 
								if (removeFile > 0) {
									fileMenu.remove(fileMenuOpen);
								}
								File f = fileChooser.getSelectedFile();
								Scanner input = null;
								try {
									input = new Scanner(f); 
								}
								catch (Exception ioE) {
									return;
								}
								int teamNameCount = 0; 
								while (input.hasNext()) { 
									String currentLine = input.nextLine();
									if (teamNameCount <= 0) {
										teamName = currentLine; 
										teamNameCount++;
									}
									else {
										Player newPlayer = new Player(currentLine);
										players.add(newPlayer);
									}
								}
								//sets up components on the JFrame
								setUpTeamLabel();
								setUpPlayerLabel();
								setUpComboBox();
								updateComboBox();
								setUpButtons();
								try {
									input.close(); 
								}
								catch (Exception ie){
									System.exit(1);
								}
							}
						}
					}
				);
		fileQuit.addActionListener( 
				new ActionListener() { 
					public void actionPerformed (ActionEvent e) {
						System.exit(0);
						}
					}
				);
	}
	
	public void setUpTeamLabel () {
		Font font = new Font("Courier", Font.BOLD, 50); 
		teamNameLabel = new JLabel(teamName);
		teamNameLabel.setVisible(true);
		teamNameLabel.setFont(font);
		teamNameLabel.setPreferredSize(new Dimension(750, 50));
		add(teamNameLabel);
		revalidate();
	}

	public void setUpPlayerLabel () {
		playerLabel = new JLabel("Players:");
		playerLabel.setVisible(true);
		Font font = new Font("Courier", Font.BOLD, 40);
		playerLabel.setFont(font);
		playerLabel.setPreferredSize(new Dimension(300, 50));
		add(playerLabel);
		revalidate();
	}
	
	public void setUpComboBox () {
		teamComboBox = new JComboBox<Player>(); 
		teamComboBox.setMaximumRowCount(8); 
		teamComboBox.addItemListener(this);
		teamComboBox.setPreferredSize(new Dimension(250, 25));
		add(teamComboBox);
	}
	
	public void updateComboBox() {
		teamComboBox.removeAllItems(); 
		for (Player a: players) {
			teamComboBox.addItem(a); 
		}
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) { 
				currentPlayerIndex = teamComboBox.getSelectedIndex(); 
			}
    	return;
	}
	
	public void setUpButtons () {
		passing_yards_plus = new JButton("Increase Passing yards");
		passing_yards_plus.setForeground(Color.BLUE);
		passing_yards_plus.setPreferredSize(new Dimension(300, 25));
		passing_yards_plus.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).passingYardsPlus();
					}
				}
		);
		passing_yards_negative = new JButton("Decrease Passing yards");
		passing_yards_negative.setForeground(Color.RED);
		passing_yards_negative.setPreferredSize(new Dimension(300, 25));
		passing_yards_negative.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).passingYardsMinus();
					}
				}
		);
		completions_plus = new JButton("Increase Completions");
		completions_plus.setForeground(Color.BLUE);
		completions_plus.setPreferredSize(new Dimension(300, 25));
		completions_plus.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).completionsPlus();
					}
				}
		);
		completions_negative = new JButton("Decrease Completions");
		completions_negative.setForeground(Color.RED);
		completions_negative.setPreferredSize(new Dimension(300, 25));
		completions_negative.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).completionsMinus();
					}
				}
		);
		attempts_plus = new JButton("Increase Attempts");
		attempts_plus.setForeground(Color.BLUE);
		attempts_plus.setPreferredSize(new Dimension(300, 25));
		attempts_plus.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).attemptsPlus();
					}
				}
		);
		attempts_negative = new JButton("Decrease Attempts");
		attempts_negative.setForeground(Color.RED);
		attempts_negative.setPreferredSize(new Dimension(300, 25));
		attempts_negative.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).attemptsMinus();
					}
				}
		);
		passing_touchdown_plus = new JButton("Increase Passing Touchdowns");
		passing_touchdown_plus.setForeground(Color.BLUE);
		passing_touchdown_plus.setPreferredSize(new Dimension(300, 25));
		passing_touchdown_plus.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).passingTouchdownPlus();
					}
				}
		);
		passing_touchdown_negative = new JButton("Decrease Passing Touchdowns");
		passing_touchdown_negative.setForeground(Color.RED);
		passing_touchdown_negative.setPreferredSize(new Dimension(300, 25));
		passing_touchdown_negative.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).passingTouchdownMinus();
					}
				}
		);
		interception_plus = new JButton("Increase Interceptions");
		interception_plus.setForeground(Color.BLUE);
		interception_plus.setPreferredSize(new Dimension(300, 25));
		interception_plus.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).interceptionsPlus();
					}
				}
		);
		interception_negative = new JButton("Decrease Interceptions");
		interception_negative.setForeground(Color.RED);
		interception_negative.setPreferredSize(new Dimension(300, 25));
		interception_negative.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).interceptionsMinus();
					}
				}
		);
		carries_plus = new JButton("Increase Carries");
		carries_plus.setForeground(Color.BLUE);
		carries_plus.setPreferredSize(new Dimension(300, 25));
		carries_plus.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).carriesPlus();
					}
				}
		);
		carries_negative = new JButton("Decrease Carries");
		carries_negative.setForeground(Color.RED);
		carries_negative.setPreferredSize(new Dimension(300, 25));
		carries_negative.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).carriesMinus();
					}
				}
		);
		rushing_yards_plus = new JButton("Increase Rushing Yards");
		rushing_yards_plus.setForeground(Color.BLUE);
		rushing_yards_plus.setPreferredSize(new Dimension(300, 25));
		rushing_yards_plus.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).rushingYardsPlus();
					}
				}
		);
		rushing_yards_negative = new JButton("Decrease Rushing Yards");
		rushing_yards_negative.setForeground(Color.RED);
		rushing_yards_negative.setPreferredSize(new Dimension(300, 25));
		rushing_yards_negative.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).rushingYardsMinus();
					}
				}
		);
		rushing_touchdowns_plus = new JButton("Increase Rushing Touchdowns");
		rushing_touchdowns_plus.setForeground(Color.BLUE);
		rushing_touchdowns_plus.setPreferredSize(new Dimension(300, 25));
		rushing_touchdowns_plus.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).rushingTouchdownsPlus();
					}
				}
		);
		rushing_touchdowns_negative = new JButton("Decrease Rushing Touchdowns");
		rushing_touchdowns_negative.setForeground(Color.RED);
		rushing_touchdowns_negative.setPreferredSize(new Dimension(300, 25));
		rushing_touchdowns_negative.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).rushingTouchdownsMinus();
					}
				}
		);
		catches_plus = new JButton("Increase Catches");
		catches_plus.setForeground(Color.BLUE);
		catches_plus.setPreferredSize(new Dimension(300, 25));
		catches_plus.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).catchesPlus();
					}
				}
		);
		catches_negative = new JButton("Decrease Catches");
		catches_negative.setForeground(Color.RED);
		catches_negative.setPreferredSize(new Dimension(300, 25));
		catches_negative.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).catchesMinus();
					}
				}
		);
		receiving_yards_plus = new JButton("Increase Receiving Yards");
		receiving_yards_plus.setForeground(Color.BLUE);
		receiving_yards_plus.setPreferredSize(new Dimension(300, 25));
		receiving_yards_plus.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).receivingYardsPlus();
					}
				}
		);
		receiving_yards_negative = new JButton("Decrease Receiving Yards");
		receiving_yards_negative.setForeground(Color.RED);
		receiving_yards_negative.setPreferredSize(new Dimension(300, 25));
		receiving_yards_negative.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).receivingYardsMinus();
					}
				}
		);
		receiving_touchdowns_plus = new JButton("Increase Receiving Touchdowns");
		receiving_touchdowns_plus.setForeground(Color.BLUE);
		receiving_touchdowns_plus.setPreferredSize(new Dimension(300, 25));
		receiving_touchdowns_plus.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).receivingTouchdownsPlus();
					}
				}
		);
		receiving_touchdowns_negative = new JButton("Decrease Receiving Touchdowns");
		receiving_touchdowns_negative.setForeground(Color.RED);
		receiving_touchdowns_negative.setPreferredSize(new Dimension(300, 25));
		receiving_touchdowns_negative.addActionListener(
				new ActionListener () {
					public void actionPerformed (ActionEvent e) {
						players.get(currentPlayerIndex).receivingTouchdownsMinus();
					}
				}
		);
		add(passing_yards_plus);
		add(passing_yards_negative);
		add(completions_plus);
		add(completions_negative);
		add(attempts_plus);
		add(attempts_negative);
		add(passing_touchdown_plus);
		add(passing_touchdown_negative);
		add(interception_plus);
		add(interception_negative);
		add(carries_plus);
		add(carries_negative);
		add(rushing_yards_plus);
		add(rushing_yards_negative);
		add(rushing_touchdowns_plus);
		add(rushing_touchdowns_negative);
		add(catches_plus);
		add(catches_negative);
		add(receiving_yards_plus);
		add(receiving_yards_negative);
		add(receiving_touchdowns_plus);
		add(receiving_touchdowns_negative);
	}
}

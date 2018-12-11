package Eliza;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class ElizaGUI extends JFrame implements ActionListener{
	
	private JLabel lbl, lbl2, a;
	private JTextField ans;
	private JButton jb, viewAll, viewLong, viewLongAlpha;
	private JTextArea txtArea;
	JScrollPane scroll;
	private JPanel answer, views;
	private int sessNum = 1;
	private int quesNum = 1;
	private int numQs = 0;
	private QuestionBank qBank;
	private TextFileHandler tfHand = new TextFileHandler();
	private String absLongest = "";
	private String absShortest = "          ";
	private String path = "Project2_Eliza/MyFiles/";
	private String slog = path+"Entire Log.txt";
	private String lwords = path+"Long Words.txt";
	private String lawords = path+"Long Words Alpha.txt";
	private Dimension D = new Dimension(1200, 600);
	private Dimension d = new Dimension(1200, 300);

	private static final String ACTION_COMMAND_START = "Start Session";
	private static final String ACTION_COMMAND_NEXT = "Next Question";
	private static final String ACTION_COMMAND_FINISH = "Finish Session";
	private static final String ACTION_COMMAND_VIEW_ALL = "View Entire Log";
	private static final String ACTION_COMMAND_VIEW_LONG = "View Longest Words";
	private static final String ACTION_COMMAND_VIEW_LONG_ALPHA = "View Longest Words Alphabetically";
	private static final Font BIG_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 24);
	private static final Font SMALL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 16);
	
	public ElizaGUI() {
		createFiles();
		
		JPanel mainJP = new JPanel();
		answer = new JPanel();
		views = new JPanel();
		GroupLayout gl = new GroupLayout(mainJP);
		GroupLayout gl2 = new GroupLayout(answer);
		GroupLayout gl3 = new GroupLayout(views);
		mainJP.setLayout(gl);
		answer.setLayout(gl2);
		views.setLayout(gl3);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		gl2.setAutoCreateGaps(true);
		gl2.setAutoCreateContainerGaps(true);
		gl3.setAutoCreateGaps(true);
		gl3.setAutoCreateContainerGaps(true);
		
		lbl = new JLabel("Hello. Welcome to Eliza, your personal Psychotherapist.");
		lbl2 = new JLabel ("Please enter how many questions you would like (20 and below) and press the start button.");
		a = new JLabel("Answer: ", JLabel.RIGHT);
		ans = new JTextField();		
		jb = new JButton(ACTION_COMMAND_START);
		viewAll = new JButton(ACTION_COMMAND_VIEW_ALL);
		viewLong = new JButton(ACTION_COMMAND_VIEW_LONG);
		viewLongAlpha = new JButton(ACTION_COMMAND_VIEW_LONG_ALPHA);
		txtArea = new JTextArea();
		scroll = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		jb.addActionListener(this);
		viewAll.addActionListener(this);
		viewLong.addActionListener(this);
		viewLongAlpha.addActionListener(this);
		
		lbl.setHorizontalAlignment(JLabel.CENTER);
		lbl2.setHorizontalAlignment(JLabel.CENTER);
		lbl.setVerticalAlignment(JLabel.CENTER);
		lbl2.setVerticalAlignment(JLabel.CENTER);
		
		lbl.setFont(BIG_FONT);
		a.setFont(BIG_FONT);
		ans.setFont(BIG_FONT);
		jb.setFont(BIG_FONT);
		lbl2.setFont(SMALL_FONT);
		txtArea.setFont(SMALL_FONT);
		viewAll.setFont(SMALL_FONT);
		viewLong.setFont(SMALL_FONT);
		viewLongAlpha.setFont(SMALL_FONT);
		
		answer.setMaximumSize(new Dimension(1100, 100));
		
		txtArea.setEditable(false);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		
		views.setVisible(false);
		scroll.setVisible(false);
		
		gl.setVerticalGroup(
			gl.createSequentialGroup()
				.addComponent(lbl)
				.addComponent(lbl2)
				.addComponent(answer)
				.addComponent(jb)
				.addComponent(views)
				.addComponent(scroll)
		);
		gl.setHorizontalGroup(
			gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(lbl)
						.addComponent(lbl2)
						.addComponent(answer)
						.addComponent(jb)
						.addComponent(views)
						.addComponent(scroll))
		);
		
		gl2.setVerticalGroup(
			gl2.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(a)
				.addComponent(ans)
		);
		gl2.setHorizontalGroup(
			gl2.createSequentialGroup()
				.addComponent(a)
				.addComponent(ans)
		);
		
		gl3.setVerticalGroup(
			gl3.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(viewAll)
				.addComponent(viewLong)
				.addComponent(viewLongAlpha)
		);
		gl3.setHorizontalGroup(
			gl3.createSequentialGroup()
				.addComponent(viewAll)
				.addComponent(viewLong)
				.addComponent(viewLongAlpha)
		);
		
		Color lemonChiffon = new Color(255, 250, 205);
		Color veryLightGray = new Color(245, 245, 245);
		Color darkBlue = new Color(25, 25, 112);
		
		mainJP.setBackground(lemonChiffon);
		lbl.setBackground(lemonChiffon);
		lbl2.setBackground(lemonChiffon);
		answer.setBackground(lemonChiffon);
		a.setBackground(lemonChiffon);
		ans.setBackground(veryLightGray);
		jb.setBackground(veryLightGray);
		views.setBackground(lemonChiffon);
		viewAll.setBackground(veryLightGray);
		viewLong.setBackground(veryLightGray);
		viewLongAlpha.setBackground(veryLightGray);
		txtArea.setBackground(veryLightGray);
		
		lbl.setForeground(darkBlue);
		lbl2.setForeground(darkBlue);
		a.setForeground(darkBlue);
		ans.setForeground(darkBlue);
		jb.setForeground(darkBlue);
		viewAll.setForeground(darkBlue);
		viewLong.setForeground(darkBlue);
		viewLongAlpha.setForeground(darkBlue);
		txtArea.setForeground(darkBlue);
		
		add(mainJP);
		setSize(d);
		setTitle("Eliza");
		setLocation(50, 50);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void createFiles(){
		tfHand.createNewFile(slog);
		tfHand.createNewFile(lwords);
		tfHand.createNewFile(lawords);
	}
	
	public void addSessionLog() {
		String ansTxt = ans.getText().toLowerCase();
		
		String[] words = ansTxt.split(" ");
		
		String longest = "";
		
		for(int i = 0; i < words.length; i++) {
			if(words[i].length() > longest.length()) {
				longest = words[i].toLowerCase();
			}
			if(words[i].length() > absLongest.length()) {
				absLongest = words[i].toLowerCase();
			}
			if(words[i].length() < absShortest.length() && words[i].length() > 0) {
				absShortest = words[i].toLowerCase();
			}
		}
		
		String s = lbl.getText()+"\n\tAnswer: "+ansTxt;
		tfHand.appendToFile(slog, s);
		tfHand.appendToFile(lwords, longest);
	}
	
	public void addAlphaLong() {
		tfHand.createNewFile(lawords);
		
		String[] alphaLong = tfHand.readFileArray(lwords);
		
		alphaLong = cleanAlphaLong(alphaLong);
		
		Arrays.sort(alphaLong);
		
		for(int i = 0; i < alphaLong.length; i++) {
			tfHand.appendToFile(lawords, alphaLong[i]);
		}
	}
	
	public String[] cleanAlphaLong(String[] s) {
		for(int i = 0; i < s.length; i++) {
			if(s[i] == null) {
				String[] news = new String[i];
				for(int j = 0; j < i; j++) {
					news[j] = s[j];
				}
				return news;
			}
		}
		return s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btnAction = e.getActionCommand();
		
		switch(btnAction) {
		case ACTION_COMMAND_START:
			try {
				int n = Integer.parseInt(ans.getText());
				if(n < 1 || n > 20) {
					throw new Exception();
				}
			}catch(Exception ex) {
				ex.printStackTrace();
				lbl.setText("Invalid Entry");
				break;
			}
			startSession();
			break;
		case ACTION_COMMAND_NEXT:
			nextQuestion();
			break;
		case ACTION_COMMAND_FINISH:
			finishSession();
			break;
		case ACTION_COMMAND_VIEW_ALL:
			txtArea.setText(tfHand.readFile(slog));
			break;
		case ACTION_COMMAND_VIEW_LONG:
			txtArea.setText(tfHand.readFile(lwords));
			break;
		case ACTION_COMMAND_VIEW_LONG_ALPHA:
			txtArea.setText(tfHand.readFile(lawords));
			break;
		}
	}
	
	public void startSession() {
		setSize(d);
		scroll.setVisible(false);
		lbl2.setVisible(false);
		views.setVisible(false);
		absLongest = "";
		absShortest = "          ";
		numQs = Integer.parseInt(ans.getText());
		qBank = new QuestionBank(numQs);
		lbl.setText("Session "+sessNum+": Q"+quesNum+": "+qBank.getQuestion(quesNum-1));
		jb.setText(ACTION_COMMAND_NEXT);
		if(quesNum == numQs) {
			jb.setText(ACTION_COMMAND_FINISH);
		}
		ans.setText("");
	}
	
	public void nextQuestion() {
		addSessionLog();
		
		if(quesNum == numQs-1) {
			jb.setText(ACTION_COMMAND_FINISH);
		}
		
		quesNum++;
		
		lbl.setText("Session "+sessNum+": Q"+quesNum+": "+qBank.getQuestion(quesNum-1));
		ans.setText("");
	}
	
	public void finishSession() {
		addSessionLog();
		tfHand.appendToFile(slog, "");
		addAlphaLong();
		ans.setText("");
		txtArea.setText("");
		views.setVisible(true);
		jb.setText(ACTION_COMMAND_START);
		lbl.setText("End of session "+sessNum+" Analysis: You obviously love "+absLongest+" but hate "+absShortest+".");
		lbl2.setVisible(true);
		scroll.setVisible(true);
		setSize(D);
		sessNum++;
		quesNum = 1;
	}

}

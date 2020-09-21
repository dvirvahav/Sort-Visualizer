import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CustomLayout extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSlider arraySlider = new JSlider(50, 150);
	JSlider speedSlider = new JSlider(1, 50);
	Icon starticon = new ImageIcon("C:\\Users\\Dvir Vahav\\eclipse-workspace\\SortVisualizer\\start.gif");
	Icon shuffleicon = new ImageIcon("C:\\Users\\Dvir Vahav\\eclipse-workspace\\SortVisualizer\\shuffle.gif");
	Icon abouticon = new ImageIcon("C:\\Users\\Dvir Vahav\\eclipse-workspace\\SortVisualizer\\about.gif");
	Icon stopicon = new ImageIcon("C:\\Users\\Dvir Vahav\\eclipse-workspace\\SortVisualizer\\stop.gif");
	JButton start = new JButton(starticon);
	JButton shuffle = new JButton(shuffleicon);
	JButton stop = new JButton(stopicon);
	JButton about = new JButton(abouticon);
	JPanel controlPanel = new JPanel();
	JLabel alg = new JLabel("Algorythms:");
	JLabel BestCase = new JLabel();
	JLabel BestCaseSet = new JLabel();
	JLabel WorstCase = new JLabel();
	JLabel WorstCaseSet = new JLabel();
	JLabel AvgCase = new JLabel();
	JLabel AvgCaseSet = new JLabel();

	String[] sorting = { "BubbleSort", "HeapSort", "InsertionSort", "SelectionSort", "MergeSort", "QuickSort",
			"CountSort" };
	JComboBox<String> mainmenu = new JComboBox<String>(sorting);
	JPanel sliderspanel = new JPanel();
	JMenu menu = new JMenu();
	JPanel menupanel = new JPanel();
	JPanel infopanel = new JPanel();
	String SelectedSort;
	int shuffleFlag = 0;
	int startrun = 0;
	int runFlag = 0;
	int speed = speedSlider.getValue(), MaxSize = 658;
	Container mainContainer = this.getContentPane();
	ArrayList<Integer> list = new ArrayList<Integer>();
	drawPanel centerPanel = new drawPanel(list);
	Thread sortingMethod;

	public CustomLayout(String title) {
		super(title); // set title to the frame
		this.setSize(1000, 800); // size of the frame
		this.setResizable(false);
		this.setLocation(300, 100); // set location on screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// first setup
		Util.Random(list, arraySlider.getValue(), MaxSize);

		// Menu configuration

		// default sorting
		SelectedSort = "BubbleSort";
		menu.setText("BubbleSort");

		//ComboBox Menu CONFIGURATION
		mainmenu.addActionListener(e -> {
			SelectedSort = sorting[mainmenu.getSelectedIndex()];
			UpdateThread();

		});
		menupanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Algorythm:"));
		menupanel.add(mainmenu);

		// SLIDERS CONFIGURATION
		sliderspanel.setLayout(new BoxLayout(sliderspanel, 3));
		sliderspanel.add(arraySlider);
		sliderspanel.add(speedSlider);

		arraySlider.addChangeListener(e -> {
			if (runFlag == 1) {
				Util.exit = true;
				Util.Random(list, arraySlider.getValue(), MaxSize);
				UpdateThread();
				centerPanel.repaint();
				runFlag = 0;
			} else {
				Util.Random(list, arraySlider.getValue(), MaxSize);
				UpdateThread();
				centerPanel.repaint();
			}
		});
		arraySlider.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "ArraySize"));
		speedSlider.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Speed"));

		// speedSlider action when changed
		speedSlider.addChangeListener(e -> {
			if (runFlag == 1) {
				speed = speedSlider.getValue();
				Util.exit = true;
				runFlag = 0;
				start.doClick();

			} else {
				speed = speedSlider.getValue();
				UpdateThread();
			}

		});
//INFO CONFIGURATION
		BestCase.setText("BestCase:");
		BestCaseSet.setText(" O(n)");
		WorstCase.setText("WorstCase:");
		WorstCaseSet.setText(" O(n^2)");
		AvgCase.setText("AvgCase:");
		AvgCaseSet.setText(" O(n^2)");

		infopanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 1), "Complexity:"));
		infopanel.setLayout(new GridLayout(4, 4));
		infopanel.add(BestCase);
		infopanel.add(BestCaseSet);
		infopanel.add(WorstCase);
		infopanel.add(WorstCaseSet);
		infopanel.add(AvgCase);
		infopanel.add(AvgCaseSet);

		// MAIN CONTAINER CONFIG

		mainContainer.setLayout(new BorderLayout()); // sets horizontal and vertical gap between components.
		mainContainer.setBackground(Color.GRAY);
		mainContainer.add(controlPanel, BorderLayout.NORTH);
		mainContainer.add(centerPanel, BorderLayout.CENTER);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		// CONTROLS PANEL CONFIGURATION

		controlPanel.setBorder(new LineBorder(new Color(169, 169, 169), 1));
		controlPanel.setLayout(new FlowLayout());
		controlPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(20334455), 2), "Controls"));
		controlPanel.setBackground(new Color(169, 169, 169));
		controlPanel.add(menupanel);
		controlPanel.add(start);
		controlPanel.add(stop);
		controlPanel.add(shuffle);
		controlPanel.add(about);
		controlPanel.add(sliderspanel);
		controlPanel.add(infopanel);

		// DRAW PANEL CONFIGURATION
		centerPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		// SETS START BUTTON CONFIGURATION

		start.addActionListener(e -> {
			if (runFlag == 1)
				return;
			if (startrun == 1) {
				UpdateThread();
				Util.exit = false;
				sortingMethod.start();
				runFlag = 1;

			}

			if (startrun == 0) {
				Util.Random(list, arraySlider.getValue(), MaxSize);
				UpdateThread();
				Util.exit = false;
				sortingMethod.start();
				runFlag = 1;
				startrun = 1;

				return;
			}

		});

		// SETS SHUFFLE BUTTON CONFIGURATION
		shuffle.addActionListener(e -> {
			if (runFlag == 1) {
				Util.exit = true;

				Util.Random(list, arraySlider.getValue(), MaxSize);
				UpdateThread();
				centerPanel.repaint();
				runFlag = 0;
			} else {
				Util.Random(list, arraySlider.getValue(), MaxSize);
				UpdateThread();
				centerPanel.repaint();
			}

		});

		// SETS STOP BUTTON CONFIGURATION
		stop.addActionListener(e -> {
			if (runFlag == 1) {
				Util.exit = true;
				UpdateThread();
				centerPanel.repaint();
				runFlag = 0;
			}

		});
		// SETS ABOUT BUTTON CONFIGURATION

		about.addActionListener(e -> {
			this.setEnabled(false);
			JFrame aboutFrame = new JFrame();
			JPanel aboutPanel = new JPanel();
			Icon linkedinicon = new ImageIcon("C:\\Users\\Dvir Vahav\\eclipse-workspace\\SortVisualizer\\linkedin.png");
			JButton close = new JButton("Close");
			JButton linkedin = new JButton(linkedinicon);

			JLabel aboutText = new JLabel(
					"<html> -Sort Visualizer-<br/><br/>  Developed by Dvir Vahav<br/>as part of self-project <br/>to extend Java GUI experience<br/><br/> Build Date: 21.9.2020<br/> Dvirvah@gmail.com <br/</html>",
					SwingConstants.CENTER);
			linkedin.addActionListener(b -> {
				try {
					Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/dvir-vahav-219907154/"));
				} catch (URISyntaxException e1) {

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			close.addActionListener(a -> {
				this.setEnabled(true);
				aboutFrame.dispose();

			});

			aboutPanel.add(aboutText);
			aboutPanel.add(linkedin);
			aboutPanel.add(close);
			aboutFrame.setResizable(false);
			aboutFrame.setLocation(800, 400);
			aboutFrame.setSize(200, 270);
			aboutFrame.setTitle("Info");
			aboutFrame.add(aboutPanel);
			aboutFrame.setVisible(true);
			aboutFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		});

	}

	// draws the current state of array.
	class drawPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		ArrayList<Integer> list;
		int x = 40, width = 5;

		drawPanel(ArrayList<Integer> list) {
			this.list = list;

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			x = 50;
			width = 800 / list.size();

			for (int i = 0; i < list.size(); i++) {
				g.setColor(Color.GRAY);
				g.fillRect(x, MaxSize - list.get(i), width, list.get(i));
				x += width + 1;

			}

		}
	}

	// Update sorting if changed.
	public void UpdateThread() {
		if (runFlag == 1) {
			Util.exit = true;
			runFlag = 0;
		}

		switch (SelectedSort) {

		case "BubbleSort": {

			sortingMethod = new Thread(new BubbleSort(list, centerPanel, speed));

			BestCaseSet.setText(" O(n)");
			WorstCaseSet.setText(" O(n^2)");
			AvgCaseSet.setText(" O(n^2)");
			break;
		}
		case "HeapSort": {
			sortingMethod = new Thread(new HeapSort(list, centerPanel, speed));
			BestCaseSet.setText(" O(nlogn)");
			WorstCaseSet.setText(" O(nlogn)");
			AvgCaseSet.setText(" O(nlogn)");
			break;

		}
		case "QuickSort": {
			sortingMethod = new Thread(new QuickSort(list, centerPanel, speed));
			BestCaseSet.setText(" O(nlogn)");
			WorstCaseSet.setText(" O(n^2)");
			AvgCaseSet.setText(" O(nlogn)");
			break;

		}
		case "MergeSort": {
			sortingMethod = new Thread(new MergeSort(list, centerPanel, speed));
			BestCaseSet.setText(" O(nlogn)");
			WorstCaseSet.setText(" O(nlogn)");
			AvgCaseSet.setText(" O(nlogn)");
			break;

		}
		case "InsertionSort": {
			sortingMethod = new Thread(new InsertionSort(list, centerPanel, speed));
			BestCaseSet.setText(" O(n)");
			WorstCaseSet.setText(" O(n^2)");
			AvgCaseSet.setText(" O(n^2)");
			break;

		}
		case "CountSort": {
			sortingMethod = new Thread(new CountSort(list, centerPanel, speed));
			BestCaseSet.setText(" O(n+k) k Max");
			WorstCaseSet.setText(" O(n+k)");
			AvgCaseSet.setText(" O(n+k)");
			break;

		}
		case "SelectionSort": {
			sortingMethod = new Thread(new SelectionSort(list, centerPanel, speed));
			BestCaseSet.setText(" O(n^2)");
			WorstCaseSet.setText(" O(n^2)");
			AvgCaseSet.setText(" O(n^2)");
			break;
		}
		}
	}

	// INIT NEW APP
	public static void main(String[] args) {
		CustomLayout mylayout = new CustomLayout("SortingVisual");

		mylayout.setVisible(true);
	}

}

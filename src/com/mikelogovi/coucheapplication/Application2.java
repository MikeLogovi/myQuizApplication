package com.mikelogovi.coucheapplication;
import java.awt.BorderLayout;
import com.mikelogovi.couchemetier.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mikelogovi.coucheapplication.Constante;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.Timer;
public class Application2 extends JWindow{
	    private Musique musique = new Musique(); 
        JProgressBar jpgb = new JProgressBar();
        private final String TITRE="QUIZ-APPLICATION";
        int increment=0;
        JOptionPane jp = new JOptionPane(); 
        Panel panel = new Panel(); 
        JFrame loadindPlayerWindow= new JFrame();
        JFrame gamePresentation = new JFrame();
        JFrame choosePresentation = new JFrame();
        JFrame quizInterface = new JFrame();
        JTextField utilisateur = new JTextField("",10);
        JPasswordField motpass = new JPasswordField();
        private Partie partie = new Partie();
        private PartieManager partieManager = new PartieManager(); 
        private Joueur joueur = new Joueur();
        private JoueurManager joueurManager= new JoueurManager();
        private Quiz quiz = new Quiz();
        private Gaming[] game= {Gaming.NOUVELLE_PARTIE,Gaming.CONTINUER_PARTIE,Gaming.CLASSEMENTS,Gaming.STATISTIQUES,Gaming.QUITTER};
        public Application2() {
        	this.setSize(361,267);
        	this.setLocationRelativeTo(null);
        	this.add(createPanel(),BorderLayout.CENTER);
        	jpgb.setStringPainted(true);
        	jpgb.setForeground(new Color(220,0,0));
        
        	this.add(jpgb,BorderLayout.SOUTH);
        	this.setVisible(true);
        	
        	//Event
        	Timer timer = new Timer(100,this::progress);
        	timer.start();
        }
        
        private JPanel createPanel() {
                
        	return panel;
        }
        private void progress(ActionEvent event) {
             increment++;
             jpgb.setValue(increment);
             if(increment==100) {
            	 ((Timer)event.getSource()).stop();
            	 dispose();
            	 int rep = jp.showConfirmDialog(null,"NOUVEAU JOUEUR?",TITRE,JOptionPane.YES_NO_CANCEL_OPTION);
                 if(rep==jp.NO_OPTION) {
                	 
                	 createLoadindPlayerWindow();
                 }
                 else if(rep==jp.YES_OPTION) {
				       createPlayer(); 
				   	}
				
              }
             

        }

		private void createLoadindPlayerWindow() {
			customizeFrame(loadindPlayerWindow,"Connexion au Quiz-Game",530,350,FrameClosing.NOTHING);
			
			JPanel panelContainer = panelContainer();
			JPanel pan = new JPanel();
			JPanel pan2 = new JPanel();
			JPanel pan3 = new JPanel();
			JPanel pan4 = new JPanel();
			JPanel pan5 = new JPanel();
			JButton btnOK = new JButton("OK");
			JButton btnQuitter = new JButton("QUITTER");
			pan2.setLayout(new BorderLayout());
			JLabel username = customizeJLabel("Nom d'utilisateur",null,null);
			JLabel password = customizeJLabel("Mot de passe",null,null);
			JLabel label3 = customizeJLabel("Connectez Vous",new Font("Tahoma",Font.BOLD,25),new Color(255,255,0));
			JLabel lab = customizeJLabel("QUIZ - APPLICATION",new Font("Verdane",Font.ROMAN_BASELINE,48),null);
			JLabel lab2 = new JLabel(new ImageIcon("src/com/mikelogovi/images/user.png"));
			pan.setLayout(new GridLayout(1,1));
			pan.setBackground(new Color(255,97,19,100));
			pan.add(lab);
			
			pan4.setBackground(new Color(255,97,19));
			pan3.setLayout(new GridLayout(5,1,10,10));
			pan3.add(username);
			
			pan3.add(utilisateur);
			pan3.add(password);
			
			pan3.add(motpass);
			pan3.add(pan5);
			pan5.setBackground(new Color(255,97,19));
			pan3.setBackground(new Color(255,97,19));
			
			pan2.add(label3,BorderLayout.NORTH);
			pan2.add(pan3,BorderLayout.CENTER);
			pan5.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
			pan5.add(btnOK);
			pan5.add(btnQuitter);
			//pan2.add(pan5,BorderLayout.SOUTH);
			pan2.setBackground(new Color(255,97,19));
			panelContainer.setBackground(new Color(255,97,19));
			//pan.setPreferredSize(new Dimension(500,50));
			panelContainer.add(pan,BorderLayout.NORTH);
			panelContainer.add(lab2,BorderLayout.WEST);
			panelContainer.add(pan2,BorderLayout.CENTER);
			panelContainer.add(pan4,BorderLayout.EAST);
			loadindPlayerWindow.add(panelContainer);
			loadindPlayerWindow.setVisible(true);
			//EVENT
			
			
			/*.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount()==2) {
						loadindPlayerWindow.dispose();
					}
				}
			});*/
			btnQuitter.addActionListener((e) ->loadindPlayerWindow.dispose());
			loadindPlayerWindow.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent arg0) {
					int rep=jp.showConfirmDialog(loadindPlayerWindow, "Etes vous sur de quitter?", "Quitter", jp.YES_NO_OPTION);
				    if(rep==jp.YES_OPTION) {
				    	loadindPlayerWindow.dispose();
				    }
				}
			});
			btnOK.addActionListener(this::verifyUser);
			}
		private void createPlayer() {
			  String nomJoueur=jp.showInputDialog("Entrez votre nom d'utilisateur");
		      String passJoueur = null;
		      JPasswordField pf = new JPasswordField();
		      String confPassJoueur = null;
			  do {
		    	    int rep1=jp.showConfirmDialog(null,pf,"Entrez votre mot de passe",JOptionPane.OK_CANCEL_OPTION);
		    	    if(rep1==JOptionPane.OK_OPTION) {
		    	    	passJoueur=new String(pf.getPassword());
		    	    	
		    	    	int rep2=jp.showConfirmDialog(null,pf,"Confirmer le mot de passe",JOptionPane.OK_CANCEL_OPTION);
			    	    if(rep2==JOptionPane.OK_OPTION) {
			    	    	 confPassJoueur=new String(pf.getPassword());
			    	    }
			    	    else {
			    	    	break;
			    	    }
		    	    }
		    	   
		    	    if(!passJoueur.equals(confPassJoueur)) {
		    	    	jp.showMessageDialog(null, "Votre mot de passe n'a pas ete bien confirme.\nVeuillez reessayer.");
		    	    }
			  }while(!passJoueur.equals(confPassJoueur));
			  joueur.setNom(nomJoueur);
			  joueur.setMotpass(passJoueur);
			  joueurManager.createPlayer(joueur);
			  createGamePresentation();
		}
		
		private void verifyUser(ActionEvent e) {
			joueur.setNom(utilisateur.getText()); 
			joueur.setMotpass(new String(motpass.getPassword()));
			Joueur joueurRecuperer = joueurManager.readPlayer(joueur);
			if(joueurRecuperer!=null) {
				joueurRecuperer.setId(joueurRecuperer.getId());
				joueur.setNom(joueurRecuperer.getNom()); 
				joueur.setMotpass(joueurRecuperer.getMotpass());
				joueur.setPartie(partieManager.readPartie(joueurRecuperer));
				loadindPlayerWindow.dispose();
				createGamePresentation();
			}
			else {
				JOptionPane.showMessageDialog(null, "Identifiant/Mot de passe incorrect"+"\nIDENTIFIANT: "+utilisateur.getText()+"\n MOT DE PASSE: "+new String(motpass.getPassword()));
			}
		}
		
		
		private void createGamePresentation() {
			customizeFrame(gamePresentation,TITRE,600,400,FrameClosing.NOTHING);
			//gamePresentation.setLayout(new BorderLayout());
			JPanel panelContainer = panelContainer() ;
			
			JPanel topPanel = createGameTopPanel("QUIZ-GAME");
			HashMap<Gaming,JButton> bouton = new HashMap<Gaming,JButton>();
			JPanel middlePanel = new JPanel();
			middlePanel.setLayout(new GridLayout(5,1,20,20));
			for(Gaming gamePlay:game) {
				JButton choice = new JButton(gamePlay.toString());
				 middlePanel.add(choice);
				bouton.put(gamePlay,choice);
			}
		    middlePanel.setBackground(new Color(255,200,18));
		    /*
			JLabel topAnim1 = new JLabel(new ImageIcon("src/com/mikelogovi/images/gif.gif"));
			JLabel topAnim2 = new JLabel(new ImageIcon("src/com/mikelogovi/images/gif.gif"));
			JLabel topAnim3 = new JLabel(new ImageIcon("src/com/mikelogovi/images/gif.gif"));
			*/
	
			/*topPanel.add(topAnim1,BorderLayout.WEST);
			topPanel.add(topAnim2,BorderLayout.EAST);*/
			panelContainer.add(topPanel,BorderLayout.NORTH);
			panelContainer.add(middlePanel,BorderLayout.CENTER);
			gamePresentation.add(panelContainer);
			gamePresentation.setVisible(true);
			//EVENEMENTS
			for(JButton button:bouton.values()) {
				button.setFont(new Font("Verdana",Font.BOLD,20));
				button.setBackground(new Color(255,255,255));
				button.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseEntered(MouseEvent arg0) {
						button.setBackground(new Color(0,255,0));
						button.setForeground(new Color(255,255,255));
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						 button.setBackground(new Color(255,255,255));
						 button.setForeground(new Color(0,0,0));
						 
					}

				
				});
			}
			gamePresentation.addWindowListener(new WindowAdapter() {
				@SuppressWarnings("deprecation")
				@Override
				public void windowClosing(WindowEvent arg0) {
					 int rep=jp.showConfirmDialog(gamePresentation,"Etes vous sur de quitter?","Quitter le jeu",JOptionPane.YES_NO_OPTION);
					  if(rep==JOptionPane.YES_OPTION) {
						  gamePresentation.dispose();
						  musique.interrupt();
					  }
				}	
			});
			bouton.get(Gaming.NOUVELLE_PARTIE).addActionListener(this::choosePartie);
			bouton.get(Gaming.QUITTER).addActionListener((event)->gamePresentation.dispose());
			//THREAD
			musique.start();			
		}
		private void choosePartie(ActionEvent e) {
			customizeFrame(choosePresentation,"Mode de jeu",500,250,FrameClosing.DISPOSE);
		    JPanel panelContainer = panelContainer() ;
		    JPanel topPanel=createGameTopPanel("QUIZ-MODE");
		    panelContainer.add(topPanel,BorderLayout.NORTH);
		    JPanel  middlePanel = panelContainer();
		    JPanel selectQuiz = new JPanel();
		    selectQuiz.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		    JLabel quizType = customizeJLabel("Type Quiz",new Font("Arial",Font.TRUETYPE_FONT,20),new Color(0,0,0));
		    selectQuiz.add(quizType);
		    JComboBox<String> box= new JComboBox<String>();
		    box.setBackground(Color.WHITE);
		    box.addItem("MATH");
		    box.addItem("CULTURE GENERAL");
		    box.addItem("PAYS CAPITAL");
		    selectQuiz.add(box);
		    quiz.setType(TypeQuiz.MATH);
		    partie.setQuiz(quiz);
		    box.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent event) {
					if(event.getStateChange()==ItemEvent.SELECTED) {
						switch((String)event.getItem()) {
						 case "MATH":quiz.setType(TypeQuiz.MATH);
						      break;
						 case "CULTURE GENERAL":quiz.setType(TypeQuiz.CULTURE_GENERAL);
						      break;
						 case "PAYS CAPITAL":quiz.setType(TypeQuiz.PAYS_CAPITAL);
						}
						partie.setQuiz(quiz);
					}
					
				}	
		    });
		    middlePanel.add(selectQuiz,BorderLayout.NORTH);
		    JPanel selectPartie = panelContainer();
		    JPanel modeJeu =new JPanel();
		    modeJeu.setLayout(new FlowLayout(FlowLayout.CENTER));
		    JLabel modeJeuLabel =customizeJLabel("MODE DE JEU",new Font("Arial",Font.BOLD,28),new Color(255,0,0));
		    modeJeu.add(modeJeuLabel);
		    selectPartie.add(modeJeu,BorderLayout.NORTH);
		    JPanel modeJeuButton =new JPanel();
		    modeJeuButton.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		    selectPartie.add(modeJeuButton,BorderLayout.CENTER);
		    middlePanel.add(selectPartie,BorderLayout.SOUTH);
		    panelContainer.add(middlePanel,BorderLayout.CENTER);
		    JPanel[] mdPan = {selectPartie,selectQuiz,modeJeu,modeJeuButton};
		    for(JPanel pan:mdPan ) {
		    	pan.setBackground(new Color(255,200,18));
		    }
		    choosePresentation.add(panelContainer);
		    TypePartie[] nomBouton = {TypePartie.SOLO,TypePartie.DUEL,TypePartie.EN_EQUIPE};
		    HashMap<TypePartie,JButton>myBtn =new HashMap<TypePartie,JButton>();
		    for(TypePartie nom:nomBouton) {
		    	JButton btn = customizeButton(nom.toString(),new Dimension(100,45),new Font("Verdana",Font.BOLD,10),new Color(253,151,31),Color.WHITE);
		    	myBtn.put(nom, btn);
		    	modeJeuButton.add(btn);
		    	//EVENEMENTS
		    	btn.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent arg0) {
						btn.setBackground(new Color(0,255,0));
				    	btn.setForeground(Color.BLACK);
					}
					@Override
					public void mouseExited(MouseEvent arg0) {
						btn.setBackground(new Color(253,151,31));
				    	btn.setForeground(Color.WHITE);	
					}	
		    	});
		    }
		    myBtn.get(TypePartie.SOLO).addActionListener(this::createSoloQuiz);
		    choosePresentation.setVisible(true);
		}
		private JPanel createGameTopPanel(String title) {
			JPanel topPanel = new JPanel();
			topPanel.setBackground(new Color(253,151,31));
			topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			JLabel topTitle = new JLabel(title);
			topTitle.setFont(new Font("Tahoma",Font.HANGING_BASELINE,48));
			topPanel.add(topTitle);
			return topPanel;
		}
		
		private void createSoloQuiz(ActionEvent e) {
			partie.setTypePartie(TypePartie.SOLO);
			switch(quiz.getType()) {
			case MATH:generateMathQuiz();
			     break;
			case CULTURE_GENERAL:generateGeneralCultureQuiz();
			     break;
			case PAYS_CAPITAL:generatePaysCapitalQuiz();
			    break;
			default:jp.showMessageDialog(choosePresentation, "Veillez choisir un mode de jeu");
			}
		}
		private void  generateMathQuiz() {
			customizeFrame(quizInterface,"Quiz-Math-Solo",600,400,FrameClosing.DISPOSE);
			JMenuBar menuBar = new JMenuBar();
			JMenu menu = new JMenu("Jeu");
			for(Gaming name:game) {
				JMenuItem item = new JMenuItem(name.toString());
				menu.add(item);
			}
			menuBar.add(menu);
			quizInterface.setJMenuBar(menuBar);
			JPanel panelContainer=panelContainer();
			JPanel topPanel =createGameTopPanel("QUIZ - MATH");
			panelContainer.add(topPanel,BorderLayout.NORTH);
			JPanel  middlePanel = panelContainer();
			JPanel mainPanel = panelContainer();
			JPanel timerPanel = new JPanel();
			timerPanel.setLayout(new GridLayout(2,1,5,5));
			timerPanel.setBackground(new Color(255,200,18));
			JPanel panCounterName = new JPanel();
			panCounterName.setLayout(new FlowLayout(FlowLayout.CENTER));
			JLabel counterName = customizeJLabel("Temps restant",new Font("URW Bookman L",Font.BOLD,20),new Color(255,255,0));
			JLabel counter = customizeJLabel("10s",new Font("URW Bookman L",Font.BOLD,15),new Color(255,0,0));
			JPanel panCounter = new JPanel();
			panCounterName.add(counterName);
			panCounterName.setBackground(new Color(255,200,18));
			panCounterName.add(counterName);
			panCounter.setLayout(new FlowLayout(FlowLayout.CENTER));
			panCounter.setBackground(new Color(255,200,18));
			panCounter.add(counter);
			timerPanel.add(panCounterName);
			timerPanel.add(panCounter);
			JPanel mainPanelNorth = new JPanel();
			JPanel mainPanelSouth = new JPanel();
			JPanel mainPanelCenter = new JPanel();
			mainPanelCenter.setBackground(Color.WHITE);
			
			JLabel question =customizeJLabel("Qui est le fils d'Hitler?\nA-Mike\nB-Kodjo\n C-AFI",new Font("Garuda",Font.BOLD,15),Color.BLUE);
			mainPanelCenter.add(question);
			//JScrollPane jscrp =new JScrollPane(mainPanelCenter);
			JLabel nomQuestion = customizeJLabel("Question",new Font("Unbuntu",Font.BOLD,24),Color.BLUE);
			JLabel numQuestion = customizeJLabel("1",new Font("Unbuntu",Font.BOLD,24),Color.BLUE);
			mainPanelNorth.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
			mainPanelNorth.add(nomQuestion);
			mainPanelNorth.add(numQuestion);
			mainPanelNorth.setBackground(Color.WHITE);
			mainPanelSouth.setLayout(new GridLayout(1,2));
			JTextField textField = new JTextField();
			JButton valider = customizeButton("Valider",new Dimension(100,35),new Font("Verdana",Font.BOLD,10),new Color(253,151,31),Color.white);
			textField.setPreferredSize(new Dimension(250,35));
			textField.setBackground(new Color(255,250,250));
			mainPanelSouth.add(textField);
			mainPanelSouth.add(valider);
			mainPanel.setBackground(Color.WHITE);
			mainPanel.add(mainPanelNorth,BorderLayout.NORTH);
			mainPanel.add(mainPanelCenter,BorderLayout.CENTER);
			mainPanel.add(mainPanelSouth,BorderLayout.SOUTH);
			middlePanel.add(timerPanel,BorderLayout.WEST);
			middlePanel.add(mainPanel,BorderLayout.CENTER);
			JPanel scorePanel = new JPanel();
			scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
			scorePanel.setBackground(new Color(255,200,18));
			JLabel scoreName = customizeJLabel("Score: ",new Font("URW Bookman L",Font.BOLD,30),new Color(0,255,0));
			JLabel score = customizeJLabel("0",new Font("URW Bookman L",Font.BOLD,30),new Color(128,255,128));
			scorePanel.add(scoreName);
			scorePanel.add(score);
			panelContainer.add(middlePanel,BorderLayout.CENTER);
			panelContainer.add(scorePanel,BorderLayout.SOUTH);
			
			JSplitPane split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,timerPanel,mainPanel);
			JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,split1,scorePanel);
			//panelContainer.add(split1);
			panelContainer.add(split2);
			quizInterface.add(panelContainer);
			quizInterface.setVisible(true);
		}
		private void generateGeneralCultureQuiz() {
			
		}
		private void generatePaysCapitalQuiz() {
			
		}		
		private void customizeFrame(JFrame frame,String title,int x,int y,FrameClosing closing ) {
			frame.setTitle(title);
			frame.setSize(x,y);
			switch(closing) {
			case DISPOSE:frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			     break;
			case EXIT:frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  	     break;
			case NOTHING:frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		    frame.setLocationRelativeTo(null);
		    frame.setResizable(false);
		}
		private JLabel customizeJLabel(String title,Font font,Color color) {
			JLabel label = new JLabel(title);
			label.setFont(font);
			label.setForeground(color);
			return label;
		}
		private JButton customizeButton(String title,Dimension dimension,Font font,Color background,Color foreground) {
			JButton bouton = new JButton(title);
			bouton.setPreferredSize(dimension);
			bouton.setFont(font);
			bouton.setBackground(background);
			bouton.setForeground(foreground);
			return bouton;
		}
		private JPanel panelContainer() {
			JPanel panelContainer = new JPanel();
			panelContainer.setLayout(new BorderLayout());
			return panelContainer;
		}	
		
		private JPanel customizePanel(Object layout,Color background) {
			JPanel panel = new JPanel();
			switch(layout.getClass()+"") {
			  case "java.awt.BorderLayout":panel.setLayout(new BorderLayout());
			       break;
			  case "java.awt.FlowLayout":panel.setLayout(new FlowLayout());
			       break;
			  case "java.awt.GridLayout":panel.setLayout(new GridLayout());
			       break;
			}
			panel.setBackground(background);
			return panel;
		}
}

package com.mikelogovi.coucheapplication;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;

import com.mikelogovi.couchemetier.Joueur;
import com.mikelogovi.couchemetier.JoueurManager;
import com.mikelogovi.couchemetier.Partie;
import com.mikelogovi.couchemetier.PartieManager;
import com.mikelogovi.couchemetier.Quiz;
import com.mikelogovi.couchemetier.QuizManager;
import com.mikelogovi.couchemetier.TypePartie;
import com.mikelogovi.couchemetier.TypeQuiz;
public class Application extends JWindow{
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
        JFrame startOrNot = new JFrame();
        JFrame afterGaming = new JFrame();
        JTextField utilisateur = new JTextField("",10);
        JPasswordField motpass = new JPasswordField();
        

        private Partie partie = new Partie();
        private PartieManager partieManager = new PartieManager(); 
        private Joueur joueur = new Joueur();
        private JoueurManager joueurManager= new JoueurManager();
        private Quiz quiz = new Quiz();
        private QuizManager quizManager = new QuizManager();
        private Gaming[] game= {Gaming.NOUVELLE_PARTIE,Gaming.CONTINUER_PARTIE,Gaming.CLASSEMENTS,Gaming.STATISTIQUES,Gaming.QUITTER};
        private AfterGaming[] afterGamingButtons= {AfterGaming.REJOUER,AfterGaming.MENU_PRINCIPAL,AfterGaming.QUITTER};
        private HashMap<String,String>questionnaire=new HashMap<String,String>();
        private Random rd = new Random();
        private int scoreGame=0;
        private String stockQuestion;
        private int verScore=-1;
        private int timeCount;
        private long timeStartGame;
        public Application() {
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
			
			JPanel panelContainer = customizeJPanel(new BorderLayout(),new Color(255,97,19));
			JPanel pan = customizeJPanel(new GridLayout(1,1),new Color(255,97,19,100));
			JPanel pan2 = customizeJPanel(new BorderLayout(),new Color(255,97,19));
			JPanel pan3 = customizeJPanel(new GridLayout(5,1,10,10),new Color(255,97,19));
			JPanel pan4 = customizeJPanel(new BorderLayout(),new Color(255,97,19));
			JPanel pan5 = customizeJPanel(new FlowLayout(FlowLayout.CENTER,10,10),new Color(255,97,19));
			JButton btnOK = new JButton("OK");
			JButton btnQuitter = new JButton("QUITTER");
			JLabel username = customizeJLabel("Nom d'utilisateur",null,null);
			JLabel password = customizeJLabel("Mot de passe",null,null);
			JLabel label3 = customizeJLabel("Connectez Vous",new Font("Tahoma",Font.BOLD,25),new Color(255,255,0));
			JLabel lab = customizeJLabel("QUIZ - APPLICATION",new Font("Verdane",Font.ROMAN_BASELINE,48),null);
			JLabel lab2 = new JLabel(new ImageIcon("src/com/mikelogovi/images/user.png"));
			pan.add(lab);
			pan3.add(username);
			pan3.add(utilisateur);
			pan3.add(password);
			pan3.add(motpass);
			pan3.add(pan5);
			pan2.add(label3,BorderLayout.NORTH);
			pan2.add(pan3,BorderLayout.CENTER);
			pan5.add(btnOK);
			pan5.add(btnQuitter);
			//pan2.add(pan5,BorderLayout.SOUTH);
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
			JPanel panelContainer =  customizeJPanel(new BorderLayout(),null); 
            JPanel topPanel = createGameTopPanel("QUIZ-GAME");
			HashMap<Gaming,JButton> bouton = new HashMap<Gaming,JButton>();
			JPanel middlePanel =  customizeJPanel(new GridLayout(5,1,20,20),new Color(255,200,18));
			for(Gaming gamePlay:game) {
				JButton choice = new JButton(gamePlay.toString());
				choice.setFont(new Font("Verdana",Font.BOLD,20));
				choice.setBackground(new Color(255,255,255));
				choice.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseEntered(MouseEvent arg0) {
						choice.setBackground(new Color(0,255,0));
						choice.setForeground(new Color(255,255,255));
					}
					@Override
					public void mouseExited(MouseEvent arg0) {
						 choice.setBackground(new Color(255,255,255));
						 choice.setForeground(new Color(0,0,0));			 
					}
				});
				middlePanel.add(choice);
				bouton.put(gamePlay,choice);
			}
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
		    JPanel panelContainer = customizeJPanel(new BorderLayout(),null);
		    JPanel topPanel=createGameTopPanel("QUIZ-MODE");
		    panelContainer.add(topPanel,BorderLayout.NORTH);
		    JPanel  middlePanel = customizeJPanel(new BorderLayout(),null);
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
		    JPanel selectPartie = customizeJPanel(new BorderLayout(),null);
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
		    myBtn.get(TypePartie.SOLO).addActionListener((event)->createSoloQuiz(choosePresentation));
		    choosePresentation.setVisible(true);
		}
		private JPanel createGameTopPanel(String title) {
			JPanel topPanel = customizeJPanel(new FlowLayout(FlowLayout.CENTER),new Color(253,151,31)); 
			JLabel topTitle = new JLabel(title);
			topTitle.setFont(new Font("Tahoma",Font.HANGING_BASELINE,48));
			topPanel.add(topTitle);
			return topPanel;
		}
		
		private void createSoloQuiz(JFrame frame) {
			frame.dispose();
            gamePresentation.dispose();
			partie.setTypePartie(TypePartie.SOLO);
			String title="Quiz-"+quiz.getType().toString()+"-"+partie.getTypePartie().toString();
			doYouWantToStartGame(title);
		}
		private void  generateMathQuiz(String title) {
		    questionnaire=quizManager.readQuestionnaire(TypeQuiz.MATH);
		    generateGameProperInterface(title);
		}
		private void generateGeneralCultureQuiz(String title) {
			questionnaire=quizManager.readQuestionnaire(TypeQuiz.CULTURE_GENERAL);
			generateGameProperInterface(title);
		}
		private void generatePaysCapitalQuiz(String title) {
			questionnaire=quizManager.readQuestionnaire(TypeQuiz.PAYS_CAPITAL);
			generateGameProperInterface(title);
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
		private JPanel customizeJPanel(BorderLayout layout,Color background) {
			return setPanelCustomization( layout, background);
		}
		private JPanel customizeJPanel(FlowLayout layout,Color background) {		
			return setPanelCustomization( layout, background);
		}
		private JPanel customizeJPanel(GridLayout layout,Color background) {	
			return setPanelCustomization( layout, background);
		}
		private JPanel setPanelCustomization(Object layout,Color background) {
			JPanel panel = new JPanel();
			switch(layout.getClass()+"") {
			  case "class java.awt.BorderLayout":panel.setLayout((BorderLayout)layout);
			       break;
			  case "class java.awt.FlowLayout":panel.setLayout((FlowLayout)layout);
                   break;
			  case "class java.awt.GridLayout":panel.setLayout((GridLayout)layout);
			}
			panel.setBackground(background);	
			return panel;
		}
        private void doYouWantToStartGame(String title) {
        	customizeFrame(startOrNot,title,600,400,FrameClosing.DISPOSE);
			JMenuBar menuBar = new JMenuBar();
			JMenu menu = new JMenu("Jeu");
			for(Gaming name:game) {
				JMenuItem item = new JMenuItem(name.toString());
				menu.add(item);
			}
			menuBar.add(menu);
			startOrNot.setJMenuBar(menuBar);
			JPanel panelContainer=customizeJPanel(new BorderLayout(),null);
			JPanel topPanel =createGameTopPanel("QUIZ - "+quiz.getType());
			panelContainer.add(topPanel,BorderLayout.NORTH);
			JPanel  middlePanel = customizeJPanel(new GridLayout(3,1,50,50),Color.white);
			JButton btn = customizeButton("COMMENCER",new Dimension(200,50),new Font("Verdana",Font.BOLD,15),new Color(0,255,0),Color.white);
			JPanel btnPanel =customizeJPanel(new FlowLayout(FlowLayout.CENTER),Color.white);
			JLabel intro = customizeJLabel("Appuyez sur ce bouton pour commencer!",new Font("Unbuntu",Font.BOLD,25),new Color(255,255,0));
			btnPanel.add(btn);
			middlePanel.add(intro);
			middlePanel.add(btnPanel);
			panelContainer.add(middlePanel);
			startOrNot.add(panelContainer);
			startOrNot.setVisible(true);
            
			//EVENEMENTS
			
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					startOrNot.dispose();
					switch(quiz.getType()) {
					case MATH:generateMathQuiz(title);
					     break;
					case CULTURE_GENERAL:generateGeneralCultureQuiz(title);
					     break;
					case PAYS_CAPITAL:generatePaysCapitalQuiz(title);
					    break;
					default:jp.showMessageDialog(choosePresentation, "Veillez choisir un mode de jeu");
					}
				}
				
			});
        }
		private void generateGameProperInterface(String title) {
			customizeFrame(quizInterface,title,600,400,FrameClosing.DISPOSE);
			JMenuBar menuBar = new JMenuBar();
			JMenu menu = new JMenu("Jeu");
			for(Gaming name:game) {
				JMenuItem item = new JMenuItem(name.toString());
				menu.add(item);
			}
			menuBar.add(menu);
			quizInterface.setJMenuBar(menuBar);
			JPanel panelContainer=customizeJPanel(new BorderLayout(),null);
			JPanel topPanel =createGameTopPanel("QUIZ - "+quiz.getType().toString());
			panelContainer.add(topPanel,BorderLayout.NORTH);
			JPanel  middlePanel = customizeJPanel(new BorderLayout(),null);
			JPanel mainPanel = customizeJPanel(new BorderLayout(),Color.WHITE);
			JPanel timerPanel = customizeJPanel(new BorderLayout(),new Color(255,200,18));
			JPanel panCounterName = customizeJPanel(new FlowLayout(FlowLayout.CENTER),new Color(255,200,18));
			JLabel counterName = customizeJLabel("Temps restant",new Font("Arial",Font.BOLD,20),new Color(255,255,255));
			JLabel counter = customizeJLabel("10s",new Font("Verdana",Font.BOLD,15),new Color(255,0,0));
			JPanel panCounter = customizeJPanel(new FlowLayout(FlowLayout.CENTER),new Color(255,200,18));
			panCounterName.add(counterName);
			panCounterName.add(counterName);
			panCounter.add(counter);
			timerPanel.add(panCounterName,BorderLayout.NORTH);
			timerPanel.add(panCounter,BorderLayout.CENTER);
			JPanel mainPanelNorth = customizeJPanel(new FlowLayout(FlowLayout.CENTER,10,10),Color.WHITE);
			JPanel mainPanelSouth =customizeJPanel(new GridLayout(1,2),null);
			JPanel mainPanelCenter = new JPanel();
			mainPanelCenter.setBackground(Color.WHITE);
			 JTextArea question =new JTextArea("");
				JLabel numQuestion = customizeJLabel("",new Font("Unbuntu",Font.BOLD,24),Color.BLUE);
				JLabel nomQuestion = customizeJLabel("",new Font("Unbuntu",Font.BOLD,24),Color.BLUE);
			question.setBorder(null);
	        question.setFont(new Font("Arial",Font.BOLD,15));
			question.setEditable(false);
			mainPanelCenter.add(question);
			//JScrollPane jscrp =new JScrollPane(mainPanelCenter);
			
			mainPanelNorth.add(nomQuestion);
			mainPanelNorth.add(numQuestion);
			JTextField textField = new JTextField();
			JButton valider = customizeButton("Valider",new Dimension(100,35),new Font("Verdana",Font.BOLD,10),new Color(253,151,31),Color.white);
			textField.setPreferredSize(new Dimension(250,35));
			textField.setBackground(new Color(250,250,250));
			mainPanelSouth.add(textField);
			mainPanelSouth.add(valider);
			mainPanel.add(mainPanelNorth,BorderLayout.NORTH);
			mainPanel.add(mainPanelCenter,BorderLayout.CENTER);
			mainPanel.add(mainPanelSouth,BorderLayout.SOUTH);
			middlePanel.add(timerPanel,BorderLayout.WEST);
			middlePanel.add(mainPanel,BorderLayout.CENTER);
			JPanel scorePanel = customizeJPanel(new FlowLayout(FlowLayout.CENTER,10,10),new Color(255,128,0));
			JLabel scoreName = customizeJLabel("Score: ",new Font("URW Bookman L",Font.BOLD,30),new Color(255,255,0));
			JLabel score = customizeJLabel("0",new Font("URW Bookman L",Font.BOLD,30),new Color(255,255,128));
			scorePanel.add(scoreName);
			scorePanel.add(score);
			panelContainer.add(middlePanel,BorderLayout.CENTER);
			panelContainer.add(scorePanel,BorderLayout.SOUTH);
			quizInterface.add(panelContainer);
			
			//EVENEMENTS
			valider.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					valider.setBackground(new Color(0,255,0));
					valider.setForeground(Color.WHITE);
				}
				public void mouseExited(MouseEvent e) {
					valider.setBackground(new Color(253,151,31));
					valider.setForeground(Color.WHITE);
				}
			});
			ArrayList<String> questions = new ArrayList<String>(questionnaire.keySet());
			
			Timer timer3 =new Timer(1000,new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					timeCount++;
					System.out.println(increment);
					counter.setText((10-timeCount)+" s");
					if(increment>=11) {
						((Timer)e.getSource()).stop();
						 numQuestion.setText("");
						 question.setText("");
						 nomQuestion.setText("");
						 score.setText("0");
					}
				}
            	 
             });				 
			Timer timer = new Timer(10000,new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 if(increment>=10) {
						 ((Timer)e.getSource()).stop();
						 numQuestion.setText("");
						 question.setText("");
						 nomQuestion.setText("");
						 timer3.stop();
						 score.setText("0");
						 afterGame("Le choix");
						 
					 }else {
						 gameLogiq(question, numQuestion,nomQuestion, questions);
						 int aleatoire=questions.size();
						 Application.this.stockQuestion=questions.get(rd.nextInt(aleatoire));
						 question.setText(stockQuestion);
				 		 questions.remove(stockQuestion); 
				 		 nomQuestion.setText("Question");
					 }	
				}
			});
			timer.start();
			timer3.start();
			increment = 1;
			numQuestion.setText(increment+"");
			nomQuestion.setText("Question");
			stockQuestion=questions.get(rd.nextInt(questions.size()));
			question.setText(stockQuestion);
			timeStartGame=System.currentTimeMillis();
			 valider.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(increment >= 10) {
							 numQuestion.setText("");
							 question.setText("");
							 nomQuestion.setText("");
							 timer.stop();
							 timer3.stop();
							 score.setText("0");
						 }
						 if(textField.getText().equalsIgnoreCase(questionnaire.get(stockQuestion))) {
						 		if(verScore!=0 && increment<11) {
						 			scoreGame++;
						 			verScore=0;
							 		score.setText(""+scoreGame);
							 		textField.setText("");	
							 		gameLogiq(question, numQuestion,nomQuestion, questions);
							 		 int aleatoire=questions.size();
							 		 questions.remove(stockQuestion);
									 Application.this.stockQuestion=questions.get(rd.nextInt(aleatoire));
									 question.setText(stockQuestion);
							 		 
							 		 timer.restart();
						 		}
						 		if(increment>=11) {
						 			 numQuestion.setText("");
									 question.setText("");
									 nomQuestion.setText("");
						 			 timer.stop();
									 timer3.stop();
									 score.setText("0");
						 			 afterGame("Le choix");
						 		}
						 }
						 else{
						 		verScore=0;
						 		JOptionPane.showMessageDialog(null, "C'est faux, la reponse est "+questionnaire.get(stockQuestion)+"\nVous avez dit "+textField.getText())  ;
								textField.setText("");
	                            gameLogiq(question, numQuestion, nomQuestion,questions);
	                            int aleatoire=questions.size();
	                            questions.remove(stockQuestion); 
	                            Application.this.stockQuestion=questions.get(rd.nextInt(aleatoire));
								question.setText(stockQuestion);
	                             timer.restart();
	                             if(increment>=11) {
	                            	 numQuestion.setText("");
	    							 question.setText("");
	    							 nomQuestion.setText("");
	                            	 timer.stop();
	    							 timer3.stop();
	    							 score.setText("0"); 
						 			 afterGame("Le choix");
						 		}
						 }
	                }
               });
			 quizInterface.setVisible(true);
		}
		private void gameLogiq(JTextArea question, JLabel numQuestion,JLabel nomQuestion, ArrayList<String> questions) {
			if(increment<11) {
				 timeCount=0; 
				 increment++;
				 verScore=-1;
				 numQuestion.setText(increment+"");
				 nomQuestion.setText("Question");
              }
			else {
				 numQuestion.setText("");
				 question.setText("");
				 nomQuestion.setText("");
				 numQuestion.setText("");
				 
			}
	
			 
		}
		
		private void afterGame() {
			JOptionPane.showMessageDialog(null, "\tPARTIE TERMINEE\n"
			 		                            + "Vous avez terminé la partie dans environ "+(System.currentTimeMillis()-timeStartGame)+" s.\n"
			 		                              +"     Votre score : "+scoreGame+" points");
			 int rep=JOptionPane.showConfirmDialog(null,"Rejouer?","Voulez vous rejouez une partie?",JOptionPane.YES_NO_OPTION);
			 if(rep==JOptionPane.YES_OPTION) {
				 createSoloQuiz(quizInterface);
			 }
			 else {
				rep=JOptionPane.showConfirmDialog(null,"Men Principal?","Retourner au menu principal",JOptionPane.YES_NO_OPTION); 
				if(rep==JOptionPane.YES_OPTION) {
					createGamePresentation();
				 }
				else {
					quizInterface.dispose();
				}
			 }
		}
		private void afterGame(String title) {
			JOptionPane.showMessageDialog(null, "\tPARTIE TERMINEE\n"
                     + "Vous avez terminé la partie dans environ "+(System.currentTimeMillis()-timeStartGame)/1000+" s.\n"
                       +"             Votre score : "+scoreGame+" points");
			customizeFrame(afterGaming,title,300,200,FrameClosing.NOTHING);
			JPanel topPanel = customizeJPanel(new FlowLayout(FlowLayout.CENTER),new Color(253,151,31)); 
	 		JPanel panelContainer=customizeJPanel(new BorderLayout(),null); 
			JLabel topLabel = customizeJLabel("Que voulez vous faire?",new Font("Tahoma",Font.HANGING_BASELINE,20),new Color(0,0,0));
	        topPanel.add(topLabel);
	        JPanel middlePanel = customizeJPanel(new GridLayout(3,1,10,10),new Color(255,200,18));
	        HashMap<AfterGaming,JButton> bouton = new HashMap<AfterGaming,JButton>();
	        for(AfterGaming afterGamingPlay:afterGamingButtons) {
				JButton choice = customizeButton(afterGamingPlay.toString(),null,new Font("Verdana",Font.BOLD,20),new Color(255,255,255),new Color(0,0,0));
				choice.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseEntered(MouseEvent arg0) {
						choice.setBackground(new Color(0,255,0));
						choice.setForeground(new Color(255,255,255));
					}
					@Override
					public void mouseExited(MouseEvent arg0) {
						 choice.setBackground(new Color(255,255,255));
						 choice.setForeground(new Color(0,0,0));			 
					}
				});
				middlePanel.add(choice);
				bouton.put(afterGamingPlay,choice);
			}
	        panelContainer.add(topPanel,BorderLayout.NORTH);
	        panelContainer.add(middlePanel,BorderLayout.CENTER);
	        afterGaming.add(panelContainer); 
	        afterGaming.setVisible(true);
	        //EVENEMENTS
	        bouton.get(AfterGaming.REJOUER).addActionListener((event)->{afterGaming.dispose();quizInterface.dispose();increment=1;scoreGame=0;createSoloQuiz(quizInterface);});
	        bouton.get(AfterGaming.MENU_PRINCIPAL).addActionListener((event)->{afterGaming.dispose();quizInterface.dispose();increment=1;createGamePresentation();});
	        bouton.get(AfterGaming.QUITTER).addActionListener((event)->{afterGaming.dispose();quizInterface.dispose();});
	        	
		}
		

}
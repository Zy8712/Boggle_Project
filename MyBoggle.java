//Header

/* File Name: MyBoggle
 Author: [Removed for Privacy Reasons]
 Date: Nov 17, 2017
 */

//Import libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class MyBoggle extends JFrame implements ActionListener //Class definition. Implement ActionListener.
{
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//GLOBAL VARIABLES. GLOBAL VARIABLES. GLOBAL VARIABLES. GLOBAL VARIABLES. GLOBAL VARIABLES. GLOBAL VARIABLES.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//Homepage Components.
  JLabel Title = new JLabel ("My Boggle"); //Create a label
  JButton Instructions = new JButton ("How To play"); //Create a button for instuctions
  JButton Player1 = new JButton("1 Player"); //Create a button 1 player mode
  JButton Player2 = new JButton ("2 Players"); //Create a button for 2 player mode
  JButton quitButton = new JButton ("Quit"); //Create a button for exiting program
  JPanel pan = new JPanel(); //Create a panel for title
  JPanel pan3 = new JPanel(); //Create a panel for 1 Player Button, 2 Player Button, How to Play Button
  JPanel qpan = new JPanel(); //Create a panel for Quit Button
  JPanel ppan = new JPanel(); //Create a panel for different point limit option buttons
  JButton p10 = new JButton ("10 Points"); //Create a button for 10 point limit
  JButton p20 = new JButton ("20 Points"); //Create a button for 20 point limit
  JButton p30 = new JButton ("30 Points"); //Create a button for 30 point limit
  JButton p40 = new JButton ("40 Points"); //Create a button for 40 point limit
  JButton p50 = new JButton ("50 Points"); //Create a button for 50 point limit
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//Instructions Page Components
  JLabel instruct = new JLabel ("<HTML><U>How to Play</U></HTML>"); //Create a label
  JLabel instruct2 = new JLabel ("<html>1) A 5 by 5 grid of letters is randomly generated.<br>2) Users must find words on the board that connect in some way (letters can not be reused)<br>"
                                   + "3) Words must be at least 3 letters long.<br>4) Earn 1 point for every letter identified correctly.<br>5) Each players has 15 seconds per turn.<br>"
                                   + "6) When both players pass twice, there will be an option to randomize the board.<br>7) Earn as much points as possible.<html>"); //Create a label for all the rules
  JButton Back = new JButton("BACK"); //Create a button
  JPanel ipan = new JPanel(); //Create a panel
  JPanel ipan2 = new JPanel(); //Create a panel
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//Gamepage GUI Elements/Variables (Visible Components)
  JLabel[][] letters = new JLabel[5][5]; //Create a 2 dimensional array of labels for letters
  JLabel prompt = new JLabel("Enter a word", JLabel.RIGHT); //Create a label prompting user to enter a word into the textfield
  JTextField entry = new JTextField (20); //Create a textfield for user's word entry
  JButton goButton = new JButton ("Go"); //Create a button to enter word from textfield
  JButton pauseButton = new JButton ("Pause"); //Create a button for pausing game
  JButton exitButton = new JButton ("Exit"); //Create a button for exiting game page
  JButton reshuffle = new JButton ("Re-Shuffle"); //Create a button for re-shuffling grid of letters
  JButton resumeButton = new JButton ("Resume"); //Create a button for resuming game
  JPanel pan1= new JPanel(); //Create a panel for grid of letters
  JPanel pan2= new JPanel(); //Create a panel for textfield, label prompting user, and go button
  JPanel p1Points = new JPanel(); //Declare a panel for points in single player mode
  JPanel p2Points = new JPanel(); //Declare a panel for points in multiplayer mode
  JPanel options = new JPanel(); //Create a panel for pause button, re-shuffle button, and exit button
  JPanel resume = new JPanel(); //Create a panel for resume button
  ArrayList <String> wordlist = new ArrayList <String>(); //Create an arraylist for storing words already entered
  JPanel wordEntered = new JPanel(); //Create a panel for label stating words already entered, and error messages
  JLabel words = new JLabel("Words Already Entered: "); //Create a label stating words already entered this game
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//More Gamepage Variables/GUI Elements (Behind the Scenes Components)
  Random r = new Random(); //Create a randomized variable
  boolean play1=false, play2=false, home=true, ans; //Declare boolean variables
  String userEntry = "a"; //Declare a string variable and initialize
  int num, num2; //Declare integer variables
  int counter=0, icounter=0; //Declare and initialize counter
  int P1_SCORE=0, P2_SCORE=0; //Declare counters for players scores
  int [] randomNum = new int [25]; //Declare an integer array
  String [] grid = new String [25]; //Declare a string array with letters
  String [][] grid2 = new String [5][5]; //Declare a 2 dimensional string array
  boolean turn = true; //Create a boolean variable for switching turns in multiplayer
//Declare and Initialize Two Dimensional String Array (Dices With Preset Letters)  
  String [][] dice = new String[][] //Declare a 2 dimensional string array
  {
//Initialize 2 dimensional string array    
    {"A","A","A","F","R","S"}, //Initialize Row 0
    {"A","A","E","E","E","E"}, //Initialize Row 1
    {"A","A","F","I","R","S"}, //Initialize Row 2
    {"A","D","E","N","N","N"}, //Initialize Row 3
    {"A","E","E","E","E","M"}, //Initialize Row 4
      
    {"A","E","E","G","M","U"}, //Initialize Row 5
    {"A","E","G","M","N","N"}, //Initialize Row 6
    {"A","F","I","R","S","Y"}, //Initialize Row 7
    {"B","J","K","Q","X","Z"}, //Initialize Row 8
    {"C","C","N","S","T","W"}, //Initialize Row 9
      
    {"C","E","I","I","L","T"}, //Initialize Row 10
    {"C","E","I","L","P","T"}, //Initialize Row 11
    {"C","E","I","P","S","T"}, //Initialize Row 12
    {"D","D","L","N","O","R"}, //Initialize Row 13
    {"D","H","H","L","O","R"}, //Initialize Row 14
      
    {"D","H","H","N","O","T"}, //Initialize Row 15
    {"D","H","L","N","O","R"}, //Initialize Row 16
    {"E","I","I","I","T","T"}, //Initialize Row 17
    {"E","M","O","T","T","T"}, //Initialize Row 18
    {"E","N","S","S","S","U"}, //Initialize Row 19
      
    {"F","I","P","R","S","Y"}, //Initialize Row 20
    {"G","O","R","R","V","W"}, //Initialize Row 21
    {"H","I","P","R","R","Y"}, //Initialize Row 22
    {"N","O","O","T","U","W"}, //Initialize Row 23
    {"O","O","O","T","T","U"}, //Initialize Row 24
  };
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Score Conditions Variables
  boolean [] conditions = new boolean [4]; //Declare boolean array for meeting 4 scoring conditions
  boolean p1, p2; //Declare boolean variables for single player or multiplayer
  int pointlimit = 10; //Create integer variable to hold point limit 
  JLabel pointLabel = new JLabel ("Current Point Limit: " +pointlimit); //Create a label showing the current 
  int length=1; //Declare integer value length and initialize
  
//Errors
  JLabel error = new JLabel("Word is too short!"); //Create a label for length of word error message
  JLabel error2 = new JLabel ("Word was not found on board!"); //Create a label for searching grid error message
  JLabel error4 = new JLabel ("Word entered does not exist!"); //Create a label for wordlist check error message
  JLabel error3 = new JLabel ("Word was already entered!"); //Create a label for duplicate entry error message
  
  ArrayList <String> worddt = new ArrayList <String> (); //Declare arraylist for words from textfile
  String [] word = new String [userEntry.length()]; //Declare array for splitting word into individual letters
  int j=0; //Declare and initialize counter
  boolean winYN =false; //Declare boolean variable to overall scoring condition
  int min, max; //Declare integer variables for recursive binary searching through arraylist
  JButton okButton = new JButton ("OK"); //Create a button for when an error pops up
  JPanel okPanel = new JPanel(); //Create a panel for ok button

  
//Keeping Track of Points
  JLabel p11 = new JLabel("<html>Player 1: <br><br><br><br>" + P1_SCORE); //Create a label. Displays a counter for single player.
  JLabel p01 = new JLabel("<html>Player 1: <br><br><br><br>" + P1_SCORE); //Create a label. Displays a counter for multiplayer.
  JLabel p02 = new JLabel("<html>Player 2: <br><br><br><br>" + P2_SCORE); //Create a label. Displays a counter for multiplayer.

//Winning Components  
  JPanel gratu = new JPanel (); //Create a panel for congratulation messages
  JLabel grats = new JLabel("Congratulations, you have won!"); //Create a label for winning message in singleplayer
  JLabel grats1 = new JLabel("Congratulations, Player 1 has won!"); //Create a label for winning message in multiplayer
  JLabel grats2 = new JLabel("Congratulations, Player 2 has won!"); //Create a label for winning message in multiplayer
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//CONSTRUCTOR. CONSTRUCTOR. CONSTRUCTOR. CONSTRUCTOR. CONSTRUCTOR. CONSTRUCTOR. CONSTRUCTOR. CONSTRUCTOR. CONSTRUCTOR.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//Constructor. Has the same name as the class and it runs once (when the program begins). This is where the GUI should be configured. This section adds the components on to the frame.  
  public MyBoggle() throws Exception
  {  
//Reads info from textfile into array. This is done once per game. Reads from file once user(s) enter the game. 
    File ReadmyFile = new File ("wordlist.txt"); //File that data is going to be read from
    Scanner input = new Scanner(ReadmyFile); //Create a Scanner and associate it with the file. Scanner is going to read from a text file
    worddt.add (input.nextLine()); //Read the first line of the file and output it to the console (this is the title)   
// Loop through the contents of the file    
    while (input.hasNext()) //Condition. If there are more lines of data after the previous line.
    {   
      length++; //Add one to counter
      worddt.add (input.nextLine()); //Store the data from the file
    }
    input.close(); //Done reading data, close file.
    
    min = 0; //Set "min" equal to zero
    max = worddt.size()-1; //Set "max" equal to length of arraylist minus one
    
//Settings for Frame/Window    
    setTitle ("My Boggle"); //Set title for the window
    setSize (990 , 985); //Set dimensions of the window
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//PAGES SETUP. PAGES SETUP. PAGES SETUP. PAGES SETUP. PAGES SETUP. PAGES SETUP. PAGES SETUP. PAGES SETUP. PAGES SETUP.5
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    FlowLayout layout3 = new FlowLayout(); //Declare a flowlayout
    pan.setLayout(layout3); //Set panel to flowlayout
    pan3.setLayout(layout3); //Set panel to flowlayout
    Title.setFont(new Font ("Times New Roman", Font.BOLD, 22)); //Set page title's font settings
    Title.setPreferredSize(new Dimension(100,100)); //Set title size
    pan.add(Title); //Add title to panel
    add(pan); //Add panel to window

//Game Mode and Intructions Panel    
    pan3.add(Instructions); //Add button to panel
    Instructions.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    pan3.add(Player1); //Add button to panel
    Player1.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    pan3.add(Player2); //Add button to panel
    Player2.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    add(pan3); //Add panel to window
    
//Point Option Panel    
    ppan.add(p10); //Add button to panel
    p10.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    ppan.add(p20); //Add button to panel
    p20.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    ppan.add(p30); //Add button to panel
    p30.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    ppan.add(p40); //Add button to panel
    p40.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    ppan.add(p50); //Add button to panel
    p50.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    ppan.add(pointLabel); //Add label to panel
    add(ppan); //Add panel to window
    
//Quit Button Panel    
    qpan.add(quitButton); //Add button to panel
    quitButton.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    add(qpan); //Add panel to window
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//Set Up Instructions Page 
    instruct.setFont(new Font ("Times New Roman", Font.BOLD, 36)); //Set page title's font settings
    instruct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); //Underline title
    instruct2.setFont(new Font ("Times New Roman", Font.BOLD, 18)); //Set text's font settings
    ipan.add(Back); //Add button to panel
    Back.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    ipan.add(instruct); //Add label to the panel
    instruct.setPreferredSize(new Dimension(200,200)); //Set title size
    ipan2.add(instruct2); //Add label to the panel
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//Set Up Game Page
    GridLayout layout1 = new GridLayout(5,5); //Declare Gridlayout
    setLayout (layout1); //Set GridLayout to organize the window
    FlowLayout layout2 = new FlowLayout (); //Declare FlowLayout
    pan1.setLayout (layout1); //Set panel 1 to layout 1
    pan2.setLayout (layout2); //Set panel 2 to layout 2
    
//Randomize side of dice    
    for (int p=0; p<25; p ++) //For Loop.
    {
      num = r.nextInt(6)+0; //Set "num" equal to a random number from 0 to 5. Min value is 0. Max value is less than 6.
      grid [p] = dice [p][num]; //Set position in string array equal to letter in position of 2 dimensional string array
    }
    
//Randomize dice's position on the 5 by 5 grid of 25 dice    
    for (int i=0; i<25; i++) //For Loop.
    {
      do 
      {
        ans = true; //Sets variable "ans" equal to true
        num2 = r.nextInt(25)+0; //Set "num2" equal to random number from 0 to 24. Min value is 0. Max value is less than 25.
        if (i>0) //Condition. If i is greater than 0.
        {
          for (int j=0; j<i; j++) //For Loop.
          {
            if (num2 == randomNum [j]) //Condition. If number randomly generated is equal to value previously randomly generated and stored in array.
            {
              ans = false; //Set ans equal to false
            }
          }
        }
      } while (ans == false); //Condition. Loop will repeat as long as ans is equal to false.
      randomNum [i] = num2; //Set position of integer array equal to "num2" which the value was randomly generated  
    }
    
//Set randomly generated grid position and side of dice into a 2 dimensional array. Also set it labels value equal to it.     
    for (int h=0; h<5; h++) //For Loop.
    {
      for (int k=0; k<5; k++) //For Loop.
      {
        grid2[h][k] = grid[randomNum[counter]]; //Sets position of 2 dimensional string array equal to letter of randomly generated position of the string array
        counter++; //Add one to counter variable       
        letters[h][k] = new JLabel ("         "  +grid2[h][k]); //Store value in position of 2 dimensional array of labels      
        letters[h][k].setFont(new Font ("Times New Roman", Font.BOLD, 28)); //Set font settings for label
        pan1.add(letters[h][k]); //Add letter to panel         
      }
    }
    counter =0; //Set counter back to 0
    p1Points.add (p11); //Add label to panel
    p2Points.add (p01); //Add label to panel
    p2Points.add (p02); //Add label to panel
    
    wordEntered.add(words); //Add label to panel
    
//Add Components to Panel 2  
    pan2.add(prompt); //Add label to panel 2
    pan2.add(entry); //Add textfield to panel 2
    pan2.add(goButton); //Add button to panel 2
    goButton.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    
    options.add(reshuffle); //Add button to panel
    reshuffle.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    options.add(pauseButton); //Add button to panel
    pauseButton.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    options.add(exitButton); //Add button to panel
    exitButton.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    
    resume.add(resumeButton); //Add button to panel
    resumeButton.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.
    setVisible(true); //Display GUI on screen
    
//Add Components to Error Panel
    okPanel.add(okButton); //Add button to panel
    okButton.addActionListener(this); //Add an action listener to the button. This lets the program know if the button was pressed.    
  }
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//Action Listener. This method runs when an event occurs. Code in here only runs when a user interacts with a component that has an action listener attached to it.
  public void actionPerformed (ActionEvent event)
  {
    String command = event.getActionCommand(); //Find out the name of the component that was used        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//HOME PAGE BUTTONS. HOME PAGE BUTTONS. HOME PAGE BUTTONS. HOME PAGE BUTTONS. HOME PAGE BUTTONS. HOME PAGE BUTTONS.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//How to Play Button. Switches panels to display instructions to game.    
    if (command.equals ("How To play")) //Condition. If button of labelled was pressed.
    {
      System.out.println ("How to Play Button Pressed"); //Display message in console
      setSize(1000,980); //Set window size
      remove(pan); //Remove panel from window
      remove(pan3); //Remove panel from window
      remove(ppan); //Remove panel from window
      remove(qpan); //Remoce panel from window
      add(ipan); //Add panel to window
      add(ipan2); //Add panel to window
    }
    
//1 Player Mode Button. Switches to game page set up for one player.    
    else if (command.equals ("1 Player")) //Condition. If button of labelled was pressed.
    {
      System.out.println ("1 Player selected"); //Display message in console  
      p1 =true; //Sets single player boolean variable to true
      p2 = false; //Sets multi player boolean variable to false
      setSize(1000,980); //Set window size
      
      remove (pan); //Remove panel from window
      remove(pan3); //Remove panel from window
      remove(ppan); //Remove panel from window
      remove(qpan); //Remove panel from window
      
      add(pan1); //Add panel to window
      add(pan2); //Add panel to window
      add(wordEntered); //Add panel to window
      add(p1Points); //Add panel to window
      options.remove(exitButton); //Remove button from panel
      options.add(reshuffle); //Add button to panel
      options.add(pauseButton); //Add button to panel
      options.add(exitButton); //Add button to panel
      add(options); //Add panel to window
      
      pan1.setVisible (true); //Make panel visible
      pan2.setVisible (true);  //Make panel visible 
      wordEntered.setVisible (true);  //Make panel visible 
      p1Points.setVisible (true);  //Make panel visible
      options.setVisible (true);  //Make panel visible      
    }
    
//2 Player Mode Button. Switchs to game page set up for two players.    
    else if (command.equals ("2 Players")) //Condition. If button of labelled was pressed.
    {
      System.out.println ("2 Players selected"); //Display message in console     
      p2 =true; //Sets multi player boolean variable to true
      p1 =false; //Sets single player boolean variable to false
      setSize(1000,980); //Set window size
      
      remove (pan); //Remove panel from window
      remove(pan3); //Remove panel from window
      remove (ppan); //Remove panel from window
      remove (qpan); //Remove panel from window
      
      add(pan1); //Add panel to window
      add(pan2); //Add panel to window 
      add(wordEntered); //Add panel to window
      add(p2Points); //Add panel to window
      options.remove(exitButton); //Remove button from panel
      options.add(reshuffle); //Add button to panel
      options.add(pauseButton); //Add button to panel
      options.add(exitButton); //Add button to panel
      add(options); //Add panel to window
      
      pan1.setVisible (true);  //Make panel visible
      pan2.setVisible (true);  //Make panel visible
      wordEntered.setVisible (true);  //Make panel visible
      p2Points.setVisible (true);  //Make panel visible
      options.setVisible (true);  //Make panel visible      
    }
    
//Quit Button. Closes Boggle Window.    
    else if (command.equals("Quit")) //Condition. If the button was pressed.
    {
      dispose(); //Closes window
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//INSTRUCTIONS PAGE BUTTON. INSTRUCTIONS PAGE BUTTON. INSTRUCTIONS PAGE BUTTON. INSTRUCTIONS PAGE BUTTON. 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//Back Button. Leaves instructions page, goes back to home page. 
    else if (command.equals ("BACK")) //Condition. If button of labelled was pressed.
    {
      setSize (990 , 985); //Set window size
      System.out.println ("Back button pressed"); //Display message in console  
      
      remove(ipan); //Remove panel from window
      remove(ipan2); //Remove panel from window
      
      add(pan); //Add panel to window
      add(pan3); //Add panel to window   
      add(ppan); //Add panel to window
      add(qpan); //Add panel to window
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//GAME PAGE BUTTONS. GAME PAGE BUTTONS. GAME PAGE BUTTONS. GAME PAGE BUTTONS. GAME PAGE BUTTONS. GAME PAGE BUTTONS. 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
//Go Button. Takes user's entry in textfield.    
    else if (command.equals("Go")) //Condition. If the button was pressed.
    {
      System.out.println ("Go Button Pressed"); //Display message in console (for testing).     
      userEntry = entry.getText(); //Set "userEntry" equal to info from textfield      
      System.out.println (userEntry); //Output info from textfield in the console (for testing).
      
      if (p1 == false && p2 == true) //Condition. If multiplayer was selected
      {
        turn = !turn; //Switches boolean variable to opposite value
      }
      
//Send to subprogram for checking of score conditions. Determine overall whether player receives the points.     
      winCon (conditions, userEntry, word, grid2, j, worddt, min, max, wordlist, error, error2, error3, error4, words, wordEntered); //Send variables to subprogram  
      if (conditions[0] == true && conditions[1] == true && conditions[2] == true && conditions[3] == true) //Condition. If all score conditions were met
      {
        System.out.println ("Word was not previously entered!"); //State message in console        
        words.setText("Words Already Entered: " +wordlist); //Update wordlist for words already entered
        winYN = true; //Set boolean variable to true
      }      
      if (winYN == true) //Condition. If boolean variable winYN is true.
      {
        if (p1 == true && p2 == false) //Condition. If singleplayer mode was selected.
        {
          P1_SCORE = play(p1, p2, P1_SCORE, P2_SCORE, p1Points, p2Points, turn, userEntry, pointlimit); //Set player's score equal to value returned from subprogram. Send variables to subprogram.
          p11.setText("<html>Player 1: <br><br><br><br>" +P1_SCORE); //Update player's score
        }
        
        else if (p1 == false && p2 == true) //Condition. If multiplayer mode was selected.
        {
          if (turn == true) //Condition. If it is Player 1's turn.
          {
            P1_SCORE = play(p1, p2, P1_SCORE, P2_SCORE, p1Points, p2Points, turn, userEntry, pointlimit); //Set player's score equal to value returned from subprogram. Send variables to subprogram.
            p01.setText("<html>Player 1: <br><br><br><br>" +P1_SCORE); //Update player's score
          }
          
          else if (turn == false) //Condition. If it is Player 2's turn.
          {
            P2_SCORE = play(p1, p2, P1_SCORE, P2_SCORE, p1Points, p2Points, turn, userEntry, pointlimit); //Set player's score equal to value returned from subprogram. Send variables to subprogram.
            p02.setText("<html>Player 2: <br><br><br><br>" +P2_SCORE); //Update player's score
          }
        }
      }
      
      if (P1_SCORE >= pointlimit || P2_SCORE >= pointlimit) //Condtion. If a player's score exceeds the score limit
      {
        pan1.setVisible (false); //Make panel invisible
        pan2.setVisible (false); //Make panel invisible      
        
        p1Points.setVisible (false); //Make panel invisible
        p2Points.setVisible (false); //Make panel invisible
        wordEntered.setVisible (false); //Make panel invisible
        
        remove(wordEntered); //Remove panel from window
        remove(p1Points); //Remove panel from window
        remove(p2Points); //Remove panel from window
        options.remove(pauseButton); //Remove button from panel
        options.remove(reshuffle); //Remove button from panel
        remove(options); //Remove button from panel
        
        if (p1 == false && p2 == true) //Condtion. If multiplayer mode was selected.
        {
          if (turn == true) //Condition. If Player 1's turn.
          {
            gratu.add(grats1); //Add label to panel
            add(gratu); //Add panel to window
          }
          else //Condition. If Player 2's turn.
          {
            gratu.add(grats2); //Add label to panel
            add(gratu); //Add panel to window
          }
        }
        if (p1 == true && p2 == false) //Condition. If singleplayer mode was selected.
        {
          gratu.add(grats); //Add label to panel
          add(gratu); //Add panel to window
        }
      }
      add(options); //Add panel to window
      
      if (winYN == false) //Condition. If boolean variable winYN is false.
      {
        pan1.setVisible (false); //Make panel invisible
        pan2.setVisible (false); //Make panel invisible      
        
        if (p1 == true && p2 == false) //Condition. If singleplayer mode was selected.
        {
          p1Points.setVisible (false); //Make panel invisible
          remove(p1Points); //Remove panel from window
        }
        if (p1 == false && p2 == true) //Condition. If multiplayer mode was selected.
        {
          p2Points.setVisible (false);  //Make panel invisible
          remove(p2Points); //Remove panel form window
        }
        options.setVisible (false); //Make panel invisible   
        
        remove(options); //Remove panel from window
        
        if (conditions[0] == false) //Condition. If first score condition was not met.
        {
          words.setVisible (false); //Make label invisible
          wordEntered.remove(words); //Remove label from panel
          wordEntered.add(error); //Add label to panel
          error.setVisible(true); //Make label visible
        }
        
        if (conditions[0] == true && conditions[1] == false) //Condition. If second score condition was not met.
        {
          words.setVisible (false); //Make label invisible
          wordEntered.remove(words); //Remove label from panel  
          wordEntered.add(error2); //Add label to panel
          error2.setVisible(true); //Make label visible
        }
        
        if (conditions[0] == true && conditions[1] == true && conditions[2] == false) //Condition. If third score condition was not met.
        {
          words.setVisible (false); //Make label invisible
          wordEntered.remove(words); //Remove label from panel  
          wordEntered.add(error4); //Add label to panel
          error4.setVisible(true); //Make label visible
        }
        
        if (conditions[0] == true && conditions[1] == true && conditions[2] == true && conditions[3] == false) //Condition. If fourth score condition was not met.
        {
          words.setVisible (false); //Make label invisible
          wordEntered.remove(words); //Remove label from panel  
          wordEntered.add(error3); //Add label to panel
          error3.setVisible(true); //Make label visible
        }
        add(okPanel); //Add panel to window
        okPanel.setVisible (true); //Make panel visible  
      }      
      reset (conditions); //Send variable to subprogram
      System.out.println ("Overall Win Condition: " +winYN); //Display message in console      
      winYN = false; //Set boolean variable back to false
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//Re-shuffle Button. Changes the letters on the grid.    
    else if (command.equals("Re-Shuffle")) //Condition. If the button was pressed.
    {  
      System.out.println ("Reshuffle"); //Output message in console.
      
      pan1.setVisible(false); //Make panel invisible
      pan2.setVisible(false); //Make panel invisible
      wordEntered.setVisible(false); //Make panel invisible
      p1Points.setVisible(false); //Make panel invisible
      options.setVisible(false); //Make panel invisible
      
      remove(pan1); //Remove panel from window 
      remove(pan2); //Remove panel from window 
      remove(wordEntered); //Remove panel from window 
      remove(p1Points); //Remove panel from window 
      remove(options); //Remove panel from window 
      
      for (int y=0; y<5; y++) //For Loop.
      {
        for (int u=0; u<5; u++) //For Loop.
        {
          pan1.remove(letters[y][u]); //Remove label from panel
        }
      }
      reShuffle (randomNum, grid, grid2, dice, r,num, ans, num2,counter, letters); //Send variables to subprogram     
      
//Set randomly generated grid position and side of dice into a 2 dimensional array. Also set it labels value equal to it.     
      for (int h=0; h<5; h++) //For Loop.
      {
        for (int k=0; k<5; k++) //For Loop.
        {
          grid2[h][k] = grid[randomNum[counter]]; //Sets position of 2 dimensional string array equal to letter of randomly generated position of the string array
          counter++; //Add one to counter variable      
          letters[h][k] = new JLabel ("         "  +grid2[h][k]); //Store value in position of 2 dimensional array of labels
          letters[h][k].setFont(new Font ("Times New Roman", Font.BOLD, 28)); //Set font for labels
          pan1.add(letters[h][k]); //Add letter to panel     
        }
      }
      counter=0; //Reinitialize counter 
      add(pan1); //Add panel to window 
      pan1.setVisible(true); //Make panel visible
      add(pan2); //Add panel to window 
      pan2.setVisible(true);//Make panel visible
      add(wordEntered); //Add panel to window 
      wordEntered.setVisible(true); //Make panel visible   
      
      if (p1==true && p2 == false) //Condition. If single player boolean is true, and multiplayer boolean is false
      {
        add(p1Points); //Add panel to window
        p1Points.setVisible(true); //Make panel visible   
      }
      
      if (p1==false && p2 == true) //Condition. If single player boolean is false, and multiplayer boolean is true
      {
        add(p2Points); //Add panel to window
        p1Points.setVisible(true); //Make panel visible   
      }     
      add(options); //Add panel to window
      options.setVisible(true); //Make panel visible
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//Pause Button.
    else if (command.equals("Pause")) //Condition. If the button was pressed.
    {
      pan1.setVisible (false); //Make panel invisible
      pan2.setVisible (false); //Make panel invisible      
      wordEntered.setVisible (false); //Make panel invisible
      p1Points.setVisible (false); //Make panel invisible
      options.setVisible (false); //Make panel invisible
      
      remove(pan1); //Remove panel from window
      remove(pan2); //Remove panel from window
      remove(wordEntered); //Remove panel from window
      remove(p1Points); //Remove panel from window
      remove(p2Points); //Remove panel from window
      remove(options); //Remove panel from window
      
      add(resume); //Add panel to window
      resume.setVisible (true); //Make panel visible      
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//Resume Button. Removes everything off the screen.    
    else if (command.equals("Resume")) //Condition. If the button was pressed.
    { 
      resume.setVisible (false);  //Make panel invisible
      remove(resume); //Remove panel from window
      
      add(pan1); //Add panel to window
      add(pan2); //Add panel to window
      add(wordEntered); //Add panel to window
      
      pan1.setVisible (true); //Make panel visible
      pan2.setVisible (true); //Make panel visible
      wordEntered.setVisible (true); //Make panel visible
      
      if (p1==true && p2 == false) //Condition. If single player boolean is true, and multiplayer boolean is false
      {
        add(p1Points); //Add panel to window
        p1Points.setVisible (true); //Make panel visible
      }
      
      if (p1 ==false && p2==true) //Condition. If single player boolean is false, and multiplayer boolean is true
      {
        add(p2Points); //Add panel to window
        p2Points.setVisible (true); //Make panel visible
      }      
      add(options); //Add panel to window
      options.setVisible (true); //Make panel visible
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//Exit Button.    
    else if (command.equals("Exit")) //Condition. If the button was pressed.
    {
      wordlist.clear(); //Removes all words from arraylist
      words.setText("Words Already Entered: " +wordlist); //Updates label with arraylist
            
      if (p1==true && p2 == false) //Condition. If single player boolean is true, and multiplayer boolean is false
      {
        P1_SCORE=0; //Sets score back to 0
        p11.setText("<html>Player 1: <br><br><br><br>" +P1_SCORE); //Update label with player's score
        p1Points.add(p11); //Update label in panel
        remove(p1Points); //Remove panel from window
      }
      if (p1==false && p2 == true) //Condition. If single player boolean is true, and multiplayer boolean is false
      {
        P1_SCORE=0; //Sets score back to 0
        P2_SCORE=0; //Sets score back to 0
        p01.setText("<html>Player 1: <br><br><br><br>" +P1_SCORE); //Update label with player's score
        p02.setText("<html>Player 2: <br><br><br><br>" +P2_SCORE); //Update label with player's score
        p2Points.add(p01); //Update label in panel
        p2Points.add(p02); //Update label in panel
        remove(p2Points); //Remove panel from window
      }
      p1=false; //Sets single player boolean variable to false
      p2=false; //Sets multiplayer boolean variable to false
      
      setSize (990 , 985); //Set window size
      System.out.println ("Exit button pressed"); //Display message in console    
      
      remove(gratu); //Remove panel from window
      remove(pan1); //Remove panel from window
      remove(pan2); //Remove panel from window
      remove(wordEntered); //Remove panel from window
      
      remove(options); //Remove panel from window
      
      add(pan); //Add panel to window
      add(pan3); //Add panel to window 
      add(ppan); //Add panel to window
      add(qpan); //Add panel to window
      
      for (int y=0; y<5; y++) //For Loop.
      {
        for (int u=0; u<5; u++) //For Loop.
        {
          pan1.remove(letters[y][u]); //Remove label from panel
        }
      }
      reShuffle (randomNum, grid, grid2, dice, r,num, ans, num2,counter, letters); //Send variables to subprogram     
      
//Set randomly generated grid position and side of dice into a 2 dimensional array. Also set it labels value equal to it.     
      for (int h=0; h<5; h++) //For Loop.
      {
        for (int k=0; k<5; k++) //For Loop.
        {
          grid2[h][k] = grid[randomNum[counter]]; //Sets position of 2 dimensional string array equal to letter of randomly generated position of the string array
          counter++; //Add one to counter variable
          
          letters[h][k] = new JLabel ("         "  +grid2[h][k]); //Store value in position of 2 dimensional array of labels
          
          letters[h][k].setFont(new Font ("Times New Roman", Font.BOLD, 28)); //Set font for labels
          pan1.add(letters[h][k]); //Add letter to panel     
        }
      }
      counter=0; //Reinitialize counter 
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//ERROR PAGE BUTTON. ERROR PAGE BUTTON. ERROR PAGE BUTTON. ERROR PAGE BUTTON. ERROR PAGE BUTTON. ERROR PAGE BUTTON. 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    if (command.equals("OK")) //Condition. If the button was pressed.
    { 
      okPanel.setVisible (false);  //Make panel invisible
      remove(okPanel); //Remove panel from window
      
      add(pan1); //Add panel to window
      add(pan2); //Add panel to window
      
      wordEntered.add(words); //Add label to panel
      words.setVisible (true); //Make label visible
      
      error4.setVisible(false); //Make label invisible
      wordEntered.remove(error4); //Remove label from window
      error3.setVisible(false); //Make label invisible
      wordEntered.remove(error3); //Remove label from window
      error2.setVisible(false); //Make label invisible
      wordEntered.remove(error2); //Remove label from window
      error.setVisible(false); //Make label invisible
      wordEntered.remove(error); //Remove label from window
      
      add(wordEntered); //Add panel to window
      
      pan1.setVisible (true); //Make panel visible
      pan2.setVisible (true); //Make panel visible
      wordEntered.setVisible (true); //Make panel visible
      
      if (p1==true && p2 == false) //Condition. If single player boolean is true, and multiplayer boolean is false
      {
        add(p1Points); //Add panel to window
        p1Points.setVisible (true); //Make panel visible
      }
      
      if (p1 ==false && p2==true) //Condition. If single player boolean is false, and multiplayer boolean is true
      {
        add(p2Points); //Add panel to window
        p2Points.setVisible (true); //Make panel visible
      }
      
      add(options); //Add panel to window
      options.setVisible (true); //Make panel visible
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//POINT BUTTONS. POINT BUTTONS. POINT BUTTONS. POINT BUTTONS. POINT BUTTONS. POINT BUTTONS. POINT BUTTONS. POINT BUTTONS. 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    if (command.equals("10 Points")) //Condition. If the button was pressed.
    { 
      pointlimit=10; //Set point limit
      pointLabel.setText("Current Point Limit: " +pointlimit); //Update label stating current point limit
    }
    if (command.equals("20 Points")) //Condition. If the button was pressed.
    { 
      pointlimit=20; //Set point limit
      pointLabel.setText("Current Point Limit: " +pointlimit); //Update label stating current point limit
    }
    if (command.equals("30 Points")) //Condition. If the button was pressed.
    { 
      pointlimit=30; //Set point limit
      pointLabel.setText("Current Point Limit: " +pointlimit); //Update label stating current point limit
    }
    if (command.equals("40 Points")) //Condition. If the button was pressed.
    { 
      pointlimit=40; //Set point limit
      pointLabel.setText("Current Point Limit: " +pointlimit); //Update label stating current point limit
    }
    if (command.equals("50 Points")) //Condition. If the button was pressed.
    { 
      pointlimit=50; //Set point limit
      pointLabel.setText("Current Point Limit: " +pointlimit); //Update label stating current point limit
    }
  }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//CHECKING FOR WIN CONDITIONS. CHECKING FOR WIN CONDITIONS. CHECKING FOR WIN CONDITIONS. CHECKING FOR WIN CONDITIONS.  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//Subprogram. Sends variables to subprograms and determines whether to send it to the next subprogram. Determines overall win condition. 
//User entry must be at least 3 letters long, must not alrady be entered, must exist on the board, and must exist in the dictionary.  
  public static void winCon (boolean [] conditions, String userEntry, String [] word, String [][] grid2, int j, ArrayList <String> worddt, int min, int max, ArrayList <String> wordlist, JLabel error, JLabel error2, JLabel error3, JLabel error4, JLabel words, JPanel wordEntered)
  {    
//Check length of word.    
    if (userEntry.length() < 3) //Condition. If word entered by user is less than 3 letters long.
    {
      conditions[0] = false; //Set boolean to false
      System.out.println ("Word is too short!"); //Display message in console     
    }
        
    else //Condition. If word entered is greater than 3 letters.
    {
      System.out.println ("Word meets required length!"); //Display message in console
      conditions[0] = true; //Set boolean to true
    }
    
//Check for first letter of word entered. Then send to subprogram to find other letters.    
    if (conditions[0] == true) //Condition. If previous condition was met.
    {
      word = userEntry.split(""); //Split word entered by user and store each letter into an array
      boolean lock[][] = new boolean [5][5]; //Delcare a 2 dimensional boolean array
      
      for (int x=0; x<5; x++) //For Loop.
      {
        for (int y=0; y<5; y++) //For Loop.
        {
          if (word[j].equalsIgnoreCase(grid2[x][y])) //Condition. Finds first letter of word entered in the grid.
          {
            lock[x][y] =true; //Set position of boolean array to true
            search (grid2, word, j, lock, x, y, conditions); //Send varaibles to subprogram
          }
          if (conditions[1] == true) //Condition. If second score condition is met.
          {
            break; //Exit loop
          }
          else //Condtion. If second score condition is not met.
          {
            lockreset (lock); //Send variable to subprogram
          }
        }
        if (conditions[1] == true) //Condition. If second score condition is met.
        {
          break; //Exit loop
        }
      }
    }
    
//Checks for word in dictionary (list of words from textfile).    
    if (conditions[0] == true && conditions[1] == true) //Conditon. If previous conditions were met.
    {
      System.out.println ("Word was found on grid!"); //Display message in console
      wordExist (userEntry, conditions, worddt, min, max); //Send variables to subprogram.
    }
    
    else if (conditions[0] == true && conditions[1] == false) //Conditon. If previous conditions was not met.
    {
      System.out.println ("Word was not found on grid!"); //Display message in console
    }
    
//Checks if word was previously entered.    
    if (conditions[0] == true && conditions[1] == true && conditions[2] == true) //Conditon. If previous conditions were met.
    {
      System.out.println ("Word was found in dictionary!"); //Display message in console
      duplicateWord (userEntry, error3, conditions, wordlist, wordEntered, words); //Send variables to subprogram.
    }
    
    else if (conditions[0] == true && conditions[1] == true && conditions[2] == false) //Conditon. If previous conditions was not met.
    {
      System.out.println ("Word was not found in dictionary!"); //Display message in console
    }      
  }
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//GRID SEARCH. GRID SEARCH. GRID SEARCH. GRID SEARCH. GRID SEARCH. GRID SEARCH. GRID SEARCH. GRID SEARCH. GRID SEARCH.   
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//Subprogram. Searches grid for word entered by user
  public static void search (String[][] grid2, String [] word, int j, boolean [][] lock, int x, int y, boolean [] conditions)
  {
    j++; //Add one to counter
    
    if (word.length == j) //Condtion. If counter matches length of the word entered by the user.
    {
      conditions[1] = true; //Set first win conditon boolean to true
    }
    
    if (word.length != j) //Condtion. If counter doesn't match length of the word entered by the user.
    {
      if (x > 0)
      {
//Check Below It 
        if (word[j].equalsIgnoreCase(grid2[x-1][y]) && lock[x-1][y] == false)
        {
          lock[x-1][y] =true; //Set boolean 2d array position to true
          search(grid2,word,j,lock,x-1, y,conditions); //Recursion
        } 
      }
      
      if (y >0)
      {
//Check Left of It      
        if (word [j].equalsIgnoreCase(grid2[x][y-1])&& lock[x][y-1] == false)
        {
          lock[x][y-1] =true; //Set boolean 2d array position to true
          search( grid2,word,j,lock,x, y-1,conditions); //Recursion
        }
      }
      
      if (x > 0 && y > 0)
      {
//Check Left and Above of It      
        if (word[j].equalsIgnoreCase(grid2[x-1][y-1])&& lock [x-1][y-1] == false)
        {
          lock[x-1][y-1] =true; //Set boolean 2d array position to true
          search(grid2,word,j,lock,x-1, y-1,conditions); //Recursion
        } 
      }
      
      if (x < 4)
      {
//Check the Below It      
        if (word[j].equalsIgnoreCase(grid2[x+1][y])&& lock [x+1][y]==false)
        {
          lock[x+1][y] =true; //Set boolean 2d array position to true
          search(grid2,word,j,lock,x+1, y,conditions); //Recursion
        }
      }
      
      if (y < 4)
      {
//Check Right of It      
        if (word[j].equalsIgnoreCase(grid2[x][y+1])&& lock [x][y+1]==false)
        {
          lock[x][y+1] =true; //Set boolean 2d array position to true
          search(grid2,word,j,lock,x, y+1,conditions); //Recursion
        }
      }
      
      if (x < 4 && y < 4)
      {
//Check Down amd Right of It      
        if (word[j].equalsIgnoreCase(grid2[x+1][y+1])&& lock [x+1][y+1]==false)
        {
          lock[x+1][y+1] =true; //Set boolean 2d array position to true
          search(grid2,word,j,lock,x+1, y+1,conditions); //Recursion
        }
      }
      
      if (x < 4 && y > 0)
      {
//Check Above and Right of It
        if (word[j].equalsIgnoreCase(grid2[x+1][y-1]) && lock [x+1][y-1] == false)
        {
          lock[x+1][y-1] =true; //Set boolean 2d array position to true
          search( grid2,word,j,lock,x+1, y-1,conditions); //Recursion
        }
      }
      
      if (x > 0 && y < 4)
      {
//Check Left and Down of It      
        if (word[j].equalsIgnoreCase(grid2[x-1][y+1]) && lock [x-1][y+1] == false)
        {
          lock[x-1][y+1] =true; //Set boolean 2d array position to true
          search( grid2,word,j,lock,x-1, y+1,conditions); //Recursion
        }
      }
    }   
  }  

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//BINARY SEARCH. BINARY SEARCH. BINARY SEARCH. BINARY SEARCH. BINARY SEARCH. BINARY SEARCH. BINARY SEARCH. BINARY SEARCH.  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//Subprogram. Checks for worded entered by user in dictionary to see whether it exists in the English language. Recursive Binary Search.
  public static void wordExist(String userEntry, boolean [] conditions, ArrayList <String> worddt, int min, int max)
  {
    int middle = (min+max)/2; //Set integer variable middle equal the max + min divided by 2
    String result = userEntry.toLowerCase(); //Shange user's entry to all lowercase
    
    if (min <= max) //Conditon. If min is less than or equal to max.
    {
      if (result.compareTo(worddt.get(middle)) == 0) //Conditon. If user's entry matches word in word list.
      {
 //Try Catch.       
        try{
          conditions[2] = true; //Set boolean for third score contion to true
        }
        catch(StackOverflowError e){
          min = max+1; //Set min equal to max plus 1
        }
      }
      
      else if (result.compareTo(worddt.get(middle)) > 0) //Conditon. If user's entry is before word of position [middle].
      {
        try{
          wordExist(userEntry, conditions, worddt, middle-1, max); //Recursion
        }
        catch(StackOverflowError e){
          min = max+1; //Set min equal to max plus 1
        }
      }
      
      else if (result.compareTo(worddt.get(middle)) < 0) //Conditon. If user's entry is after word of position [middle].
      {
        try{
          wordExist(userEntry, conditions, worddt, min, middle+1); //Recusion
        }
        catch(StackOverflowError e){
          min = max+1; //Set min equal to max plus 1
        }
      }
    }
    
    else
    {
      conditions[2] = false; //Ser thrid score condition boolean to false
    }    
  }
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//NO DUPLICATES. NO DUPLICATES. NO DUPLICATES. NO DUPLICATES. NO DUPLICATES. NO DUPLICATES. NO DUPLICATES. NO DUPLICATES.  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//Subprogram. Checks for whether word has been previously entered by user from this board.  
  public static void duplicateWord (String userEntry, JLabel error3, boolean [] conditions, ArrayList <String> wordlist, JPanel wordEntered, JLabel words)
  { 
    if (wordlist.isEmpty() == true) //Conditon. If arraylist is empty.
    {
      wordlist.add(userEntry); //Add user's entry to arraylist
      conditions[3] = true; //Set fourth score condition to true
    }
    
    else
    { 
      for (int i = 0; i < wordlist.size(); i++) //For Loop.
      {
        if (userEntry.equals(wordlist.get(i))) //Condition. If user's entry matches value in arraylist.
        {
          System.out.println ("Word was already entered!"); //Display message in console
          conditions[3] = false; //Set fourth score condition to false
          break; //Exit loop
        }
        
        else
        {
          conditions[3] = true; //Set fourth score condition to false
        }
      }
      
      if (conditions[3] == true) //Condition. If fourth score condition is true.
      {
        wordlist.add(userEntry); //Add user's entry to arraylist
      }
    }
  }
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//RESET VARIABLES. RESET VARIABLES. RESET VARIABLES. RESET VARIABLES. RESET VARIABLES. RESET VARIABLES. RESET VARIABLES.    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
  public static void lockreset (boolean [][] lock)
  {
    for (int x=0; x<5; x++) //For Loop.
    {
      for (int y=0; y<5; y++) //For Loop.
      {
        lock[x][y] = false; //Set boolean array position to false
      }
    }
  }
  
//Subprogram. Resets all win conditions.  
  public static void reset (boolean [] conditions)
  {
    for (int i=0; i<conditions.length; i++) //For Loop.
    {
      conditions[i] = false; //Set boolean array position to false
    }
  }
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//RE-SHUFFLING. RE-SHUFFLING. RE-SHUFFLING. RE-SHUFFLING. RE-SHUFFLING. RE-SHUFFLING. RE-SHUFFLING. RE-SHUFFLING.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//Subprogram. Reshuffles letters on board.  
  public static void reShuffle (int [] randomNum, String [] grid, String [][] grid2,String [][] dice, Random r, int num, boolean ans , int num2, int counter, JLabel[][] letters)
  {
//Randomize side of dice    
    for (int p=0; p<25; p ++) //For Loop.
    {
      num = r.nextInt(6)+0; //Set "num" equal to a random number from 0 to 5. Min value is 0. Max value is less than 6.
      grid [p] = dice [p][num]; //Set position in string array equal to letter in position of 2 dimensional string array
    }
    
//Randomize dice's position on the 5 by 5 grid of 25 dice    
    for (int i=0; i<25; i++) //For Loop.
    {
      do 
      {
        ans = true; //Sets variable "ans" equal to true
        num2 = r.nextInt(25)+0; //Set "num2" equal to random number from 0 to 24. Min value is 0. Max value is less than 25.
        if (i>0) //Condition. If i is greater than 0.
        {
          for (int j=0; j<i; j++) //For Loop.
          {
            if (num2 == randomNum [j]) //Condition. If number randomly generated is equal to value previously randomly generated and stored in array.
            {
              ans = false; //Set ans equal to false
            }
          }
        }
      } while (ans == false); //Condition. Loop will repeat as long as ans is equal to false.
      randomNum [i] = num2; //Set position of integer array equal to "num2" which the value was randomly generated  
    }      
  } 
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//POINT CALCULATION. POINT CALCULATION. POINT CALCULATION. POINT CALCULATION. POINT CALCULATION. POINT CALCULATION.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
  public static int play (boolean p1, boolean p2, int P1_SCORE, int P2_SCORE, JPanel p1Points, JPanel p2Points, boolean turn, String userEntry, int pointlimit)
  {
    if (P1_SCORE != pointlimit || P2_SCORE != pointlimit)
    {
      if (p1 == false && p2 == true) //Condition. If multiplayer mode was selected.
      {
        if (turn == true) //Condition. If Player 1's turn.
        {
          P1_SCORE += userEntry.length(); //Add points equal to length of user's word
          return P1_SCORE; //Return to caller 
        }
        else //Condition. If Player 2's turn.
        {
          return P2_SCORE = P2_SCORE + userEntry.length(); //Return to caller. Add points equal to length of user's word
        }
      }
      if (p1 == true && p2 == false) //Condition. If single player mode was selected.
      {
        P1_SCORE += userEntry.length(); //Add points equal to length of user's word
        return P1_SCORE; //Return to caller 
      }
    }
    return P1_SCORE; //Return to caller 
  }
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
//Main Method. It starts the GUI.    
  public static void main (String [] args) throws Exception
  {
    MyBoggle frame1 = new MyBoggle (); //Start the GUI    
  }  
} 

	package game.gui;
		
	

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import game.engine.*;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
	
	public class Main extends Application {
		
		
//		private void removeNodesInRow(GridPane gridPane, int row) {
//            gridPane.getChildren().removeIf(node -> {
//                Integer rowIndex = GridPane.getRowIndex(node);
//                return rowIndex != null && rowIndex == row;
//            });
//        }
		
	    static Battle battle;
	    boolean easy;
	
	    ProgressBar p1 = new ProgressBar();
        ProgressBar p2 = new ProgressBar();
        ProgressBar p3 = new ProgressBar();
        ProgressBar p4 = new ProgressBar();
        ProgressBar p5 = new ProgressBar();

	    
	    Lane CurrLane;
	    static Text CurrentScoreNum;
	    static Text CurrentTurnNum ;
	    static Text CurrentPhaseNum;
	    static Text CurrentResourcesNum;
	    public void start(Stage primaryStage) throws Exception {
	        primaryStage.setTitle("Attack On The Titans");
	        try {

	            	StackPane root = new StackPane();
	            	Scene scene = new Scene(root,1600,800);
	            	
	            	String soundFilePath = "pew.mp3";
	                File soundFile = new File(soundFilePath);
	                AudioClip clickSound = new AudioClip(soundFile.toURI().toString());
	                
	                String hardsoundpath = "HardRana.mp3";
	                File hardsound = new File(hardsoundpath);
	                AudioClip clickhard = new AudioClip(hardsound.toURI().toString());
	                
	                String easysoundpath = "EasyFarah.mp3";
	                File easysound = new File(easysoundpath);
	                AudioClip clickeasy = new AudioClip(easysound.toURI().toString());
	                
	                String Backgroundpath = "background.mp3"; // Replace with the actual file name
	                Media backgroundsound = new Media(new File(Backgroundpath).toURI().toString());
	                MediaPlayer Backgroundsoundplayer = new MediaPlayer(backgroundsound);

	                // Set the media player to loop the music
	                Backgroundsoundplayer.setCycleCount(MediaPlayer.INDEFINITE);
	               

	                
	                
	            	
	            	Media media = new Media("file:///C:/Users/HP/workspace/Game/intro.mp4");		            
		            // Create MediaPlayer object
		            MediaPlayer mediaPlayer = new MediaPlayer(media);
		            
		            // Create MediaView object
		            MediaView mediaView = new MediaView(mediaPlayer);
		            
		            // Set up the layout
		            
		            root.getChildren().add(mediaView);
		            mediaView.fitWidthProperty().bind(primaryStage.widthProperty());
		            mediaView.fitHeightProperty().bind(primaryStage.heightProperty());
		            mediaView.setPreserveRatio(true);
		            
		            // Play the video
		            mediaPlayer.play();
		            
		            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
					

	            // Scene2
					StackPane root2 = new StackPane();
			        Scene scene2 = new Scene(root2, 1600, 800);
			        root2.setStyle("-fx-background-color: black;");

			        // Load the image
			        Image mode = new Image("mode.png");
			        ImageView modee = new ImageView(mode);
			        modee.setFitWidth(1000); 
			        modee.setFitHeight(1000);
			        modee.setPreserveRatio(true);

			        // Add button to root
			        root2.getChildren().add(modee);
	
	            
	            
	            
	            // Scene3
	            BorderPane root3 = new BorderPane();
	            Scene scene3 = new Scene(root3, 1600, 800);
	            root3.setStyle("-fx-background-color: cadetblue;");
	            HBox hbox = new HBox();
	            
	            
	            //rana code
	          //Scene4 Easy instructions
	            BorderPane root4 = new BorderPane();
	            Scene scene4 = new Scene(root4, 1600, 800);
	            root4.setStyle("-fx-background-color: darkred;");
	            
	            Text gameInstructionsEasy = new Text("Game Instructions - Easy Mode");
	            gameInstructionsEasy.setFont(Font.font("Times New Roman", 60));
	            gameInstructionsEasy.setStyle("-fx-fill: black;"); // Optional: to change the text color
	            
	            
	            Text l1easy = new Text("1. This is a tower defense game. Your main goal is to protect your lane walls from the approaching titans by buying weapons and placing them into the lanes to attack the titans.");
	            l1easy.setFont(Font.font("Times New Roman", 20));
	            l1easy.setStyle("-fx-fill: black;");
	            
	            Text l2easy = new Text("2. The game starts with an initial score of 0 and the number of turns begins at 1.");
	            l2easy.setFont(Font.font("Times New Roman", 20));
	            l2easy.setStyle("-fx-fill: black;");
	            
	            Text l3easy = new Text("3. There are two modes in the game: Easy and Hard. This guide is for the Easy mode.");
	            l3easy.setFont(Font.font("Times New Roman", 20));
	            l3easy.setStyle("-fx-fill: black;");
	            
	            Text l4easy = new Text("4. In Easy mode, you will have 3 initial lanes and each lane will have 250 initial resources.");
	            l4easy.setFont(Font.font("Times New Roman", 20));
	            l4easy.setStyle("-fx-fill: black;");
	            
	            Text l5easy = new Text("5. Each lane has a wall at one end and a titan spawn point at the other. The distance between them depends on your screen size and game orientation.");
	            l5easy.setFont(Font.font("Times New Roman", 20));
	            l5easy.setStyle("-fx-fill: black;");
	            
	            Text l6easy = new Text("6. Game Interface:");
	            l6easy.setFont(Font.font("Times New Roman", 20));
	            l6easy.setStyle("-fx-fill: black;");
	            
	            Text l7easy = new Text("   - Current score, turn, phase, and resources are displayed throughout the game.");
	            l7easy.setFont(Font.font("Times New Roman", 20));
	            l7easy.setStyle("-fx-fill: black;");
	            
	            Text l8easy = new Text("   - Weapon shop displays name, type, price, and damage points of each weapon.");
	            l8easy.setFont(Font.font("Times New Roman", 20));
	            l8easy.setStyle("-fx-fill: black;");
	            
	            Text l9easy = new Text("   - Lane information includes wall health, danger level, available weapons, and active titans.");
	            l9easy.setFont(Font.font("Times New Roman", 20));
	            l9easy.setStyle("-fx-fill: black;");
	            
	            Text l10easy = new Text("   - Titan details such as health, position, and speed are shown within the lanes.");
	            l10easy.setFont(Font.font("Times New Roman", 20));
	            l10easy.setStyle("-fx-fill: black;");
	            
	            Text l11easy = new Text("7. Player Actions:");
	            l11easy.setFont(Font.font("Times New Roman", 20));
	            l11easy.setStyle("-fx-fill: black;");
	            
	            Text l12easy = new Text("   - Pass turn, select a lane to buy a weapon for, select a weapon from the shop, and buy the weapon.");
	            l12easy.setFont(Font.font("Times New Roman", 20));
	            l12easy.setStyle("-fx-fill: black;");
	            
	            Text l13easy = new Text("   - Bought weapons are displayed in the selected lane. Distinguish between types of titans and weapons.");
	            l13easy.setFont(Font.font("Times New Roman", 20));
	            l13easy.setStyle("-fx-fill: black;");
	            
	            Text l14easy = new Text("   - Lost and active lanes are distinguished, and invalid actions provide an indicator with reasons.");
	            l14easy.setFont(Font.font("Times New Roman", 20));
	            l14easy.setStyle("-fx-fill: black;");
	            
	            Text l15easy = new Text("8. Losing the game will display a loss message, the final score, and return you to the start window.");
	            l15easy.setFont(Font.font("Times New Roman", 20));
	            l15easy.setStyle("-fx-fill: black;");
	            
	            VBox instructionsBoxeasy = new VBox(10, l1easy, l2easy, l3easy, l4easy, l5easy, l6easy, l7easy, l8easy, l9easy, l10easy, l11easy, l12easy, l13easy, l14easy, l15easy);
	            instructionsBoxeasy.setAlignment(Pos.CENTER); // Center the text within the VBox
	            

	            
	            // Buttons
	            
	            Button ContinueBottoneasy = new Button("Continue");
	            
	
	    
	            ContinueBottoneasy.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent e) {
	                	clickSound.play();
	                    primaryStage.setScene(scene3);
	                }
	            }); // Set to the next scene
	            
	            VBox buttonsBoxeasy = new VBox(10, ContinueBottoneasy);
	            buttonsBoxeasy.setAlignment(Pos.CENTER);
	
	            VBox mainBoxeasy = new VBox(20, gameInstructionsEasy, instructionsBoxeasy, buttonsBoxeasy);
	            mainBoxeasy.setAlignment(Pos.CENTER); // Center the VBox within the BorderPane
	            root4.setCenter(mainBoxeasy);
	            
	            
	            
	          //Scene5 Hard instructions
	            BorderPane root5 = new BorderPane();
	            Scene scene5 = new Scene(root5, 1600, 800);
	            root5.setStyle("-fx-background-color: blue;");
	            Text gameInstructionsHard = new Text("Game Instructions - Hard Mode");
	            gameInstructionsHard.setFont(Font.font("Times New Roman", 60));
	            gameInstructionsHard.setStyle("-fx-fill: black;"); // Change text color to black
	            
	            // Instructions text
	            Text l1hard = new Text("1. This is a tower defense game. Your main goal is to protect your lane walls from the approaching titans by buying weapons and placing them into the lanes to attack the titans.");
	            l1hard.setFont(Font.font("Times New Roman", 20));
	            l1hard.setStyle("-fx-fill: black;");
	            
	            Text l2hard = new Text("2. The game starts with an initial score of 0 and the number of turns begins at 1.");
	            l2hard.setFont(Font.font("Times New Roman", 20));
	            l2hard.setStyle("-fx-fill: black;");
	            
	            Text l3hard = new Text("3. There are two modes in the game: Easy and Hard. This guide is for the Hard mode.");
	            l3hard.setFont(Font.font("Times New Roman", 20));
	            l3hard.setStyle("-fx-fill: black;");
	            
	            Text l4hard = new Text("4. In Hard mode, you will have 5 initial lanes and each lane will have 125 initial resources.");
	            l4hard.setFont(Font.font("Times New Roman", 20));
	            l4hard.setStyle("-fx-fill: black;");
	            
	            Text l5hard = new Text("5. Each lane has a wall at one end and a titan spawn point at the other. The distance between them depends on your screen size and game orientation.");
	            l5hard.setFont(Font.font("Times New Roman", 20));
	            l5hard.setStyle("-fx-fill: black;");
	            
	            Text l6hard = new Text("6. Game Interface:");
	            l6hard.setFont(Font.font("Times New Roman", 20));
	            l6hard.setStyle("-fx-fill: black;");
	            
	            Text l7hard = new Text("   - Current score, turn, phase, and resources are displayed throughout the game.");
	            l7hard.setFont(Font.font("Times New Roman", 20));
	            l7hard.setStyle("-fx-fill: black;");
	            
	            Text l8hard = new Text("   - Weapon shop displays name, type, price, and damage points of each weapon.");
	            l8hard.setFont(Font.font("Times New Roman", 20));
	            l8hard.setStyle("-fx-fill: black;");
	            
	            Text l9hard = new Text("   - Lane information includes wall health, danger level, available weapons, and active titans.");
	            l9hard.setFont(Font.font("Times New Roman", 20));
	            l9hard.setStyle("-fx-fill: black;");
	            
	            Text l10hard = new Text("   - Titan details such as health, position, and speed are shown within the lanes.");
	            l10hard.setFont(Font.font("Times New Roman", 20));
	            l10hard.setStyle("-fx-fill: black;");
	            
	            Text l11hard = new Text("7. Player Actions:");
	            l11hard.setFont(Font.font("Times New Roman", 20));
	            l11hard.setStyle("-fx-fill: black;");
	            
	            Text l12hard = new Text("   - Pass turn, select a lane to buy a weapon for, select a weapon from the shop, and buy the weapon.");
	            l12hard.setFont(Font.font("Times New Roman", 20));
	            l12hard.setStyle("-fx-fill: black;");
	            
	            Text l13hard = new Text("   - Bought weapons are displayed in the selected lane. Distinguish between types of titans and weapons.");
	            l13hard.setFont(Font.font("Times New Roman", 20));
	            l13hard.setStyle("-fx-fill: black;");
	            
	            Text l14hard = new Text("   - Lost and active lanes are distinguished, and invalid actions provide an indicator with reasons.");
	            l14hard.setFont(Font.font("Times New Roman", 20));
	            l14hard.setStyle("-fx-fill: black;");
	            
	            Text l15hard = new Text("8. Losing the game will display a loss message, the final score, and return you to the start window.");
	            l15hard.setFont(Font.font("Times New Roman", 20));
	            l15hard.setStyle("-fx-fill: black;");
	            
	            VBox instructionsBoxhard = new VBox( l1hard, l2hard, l3hard, l4hard, l5hard, l6hard, l7hard, l8hard, l9hard, l10hard, l11hard, l12hard, l13hard, l14hard, l15hard);
	            instructionsBoxhard.setAlignment(Pos.CENTER); // Center the text within the VBox
	

	            
	            
	            
	            
	            
	            //scene 6
	            StackPane s = new StackPane();
	            Scene scene6 = new Scene(s, 1600, 800);
				
//            
	            
	            Media media1 = new Media("file:///C:/Users/HP/workspace/Game/outro.mp4");        
	            // Create MediaPlayer object
	            MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
	            
	            // Create MediaView object
	            MediaView mediaView1 = new MediaView(mediaPlayer1);
	            
	            // Set up the layout
	            
	            s.getChildren().add(mediaView1);
	            mediaView1.fitWidthProperty().bind(primaryStage.widthProperty());
	            mediaView1.fitHeightProperty().bind(primaryStage.heightProperty());
	            mediaView1.setPreserveRatio(true);
	            
// Buttons
	            
	            Button ContinueBottonhard = new Button("Continue");
	            ContinueBottonhard.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent e) {
	                	clickSound.play();
	                    primaryStage.setScene(scene3);
	                }
	            }); // Set to the next scene
	            
	            VBox buttonsBoxhard = new VBox(10, ContinueBottonhard);
	            buttonsBoxhard.setAlignment(Pos.CENTER);
	
	            VBox mainBoxhard = new VBox(20, gameInstructionsHard, instructionsBoxhard, buttonsBoxhard);
	            mainBoxhard.setAlignment(Pos.CENTER); // Center the VBox within the BorderPane
	            root5.setCenter(mainBoxhard);
	            
	            
	            
	            
	            
	            hbox.setSpacing(50);
	            Text CurrentScore = new Text("Current score");
	             CurrentScoreNum = new Text();
	            
	
	            Text CurrentTurn = new Text("Current turn");
	            CurrentTurnNum = new Text();
	
	            Text CurrentPhase = new Text("Current phase");
	            CurrentPhaseNum = new Text();
	
	            Text CurrentResources = new Text("Current resources");
	            CurrentResourcesNum = new Text();
	
	            VBox lanelist = new VBox();
	            lanelist.setSpacing(10);
	
	            VBox CurrentScorev = new VBox(CurrentScore, CurrentScoreNum);
	            VBox CurrentTurnv = new VBox(CurrentTurn, CurrentTurnNum);
	            VBox CurrentPhasev = new VBox(CurrentPhase, CurrentPhaseNum);
	            VBox CurrentResourcesv = new VBox(CurrentResources, CurrentResourcesNum);
	            hbox.getChildren().addAll(CurrentScorev, CurrentTurnv, CurrentPhasev, CurrentResourcesv);
	            hbox.setPadding(new Insets(10));
	            root3.setTop(hbox);
	            
	            BorderPane passroot = new BorderPane();
	            
	            Button W1 = new Button();
	            Button W2 = new Button();
	            Button W3 = new Button();
	            Button W4 = new Button();
	            W1.setVisible(false);
	            W2.setVisible(false);
	            W3.setVisible(false);
	            W4.setVisible(false);
	            
	            Button L1 = new Button("Lane 1");
	            L1.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
	            L1.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                    CurrLane=battle.getOriginalLanes().get(0);
	                    W1.setVisible(true);
	    	            W2.setVisible(true);
	    	            W3.setVisible(true);
	    	            W4.setVisible(true);
	                }
	            });
	            Button L2 = new Button("Lane 2");
	            L2.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
	            L2.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                	CurrLane=battle.getOriginalLanes().get(1); 
	                	 	W1.setVisible(true);
		    	            W2.setVisible(true);
		    	            W3.setVisible(true);
		    	            W4.setVisible(true);}
	            });
	            Button L3 = new Button("Lane 3");
	            L3.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
	            L3.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                		W1.setVisible(true);
		    	            W2.setVisible(true);
		    	            W3.setVisible(true);
		    	            W4.setVisible(true);
	                	CurrLane=battle.getOriginalLanes().get(2);                }
	            });
	            Button L4 = new Button("Lane 4");
	            L4.setPrefSize(100, 120);
	            L4.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
	            L4.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                		W1.setVisible(true);
		    	            W2.setVisible(true);
		    	            W3.setVisible(true);
		    	            W4.setVisible(true);
	                	CurrLane=battle.getOriginalLanes().get(3);                }
	            });
	            Button L5 = new Button("Lane 5");
	            L5.setPrefSize(100, 120);
	            L5.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
	            L5.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                		W1.setVisible(true);
		    	            W2.setVisible(true);
		    	            W3.setVisible(true);
		    	            W4.setVisible(true);
	                	CurrLane=battle.getOriginalLanes().get(4);                }
	            });
	
	            
	            
	            GridPane lanes = new GridPane();
	            lanes.setPadding(new Insets(10));
	            lanes.setStyle("-fx-border-color: black; -fx-border-width: 2;");
	            lanes.setGridLinesVisible(false);	            
	            lanes.prefWidthProperty().bind(scene.widthProperty());
	            lanes.prefHeightProperty().bind(scene.heightProperty());
	            root3.setCenter(lanes);


	            
	            
	            
	            
	            //farah
	            VBox rightbox = new VBox();
	            rightbox.setSpacing(50);
	            Text weapon = new Text("   Choose your Weapons   ");
	            weapon.setFont(Font.font(30));
	            weapon.setFill(Color.DARKRED);
	            Text PiercingCannon1 = new Text();
	            Text SniperCannon = new Text();
	            Text VolleySpreadCannon = new Text();
	            Text WallTrap = new Text();
	            

	            HBox Weapon1  = new HBox(PiercingCannon1, W1);
	            HBox Weapon2 = new HBox(SniperCannon, W2);
	            HBox Weapon3 = new HBox(VolleySpreadCannon, W3);
	            HBox Weapon4 = new HBox(WallTrap, W4);
	            PiercingCannon1.setText( "     Name: Anti-Titan Shell \n     Type: Piercing Cannon \n     Price: 25 \n     Damage Points: 10 ");
	            SniperCannon.setText( "     Name: Long Range Spear  \n     Type: Sniper Cannon \n     Price: 25 \n     Damage Points: 35");
	            VolleySpreadCannon.setText( "     Name: Wall Spread Cannon  \n     Type: Volley Spread Cannon \n     Price: 100 \n     Damage Points: 5");
	            WallTrap.setText( "     Name: Proximity Trap \n     Type: Wall Trap \n     Price: 75 \n     Damage Points: 100");
	            rightbox.getChildren().addAll(weapon,Weapon1,Weapon2 , Weapon3, Weapon4,passroot);
	            root3.setRight(rightbox);
	            
	
	            
	            Image pc = new Image("pc2.png");
	            ImageView imageView = new ImageView(pc);
	            imageView.setFitWidth(100); // Set width
	            imageView.setFitHeight(50);
	            W1.setGraphic(imageView);
	           
	            Image sc = new Image("w2.png");
	            ImageView imageView2 = new ImageView(sc);
	            imageView2.setFitWidth(100); // Set width
	            imageView2.setFitHeight(50);
	            W2.setGraphic(imageView2);
	           
	            Image sc2 = new Image("w3.png");
	            ImageView imageView3 = new ImageView(sc2);
	            imageView3.setFitWidth(100); // Set width
	            imageView3.setFitHeight(50);
	            W3.setGraphic(imageView3);
	           
	            Image wt = new Image("walltrap .png");
	            ImageView imageView4 = new ImageView(wt);
	            imageView4.setFitWidth(100); // Set width
	            imageView4.setFitHeight(50);
	            W4.setGraphic(imageView4);
	            
	            

	            
	            
	            
	      
	            
	            
	
	
	          //Start button scene 1
	            Button startbutton = new Button();
	            Image images = new Image("start.png"); // Replace with your image file path

	            // Create an ImageView
	            ImageView imageViews = new ImageView(images);
	            imageViews.setFitWidth(500); // Set the desired width
	            imageViews.setFitHeight(500); // Set the desired height
	            imageViews.setPreserveRatio(true); // Preserve the aspect ratio of the image

	            // Create a Button and set the ImageView as its graphic
	            
	            startbutton.setGraphic(imageViews);
	            startbutton.setStyle("-fx-background-color: transparent;"); // Make the button background transparent

	            // Add the button to a layout pane
	            root.getChildren().add(startbutton);
	            startbutton.setTranslateY(250);
	            startbutton.setPrefSize(200, 100);
	            startbutton.setVisible(false);
	            mediaPlayer.setOnEndOfMedia(() -> startbutton.setVisible(true));
	            startbutton.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                    primaryStage.setScene(scene2);
	                    mediaPlayer.stop();
	                    clickSound.play();
	                    Backgroundsoundplayer.play();
	                }
	            });
	            //Exit Button
	            Button exitbutton = new Button("Exit");
	            BorderPane.setAlignment(exitbutton, javafx.geometry.Pos.BOTTOM_RIGHT);
	            root3.setBottom(exitbutton);
	            exitbutton.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent e) {
	                	clickSound.play();
	                    primaryStage.setScene(scene);
	                }
	            });
            

	
	            // Easy button scene 2
	            Button easybutton = new Button("Easy");
	            root2.getChildren().add(easybutton);
	            easybutton.setTranslateY(100);
	            easybutton.setTranslateX(-600);
	            easybutton.setPrefSize(100, 70);
	            easybutton.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                	clickeasy.play();
	                    // Make it non-editable
	                    easy = true;
	                    primaryStage.setScene(scene4);
	                    p1.setProgress(1);
	                    p2.setProgress(1);
	                    p3.setProgress(1);
	                    L1.setPrefSize(100, 200);
	                    L2.setPrefSize(100, 200);
	                    L3.setPrefSize(100, 200);
	                    VBox v1 = new VBox(p1, L1);
	                    VBox v2 = new VBox(p2, L2);
	                    VBox v3 = new VBox(p3, L3);

	                    VBox big = new VBox();
	                    big.getChildren().addAll(v1, v2, v3);
	                    root3.setLeft(big);

	                    // Assuming the size of your GridPane is numRows x numColumns
	                    int numRows = 3; // Specify the number of rows in your GridPane
	                    int numColumns = 50; // Specify the number of columns in your GridPane

	                    GridPane lanes = new GridPane(); // Create a new GridPane

	                    // Fill the GridPane with empty Region nodes
	                    for (int row = 0; row < numRows; row++) {
	                        for (int col = 1; col < numColumns; col++) {
	                            Region emptyNode = new Region();
	                            // Set the preferred size of the empty node if needed
	                            emptyNode.setPrefSize(50, 50); // Adjust the size as per your requirements
	                            lanes.add(emptyNode, col, row);
	                        }
	                    }

	                    lanes.setGridLinesVisible(true); // for demonstration purposes
	                    try {
	                        battle = new Battle(1, 0, 50, 3, 250); // Modify these parameters as needed
	                        updateAndSpawn();                 
	                        } catch (IOException e) {
	                        e.printStackTrace();
	                    }
 
	                }
	            });
	
	            //  scene 2
	            Button hardbutton = new Button("Hard");
	            root2.getChildren().add(hardbutton);
	            hardbutton.setPrefSize(100, 70);
	            hardbutton.setTranslateY(100);
	            hardbutton.setTranslateX(600);
	            hardbutton.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                	try {
	                		clickhard.play();
	                		// Create a Battle object with parameters for hard mode
	                		battle = new Battle(1, 0, 50, 5, 125); // Modify these parameters as needed
	                		updateAndSpawn();
	                	} catch (IOException e) {
	                		e.printStackTrace();
	                	}
	                	easy=false;
	                    primaryStage.setScene(scene5);
	                    p1.setProgress(1);
	                    p2.setProgress(1);
	                    p3.setProgress(1);
	                    p4.setProgress(1);
	                    p5.setProgress(1);
		            	L1.setPrefSize(100, 120);
		            	L2.setPrefSize(100, 120);
		            	L3.setPrefSize(100, 120);
	                    VBox v1 = new VBox(p1,L1);
	                    VBox v2 = new VBox(p2,L2);
	                    VBox v3 = new VBox(p3,L3);
	                    VBox v4 = new VBox(p4,L4);
	                    VBox v5 = new VBox(p5,L5);

	                    VBox big = new VBox();
                    	big.getChildren().addAll(v1,v2,v3,v4,v5);
        	            root3.setLeft(big);
                            
                            int numRows = 5; // Specify the number of rows in your GridPane
	                        int numColumns = 49; // Specify the number of columns in your GridPane

	                        // Fill the GridPane with empty Region nodes
	                        for (int row = 0; row < numRows; row++) {
	                            for (int col = 1; col < numColumns; col++) {
	                                Region emptyNode = new Region();
	                                // Set the preferred size of the empty node if needed
	                                emptyNode.setPrefSize(50, 50); // Adjust the size as per your requirements
	                                lanes.add(emptyNode, col, row);
	                            }
	                        }
	                }
	            });
	            
	            
	            Button PassTurn = new Button("Pass Turn");
	            PassTurn.setPrefSize(200, 40);
	            PassTurn.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                	//clickSound.play();
	                    battle.passTurn();
	                    updateAndSpawn();

	                    // Clear all nodes except those in column 0
	                    lanes.getChildren().removeIf(node -> {
	                        Integer columnIndex = GridPane.getColumnIndex(node);
	                        // Remove the node if it is not in column 0 (consider null as column 0)
	                        System.out.println("cleared");
	                        return columnIndex == null || columnIndex != 0;
	                    });

	                    // Add empty nodes to all columns except column 0
	                    int rownum;
	                    if (easy){
	                    	rownum=3;
	                    	System.out.println("easy");
	                    }
	                    else{
	                    	rownum=5;
	                    	System.out.println("hard");
	                    }
	                    for (int row = 0; row < rownum; row++) { // Assuming 3 rows, adjust as needed
	                        for (int col = 1; col < 50; col++) { // Assuming 50 columns, adjust as needed
	                            Region emptyNode = new Region();
	                            // Set the preferred size of the empty node if needed
	                            emptyNode.setPrefSize(50, 50); // Adjust the size as per your requirements
	                            lanes.add(emptyNode, col, row);
	                            System.out.println("put empty");
	                        }
	                    }

	                    int[] laneIndex = {0}; // Use an array to hold the lane index

	                    for (Lane currentLane : battle.getOriginalLanes()) { // Use original lanes
	                        // Check if the lane is lost and set the row to black
	                    	System.out.println("for");
	                    	if (currentLane.isLaneLost()) {
	                    	    System.out.println("lane lost");
	                    	    int currentRow = laneIndex[0];

	                    	    // Calculate the number of columns
	                    	    int numCols = lanes.getColumnConstraints().size();
	                    	    if (numCols == 0) {
	                    	        // If no column constraints are set, we assume the maximum column index + 1
	                    	        for (Node node : lanes.getChildren()) {
	                    	            Integer colIndex = GridPane.getColumnIndex(node);
	                    	            if (colIndex != null && colIndex + 1 > numCols) {
	                    	                numCols = colIndex + 1;
	                    	            }
	                    	        }
	                    	    }

	                    	    // Create a black pane and span it across all columns in the current row
	                    	    Pane blackRow = new Pane();
	                    	    blackRow.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));
	                    	    lanes.add(blackRow, 0, currentRow, numCols, 1); // Span all columns

	                    	    // Bring the black row to the front to ensure it overlays other nodes
	                    	    GridPane.setColumnSpan(blackRow, GridPane.REMAINING);
	                    	    blackRow.toFront();
	                    	}
	                        double progressValue = (double) currentLane.getLaneWall().getCurrentHealth() / 10000.0;

	                        // Update the corresponding progress bar
	                        switch (laneIndex[0]) {
	                            case 0:
	                                p1.setProgress(progressValue);
	                                System.out.println("1");
	                                break;
	                            case 1:
	                                p2.setProgress(progressValue);
	                                System.out.println("2");
	                                break;
	                            case 2:
	                                p3.setProgress(progressValue);
	                                System.out.println("3");
	                                break;
	                            case 3:
	                                p4.setProgress(progressValue);
	                                System.out.println("4");
	                                break;
	                            case 4:
	                                p5.setProgress(progressValue);
	                                System.out.println("5");
	                                break;
	                            default:
	                            	System.out.println("wtf");
	                                break;
	                        }

	                        // Handle titans in the lane
	                        for (Titan titan : currentLane.getTitans()) { // Use original titans
	                            TitanChar tit = new TitanChar(titan,easy);
	                            if (easy){
	                            	lanes.setVgap(100);
	                            }
	                            else{
	                            	lanes.setVgap(40);
	                            }
	                            lanes.add(tit.vbox, tit.t.getDistance(), laneIndex[0]); // Add the TitanChar to the GridPane
	                            GridPane.setHalignment(tit.vbox, HPos.CENTER);
	                            if (tit.t.isDefeated()) {
	                                lanes.getChildren().remove(tit.vbox);
	                            }
	                        }
	                        laneIndex[0]++; // Increment the lane counter
	                    }

	                    // Check if the game is over
	                    if (battle.isGameOver()) {
	                    	Backgroundsoundplayer.stop();
	                        primaryStage.setScene(scene6);
	                        Text endText = new Text("Game Over" + "\n" + "Final Score:" + battle.getScore());
	                        StackPane endPane = new StackPane(endText);
	                        endText.setFont(Font.font("Times New Roman", 100));
	                        endText.setStyle("-fx-fill: white;");
	                        Scene endScene = new Scene(endPane, 1600, 800);
	                        endPane.setStyle("-fx-background-color: black;");
	                        

	                        Image restartb = new Image("restartbutton.png");
	                        ImageView imageViews = new ImageView(restartb);
	                        imageViews.setFitWidth(700); 
	                        imageViews.setFitHeight(700);
	        	            imageViews.setPreserveRatio(true);
	        	            Button restart = new Button();
	        	            restart.setGraphic(imageViews);
	        	            restart.setStyle("-fx-background-color: transparent;"); // Make the button background transparent
	        	            endPane.getChildren().add(restart);
	        	            restart.setTranslateY(250);
	        	            restart.setPrefSize(200, 100);
	        	            restart.setOnAction(new EventHandler<ActionEvent>() {
	        	                @Override
	        	                public void handle(ActionEvent event) {
	        	                    primaryStage.setScene(scene2);
	        	                    mediaPlayer.stop();
	        	                }
	        	            });
	        	            mediaPlayer.setOnEndOfMedia(() -> restart.setVisible(true));
	        	            
	                        mediaPlayer1.play();
	                        mediaPlayer1.setOnEndOfMedia(() -> primaryStage.setScene(endScene));
	                    }
	                }
	            });
		          //Weapon action
	            W1.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                    try {
							battle.purchaseWeapon(1, CurrLane);
						} catch (InsufficientResourcesException e) {
							displayAlert("Not Enough Resources!","You don't have enough resources to buy this weapon");
							e.printStackTrace();
						} catch (InvalidLaneException e) {
							displayAlert("Invalid Lane!","The specified lane is invalid.");
							e.printStackTrace();
						}
	                    updateAndSpawn();

	                    // Clear all nodes except those in column 0
	                    lanes.getChildren().removeIf(node -> {
	                        Integer columnIndex = GridPane.getColumnIndex(node);
	                        // Remove the node if it is not in column 0 (consider null as column 0)
	                        System.out.println("cleared");
	                        return columnIndex == null || columnIndex != 0;
	                    });

	                    // Add empty nodes to all columns except column 0
	                    int rownum;
	                    if (easy){
	                    	rownum=3;
	                    	System.out.println("easy");
	                    }
	                    else{
	                    	rownum=5;
	                    	System.out.println("hard");
	                    }
	                    for (int row = 0; row < rownum; row++) { // Assuming 3 rows, adjust as needed
	                        for (int col = 1; col < 50; col++) { // Assuming 50 columns, adjust as needed
	                            Region emptyNode = new Region();
	                            // Set the preferred size of the empty node if needed
	                            emptyNode.setPrefSize(50, 50); // Adjust the size as per your requirements
	                            lanes.add(emptyNode, col, row);
	                            System.out.println("put empty");
	                        }
	                    }

	                    int[] laneIndex = {0}; // Use an array to hold the lane index

	                    for (Lane currentLane : battle.getOriginalLanes()) { // Use original lanes
	                        // Check if the lane is lost and set the row to black
	                    	System.out.println("for");
	                    	if (currentLane.isLaneLost()) {
	                    	    System.out.println("lane lost");
	                    	    int currentRow = laneIndex[0];

	                    	    // Calculate the number of columns
	                    	    int numCols = lanes.getColumnConstraints().size();
	                    	    if (numCols == 0) {
	                    	        // If no column constraints are set, we assume the maximum column index + 1
	                    	        for (Node node : lanes.getChildren()) {
	                    	            Integer colIndex = GridPane.getColumnIndex(node);
	                    	            if (colIndex != null && colIndex + 1 > numCols) {
	                    	                numCols = colIndex + 1;
	                    	            }
	                    	        }
	                    	    }

	                    	    // Create a black pane and span it across all columns in the current row
	                    	    Pane blackRow = new Pane();
	                    	    blackRow.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));
	                    	    lanes.add(blackRow, 0, currentRow, numCols, 1); // Span all columns

	                    	    // Bring the black row to the front to ensure it overlays other nodes
	                    	    GridPane.setColumnSpan(blackRow, GridPane.REMAINING);
	                    	    blackRow.toFront();
	                    	}

	                        double progressValue = (double) currentLane.getLaneWall().getCurrentHealth() / 10000.0;

	                        // Update the corresponding progress bar
	                        switch (laneIndex[0]) {
	                            case 0:
	                                p1.setProgress(progressValue);
	                                System.out.println("1");
	                                break;
	                            case 1:
	                                p2.setProgress(progressValue);
	                                System.out.println("2");
	                                break;
	                            case 2:
	                                p3.setProgress(progressValue);
	                                System.out.println("3");
	                                break;
	                            case 3:
	                                p4.setProgress(progressValue);
	                                System.out.println("4");
	                                break;
	                            case 4:
	                                p5.setProgress(progressValue);
	                                System.out.println("5");
	                                break;
	                            default:
	                            	System.out.println("wtf");
	                                break;
	                        }

	                        // Handle titans in the lane
	                        for (Titan titan : currentLane.getTitans()) { // Use original titans
	                            TitanChar tit = new TitanChar(titan,easy);
	                            if (easy){
	                            	lanes.setVgap(100);
	                            }
	                            else{
	                            	lanes.setVgap(40);
	                            }
	                           
	                            lanes.add(tit.vbox, tit.t.getDistance(), laneIndex[0]); // Add the TitanChar to the GridPane
	                            if (tit.t.isDefeated()) {
	                                lanes.getChildren().remove(tit.vbox);
	                            }
	                        }
	                        laneIndex[0]++; // Increment the lane counter
	                    }

	                    // Check if the game is over
	                    if (battle.isGameOver()) {
	                    	Backgroundsoundplayer.stop();
	                        primaryStage.setScene(scene6);
	                        Text endText = new Text("Game Over" + "\n" + "Final Score:" + battle.getScore());
	                        StackPane endPane = new StackPane(endText);
	                        endText.setFont(Font.font("Times New Roman", 100));
	                        endText.setStyle("-fx-fill: white;");
	                        Scene endScene = new Scene(endPane, 1600, 800);
	                        endPane.setStyle("-fx-background-color: black;");
	                        

	                        Image restartb = new Image("restartbutton.png");
	                        ImageView imageViews = new ImageView(restartb);
	                        imageViews.setFitWidth(700); 
	                        imageViews.setFitHeight(700);
	        	            imageViews.setPreserveRatio(true);
	        	            Button restart = new Button();
	        	            restart.setGraphic(imageViews);
	        	            restart.setStyle("-fx-background-color: transparent;"); // Make the button background transparent
	        	            endPane.getChildren().add(restart);
	        	            restart.setTranslateY(250);
	        	            restart.setPrefSize(200, 100);
	        	            restart.setOnAction(new EventHandler<ActionEvent>() {
	        	                @Override
	        	                public void handle(ActionEvent event) {
	        	                    primaryStage.setScene(scene2);
	        	                    mediaPlayer.stop();
	        	                }
	        	            });
	        	            mediaPlayer.setOnEndOfMedia(() -> restart.setVisible(true));
	        	            
	                        mediaPlayer1.play();
	                        mediaPlayer1.setOnEndOfMedia(() -> primaryStage.setScene(endScene));
	                    }
	                }

					private void displayAlert(String title, String message) {
						Stage alertStage = new Stage();
				        alertStage.setTitle(title);

				        Label label = new Label(message);
				        Button closeButton = new Button("return to game");
				        closeButton.setOnAction(event -> alertStage.close());

				        BorderPane pane = new BorderPane();
				        pane.setTop(label);
				        pane.setCenter(closeButton);

				        Scene scene = new Scene(pane, 500, 100);
				        alertStage.setScene(scene);
				        alertStage.show();
						
						
					}
	            });	
	            W2.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                    try {
							battle.purchaseWeapon(2, CurrLane);
						} catch (InsufficientResourcesException e) {
							displayAlert("Not Enough Resources!","You don't have enough resources to buy this weapon");
							e.printStackTrace();
						} catch (InvalidLaneException e) {
							displayAlert("Invalid Lane!","The specified lane is invalid.");
							e.printStackTrace();
						}
	                    updateAndSpawn();

	                    // Clear all nodes except those in column 0
	                    lanes.getChildren().removeIf(node -> {
	                        Integer columnIndex = GridPane.getColumnIndex(node);
	                        // Remove the node if it is not in column 0 (consider null as column 0)
	                        System.out.println("cleared");
	                        return columnIndex == null || columnIndex != 0;
	                    });

	                    // Add empty nodes to all columns except column 0
	                    int rownum;
	                    if (easy){
	                    	rownum=3;
	                    	System.out.println("easy");
	                    }
	                    else{
	                    	rownum=5;
	                    	System.out.println("hard");
	                    }
	                    for (int row = 0; row < rownum; row++) { // Assuming 3 rows, adjust as needed
	                        for (int col = 1; col < 50; col++) { // Assuming 50 columns, adjust as needed
	                            Region emptyNode = new Region();
	                            // Set the preferred size of the empty node if needed
	                            emptyNode.setPrefSize(50, 50); // Adjust the size as per your requirements
	                            lanes.add(emptyNode, col, row);
	                            System.out.println("put empty");
	                        }
	                    }

	                    int[] laneIndex = {0}; // Use an array to hold the lane index

	                    for (Lane currentLane : battle.getOriginalLanes()) { // Use original lanes
	                        // Check if the lane is lost and set the row to black
	                    	System.out.println("for");
	                        if (currentLane.isLaneLost()) {
	                            System.out.println("lane lost");
	                            int currentRow = laneIndex[0];
	                            lanes.getChildren().removeIf(node -> {
	                                Integer rowIndex = GridPane.getRowIndex(node);
	                                return rowIndex != null && rowIndex == currentRow;
	                            });
	                            Pane blackRow = new Pane();
	                            blackRow.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));
	                            lanes.add(blackRow, 0, currentRow, 50, 1); // Spanning all columns in the grid
	                        }

	                        double progressValue = (double) currentLane.getLaneWall().getCurrentHealth() / 10000.0;

	                        // Update the corresponding progress bar
	                        switch (laneIndex[0]) {
	                            case 0:
	                                p1.setProgress(progressValue);
	                                System.out.println("1");
	                                break;
	                            case 1:
	                                p2.setProgress(progressValue);
	                                System.out.println("2");
	                                break;
	                            case 2:
	                                p3.setProgress(progressValue);
	                                System.out.println("3");
	                                break;
	                            case 3:
	                                p4.setProgress(progressValue);
	                                System.out.println("4");
	                                break;
	                            case 4:
	                                p5.setProgress(progressValue);
	                                System.out.println("5");
	                                break;
	                            default:
	                            	System.out.println("wtf");
	                                break;
	                        }

	                        // Handle titans in the lane
	                        for (Titan titan : currentLane.getTitans()) { // Use original titans
	                            TitanChar tit = new TitanChar(titan,easy);
	                            if (easy){
	                            	lanes.setVgap(100);
	                            }
	                            else{
	                            	lanes.setVgap(40);
	                            }
	                            lanes.add(tit.vbox, tit.t.getDistance(), laneIndex[0]); // Add the TitanChar to the GridPane
	                            if (tit.t.isDefeated()) {
	                                lanes.getChildren().remove(tit.vbox);
	                            }
	                        }
	                        laneIndex[0]++; // Increment the lane counter
	                    }

	                    // Check if the game is over
	                    if (battle.isGameOver()) {
	                    	Backgroundsoundplayer.stop();
	                        primaryStage.setScene(scene6);
	                        Text endText = new Text("Game Over" + "\n" + "Final Score:" + battle.getScore());
	                        StackPane endPane = new StackPane(endText);
	                        endText.setFont(Font.font("Times New Roman", 100));
	                        endText.setStyle("-fx-fill: white;");
	                        Scene endScene = new Scene(endPane, 1600, 800);
	                        endPane.setStyle("-fx-background-color: black;");
	                        

	                        Image restartb = new Image("restartbutton.png");
	                        ImageView imageViews = new ImageView(restartb);
	                        imageViews.setFitWidth(700); 
	                        imageViews.setFitHeight(700);
	        	            imageViews.setPreserveRatio(true);
	        	            Button restart = new Button();
	        	            restart.setGraphic(imageViews);
	        	            restart.setStyle("-fx-background-color: transparent;"); // Make the button background transparent
	        	            endPane.getChildren().add(restart);
	        	            restart.setTranslateY(250);
	        	            restart.setPrefSize(200, 100);
	        	            restart.setOnAction(new EventHandler<ActionEvent>() {
	        	                @Override
	        	                public void handle(ActionEvent event) {
	        	                    primaryStage.setScene(scene2);
	        	                    mediaPlayer.stop();
	        	                }
	        	            });
	        	            mediaPlayer.setOnEndOfMedia(() -> restart.setVisible(true));
	        	            
	                        mediaPlayer1.play();
	                        mediaPlayer1.setOnEndOfMedia(() -> primaryStage.setScene(endScene));
	                    }
	                }

					private void displayAlert(String title, String message) {
						Stage alertStage = new Stage();
				        alertStage.setTitle(title);

				        Label label = new Label(message);
				        Button closeButton = new Button("return to game");
				        closeButton.setOnAction(event -> alertStage.close());

				        BorderPane pane = new BorderPane();
				        pane.setTop(label);
				        pane.setCenter(closeButton);

				        Scene scene = new Scene(pane, 500, 100);
				        alertStage.setScene(scene);
				        alertStage.show();
						
					}
	            });
	            W3.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                    try {
							battle.purchaseWeapon(3, CurrLane);
						} catch (InsufficientResourcesException e) {
							displayAlert("Not Enough Resources!","You don't have enough resources to buy this weapon");
							e.printStackTrace();
						} catch (InvalidLaneException e) {
							displayAlert("Invalid Lane!","The specified lane is invalid.");
							e.printStackTrace();
						}
	                    updateAndSpawn();

	                    // Clear all nodes except those in column 0
	                    lanes.getChildren().removeIf(node -> {
	                        Integer columnIndex = GridPane.getColumnIndex(node);
	                        // Remove the node if it is not in column 0 (consider null as column 0)
	                        System.out.println("cleared");
	                        return columnIndex == null || columnIndex != 0;
	                    });

	                    // Add empty nodes to all columns except column 0
	                    int rownum;
	                    if (easy){
	                    	rownum=3;
	                    	System.out.println("easy");
	                    }
	                    else{
	                    	rownum=5;
	                    	System.out.println("hard");
	                    }
	                    for (int row = 0; row < rownum; row++) { // Assuming 3 rows, adjust as needed
	                        for (int col = 1; col < 50; col++) { // Assuming 50 columns, adjust as needed
	                            Region emptyNode = new Region();
	                            // Set the preferred size of the empty node if needed
	                            emptyNode.setPrefSize(50, 50); // Adjust the size as per your requirements
	                            lanes.add(emptyNode, col, row);
	                            System.out.println("put empty");
	                        }
	                    }

	                    int[] laneIndex = {0}; // Use an array to hold the lane index

	                    for (Lane currentLane : battle.getOriginalLanes()) { // Use original lanes
	                        // Check if the lane is lost and set the row to black
	                    	System.out.println("for");
	                        if (currentLane.isLaneLost()) {
	                            System.out.println("lane lost");
	                            int currentRow = laneIndex[0];
	                            lanes.getChildren().removeIf(node -> {
	                                Integer rowIndex = GridPane.getRowIndex(node);
	                                return rowIndex != null && rowIndex == currentRow;
	                            });
	                            Pane blackRow = new Pane();
	                            blackRow.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));
	                            lanes.add(blackRow, 0, currentRow, 50, 1); // Spanning all columns in the grid
	                        }

	                        double progressValue = (double) currentLane.getLaneWall().getCurrentHealth() / 10000.0;

	                        // Update the corresponding progress bar
	                        switch (laneIndex[0]) {
	                            case 0:
	                                p1.setProgress(progressValue);
	                                System.out.println("1");
	                                break;
	                            case 1:
	                                p2.setProgress(progressValue);
	                                System.out.println("2");
	                                break;
	                            case 2:
	                                p3.setProgress(progressValue);
	                                System.out.println("3");
	                                break;
	                            case 3:
	                                p4.setProgress(progressValue);
	                                System.out.println("4");
	                                break;
	                            case 4:
	                                p5.setProgress(progressValue);
	                                System.out.println("5");
	                                break;
	                            default:
	                            	System.out.println("wtf");
	                                break;
	                        }

	                        // Handle titans in the lane
	                        for (Titan titan : currentLane.getTitans()) { // Use original titans
	                            TitanChar tit = new TitanChar(titan,easy);
	                            if (easy){
	                            	lanes.setVgap(100);
	                            }
	                            else{
	                            	lanes.setVgap(40);
	                            }
	                            lanes.add(tit.vbox, tit.t.getDistance(), laneIndex[0]); // Add the TitanChar to the GridPane
	                            if (tit.t.isDefeated()) {
	                                lanes.getChildren().remove(tit.vbox);
	                            }
	                        }
	                        laneIndex[0]++; // Increment the lane counter
	                    }

	                    // Check if the game is over
	                    if (battle.isGameOver()) {
	                    	Backgroundsoundplayer.stop();
	                        primaryStage.setScene(scene6);
	                        Text endText = new Text("Game Over" + "\n" + "Final Score:" + battle.getScore());
	                        StackPane endPane = new StackPane(endText);
	                        endText.setFont(Font.font("Times New Roman", 100));
	                        endText.setStyle("-fx-fill: white;");
	                        Scene endScene = new Scene(endPane, 1600, 800);
	                        endPane.setStyle("-fx-background-color: black;");
	                        

	                        Image restartb = new Image("restartbutton.png");
	                        ImageView imageViews = new ImageView(restartb);
	                        imageViews.setFitWidth(700); 
	                        imageViews.setFitHeight(700);
	        	            imageViews.setPreserveRatio(true);
	        	            Button restart = new Button();
	        	            restart.setGraphic(imageViews);
	        	            restart.setStyle("-fx-background-color: transparent;"); // Make the button background transparent
	        	            endPane.getChildren().add(restart);
	        	            restart.setTranslateY(250);
	        	            restart.setPrefSize(200, 100);
	        	            restart.setOnAction(new EventHandler<ActionEvent>() {
	        	                @Override
	        	                public void handle(ActionEvent event) {
	        	                    primaryStage.setScene(scene2);
	        	                    mediaPlayer.stop();
	        	                }
	        	            });
	        	            mediaPlayer.setOnEndOfMedia(() -> restart.setVisible(true));
	        	            
	                        mediaPlayer1.play();
	                        mediaPlayer1.setOnEndOfMedia(() -> primaryStage.setScene(endScene));
	                    }
	                }

					private void displayAlert(String title, String message) {
						Stage alertStage = new Stage();
				        alertStage.setTitle(title);

				        Label label = new Label(message);
				        Button closeButton = new Button("return to game");
				        closeButton.setOnAction(event -> alertStage.close());

				        BorderPane pane = new BorderPane();
				        pane.setTop(label);
				        pane.setCenter(closeButton);

				        Scene scene = new Scene(pane, 500, 100);
				        alertStage.setScene(scene);
				        alertStage.show();
						
					}
	            });
	            W4.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent event) {
	                    try {
							battle.purchaseWeapon(4, CurrLane);
						} catch (InsufficientResourcesException e) {
							displayAlert("Not Enough Resources!","You don't have enough resources to buy this weapon");
							e.printStackTrace();
						} catch (InvalidLaneException e) {
							displayAlert("Invalid Lane!","The specified lane is invalid.");
							e.printStackTrace();
						}
	                    updateAndSpawn();

	                    // Clear all nodes except those in column 0
	                    lanes.getChildren().removeIf(node -> {
	                        Integer columnIndex = GridPane.getColumnIndex(node);
	                        // Remove the node if it is not in column 0 (consider null as column 0)
	                        System.out.println("cleared");
	                        return columnIndex == null || columnIndex != 0;
	                    });

	                    // Add empty nodes to all columns except column 0
	                    int rownum;
	                    if (easy){
	                    	rownum=3;
	                    	System.out.println("easy");
	                    }
	                    else{
	                    	rownum=5;
	                    	System.out.println("hard");
	                    }
	                    for (int row = 0; row < rownum; row++) { // Assuming 3 rows, adjust as needed
	                        for (int col = 1; col < 50; col++) { // Assuming 50 columns, adjust as needed
	                            Region emptyNode = new Region();
	                            // Set the preferred size of the empty node if needed
	                            emptyNode.setPrefSize(50, 50); // Adjust the size as per your requirements
	                            lanes.add(emptyNode, col, row);
	                            System.out.println("put empty");
	                        }
	                    }

	                    int[] laneIndex = {0}; // Use an array to hold the lane index

	                    for (Lane currentLane : battle.getOriginalLanes()) { // Use original lanes
	                        // Check if the lane is lost and set the row to black
	                    	System.out.println("for");
	                        if (currentLane.isLaneLost()) {
	                            System.out.println("lane lost");
	                            int currentRow = laneIndex[0];
	                            lanes.getChildren().removeIf(node -> {
	                                Integer rowIndex = GridPane.getRowIndex(node);
	                                return rowIndex != null && rowIndex == currentRow;
	                            });
	                            Pane blackRow = new Pane();
	                            blackRow.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, null)));
	                            lanes.add(blackRow, 0, currentRow, 50, 1); // Spanning all columns in the grid
	                        }

	                        double progressValue = (double) currentLane.getLaneWall().getCurrentHealth() / currentLane.getLaneWall().getBaseHealth();

	                        // Update the corresponding progress bar
	                        switch (laneIndex[0]) {
	                            case 0:
	                                p1.setProgress(progressValue);
	                                System.out.println("1");
	                                break;
	                            case 1:
	                                p2.setProgress(progressValue);
	                                System.out.println("2");
	                                break;
	                            case 2:
	                                p3.setProgress(progressValue);
	                                System.out.println("3");
	                                break;
	                            case 3:
	                                p4.setProgress(progressValue);
	                                System.out.println("4");
	                                break;
	                            case 4:
	                                p5.setProgress(progressValue);
	                                System.out.println("5");
	                                break;
	                            default:
	                            	System.out.println("wtf");
	                                break;
	                        }

	                        // Handle titans in the lane
	                        for (Titan titan : currentLane.getTitans()) { // Use original titans
	                            TitanChar tit = new TitanChar(titan,easy);
	                            if (easy){
	                            	lanes.setVgap(100);
	                            }
	                            else{
	                            	lanes.setVgap(40);
	                            }
	                            lanes.add(tit.vbox, tit.t.getDistance(), laneIndex[0]); // Add the TitanChar to the GridPane
	                            if (tit.t.isDefeated()) {
	                                lanes.getChildren().remove(tit.vbox);
	                            }
	                        }
	                        laneIndex[0]++; // Increment the lane counter
	                    }

	                    // Check if the game is over
	                    if (battle.isGameOver()) {
	                    	Backgroundsoundplayer.stop();
	                        primaryStage.setScene(scene6);
	                        Text endText = new Text("Game Over" + "\n" + "Final Score:" + battle.getScore());
	                        StackPane endPane = new StackPane(endText);
	                        endText.setFont(Font.font("Times New Roman", 100));
	                        endText.setStyle("-fx-fill: white;");
	                        Scene endScene = new Scene(endPane, 1600, 800);
	                        endPane.setStyle("-fx-background-color: black;");
	                        

	                        Image restartb = new Image("restartbutton.png");
	                        ImageView imageViews = new ImageView(restartb);
	                        imageViews.setFitWidth(700); 
	                        imageViews.setFitHeight(700);
	        	            imageViews.setPreserveRatio(true);
	        	            Button restart = new Button();
	        	            restart.setGraphic(imageViews);
	        	            restart.setStyle("-fx-background-color: transparent;"); // Make the button background transparent
	        	            endPane.getChildren().add(restart);
	        	            restart.setTranslateY(250);
	        	            restart.setPrefSize(200, 100);
	        	            restart.setOnAction(new EventHandler<ActionEvent>() {
	        	                @Override
	        	                public void handle(ActionEvent event) {
	        	                    primaryStage.setScene(scene2);
	        	                    mediaPlayer.stop();
	        	                }
	        	            });
	        	            mediaPlayer.setOnEndOfMedia(() -> restart.setVisible(true));
	        	            
	                        mediaPlayer1.play();
	                        mediaPlayer1.setOnEndOfMedia(() -> primaryStage.setScene(endScene));
	                    }
	                }

					private void displayAlert(String title, String message) {
						Stage alertStage = new Stage();
				        alertStage.setTitle(title);

				        Label label = new Label(message);
				        Button closeButton = new Button("return to game");
				        closeButton.setOnAction(event -> alertStage.close());

				        BorderPane pane = new BorderPane();
				        pane.setTop(label);
				        pane.setCenter(closeButton);

				        Scene scene = new Scene(pane, 500, 100);
				        alertStage.setScene(scene);
				        alertStage.show();
						
					}
	            });
	            

	            // Method to remove all nodes in a specific row
	            


	            passroot.setCenter(PassTurn);
	            
	
	            try {
	                Image icon = new Image(new FileInputStream("C:\\Users\\HP\\Desktop\\logo.png"));
	                primaryStage.getIcons().add(icon);
	            } catch (FileNotFoundException e) {
	                System.out.println("Icon file not found: " + e.getMessage());
	            }
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    
	    }
	    
	    
	    
	    
	    public static void updateAndSpawn(){
	    	CurrentScoreNum.setText("" + battle.getScore());
	        CurrentTurnNum.setText("" + battle.getNumberOfTurns());
	        CurrentPhaseNum.setText("" + battle.getBattlePhase());
	        CurrentResourcesNum.setText("" + battle.getResourcesGathered());

	    }
	    
	    public static void main(String[] args) {
	        launch(args);
	    }
	 }
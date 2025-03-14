package game.gui;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import game.engine.titans.Titan;

public class TitanChar {

    Titan t;
    Image t1 = new Image("puree.png");
    Image t2 = new Image("abnorm1.png");
    Image t3 = new Image("armo.png");
    Image t4 = new Image("cola.png");
    ProgressBar progressBar;
    VBox vbox; // Define VBox as a class attribute
    
    // Desired width and height for the images


    public TitanChar(Titan t,boolean easy) {
        this.t = t;
        
        // Resize the images
        if (easy){
	        t1 = resizeImage(t1, 60, 100);
	        t2 = resizeImage(t2, 50, 83);
	        t3 = resizeImage(t3, 60, 100);
	        t4 = resizeImage(t4, 166, 166);
        }
        else{
        	t1 = resizeImage(t1, 60, 60);
	        t2 = resizeImage(t2, 50, 50);
	        t3 = resizeImage(t3, 60, 60);
	        t4 = resizeImage(t4, 100, 100);
        }
        // Initialize the ProgressBar
        progressBar = new ProgressBar();
        double pg = (double)t.getCurrentHealth()/t.getBaseHealth();
        System.out.println((double)t.getCurrentHealth()/t.getBaseHealth());
        progressBar.setProgress(pg); // Example progress

        // Initialize the ImageView based on the Titan's danger level
        ImageView imageView = new ImageView();
        switch (t.getDangerLevel()) {
            case 1:
                imageView.setImage(t1);
                break;
            case 2:
                imageView.setImage(t2);
                break;
            case 3:
                imageView.setImage(t3);
                break;
            case 4:
                imageView.setImage(t4);
                break;
            default:
                // Set a default image or handle the case where the danger level is out of range
                imageView.setImage(t1);
                break;
        }

        // Create a Label and set the ImageView as its graphic
        Label label = new Label();
        label.setGraphic(imageView);

        // Initialize the VBox layout and add the progress bar and label
        vbox = new VBox(progressBar, label);
        vbox.setSpacing(10);
    }

    // Method to get the VBox
    public VBox getVBox() {
        return vbox;
    }

    // Example method to update the progress
    public void updateProgress(double progress) {
        progressBar.setProgress(progress);
    }
    
    // Method to resize an image
    private Image resizeImage(Image image, double width, double height) {
        // Create a WritableImage with the desired dimensions
        WritableImage resizedImage = new WritableImage((int) width, (int) height);
        
        // Get the PixelReader from the original image
        PixelReader pixelReader = image.getPixelReader();
        
        // Copy pixels from the original image to the resized image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Calculate the corresponding coordinates in the original image
                double sourceX = x / width * image.getWidth();
                double sourceY = y / height * image.getHeight();
                
                // Read the color of the pixel from the original image
                int argb = pixelReader.getArgb((int) sourceX, (int) sourceY);
                
                // Write the color to the corresponding pixel in the resized image
                resizedImage.getPixelWriter().setArgb(x, y, argb);
            }
        }
        
        // Return the resized image
        return resizedImage;
    }
}

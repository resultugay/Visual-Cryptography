/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import static cryptography.Crypto.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.layout.Border;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

/**
 *
 * @author resultugay
 */
public class CryptoJob extends Thread{
        
    @Override
    
    public void run(){        
       //  checkSelectedImages();
         Crypto.Image2 = resize();  
         //Take Color values from images to handle.
        //Create a new image object with the size of first image.
         String newImage[][] = new String[Crypto.Image1.getWidth()][Crypto.Image1.getHeight()];
         
         //Crypt taken images and return string of bits of the pixels.
          newImage = crypt(Image1Job.binariesImage1,Image2Job.binariesImage2);
          ProgressBar.setValue(50);
         //Get red ,green and blue values from the new image to have a real image. 
           int red [][] = getRGBs(newImage,0,8);
           int green [][] = getRGBs(newImage, 8, 16);
           int blue [][] = getRGBs(newImage, 16, 24);
         //Color values from the Color arrays.  
           Color newone  [][] = new Color[Crypto.Image1.getWidth()][Crypto.Image1.getHeight()];
         //With the for loop we get the new image's color  
               for(int i = 0 ; i<Crypto.Image1.getWidth(); i++)
                     for(int j = 0; j <Crypto.Image1.getHeight();j++ ){
                          //Color temp = new Color(green[i][j] ,blue[i][j],red[i][j]);
                         Color temp = new Color(red[i][j],green[i][j] ,blue[i][j]);
                         newone[i][j] = temp;
                     }
        //Create new image with the size of first image.       
        BufferedImage newoneImage = new  BufferedImage(Crypto.Image1.getWidth(),Crypto.Image1.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

        ProgressBar.setValue(75);
        //setRGB to get new encripted-image.
        for(int i = 0 ; i<Crypto.Image1.getWidth(); i++){
                     for(int j = 0; j <Crypto.Image1.getHeight();j++ ){
                         newoneImage.setRGB(i, j, newone[i][j].getRGB());
                     }
                  
        }
        ProgressBar.setValue(100);
        errorLabel.setText(Crypto.file1.getPath().replace(Crypto.file1.getName(), "Crypted.jpg"));
        //Save the new image to the path where the first image has been chosen.
        File file = new File(Crypto.file1.getPath().replace(Crypto.file1.getName(), "Crypted.jpg"));
              try{
               ImageIO.write(newoneImage, "png", file);
              }catch(IOException es){
                  JOptionPane.showMessageDialog(null, "The picture could not save");
              }
    }
         //Check whether the Images was chosen or not.
            private void checkSelectedImages(){
            if(Crypto.Image1 == null){
                    JOptionPane.showMessageDialog(null, "Please choose first image");
                   Crypto.Image1Button.doClick();
                    if(Crypto.Image2 == null){
                        JOptionPane.showMessageDialog(null, "Please choose secret image");
                        Crypto.Image2Button.doClick();
                    }
                }else if (Crypto.Image2 == null){
                    JOptionPane.showMessageDialog(null, "Please choose secret image");
                    Crypto.Image2Button.doClick();
                 }
        
        }
            
        //İf the images are not convenient for cryptography resize them.          
        private BufferedImage  resize(){
              
                        Crypto.Image1 = reSizeFirstImage();
                         if(check(Crypto.Image1, Crypto.Image2) == false){
                         // Image2 = new BufferedImage(Image1.getWidth()/4, Image1.getHeight()/4, BufferedImage.TYPE_INT_ARGB);
                         Crypto.Image2.getScaledInstance(Crypto.Image1.getWidth()/4, Crypto.Image1.getHeight()/4, Image.SCALE_DEFAULT);
                         BufferedImage scaledBI = new BufferedImage(Crypto.Image1.getWidth()/4, Crypto.Image1.getHeight()/4,  Crypto.Image1.getType());
                         Graphics2D g = scaledBI.createGraphics();
                         g.drawImage(Crypto.Image2, 0, 0, Crypto.Image1.getWidth()/4, Crypto.Image1.getHeight()/4, null); 
                         g.dispose();
                                                               
                         return scaledBI;
                         
                     }
                    return Crypto.Image2;
                    
        }
        
        //resize first image if its not convenient.
                private BufferedImage reSizeFirstImage(){
        
                if((Crypto.Image1.getWidth()%4) != 0){
                if((Crypto.Image1.getHeight()%4) != 0){
                BufferedImage scaledBI = new BufferedImage((Crypto.Image1.getWidth()/4)*4, (Crypto.Image1.getHeight()/4)*4,  Crypto.Image1.getType());
                Graphics2D g = scaledBI.createGraphics();
                g.drawImage(Crypto.Image1, 0, 0, scaledBI.getWidth(), scaledBI.getHeight(), null); 
                g.dispose();
                                    return scaledBI;
                }
               // Image1.getScaledInstance(Image1.getWidth() + 1, Image1.getHeight(), Image.SCALE_DEFAULT);
                BufferedImage scaledBI = new BufferedImage((Crypto.Image1.getWidth()/4)*4, Crypto.Image1.getHeight(),  Crypto.Image1.getType());
                Graphics2D g = scaledBI.createGraphics();
                g.drawImage(Crypto.Image1, 0, 0, scaledBI.getWidth(), scaledBI.getHeight(), null); 
                g.dispose();
               
                return scaledBI;
            
            }else  if((Crypto.Image1.getHeight()%4) != 0){
                BufferedImage scaledBI = new BufferedImage(Crypto.Image1.getWidth(), (Crypto.Image1.getHeight()/4)*4,  Crypto.Image1.getType());
                Graphics2D g = scaledBI.createGraphics();
                g.drawImage(Crypto.Image1, 0, 0, scaledBI.getWidth(), scaledBI.getHeight(), null); 
                g.dispose();
                
                return scaledBI;
            }
            return Crypto.Image1;
        
        }
           //Images are beimg checked.    
           private boolean check(BufferedImage img1,BufferedImage img2){
          
            if(img2.getWidth() != img1.getWidth()/4){
           
                return false;
            }else if(img2.getHeight() != img1.getHeight()/4)
            {
        
                return false;
            }
            return true;
        }
           
           //Get the RGB values from the given image as parameter.
           private int [][] getRGBValues(BufferedImage img){
            
            int RGBValues [][] = new int[img.getWidth()][img.getHeight()];
            for(int width = 0; width < img.getWidth() ; width++)
                for(int height = 0 ; height < img.getHeight(); height++){
                    int rgb = img.getRGB(width, height);
                    RGBValues[width][height] = rgb;
                }
           return RGBValues;
            
        }
        //Get Color array given RGB 2D dimensions array.
        private Color [][] getColor(int RGB[][],int width,int height){
            
            Color col[][] = new Color[width][height];
            for(int i = 0 ; i < width ; i++){
                for(int j = 0 ; j < height ; j++){
                    col[i][j] = new Color(RGB[i][j]);
                }
            }
                return col;
        }
        
        
         private String [][] crypt(Color [][] image1,Color [][] image2){
           String crypted [][] = new String[Crypto.Image1.getWidth()][Crypto.Image1.getHeight()] ;
                   // String alphaTemp;
                    String redTemp;
                    String greenTemp;
                    String blueTemp;
                   int i = 0;
                //Actually the criptology is just here.With the first 6-bit of pixel of the second image
                // is merged with last 2-bit of pixel of the first image.And we get the new image as same
                //the second one without deformation.(inapparently)
                   
            for(int width = 0 ; width < Crypto.Image1.getWidth() ; width++){
                for(int height = 0 ; height < Crypto.Image1.getHeight(); height++){
                 redTemp = String.format("%8s", Integer.toBinaryString(image1[width][height].getRed())).replace(' ', '0').substring(0, 6) + String.format("%8s", Integer.toBinaryString(image2[width/4][height/4].getRed())).replace(' ', '0').substring(i, i+2); 
                  greenTemp = String.format("%8s", Integer.toBinaryString(image1[width][height].getGreen())).replace(' ', '0').substring(0, 6) + String.format("%8s", Integer.toBinaryString(image2[width/4][height/4].getGreen())).replace(' ', '0').substring(i, i+2); 
                  blueTemp = String.format("%8s", Integer.toBinaryString(image1[width][height].getBlue())).replace(' ', '0').substring(0, 6) + String.format("%8s", Integer.toBinaryString(image2[width/4][height/4].getBlue())).replace(' ', '0').substring(i, i+2); 
                 
                   i = i + 2;
                   if(i == 8){
                       i = 0;
                    }
                  crypted[width][height] = redTemp+greenTemp+blueTemp;
                }
                            
            }
            
            return crypted;
        }
        public int [][] getRGBs(String newImage[][],int start,int end){
            
                int temp[][] = new int [Crypto.Image1.getWidth()][Crypto.Image1.getHeight()];
                 for(int i = 0 ; i<Crypto.Image1.getWidth(); i++)
                     for(int j = 0; j <Crypto.Image1.getHeight();j++ ){
                         temp [i][j] = Integer.parseInt(newImage[i][j].substring(start, end),2);
                  }
                 return temp;
            
        }
    
}

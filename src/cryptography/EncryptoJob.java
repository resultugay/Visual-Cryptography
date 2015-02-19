/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import static cryptography.Encrypto.*;
//import static cryptography.Crypto.*;


import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author resultugay
 */
public class EncryptoJob extends Thread{
    @Override
    public void run(){
         Color image [][] = getColor((getRGBValues(Image1)), Image1.getWidth(), Image1.getHeight());
         String newImage [][] = encryptedImage(image);  
         jProgressBar1.setValue(25);
           int alpha [][] = getRGBs(newImage, 0, 8);
           int red [][] = getRGBs(newImage,8,16);
           int green [][] = getRGBs(newImage, 16, 24);
           int blue [][] = getRGBs(newImage, 24, 32);
           
           Color newone  [][] = new Color[Image1.getWidth()/4][Image1.getHeight()/4];
              
              for(int i = 0 ; i<Image1.getWidth()/4; i++)
                     for(int j = 0; j <Image1.getHeight()/4;j++ ){
                         Color temp = new Color( red[i][j], green[i][j], blue[i][j],alpha[i][j]);
                         newone[i][j] = temp;
                     }
                      BufferedImage newoneImage = new  BufferedImage(Image1.getWidth()/4,Image1.getHeight()/4, BufferedImage.TYPE_3BYTE_BGR);
              jProgressBar1.setValue(50);
              for(int i = 0 ; i<Image1.getWidth()/4; i++)
                     for(int j = 0; j <Image1.getHeight()/4;j++ ){
                         newoneImage.setRGB(i, j, newone[i][j].getRGB());
                     }
           jProgressBar1.setValue(90);    
           File file = new File(file1.getPath().replace(file1.getName(), "Encrypted.jpg"));
              try{
               ImageIO.write(newoneImage, "png", file);
              }catch(IOException es){
                  JOptionPane.showMessageDialog(null, "The picture could not save");
              }
              
                     Image dimg2 = newoneImage.getScaledInstance(EncriptedImageLabel.getWidth(), EncriptedImageLabel.getHeight(),Image.SCALE_DEFAULT);
                     ImageIcon icon2 = new ImageIcon(dimg2);
                     OutImageLabel.setIcon(icon2);
          jProgressBar1.setValue(100);    
          errorLabel.setText(file1.getPath().replace(file1.getName(), "Encrypted.jpg"));
    
    }
            private String [][] encryptedImage(Color [][] image){
                    String redTemp = null;
                    String alphaTemp = null;
                    String greenTemp = null;
                    String blueTemp = null;

          String encrypted [][] = new String[Image1.getWidth()/4][Image1.getHeight()/4] ;
          int i = 0; 

            for(int width = 0 ; width < Image1.getWidth() ; width++){
               
                for(int height = 0 ; height < Image1.getHeight(); height++){
                  // String Image1AlphaTemp = image1[width][height].substring(0, 6);
                 //  String Image2AlphaTemp = image2[width/4][height/4].substring(i, i+2);
                 //String.format("%8s", Integer.toBinaryString(color[i][j].getBlue())).replace(' ', '0');   
                  alphaTemp += String.format("%8s", Integer.toBinaryString(image[width][height].getAlpha())).replace(' ', '0').substring(6, 8) ;
                  redTemp += String.format("%8s", Integer.toBinaryString(image[width][height].getRed())).replace(' ', '0').substring(6, 8) ; 
                  greenTemp += String.format("%8s", Integer.toBinaryString(image[width][height].getGreen())).replace(' ', '0').substring(6, 8); 
                  blueTemp += String.format("%8s", Integer.toBinaryString(image[width][height].getBlue())).replace(' ', '0').substring(6, 8) ; 
                  
                 
                   
                   // System.out.println("width  = " + width + "\n " + "height = " + height + "\n" + "i = " + i + "\n" + "Alpha = " + alphaTemp);
             /*      redTemp = image1[width][height].substring(8, 14) + image2[width/4][height/4].substring(j, j+2);
                   j = j+2;
                   if(j == 16)
                       j = 8;
                   
                   greenTemp = image1[width][height].substring(16, 22) + image2[width/4][height/4].substring(k, k+2);
                   k = k+2;
                   if(k == 24)
                       k = 16;
                   
                  blueTemp = image1[width][height].substring(24, 30) + image2[width/4][height/4].substring(u, u+2);
                   u = u+2;
                   if(u == 32)
                       u = 24;*/
                  i = i + 1;
                  if(i == 4){
                      encrypted[width/4][height/4] = alphaTemp + redTemp + greenTemp + blueTemp ;
                      i = 0;
                      alphaTemp = "";
                      redTemp = "";
                      greenTemp = "";
                      blueTemp = "";
                    
                  }
                 
                   
                }
               
                                          
            }
            
            return encrypted;
    
    }
               public static int [][] getRGBValues(BufferedImage img){
            
            int RGBValues [][] = new int[img.getWidth()][img.getHeight()];
            for(int width = 0; width < img.getWidth() ; width++)
                for(int height = 0 ; height < img.getHeight(); height++){
                    int rgb = img.getRGB(width, height);
                    RGBValues[width][height] = rgb;
                }
           return RGBValues;
            
        }
            
            
         public int [][] getRGBs(String newImage[][],int start,int end){
            
                int tempAlpha [][] = new int [Image1.getWidth()/4][Image1.getHeight()/4];
                 for(int i = 0 ; i<Image1.getWidth()/4; i++)
                     for(int j = 0; j <Image1.getHeight()/4;j++ ){
                         tempAlpha [i][j] = Integer.parseInt(newImage[i][j].substring(start, end),2);
                  }
                 return tempAlpha;
            
        }
         
    
}

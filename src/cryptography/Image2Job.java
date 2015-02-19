/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static cryptography.Crypto.*;

/**
 *
 * @author resultugay
 */
public class Image2Job extends  Thread{
    static Color binariesImage2 [][];
    @Override
    public void run(){
          Image2 = resize();  
          //Take Color values from images to handle.
          binariesImage2 = getColor(getRGBValues(Image2), Image2.getWidth(), Image2.getHeight());
             ProgressBar.setValue(25);
    }
     private BufferedImage  resize(){
                       
       if(check(Image1, Image2) == false){
        // Image2 = new BufferedImage(Image1.getWidth()/4, Image1.getHeight()/4, BufferedImage.TYPE_INT_ARGB);
        Image2.getScaledInstance(Image1.getWidth()/4, Image1.getHeight()/4, Image.SCALE_DEFAULT);
        BufferedImage scaledBI = new BufferedImage(Image1.getWidth()/4, Image1.getHeight()/4,  Image1.getType());
        Graphics2D g = scaledBI.createGraphics();
        g.drawImage(Image2, 0, 0, Image1.getWidth()/4, Image1.getHeight()/4, null); 
        g.dispose();
        return scaledBI;
       }
     return Image2;
     
    }
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
    
}

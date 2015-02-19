/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static cryptography.Crypto.*;
/**
 *
 * @author resultugay
 */
public class Image1Job extends Thread{
    public static Color binariesImage1[][];    
    @Override
    public void run(){
        Image1 = reSizeFirstImage();
        binariesImage1 = getColor(getRGBValues(Image1), Image1.getWidth(), Image1.getHeight());
        ProgressBar.setValue(12);
    }
                //resize first image if its not convenient.
                private BufferedImage reSizeFirstImage(){
        
                if((Image1.getWidth()%4) != 0){
                if((Image1.getHeight()%4) != 0){
                BufferedImage scaledBI = new BufferedImage((Image1.getWidth()/4)*4, (Image1.getHeight()/4)*4,  Image1.getType());
                Graphics2D g = scaledBI.createGraphics();
                g.drawImage(Image1, 0, 0, scaledBI.getWidth(), scaledBI.getHeight(), null); 
                g.dispose();
                    return scaledBI;
                }
               // Image1.getScaledInstance(Image1.getWidth() + 1, Image1.getHeight(), Image.SCALE_DEFAULT);
                BufferedImage scaledBI = new BufferedImage((Image1.getWidth()/4)*4, Image1.getHeight(),  Image1.getType());
                Graphics2D g = scaledBI.createGraphics();
                g.drawImage(Image1, 0, 0, scaledBI.getWidth(), scaledBI.getHeight(), null); 
                g.dispose();
               
                return scaledBI;
            
            }else  if((Image1.getHeight()%4) != 0){
                BufferedImage scaledBI = new BufferedImage(Image1.getWidth(), (Image1.getHeight()/4)*4,  Image1.getType());
                Graphics2D g = scaledBI.createGraphics();
                g.drawImage(Image1, 0, 0, scaledBI.getWidth(), scaledBI.getHeight(), null); 
                g.dispose();
                
                return scaledBI;
            }
            return Image1;
        
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

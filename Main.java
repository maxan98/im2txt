package com.company;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws IOException {
        File file = null;
        if (args[0] != null) {
            file = new File(args[0]);
        } else {
            System.out.println("Wrong usage.");
            System.exit(228);
        }
        ArrayList<Integer> arrayList = new ArrayList<>();


        BufferedImage bi1 = ImageIO.read(file);
        BufferedImage out = ImageResizer.resize(bi1, 100, 100);
        ImageIO.write(out, "png", new File("src/com/company/img1.png"));
        BufferedImage bi = ImageIO.read(new File("src/com/company/img1.png"));
        boolean in = false;
        boolean in1 = false;
        int max = bi.getRGB(0,0);
        System.out.println(bi.getRGB(0,0));
        int tmp = 0;
        int mcount = 0;
        int x = bi.getWidth();
        int y = bi.getHeight();
        int lastrgb = 0;
        System.out.println(x+" "+y);
        //COLOR ANALYZE
       for (int i = 0; i < bi.getWidth();i++){
           for (int j = 0; j < bi.getHeight();j++){
               if (!arrayList.contains(bi.getRGB(i,j))){

               }
             arrayList.add(bi.getRGB(i,j));
           }
       }
       // for (int m = 0; m < bi.getWidth();m++){
            //System.out.println("m= "+m);
            for (int k = 0; k < bi.getHeight();k++) {
                System.out.println("m= "+k);
                for (int i = 0; i < bi.getWidth(); i++) {
                    for (int j = 0; j < bi.getHeight(); j++) {
                        if (bi.getRGB(i, j) == max)

                            max = bi.getRGB(i, j);
                            lastrgb = bi.getRGB(i,j);
                        tmp++;
                       // System.out.println("first check");
                    }
                }
                if (tmp>mcount){
                    System.out.println(tmp+ " "+mcount);

                    mcount=tmp;
                    max = lastrgb;

                }

            }
   //    }
        System.out.println(max);
       //

       //ANSI PRINTING
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < bi.getHeight();i ++){
         //   System.out.println();
           // sb.append("\n");
            for(int j = 0;j <bi.getWidth();j ++){
                if (j == bi.getWidth()-1)
                    sb.append("\n");
                int rgb = bi.getRGB(i,j);
                int r = rgb >> 16 & 0xff;
                int g = rgb >> 8 & 0xff;
                int b = rgb & 0xff;
                if(rgb >= max && rgb <= max +500 || rgb <= max && rgb >= max -500){

                        sb.append(".");

                }
                else{

                        //System.out.print("$");
                        sb.append("!");



                }
                //System.out.println(rgb);
            }
        }
        //OUTPUT FORMATTING

       int line_length = sb.length()/bi.getHeight();
       int div = Math.floorDiv(sb.indexOf("!"),line_length);

        sb.replace(0,div*line_length-div,"");
        sb.replace(sb.lastIndexOf("!")+sb.length()/bi.getHeight(),sb.length(),"");
       System.out.println(sb.toString());
       System.out.println(line_length);
       System.out.println(sb.indexOf("!"));
    }


    public static abstract class ImageResizer {
        public static BufferedImage resize(BufferedImage imageToResize, int width,int height) {
            float dx = ((float)width)/imageToResize.getWidth();
            float dy = ((float)height)/imageToResize.getHeight();

            int genX,genY;
            int startX,startY;

            if(imageToResize.getWidth()<=width && imageToResize.getHeight()<=height) {
                genX = imageToResize.getWidth();
                genY = imageToResize.getHeight();
            } else {
                if(dx<=dy) {
                    genX = width;
                    genY = (int) (dx*imageToResize.getHeight());
                } else {
                    genX = (int) (dy *imageToResize.getWidth());
                    genY = height;
                }
            }

            startX = (width - genX ) / 2;
            startY = (height - genY)  / 2;

            BufferedImage bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = null;

            try {
                graphics2D = bufferedImage.createGraphics();
                graphics2D.fillRect(0, 0, width, height);
                graphics2D.drawImage(imageToResize, startX, startY, genX, genY, null);
            } finally {
                if(graphics2D!=null) {
                    graphics2D.dispose();
                }
            }

            return bufferedImage;
        }
    }
}
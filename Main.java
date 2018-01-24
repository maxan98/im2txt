

import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
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


        BufferedImage bi = ImageIO.read(file);
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
        for(int i = 0;i < bi.getWidth();i +=2){
            System.out.println();
            for(int j = 0;j < bi.getHeight();j +=2){
                int rgb = bi.getRGB(i,j);
                int r = rgb >> 16 & 0xff;
                int g = rgb >> 8 & 0xff;
                int b = rgb & 0xff;
                if(rgb == max){
                    if(in == true){
                        System.out.print(".");

                    }
                    in = !in;
                }
                else{
                    if(in1 == true){
                        System.out.print("$");

                    }
                    in1 = !in1;
                }
                //System.out.println(rgb);
            }}
    }
}
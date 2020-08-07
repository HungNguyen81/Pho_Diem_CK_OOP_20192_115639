package Pho_diem.OOP.hk20192;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.util.Log;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class mainFrame extends JFrame {
    private static JFrame frame;
    private static String dataPath;
    private static int[] dataArray;

    public static void main1(String[] args) throws IOException {
        Reader r = new FileReader("C:\\Users\\nguye\\IdeaProjects\\pho_diem_ck_OOP\\106949.txt");
        int i = -1;

        // Đọc lần lượt từng ký tự trong luồng.
        while ((i = r.read()) != -1) {
            // Ép về (cast) thành kiểu ký tự.
            System.out.print((char) i);
        }
        r.close();
        initArray();
        importData("C:\\Users\\nguye\\IdeaProjects\\pho_diem_ck_OOP\\106949.txt");

//        initArray();
        System.out.println();
        for(int o : dataArray){
            System.out.print(o + " ");
        }
    }
    public static void main(String[] args){
        frame = new JFrame("Bieu do pho diem ck OOP 20192");

        dataPath = System.getProperty("user.dir");

        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new Dimension(500,400));
        frame.add(chartPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setResizable(false);
//        frame.add(new TextArea(dataPath + "\\106949.txt"));
        frame.setVisible(true);
    }

    public static JFreeChart createChart(){
        JFreeChart barChart = ChartFactory
                .createBarChart("PHO DIEM CK OOP 20192 LOP 115639",
                        "Điểm", "Số Điểm",createDataset(), PlotOrientation.VERTICAL,
                        false, true, true);
        return barChart;
    }

    public static CategoryDataset createDataset(){
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        initArray();
        try {
            importData("106949.txt");
            importData("106950.txt");
//            importData("106945.txt");
//            importData("106946.txt");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error 1");
        }
        for(int i = 0; i < 11; i++){
            if(i != 10) {
                dataset.addValue(dataArray[i], "rowKey", "" + i + "-" + i + ".5");
            } else {
                dataset.addValue(dataArray[i], "rowKey", "" + i);
            }
        }
        return dataset;
    }

    public static void initArray(){
        dataArray = new int[11];
        for(int i : dataArray){
            dataArray[i] = 0;
        }
    }
    public static void importData(String filename) throws IOException {
//        File newFile = new File(dataPath + "\\" + filename);
//        File newFile = new File(filename);
        Reader reader = new FileReader(dataPath + "\\" + filename);
        int i = -1;
        int numSpace = 0;
        int oldChar = '\n';

        while((i = reader.read()) != -1){
            if((char) i != ' ' && (char) oldChar == ' ' && (char) i != '\n'){
                numSpace++;
            } else if (((char) i) == '\n'){
                numSpace = 0;
            }
            if(numSpace == 3){
                try{
//                    int j = Integer.parseInt(String.valueOf((char) i));
                    int j = (int) i - 48;
                    if(j <= 10 && j >= 0) dataArray[j] = dataArray[j] + 1;
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Error 2: \n" + e.getCause());
                }
            }
            oldChar = i;

        }
        reader.close();
    }

}

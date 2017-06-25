package cn.com.oceansoft.test;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.junit.Test;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Created by ty on 2017/6/24.
 */
public class OneTest {

    @Test
    public void oneTest() {

    }

    public static void word2pdf() {
        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();

        String officeHome = getOfficeHome();
        config.setOfficeHome(officeHome);

        OfficeManager officeManager = config.buildOfficeManager();
        officeManager.start();

        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        File inputFile = new File("G:" + File.separator + "xls.xlsx");
        File outFile = new File("g:" + File.separator + "xls.pdf");

//        String outputFilePath = getOutputFilePath(inputFilePath);
//        File inputFile = new File(inputFilePath);
//        if (inputFile.exists()) {// 找不到源文件, 则返回
//            File outputFile = new File(outputFilePath);
//            if (!outputFile.getParentFile().exists()) { // 假如目标路径不存在, 则新建该路径
//                outputFile.getParentFile().mkdirs();
//            }
//            converter.convert(inputFile, outputFile);
//        }
        converter.convert(inputFile, outFile);
        officeManager.stop();


        System.out.println("操作成功");
    }

    public static void word2pdf(String inputFilePath,String outFilePath) {
        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();

        String officeHome = getOfficeHome();
        config.setOfficeHome(officeHome);

        OfficeManager officeManager = config.buildOfficeManager();

        officeManager.start();
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        File inputFile = new File(inputFilePath);
        File outFile = new File(outFilePath);
        converter.convert(inputFile, outFile);
        officeManager.stop();
        System.out.println("操作成功");
    }


    public static void convert(String input, String output){
        File inputFile = new File(input);
        File outputFile = new File(output);
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);
            System.out.println("创建成功");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(connection != null){connection.disconnect(); connection = null;}}catch(Exception e){}
        }
    }

    public static void main(String[] args) throws Exception{
       new Thread(() -> {
           convert("G:" + File.separator + "1.xlsx","/1.pdf");
        }).start();
        new Thread(() -> {
            convert("G:" + File.separator + "2.xlsx","/2.pdf");
        }).start();
        new Thread(() -> {
            convert("G:" + File.separator + "3.xlsx","/3.pdf");
        }).start();
        new Thread(() -> {
            convert("G:" + File.separator + "4.xlsx","/4.pdf");
        }).start();
        new Thread(() -> {
            convert("G:" + File.separator + "5.xlsx","/5.pdf");
        }).start();
        new Thread(() -> {
            convert("G:" + File.separator + "6.xlsx","/6.pdf");
        }).start();
    }


    public static String getOutputFilePath(String inputFilePath) {
        String outputFilePath = inputFilePath.replaceAll(".doc", ".pdf");
        return outputFilePath;
    }

    public static String getOfficeHome() {
        String osName = System.getProperty("os.name");
        if (Pattern.matches("Linux.*", osName)) {
            return "/opt/openoffice.org3";
        } else if (Pattern.matches("Windows.*", osName)) {
            return "C:\\Program Files (x86)\\OpenOffice 4";
        } else if (Pattern.matches("Mac.*", osName)) {
            return "/Application/OpenOffice.org.app/Contents";
        }
        return null;
    }


}

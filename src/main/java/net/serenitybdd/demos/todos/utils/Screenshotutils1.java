package net.serenitybdd.demos.todos.utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Screenshotutils1 {

    public static void takeScreenshot(WebDriver driver, String fileName, String location) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File renameFile=new File(fileName);
            srcFile.renameTo(renameFile);

            File destFile = new File(location+"/"+fileName);
            FileUtils.copyFile(renameFile, destFile);
            copyFile(destFile);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void copyFile(File destFileName) throws IOException, InvalidFormatException {
        // Create a new Word document
        XWPFDocument doc = new XWPFDocument();

        // Create a new paragraph
        XWPFParagraph paragraph = doc.createParagraph();

        // Create a run to add text
        XWPFRun run = paragraph.createRun();
        run.setText("Screenshots");

        // Insert the screenshots into the Word document
        for (int i = 1; i <= 3; i++) {
            String screenshotFile = destFileName.getName();
            FileInputStream screenshotStream = new FileInputStream(destFileName);
            run.setText(screenshotFile);
            run.setBold(true);
            run.addBreak();
            run.addPicture(screenshotStream, XWPFDocument.PICTURE_TYPE_PNG, screenshotFile, Units.toEMU(200), Units.toEMU(150));
            screenshotStream.close();
        }

        // Write the Word document to a file
        FileOutputStream out = new FileOutputStream("/Users/amarsingh/Workspace/seerenity/selenium/screenplay-pattern-todomvc/screenshots/word_doc.docx");
        doc.write(out);
        out.close();
        doc.close();
    }
}

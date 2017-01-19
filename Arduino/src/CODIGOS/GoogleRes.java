/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CODIGOS;

import static CODIGOS.Tela.play;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xerces.util.URI;

/**
 *
 * @author irmoura
 */
public class GoogleRes {
    public static void main(String[] args) throws FailingHttpStatusCodeException, URI.MalformedURIException, IOException {
        
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        
        /*LINHA 61*/
//        String[] searchString = {"seis dividido por dois"};//61
//        String[] searchString = {"seis mais dois"};//61
//        String[] searchString = {"seis menos dois"};//61
//        String[] searchString = {"seis vezes dois"};//61
        //String[] searchString = {"6/2"};
//        String[] searchString = {"6+2"};
        //String[] searchString = {"6-2"};
        //String[] searchString = {"6*2"};
        //String[] searchString = {"100-10%"};
        
        String[] searchString = {"significado inteligência"};//61
        
        HtmlPage page = webClient.getPage("http://www.google.com/");
        
        HtmlForm form = page.getHtmlElementById("tsf");
        
        form.getInputByName("q").setValueAttribute(searchString[0]);
        
        HtmlButton submitButton = (HtmlButton)page.createElement("button");
        submitButton.setAttribute("type", "submit");
        form.appendChild(submitButton);
        
        HtmlPage newPage = submitButton.click();
        
        String pageText = newPage.asText();
        String results[] = pageText.split("\n");
        
        for(int i = 0; i < results.length; i++){
//            if(results[i].contains(searchString[0]))
//                System.out.println(i+"ª : "+results[i]);
        }
        System.out.println(results[61]);
        play(""+results[61]);
    }
}

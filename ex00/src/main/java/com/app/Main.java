package com.app;

import com.classes.PreProcessorToUpperImpl;
import com.classes.PrinterWithPrefixImpl;
import com.classes.RendererErrImpl;
import com.interfaces.PreProcessor;
import com.interfaces.Printer;
import com.interfaces.Renderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

//        PreProcessor preProcessor = new PreProcessorToUpperImpl();
//        Renderer renderer = new RendererErrImpl(preProcessor);
//        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
//        printer.setPrefix ("Prefix ");
//        printer.print ("Hello!");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefix", Printer.class);
        printer.print ("Hello!");
        context.close();

    }
}

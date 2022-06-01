package com.classes;

import com.interfaces.Printer;
import com.interfaces.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
        this.prefix = "";
    }

    @Override
    public void print(String s) {
        if (!prefix.equals("")) {
            s = prefix + " " + s;
        }
        renderer.render(s);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}

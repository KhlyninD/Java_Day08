package com.classes;

import com.interfaces.Printer;
import com.interfaces.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;
    private LocalDateTime time;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String s) {
        time = LocalDateTime.now();
        renderer.render(time + " " + s);
    }
}

package com.classes;

import com.interfaces.PreProcessor;
import com.interfaces.Renderer;

public class RendererStandardImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String s) {
        System.out.println(preProcessor.preProcessor(s));
    }
}

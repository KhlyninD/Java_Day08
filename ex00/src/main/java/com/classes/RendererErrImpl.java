package com.classes;

import com.interfaces.PreProcessor;
import com.interfaces.Renderer;

public class RendererErrImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String s) {
        System.err.println(preProcessor.preProcessor(s));
    }
}

package com.classes;

import com.interfaces.PreProcessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String preProcessor(String s) {
        return s.toUpperCase();
    }
}

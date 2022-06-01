package com.classes;

import com.interfaces.PreProcessor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String preProcessor(String s) {
        return s.toLowerCase();
    }
}

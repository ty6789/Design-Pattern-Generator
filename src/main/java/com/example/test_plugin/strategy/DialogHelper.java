package com.example.test_plugin.strategy;

import com.intellij.openapi.ui.DialogWrapper;

public abstract class DialogHelper extends MapAndFreemarkerConfig{

    protected DialogHelper(String basePackagePath) {
        super(basePackagePath);
    }

    protected boolean showDialog(DialogWrapper dialog) {
        dialog.setSize(400, 450);
        dialog.pack();
        dialog.show();
        if (dialog.getExitCode() != DialogWrapper.OK_EXIT_CODE)return false;
        else return true;
    }
}

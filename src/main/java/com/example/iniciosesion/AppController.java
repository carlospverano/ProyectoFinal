package com.example.iniciosesion;

import model.FincaRaiz;

public enum AppController {
    INSTANCE;
    private final FincaRaiz model;

    AppController() {
        model = new FincaRaiz();
    }

    public FincaRaiz getModel() {
        return model;
    }
}

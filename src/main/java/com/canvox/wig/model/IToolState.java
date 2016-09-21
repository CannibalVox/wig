package com.canvox.wig.model;

public interface IToolState {
    String cycleSelectedOption();
    String getCurrentOption();
    int getOptionState(String optionName);
    int cycleOptionState(String optionName, boolean direction);
}

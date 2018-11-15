package io.jenkins.plugins.plugincore.tools.console.statesnotes;


import java.util.regex.Pattern;


public class MsBuildErrorNote implements ImsBuildNote {

    Pattern PATTERN = Pattern.compile("(.*)[Ee]rror\\s(([A-Z]*)\\d+){0,1}:\\s(.*)");


    public Pattern getNotePattern(){

        return PATTERN;

    }

}
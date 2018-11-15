package io.jenkins.plugins.plugincore.tools.console.statesnotes;

import java.util.regex.Pattern;



public class MSBuildWarningNote implements ImsBuildNote {

    Pattern PATTERN = Pattern.compile("(.*)\\(\\d+(,\\d+){0,1}\\):\\s[Ww]arning\\s(([A-Z]*)\\d+){0,1}:\\s(.*)");

    public Pattern getNotePattern(){

        return PATTERN;

    }
    /*

    @Override
    public ConsoleAnnotator annotate(Object context, MarkupText text, int charPos) {
		return null;
	}*/

}
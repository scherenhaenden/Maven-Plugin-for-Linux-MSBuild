package io.jenkins.plugins.plugincore.tools.OSdetection;


import java.util.regex.Pattern;

import hudson.Launcher;


public class MSBuildNameByOsDetection {

    Launcher launcher;
    String msbuildNameONLinux="msbuild";
    String msbuildNameONWindows="msbuild.exe";

    String currentMsBuildName;



    public MSBuildNameByOsDetection(Launcher launcher){

        this.launcher = launcher;
        this.currentMsBuildName = this.detectionOS();

    }

    public String getMSBuildName(){
        return currentMsBuildName;


    }

    private String detectionOS(){
        
        if(launcher.isUnix()){

            return msbuildNameONLinux;

        }
        
        return  msbuildNameONWindows;


    }

}
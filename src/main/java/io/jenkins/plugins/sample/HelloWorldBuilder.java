package io.jenkins.plugins.sample;

import hudson.Launcher;
import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.ArgumentListBuilder;
import hudson.util.FormValidation;
import io.jenkins.plugins.plugincore.tools.console.statesnotes.MsBuildErrorNote;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Computer;
import hudson.model.Node;
import hudson.model.Result;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;

import java.io.File;
import java.io.IOException;
import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundSetter;



public class HelloWorldBuilder extends Builder implements SimpleBuildStep {

    private final String name;
    private boolean useFrench;

    @DataBoundConstructor
    public HelloWorldBuilder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isUseFrench() {
        return useFrench;
    }

    @DataBoundSetter
    public void setUseFrench(boolean useFrench) {
        this.useFrench = useFrench;
    }

    private String detectionOS(Launcher launcher){
        if(launcher.isUnix()){

            return "msbuild";

        }
        
        return  "msbuild.exe";


    }


    @Override
    //public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
       
       // only for tests
        if (launcher.isUnix()) {
            listener.getLogger().println("It is Unix");
        } else {
            listener.getLogger().println("Is not Unix");
        }

        String execName = detectionOS(launcher);
        ArgumentListBuilder args = new ArgumentListBuilder();

        EnvVars env = run.getEnvironment(listener);
        Node node = Computer.currentComputer().getNode();

        MsBuildConsoleParser mbcp = new MsBuildConsoleParser(listener.getLogger(), run.getCharset(),listener);
        MSBuildConsoleAnnotator annotator = new MSBuildConsoleAnnotator(listener.getLogger(), run.getCharset());
        
        FilePath file = run.getExecutor().getCurrentWorkspace();
        //FilePath pwd = file;
        mbcp.getNumberOfErrors();

       


        //int i= launcher.launch().cmdAsSingleString(execName).envs(env).stdout(mbcp).stdout(annotator).join();
        listener.getLogger().println("pwd");

        launcher.launch().cmdAsSingleString("pwd").envs(env).stdout(mbcp).stdout(annotator).pwd(file).join();

        launcher.launch().cmdAsSingleString(execName).envs(env).stdout(mbcp).stdout(annotator).pwd(file).join();

        

        
        
        listener.getLogger().println("It is Done, line added");

        //launcher.launch().cmds(args).envs(env).stdout(mbcp).stdout(annotator).pwd(file).join();

        

        

        //args.prepend("ls"," ./");




        //launcher.launch();



        if (useFrench) {
            listener.getLogger().println("Bonjour, " + name + "!");
        } else {
            listener.getLogger().println("Hello, " + name + "!");
        }
        test();
        //return true;
    }



    public void test(){
        //MsBuildErrorNote g = new MsBuildErrorNote();

    }

    @Symbol("greet")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public FormValidation doCheckName(@QueryParameter String value, @QueryParameter boolean useFrench)
                throws IOException, ServletException {
            if (value.length() == 0)
                return FormValidation.error(Messages.HelloWorldBuilder_DescriptorImpl_errors_missingName());
            if (value.length() < 4)
                return FormValidation.warning(Messages.HelloWorldBuilder_DescriptorImpl_warnings_tooShort());
            if (!useFrench && value.matches(".*[éáàç].*")) {
                return FormValidation.warning(Messages.HelloWorldBuilder_DescriptorImpl_warnings_reallyFrench());
            }
            return FormValidation.ok();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return Messages.HelloWorldBuilder_DescriptorImpl_DisplayName();
        }

    }

}

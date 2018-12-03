# MSBuild Plugin for Linux 
<br>

I write in .Net Framework the most of my applications and also my websites for that reason I needed CI/CD functions but Jenkins did not have a plugin for this Task. I took the Plugin used for windows and I changed a bit. 

The plugin can build Applications for .Netframework on linux but only those that relie on part of the framework that can be use on linux. Not tested Enough yet and not Tested for WPF or UI things that work only in windows because those things would not work. 


## Installation

You would nee a machine with Linux (Ubuntu 18.04 is what I use), Maven and of course 
you need to install msbuild.
Then run:

```sh
git clone https://github.com/scherenhaenden/Maven-Plugin-for-Linux-MSBuild
```
Then:

```sh
mvn hpi:run
```


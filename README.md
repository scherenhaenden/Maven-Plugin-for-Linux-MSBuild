#MSBuild Plugin for Linux 
<br>

I write in .Net Framework the most of my applications and also my websites. So I needed CI/CD  but Jenkins did not have a plugin for this Task. I took the Plugin used for windows and I changed a bit. 

It builds for now, but I have still a bit to do.



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


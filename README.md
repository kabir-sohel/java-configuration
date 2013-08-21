java-configuration
==================

Its a thread safe java configuration manager

Introduction

	Hot reloadable Java Configuration provides the following

? Its a separate application (or service like EJB) then others can request for its instance to use it.
? Configuration files can be added in before starting the configuration module or dynamically by each other module at runtime.
? Multi-threaded
? Changes of the property will have immediate impact on the Configuration core data.
? Nested property is supported




How to Use

There is a root.cfg file where we can give the initial file name to load the configuration data from. We support two directives 
	@DIR Ð means that the next line will be a directory
	@FILES Ð means that the next lines(until a new directive) will  be file names;



In future it will support new tags for other types of files. i.e xml, json.


Initialization

Get the ConfigurationBase object and you are ready to go.




it will load all the config files found in the root.cfg file. If you want to add more files at runtime you can do one of the followings
One   
Add a filename in the root.cfg file and after a while( default is 2 seconds). So after 2 seconds all of the config keys found in that file will be loaded. Similarly if a config key is changed in any of the files, it will be loaded at runtime.


Two
Add a file at runtime or you can add a configuration key at runtime.




Get what you want 

Here are some methods u can call to get the values. All of the public APIs are listed at the last section of this file. 


 
same way goes for the float , double types.

Variable Replacement

config keys can be replaced by other config keys like the following.



here address will give you http://127.0.0.1:8080



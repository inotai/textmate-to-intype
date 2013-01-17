# TextMate to Intype Bundles Convertor

## How to use

Use the precompiled package from the Download section of this repository:

	java -jar tm2i.jar PathToInput PathToOutput

### Path to input/output folders

  * **PathToInput** - path to the directory, which contains your `*.tmbundle` directories
  * **PathToOutput** - path to any directory, where the converted bundles will be copied to

Example:

	java -jar tm2i.jar c:/work/myTextMateBundles c:/work/convertedBundles

## Building from source (using IntelliJ Idea)

### Clone the sources

  * If you cloned the sources using IntelliJ Idea internal tools, it should detect the project file
  * If you used some other tool to clone the sources, locate the `tm2i.ipr` file and open it in IntelliJ Idea.

Now you should be able to build the project using Main menu > Build > Make project, or Make Module tm2i.

The compiled package should be in `PROJECT_DIR\out\artifacts\tm2i_jar\`. Now you can use it the same way as described in the **How to use** section above.

## Running from source

Clone the sources and setup the project as described in previous section.

To run the convertor from sources, you need to setup a **Run configuration** first:

1. Go to Main menu > Run and choose Edit configurations
2. Add a new configuration based on the **Application** default type
3. Name the configuration, or leave the name blank (it will be generated from the Main class name)
4. Select the Main class, which should be: `com.inotai.convertor.textmate.Main`
5. Fill in the program arguments (they are space separated) with the `PathToInput` and `PathToOutput` as described in the **How to use** section
6. Save

Now you can run the application and it will convert any bundle in the input directory and copy it in the output directory.

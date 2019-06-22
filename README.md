# Remote Exec Tool

**Class:** A Softer Space Internal

**Language:** Java

**Platform:** Windows / Linux

This project enables remotely operating computery devices in fun ways.

## Setup

Download our Toolbox-Java (which is a separate project here on github) into an adjacent directory on your hard drive.

Start the build by calling under Windows:

```
build.bat
```

Or under Linux:

```
build.sh
```

## Configure

Copy the built program onto all the computery devices that are supposed to be part of the fun.

Put into the file `config/config.json` something like:

```
{
	"name": "Moya-VII /Odyssey/",
	"id": 1,
	"sharePath": "Z:\remoteExecTool\",
	"commands": [
		{
			"shorthand": "beat",
			"kind": "shellexec",
			"path": "C:\Programs (x86)\Steam\steamapps\common\Beat Saber\Beat Saber.exe"
		}
	]
}
```

Ensure that the ids of all computery devices are unique!

## Run

To start up the Remote Exec Tool after it has been built, you can call under Windows:

```
run.bat
```

Or under Linux:

```
run.sh
```

## License

We at A Softer Space really love the Unlicense, which pretty much allows anyone to do anything with this source code.
For more info, see the file UNLICENSE.

If you desperately need to use this source code under a different license, [contact us](mailto:moya@asofterspace.com) - I am sure we can figure something out.

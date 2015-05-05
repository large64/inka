#Inka
This software helps you learn a lot of new English words just by asking them repeatedly. The project was inspired by a similar one called [Anki](http://ankisrs.net/) and **was made to be presented** to one of the responsible people of course named Project Tools at [ELTE](http://www.elte.hu/).

##Build
To build this project on your computer, you will need to have different kinds of softwares to be installed.
* The software was written in Java language, please install the latest version of JDK from [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* The project itself can be opened with Netbeans IDE, you can download it from [here (Windows)](https://netbeans.org/downloads/start.html?platform=windows&lang=en&option=javase) or from [here (Linux)](https://netbeans.org/downloads/start.html?platform=linux&lang=en&option=javase).

##Run
For running the software on your machine, you will only have to install JRE (Java Runtime Environment) from [here](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html).

##Database
Sqlite is a well-known lightweight software library that helps you with the managing of databases. For command-line interface, download sqlite3 from [here](http://sqlite.org/download.html). To see what is happening, you may want to use a software with GUI, like sqliteman for Linux (if Ubuntu, type `sudo apt-get install sqliteman` in terminal), or sqlitebrowser for Windows from [here](https://github.com/sqlitebrowser/sqlitebrowser/releases)

###Default database
To make default database work while using the software, make sure you have downloaded the default db-dump from [here](https://dl.dropboxusercontent.com/u/11975339/Inka.db). If it is done, copy the db file into the root of your project (so the Inka directory will have files like *build.xml, Inka.db, manifest.mf* etc.).

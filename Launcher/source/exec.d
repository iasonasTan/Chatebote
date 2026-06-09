module exec;

import std.process;
import core.stdc.stdlib;
import std.stdio;
import std.file;

immutable string SCRIPT_LINUX = "./chatebote.sh";
immutable string SCRIPT_WINDOWS = ".\\chatebote.bat";

void execute(string properties) {
    if(check(SCRIPT_LINUX)) {
        spawnProcess([SCRIPT_LINUX, properties]);
    } else if(check(SCRIPT_WINDOWS)) {
        spawnProcess([SCRIPT_WINDOWS, properties]);
    } else {
        writeln("Error: No script found. Please create a script named 'chatbot.bat' or 'chatbot.sh', depending on your OS.");
        writeln("     : The script must start the Java program. If a debug terminal is needed, it must be started within the script.");
        writeln("     : The script position must be the same as the program.");
    }
    exit(0);
}

bool check(string file) {
    return exists(file) && isFile(file);
}
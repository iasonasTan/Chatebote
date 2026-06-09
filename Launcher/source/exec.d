module exec;

import std.process : spawnProcess, wait;
import core.stdc.stdlib : exit;
import std.stdio : writeln;
import std.file : exists, isFile;

immutable string SCRIPT_LINUX   = "./chatebote.sh";
immutable string SCRIPT_WINDOWS = ".\\chatebote.bat";
immutable string SCRIPT_OSX   = "./chatebote.command";

void execute(string arguments) {
    version (Windows) {
        tryRunning(SCRIPT_WINDOWS, arguments);
    } else version (linux) {
        tryRunning(SCRIPT_LINUX, arguments);
    } else version (OSX) {
        tryRunning(SCRIPT_OSX, arguments);
    } else {
        writeln("Unknown OS.");
    }
    exit(0);
}

void tryRunning(string script, string arguments) {
    if(check(script)) {
        writeln("Running " ~ script);
        auto pid = spawnProcess([script, arguments]);
        wait(pid);
    } else {
        writeln("Error: No script found. Please create a script named 'chatbot.bat' or 'chatbot.sh', depending on your OS.");
        writeln("     : The script must start the Java program. If a debug terminal is needed, it must be started within the script.");
        writeln("     : The script position must be the same as the program.");
    }
}

bool check(string script) {
    return exists(script) && isFile(script);
}
module exec;

import std.process;
import core.stdc.stdlib;

void execute(string properties) {
    spawnProcess(["./chatebote.sh", properties]);
    exit(0);
}
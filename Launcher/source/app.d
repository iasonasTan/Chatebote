import std.stdio;
import dlangui;
import ui : showWindow;

mixin APP_ENTRY_POINT;

extern (C) int UIAppMain(string[] args) {
	showWindow();
	return Platform.instance.enterMessageLoop();
}
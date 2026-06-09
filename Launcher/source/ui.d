module ui;

import std.stdio : writeln;
import std.string : split;
import dlangui;

import exec : execute;

void showWindow() {
    Window window = Platform.instance.createWindow(UIString.fromRaw("Chatebote - Launcher"), null, WindowFlag.Modal);
    auto layout = new VerticalLayout();

    auto titleWidget = new TextWidget(null, UIString.fromRaw("Choose a mode."));
    layout.addChild(titleWidget);
    addButtons(layout);

    window.mainWidget = layout;
    window.show();
}

void addButtons(WidgetGroup layout) {
    bool delegate(Widget) exec = delegate bool(Widget widget) {
        const string programProperties = widget.text.to!string.split('[')[1][0 .. $ -1];
        execute(programProperties);
        return true;
    };

    auto guiButton = new Button(null, UIString.fromRaw("Launch GUI mode [gui]"));
    guiButton.click = exec;
    layout.addChild(guiButton);

    auto singleGuiButton = new Button(null, UIString.fromRaw("Launch Single GUI mode [singleGui]"));
    singleGuiButton.click = exec;
    layout.addChild(singleGuiButton);

    auto tuiButton = new Button(null, UIString.fromRaw("Launch TUI mode [tui]"));
    tuiButton.click = exec;
    layout.addChild(tuiButton);

    auto singleTuiButton = new Button(null, UIString.fromRaw("Launch Single TUI mode [singleTui]"));
    singleTuiButton.click = exec;
    layout.addChild(singleTuiButton);
}
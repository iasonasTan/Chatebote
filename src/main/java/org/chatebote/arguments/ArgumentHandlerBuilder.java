package org.chatebote.arguments;

public final class ArgumentHandlerBuilder {
    // Must be non-null when 'build()' is called.
    // Update #build when addding/removing values.
    private XUI gui, tui, singleGui, singleTui;

    public ArgumentHandlerBuilder setGui(XUI xui) {
        gui = xui;
        return this;
    }

    public ArgumentHandlerBuilder setTui(XUI xui) {
        tui = xui;
        return this;
    }

    public ArgumentHandlerBuilder setSingleTui(XUI xui) {
        singleTui = xui;
        return this;
    }

    public ArgumentHandlerBuilder setSingleGui(XUI xui) {
        singleGui = xui;
        return this;
    }

    public ArgumentHandler build() {
        if(gui == null || tui == null || singleGui == null || singleTui == null) {
            throw new IllegalStateException("Gui or Tui is not set yet.");
        }
        return new ArgumentHandler(gui, tui, singleGui, singleTui);
    }
}
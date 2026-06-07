package org.chatebote;

import org.chatebote.service.StorableMessageService;
import org.chatebote.service.MessageService;
import org.chatebote.arguments.ArgumentHandlerBuilder;
import org.chatebote.arguments.ArgumentHandler;

import org.chatebote.xui.gui.GUI_Service;
import org.chatebote.xui.gui.SINGLE_GUI_Service;
import org.chatebote.xui.tui.TUI_Service;
import org.chatebote.xui.tui.SINGLE_TUI_Service;

public class App {
    public static void main(String[] args) {
        MessageService messageService = new StorableMessageService();
        ArgumentHandler argsh = new ArgumentHandlerBuilder()
                .setGui(new GUI_Service())
                .setTui(new TUI_Service())
                .setSingleGui(new SINGLE_GUI_Service())
                .setSingleTui(new SINGLE_TUI_Service())
                .build();

        argsh.handle(args, messageService);
    }
}

package org.chatebote.xui.tui;

import org.chatebote.arguments.XUI;
import org.chatebote.service.MessageService;

public final class SINGLE_TUI_Service implements XUI {
    @Override
    public void start(MessageService messageService) {
        System.out.println("I'm sorry, single tui service is unavailable for now :(");
    }
}
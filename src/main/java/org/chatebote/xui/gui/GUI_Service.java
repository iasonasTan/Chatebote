package org.chatebote.xui.gui;

import org.chatebote.arguments.XUI;
import org.chatebote.service.MessageService;

public final class GUI_Service implements XUI {
    @Override
    public void start(MessageService messageService) {
        System.out.println("I'm sorry, gui service is unavailable for now :(");
    }
}
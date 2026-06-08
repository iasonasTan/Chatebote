package org.chatebote.xui.gui;

import com.je.gui.AbstractScreen;
import com.je.gui.component.JeGuiBuilder;
import com.je.gui.configuration.DefaultConfigurationManager;
import org.chatebote.arguments.XUI;
import org.chatebote.service.MessageService;

public final class GUI_Service implements XUI {
    @Override
    public void start(MessageService messageService) {
        JeGuiBuilder guiBuilder = new JeGuiBuilder(DefaultConfigurationManager.getDefaultLoader());
        AbstractScreen screen = new MainScreen(guiBuilder, messageService);
        screen.setVisible();
    }
}
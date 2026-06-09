package org.chatebote.xui.gui;

import org.chatebote.arguments.XUI;
import org.chatebote.service.MessageService;

import javax.swing.*;


public final class SINGLE_GUI_Service implements XUI {
    @Override
    public void start(MessageService messageService) {
        String question = JOptionPane.showInputDialog("Enter your question here.");
        String response = messageService.ask(question);
        JOptionPane.showMessageDialog(null, response, "AI Model responded.", JOptionPane.PLAIN_MESSAGE);
    }
}
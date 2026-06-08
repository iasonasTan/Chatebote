package org.chatebote.xui.tui;

import org.chatebote.arguments.XUI;
import org.chatebote.service.MessageService;

public final class SINGLE_TUI_Service implements XUI {
    private final String mQuestion;

    public SINGLE_TUI_Service(String[] args) {
        this.mQuestion = getMessage(args);
    }

    @Override
    public void start(MessageService messageService) {
        IO.println(TUI_Utils.boxText(messageService.ask(mQuestion)));
    }

    private static String getMessage(String[] args) {
        StringBuilder builder = new StringBuilder();
        for(int i=1; i<args.length; i++) {
            builder.append(args[i]).append(' ');
        }
        return builder.toString();
    }
}
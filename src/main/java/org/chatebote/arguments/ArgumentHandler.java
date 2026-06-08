package org.chatebote.arguments;

import java.util.List;

import org.chatebote.service.MessageService;

public final class ArgumentHandler {

    // Whenever a variable of this category is added/removed, methods #check(),
    // #handle(), #handleInvalid() must be updated too.
    public static final String CHAT_TYPE_GUI = "gui", CHAT_TYPE_TUI = "tui",
            CHAT_TYPE_SINGLE_GUI = "singleGui", CHAT_TYPE_SINGLE_TUI = "singleTui";

    private final XUI mGui, mTui, mSingleGui, mSingleTui;

    ArgumentHandler(XUI startGUI, XUI startTUI, XUI singleGui, XUI singleTui) {
        mGui = startGUI;
        mTui = startTUI;
        mSingleGui = singleGui;
        mSingleTui = singleTui;
    }

    /**
     * Checks if the given array is a set of valid program parameters.
     * @param args Set of program parameters to check.
     * @return True if given string array is a valid set
     * of program parameters, false otherwise.
     */
    private boolean check(String[] args) {
        if(args.length == 0) {
            return false;
        }
        String chatType = args[0];
        if(chatType == null || chatType.isBlank()) {
            return false;
        }

        return chatType.equals(CHAT_TYPE_GUI) ||
                chatType.equals(CHAT_TYPE_TUI) ||
                chatType.equals(CHAT_TYPE_SINGLE_GUI) ||
                chatType.equals(CHAT_TYPE_SINGLE_TUI);
    }

    // ATTENTION: this method may terminate the application.
    public void handle(String[] args, MessageService messageService) {
        if(!check(args)) {
            handleInvalid(args);
        }
        
        switch(args[0]) {
            case CHAT_TYPE_GUI -> mGui.start(messageService);
            case CHAT_TYPE_TUI -> mTui.start(messageService);
            case CHAT_TYPE_SINGLE_GUI -> mSingleGui.start(messageService);
            case CHAT_TYPE_SINGLE_TUI -> mSingleTui.start(messageService);
            default -> System.out.println("A serious error occurred.");
        }
    }

    // ATTENTION: this method terminates the application.
    private void handleInvalid(String[] args) {
        String argsText = List.of(args).toString();
        System.out.println("ERROR: Invalid arguments "+argsText+".");
        System.out.println("ERROR: These are the valid options: "+List.of(CHAT_TYPE_GUI, CHAT_TYPE_TUI, CHAT_TYPE_SINGLE_GUI, CHAT_TYPE_SINGLE_TUI));
        System.exit(1);
    }
}
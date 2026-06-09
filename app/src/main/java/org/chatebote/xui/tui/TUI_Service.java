package org.chatebote.xui.tui;

import org.chatebote.arguments.XUI;
import org.chatebote.service.MessageService;
import org.chatebote.service.StorableMessageService;

import java.time.LocalDateTime;
import java.util.Scanner;

public final class TUI_Service implements XUI {
    // ATTENTION: This method can terminate the application.
    @Override
    public void start(MessageService ms) {
        StorableMessageService messageService = (StorableMessageService)ms;
        greet();
        Scanner scanner = new Scanner(System.in);
        String line;
        label:
        while(true) {
            System.out.print("Ask anything: ");
            line = scanner.nextLine();
            switch (line) {
                case "@forget":
                    messageService.clearHistory();
                    System.out.println("Memory cleared!");
                    continue;
                case "@exit":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break label;
                case "@save":
                    String dateTimeStr = LocalDateTime.now().toString();
                    String fileName = String.format("Conversation-%s.txt", dateTimeStr);
                    messageService.storeHere(fileName);
                    System.out.println("Chat Saved!");
                    continue;
            }
            String reply = messageService.ask(line);
            IO.println(TUI_Utils.boxText(reply));
        }
    }

    private static void greet() {
        IO.println("Type @forget to forget everything.");
        IO.println("Type @exit to stop app.");
        IO.println("Type @save to save current conversation in a file.");
    }
}
package org.chatebote.xui.tui;

import org.chatebote.arguments.XUI;
import org.chatebote.service.StorableMessageService;
import org.chatebote.service.MessageService;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public final class TUI_Service implements XUI {
    @Override
    public void start(MessageService msgs) {
        StorableMessageService messageService = (StorableMessageService)msgs;
        greet();
        Scanner scanner = new Scanner(System.in);
        String line = null;
        while(true) {
            System.out.print("Ask anything: ");
            line = scanner.nextLine();
            if(line.equals("@forget")) {
                messageService.clearHistory();
                System.out.println("Memory cleared!");
                continue;
            }
            if(line.equals("@exit")) {
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            }
            if (line.equals("@save")) {
                String dateTimeStr = LocalDateTime.now().toString();
                String fileName = String.format("Conversation-%s.txt",dateTimeStr);
                messageService.storeHere(fileName);
                System.out.println("Chat Saved!");
                continue;
            }
            String response = messageService.ask(line);
            printResp(response);
        }
    }

    private static void printResp(String resp) {
        final int LINE_WIDTH = 46;
        StringBuilder msgBuilder = new StringBuilder();
        
        msgBuilder.append("#".repeat(LINE_WIDTH)).append('\n');

        String[] words = resp.split(" ");
        msgBuilder.append("# ");
        int lineWidth = 2;

        for(String word: words) {
            if(lineWidth+word.length()+1 > LINE_WIDTH-1) {
                msgBuilder.append(" ".repeat(LINE_WIDTH-1-lineWidth))
                        .append("#\n# ");
                lineWidth = 2;
            }
            
            msgBuilder.append(word).append(' ');
            lineWidth += word.length() + 1;
        }

        msgBuilder.append(" ".repeat(LINE_WIDTH - 1 - lineWidth))
                .append("#\n");
        
        msgBuilder.append("#".repeat(LINE_WIDTH)).append('\n');
        System.out.println(msgBuilder);
    }

    private static void greet() {
        System.out.println("Type @forget to forget everything.");
        System.out.println("Type @exit to stop app.");
        System.out.println("Type @save to save current conversation in a file.");
    }
}
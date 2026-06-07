package org.chatebote.service;

import java.io.FileWriter;
import java.io.IOException;

public class StorableMessageService extends ChatMessageService {
    public void storeHere(String fileName) {
        try(FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(getHistory());
        } catch (IOException ioe) {
            System.out.printf("Could not save chat. %s : %s\n", 
                ioe.getClass().getSimpleName(), ioe.getMessage());
        }
    }
}
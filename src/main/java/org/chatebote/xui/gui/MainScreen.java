package org.chatebote.xui.gui;

import com.je.core.JeLib;
import com.je.gui.AbstractScreen;
import com.je.gui.component.*;
import com.je.gui.layout.VerticalFlowLayout;
import org.chatebote.service.MessageService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainScreen extends AbstractScreen {
    public MainScreen(JeGuiBuilder builder, MessageService mMessageService) {
        super(builder);
        setLayout(new GridBagLayout());

        JeSection mainSection = builder.createSection(new VerticalFlowLayout());
        mainSection.setPreferredSize(new Dimension(700, 750));

        JeSection messagesSection = builder.createSection(new VerticalFlowLayout());
        Dimension messagesDimension = new Dimension(mainSection.getPreferredSize());
        messagesDimension.height -= 100;
        messagesSection.setPreferredSize(messagesDimension);

        JScrollPane messagesScrollPane = new JScrollPane(messagesSection);
        messagesScrollPane.setPreferredSize(new Dimension(messagesSection.getPreferredSize()));

        JeButton button = builder.createTextComponent(JeButton.class, "Done").orElseThrow(RuntimeException::new);
        JeInput input = builder.createComponent(JeInput.class).orElseThrow(RuntimeException::new);


        input.setForeground(Color.WHITE);
        input.setPreferredSize(new Dimension(500, 60));

        button.addActionListener(_ -> onSend(builder, messagesSection, input, mMessageService));
        input.addKeyListener(new java.awt.event.KeyListener(){
            @Override public void keyTyped(KeyEvent keyEvent) {}
            @Override public void keyReleased(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    onSend(builder, messagesSection, input, mMessageService);
                }
            }
        });

        JeSection buttonsSection = builder.createSection(new FlowLayout());
        buttonsSection.add(input);
        buttonsSection.add(button);

        mainSection.add(buttonsSection);
        mainSection.add(messagesScrollPane);
        addChild(mainSection, new GridBagConstraints());
    }

    private void onSend(JeGuiBuilder builder, JeSection section, JeInput input, MessageService mMessageService) {
        String question = input.getText();
        input.setText("");

        JeText questionText = builder.createTextComponent(JeText.class, newlined(question)).orElseThrow(RuntimeException::new);
        //questionText.setBackground(new Color(255, 119, 119));
        questionText.setForeground(new Color(173, 29, 29));

        String reply = mMessageService.ask(question);
        JeLib.console().log("REPLY: "+reply);
        JeText answerText = builder.createTextComponent(JeText.class, newlined(reply)).orElseThrow(RuntimeException::new);

        section.add(questionText);
        updateSectionHeight(section);
        section.add(answerText);
        section.revalidate();
        section.repaint();
    }

    private void updateSectionHeight(JeSection section) {
        Component[] components = section.getComponents();
        int maxHeight = 500;
        int maxWidth = 50;
        for(Component component: components) {
            Dimension componentSize = component.getPreferredSize();
            maxHeight += componentSize.height;
            maxWidth = Math.max(maxWidth, componentSize.width);
        }
        section.setPreferredSize(new Dimension(maxWidth, maxHeight));
    }

    private String newlined(String original) {
        final int LETTERS_PER_LINE = 50;
        StringBuilder line    = new StringBuilder();
        StringBuilder builder = new StringBuilder();
        String[] words = original.split(" ");
        for (String word: words) {
            line.append(word).append(' ');
            if (line.length() >= LETTERS_PER_LINE) {
                builder.append(line).append('\n');
                line.delete(0, line.length());
            }
        }
        builder.append(line).append('\n');
        return builder.toString();
    }

    @Override
    protected String title() {
        return "Chatebote - Main";
    }

    @Override
    protected Image icon() {
        return null;
    }
}

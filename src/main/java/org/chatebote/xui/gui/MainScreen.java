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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends AbstractScreen {
    private final OnSendListener mOnSendListener;
    private final JeButton mSendButton;

    public MainScreen(JeGuiBuilder builder, MessageService messageService) {
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

        mSendButton = builder.createTextComponent(JeButton.class, "Done").orElseThrow(RuntimeException::new);
        JeInput input = builder.createComponent(JeInput.class).orElseThrow(RuntimeException::new);

        input.setForeground(Color.WHITE);
        input.setPreferredSize(new Dimension(500, 60));

        mOnSendListener = new OnSendListener(builder, messagesSection, input, messageService);
        mSendButton.addActionListener(mOnSendListener);
        input.addKeyListener(mOnSendListener);

        JeSection buttonsSection = builder.createSection(new FlowLayout());
        buttonsSection.add(input);
        buttonsSection.add(mSendButton);

        mainSection.add(buttonsSection);
        mainSection.add(messagesScrollPane);
        addChild(mainSection, new GridBagConstraints());
    }

    private final class OnSendListener implements KeyListener, ActionListener {
        private final JeGuiBuilder builder;
        private final JeSection messagesSection;
        private final JeInput input;
        private final MessageService messageService;
        private volatile boolean mLocked = false;

        OnSendListener(JeGuiBuilder builder, JeSection messagesSection, 
                JeInput input, MessageService messageService) {
            this.builder = builder;
            this.messagesSection = messagesSection;
            this.input = input;
            this.messageService = messageService;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(!mLocked) {
                onSend();
            }
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if(!mLocked && keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                onSend();
            }
        }
        @Override public void keyTyped(KeyEvent keyEvent) {}
        @Override public void keyReleased(KeyEvent keyEvent) {}

        private void onSend() {
            String question = input.getText();
            input.setText("");

            JeText questionText = builder.createTextComponent(JeText.class, newlined(question)).orElseThrow(RuntimeException::new);
            questionText.setForeground(new Color(173, 29, 29));

            messagesSection.add(questionText);
            updateSectionHeight(messagesSection);
            messagesSection.revalidate();
            messagesSection.repaint();

            mLocked = true;
            mSendButton.setEnabled(false);
            messageService.askAsync(question, this::onReceive);
        }

        private void onReceive(String reply) {
            JeLib.console().log("REPLY: "+reply);
            JeText answerText = builder.createTextComponent(JeText.class, newlined(reply)).orElseThrow(RuntimeException::new);

            messagesSection.add(answerText);
            updateSectionHeight(messagesSection);
            messagesSection.revalidate();
            messagesSection.repaint();

            mLocked = false;
            mSendButton.setEnabled(true);
        }
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

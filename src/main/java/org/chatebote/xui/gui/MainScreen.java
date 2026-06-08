package org.chatebote.xui.gui;

import com.je.core.JeLib;
import com.je.gui.AbstractScreen;
import com.je.gui.component.*;
import com.je.gui.layout.VerticalFlowLayout;
import org.chatebote.service.MessageService;

import java.awt.*;

public class MainScreen extends AbstractScreen {

    public MainScreen(JeGuiBuilder builder, MessageService mMessageService) {
        super(builder);
        setLayout(new GridBagLayout());

        JeSection section = builder.createSection(new VerticalFlowLayout());
        section.setPreferredSize(new Dimension(300, 700));

        JeInput input = builder.createComponent(JeInput.class).orElseThrow(RuntimeException::new);
        input.setForeground(Color.WHITE);
        input.setPreferredSize(new Dimension(200, 60));
        section.add(input);

        JeButton button = builder.createTextComponent(JeButton.class, "Done").orElseThrow(RuntimeException::new);
        button.addActionListener(_ -> {
            String reply = mMessageService.ask(input.getText());
            input.setText("");
            JeLib.console().log("Received reply: "+reply);
            JeText text = builder.createTextComponent(JeText.class, reply).orElseThrow(RuntimeException::new);
            section.add(text);
        });
        section.add(button);

        addChild(section, new GridBagConstraints());
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

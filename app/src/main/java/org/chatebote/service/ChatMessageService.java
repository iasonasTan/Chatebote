package org.chatebote.service;

import java.util.ArrayList;
import java.util.Collection;

public class ChatMessageService extends MessageService {
    /**
     * Messages of the conversation will be stored here.
     */
    private final MessagesList mMessages = new MessagesList();

    /**
     * This method sends the given question to the AI model and returns the answer.
     * @param question Question to the AI as string.
     * @return The answer AI gave as string.
     */
    @Override
    public String ask(String question) {
        mMessages.addQuestion(question);
        question = String.format(
                "Message from user: [%s] (Those are the previous messages: [%s]) (Reply with plain text, no markup! Give a concise message)",
                question, mMessages
        );
        String reply = super.ask(question);
        mMessages.addAnswer(reply);
        return reply;
    }

    public String getHistory() {
        return mMessages.toString();
    }

    public void clearHistory() {
        mMessages.clear();
    }

    /**
     * A list data structure that only allows strings with known identity to get added.
     */
    private static final class MessagesList extends ArrayList<String> {
        /**
         * Override {@code add} method as final and unusable to prevent it from being called.
         * Use addAnswer() or addQuestion() instead.
         */
        @Override
        public boolean add(String str) {
            throw new UnsupportedOperationException("Use addAnswer() or addQuestion() instead!");
        }

        /**
         * Override {@code add} method as final and unusable to prevent it from being called.
         * Use addAnswer() or addQuestion() instead.
         */
        @Override
        public void add(int ignored1, String ignored2) {
            throw new UnsupportedOperationException("Use addAnswer() or addQuestion() instead!");
        }

        /**
         * Override {@code add} method as final and unusable to prevent it from being called.
         * Use addAnswer() or addQuestion() instead.
         */
        @Override
        public boolean addAll(Collection<? extends String> ignored) {
            throw new UnsupportedOperationException("Use addAnswer() or addQuestion() instead!");
        }

        /**
         * Override {@code add} method as final and unusable to prevent it from being called.
         * Use addAnswer() or addQuestion() instead.
         */
        @Override
        public boolean addAll(int ignored1, Collection<? extends String> ignored2) {
            throw new UnsupportedOperationException("Use addAnswer() or addQuestion() instead!");
        }

        public void addAnswer(String answer) {
            super.add("AI-Model: "+answer+"\n\n");
        }

        public void addQuestion(String ques) {
            super.add("User: "+ques+"\n\n");
        }

        @Override
        public String toString() {
            final StringBuilder messagesTextBuilder = new StringBuilder();
            forEach(messagesTextBuilder::append);
            return messagesTextBuilder.toString();
        }
    }
}
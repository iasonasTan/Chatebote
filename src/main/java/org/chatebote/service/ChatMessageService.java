package org.chatebote.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class ChatMessageService extends MessageService {
    /**
     * Messages of the conversation will be stored here.
     */
    private final MessagesList mMessages = new MessagesList();

    /**
     * This methods send given question to the AI model and returns the answer.
     * @param question Question to the AI as string.
     * @return The answer AI gave as string.
     */
    @Override
    public String ask(String question) {
        mMessages.addQuestion(question);
        question = String.format("Message from user: [%s] (Those are the previous messages: [%s]) (Reply with plain text, no markup! Give a small message)", question, mMessages.toString());
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
    private final class MessagesList extends ArrayList<String> {
        /**
         * Override {@code add} method as final and unusable to prevent it from being called.
         * Use addAnswer() or addQuestion() instead.
         */
        @Override
        public final boolean add(String str) {
            throw new UnsupportedOperationException("Use addAnswer() or addQuestion() instead!");
        }

        /**
         * Override {@code add} method as final and unusable to prevent it from being called.
         * Use addAnswer() or addQuestion() instead.
         */
        @Override
        public final void add(int idx, String str) {
            throw new UnsupportedOperationException("Use addAnswer() or addQuestion() instead!");
        }

        /**
         * Override {@code add} method as final and unusable to prevent it from being called.
         * Use addAnswer() or addQuestion() instead.
         */
        @Override
        public final boolean addAll(Collection<? extends String> strs) {
            throw new UnsupportedOperationException("Use addAnswer() or addQuestion() instead!");
        }

        /**
         * Override {@code add} method as final and unusable to prevent it from being called.
         * Use addAnswer() or addQuestion() instead.
         */
        @Override
        public final boolean addAll(int idx, Collection<? extends String> strs) {
            throw new UnsupportedOperationException("Use addAnswer() or addQuestion() instead!");
        }

        public void addAnswer(String answ) {
            super.add("AI-Model: "+answ+"\n\n");
        }

        public void addQuestion(String ques) {
            super.add("User: "+ques+"\n\n");
        }

        @Override
        public String toString() {
            final StringBuilder strBldr = new StringBuilder();
            forEach(msg -> {
                strBldr.append(msg);
            });
            return strBldr.toString();
        }
    }
}
package org.chatebote.xui.tui;

final class TUI_Utils {
    static String boxText(String resp) {
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
        return msgBuilder.toString();
    }

    /**
     * Private constructor to prevent initialization.
     */
    private TUI_Utils(){
    }
}

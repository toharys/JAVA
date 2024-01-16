import java.util.Scanner;
class Chat {
    static final String FIRST_BOT_NAME = "Tommy";
    static final String SECONED_BOT_NAME = "Joya";
    static final String FRST_STATMNT = "say Sky is the king";

    public static void main(String[] args){
        // initialize array of 2 bots
        ChatterBot[] bots = {
                new ChatterBot(FIRST_BOT_NAME,
                        new String[]{"you want me to say- "+ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE},
                        new String[]{"what "+ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST,
                                "say I should say "+ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST}),
                new ChatterBot(SECONED_BOT_NAME,
                        new String[]{"ok i'll say "+ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE},
                        new String[]{"say say "+ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST,
                                "whaaat "+ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST})
        };

        String statement = FRST_STATMNT;
        Scanner scanner = new Scanner(System.in);
        // a loop which creates a conversation between the bots
        while (true) {
            for (ChatterBot bot : bots) {
                statement = bot.replyTo(statement);
                System.out.println(bot.getName()+": "+statement);
                scanner.nextLine();
            }
        }
    }
}

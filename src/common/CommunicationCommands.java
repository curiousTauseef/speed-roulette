package common;

//Messages used for client-server communication

public class CommunicationCommands {
    private CommunicationCommands() { }
    public static final String JOIN_MESSAGE = "JOIN";
    public static final String WELCOME_MESSAGE = "WELCOME";
    public static final String QUIT_MESSAGE = "QUIT";
    public static final String QUIT_RESPONSE = "BYE";
    public static final String BET = "BET";
    public static final String STATE_REQUEST = "STATE";
    public static final String PYB="PYB";       //place your bets
    public static final String RNVP="RNVP";     //no more bets (Rien ne va plus)
    public static final String REJECT="REJECT";
    public static final String FUND="FUND";
    public static final String ACCEPT="ACCEPT";
    public static final String WIN="WIN";
    public static final String WINNUMBER = "WINNUMBER";
    public static final String BALANCE="BALANCE";
    public static final String BUSY="BUSY";
}

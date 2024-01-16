import java.util.*;

/**
 * Base file for the ChatterBot exercise.
 * The bot's replyTo method receives a statement.
 * If it starts with the constant REQUEST_PREFIX, the bot returns
 * whatever is after this prefix. Otherwise, it returns one of
 * a few possible replies as supplied to it via its constructor.
 * In this case, it may also include the statement after
 * the selected reply (coin toss).
 * @author Dan Nirel
 */
class ChatterBot {
	// constants
	// constant which resemble the phrase to replace in the legal responses templates
	public static final String PLACEHOLDER_FOR_REQUESTED_PHRASE = "<phrase>";
	// constant which resemble the phrase to replace in the illegal responses templates
	public static final String PLACEHOLDER_FOR_ILLEGAL_REQUEST = "<request>";
	static final String REQUEST_PREFIX = "say ";

	// class's attributes
	String name;
	String[] repliesToLegalRequest;  // array of possible replies for legal requests
	String[] repliesToIllegalRequest; // array of possible replies for illegal requests
	Random rand = new Random();

	/**
	 * c'tor
	 * @param name
	 * @param repliesToLegalRequest
	 * @param repliesToIllegalRequest
	 */
	ChatterBot(String name,String[] repliesToLegalRequest, String[] repliesToIllegalRequest) {
		this.name = name;
		this.repliesToLegalRequest = new String[repliesToLegalRequest.length];
		for(int i = 0 ; i < repliesToLegalRequest.length ; i = i+1) {
			this.repliesToLegalRequest[i] = repliesToLegalRequest[i];
		}
        // build the array of the replies
		this.repliesToIllegalRequest = new String[repliesToIllegalRequest.length];
		for(int i = 0 ; i < repliesToIllegalRequest.length ; i = i+1) {
			this.repliesToIllegalRequest[i] = repliesToIllegalRequest[i];
		}
	}

	/**
	 *
	 * @return the bot's name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * a method that creates the bot's reply to given statement
	 * @param statement
	 * @return
	 */
	public String replyTo(String statement) {
		return statement.startsWith(ChatterBot.REQUEST_PREFIX) ? replyToLegalRequest(statement) :
						replyToIllegalRequest(statement) ;
	}

	/**
	 * a method that recieves an array of possible responses templates, phrase and requested statemtnt and
	 * build the random response
	 * @param s
	 * @param phrase
	 * @param statement
	 * @return
	 */
	public String replacePlaceholderInARandomPattern(String[] s, String phrase, String statement){
		int randomIndex = rand.nextInt(s.length);
		String reply = s[randomIndex];
		return reply.replaceAll(phrase, statement);
	}

	/**
	 * a method that build a reply to legal statement
	 * @param phrase
	 * @return
	 */
	public String replyToLegalRequest(String phrase) {
		phrase = phrase.replaceFirst(ChatterBot.REQUEST_PREFIX, "");
		return replacePlaceholderInARandomPattern(this.repliesToLegalRequest,
				ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE,phrase);
	}

	String replyToIllegalRequest(String statement) {
		return replacePlaceholderInARandomPattern( this.repliesToIllegalRequest,
				ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST , statement);
	}

}

package de.rhistel.settings;

/**
 * Kurzbeschreibung
 */
public class Texts {
	
	//region 0. Konstanten
	private static final String USER_MSG_PREFIX               = "\n#### ";
	private static final String USER_MSG_SUFFIX               = " ####\n";
	private static final String THAT_WAS_NOT_TEXT             = "Das war keine ";
	private static final String INTEGER_TEXT                  = "Ganzzahl";
	private static final String DOUBLE_TEXT                   = "Gleitkommazahl";
	private static final String TEXT_TEXT                     = "Text";
	private static final String BINARY_QUESTION_TEXT                     = " [j/J oder n/N] ";
	private static final String INPUT_PREFIX                  = ">> ";
	private static final String INPUT_MANDATORY               = " (*)";
	private static final String INPUT_SUFFIX                  = ": ";
	private static final String INPUT_POSITIV_NUMBER_INC_ZERO = " (Zahl >= 0)";
	
	private static final String INPUT_NEGATIVE_NUMBER = " (Zahl < 0)";
	private static final String TRY_AGAIN_TEXT        = ", versuchen Sie es erneut.";
	
	private static final String POSITIVE_INC_BLANKS_TEXT = " positive ";
	private static final String NEGATIVE_INC_BLANKS_TEXT = " negative ";
	
	public static final String USER_MSG_NOT_AN_INTEGER_TRY_AGAIN = USER_MSG_PREFIX
			+ THAT_WAS_NOT_TEXT
			+ INTEGER_TEXT
			+ TRY_AGAIN_TEXT
			+ USER_MSG_SUFFIX;
	
	public static final String USER_MSG_NOT_DOUBLE_TRY_AGAIN = USER_MSG_PREFIX
			+ THAT_WAS_NOT_TEXT
			+ DOUBLE_TEXT
			+ TRY_AGAIN_TEXT
			+ USER_MSG_SUFFIX;
	
	public static final String USER_MSG_NOT_A_POSITIVE_INTEGER_TRY_AGAIN = USER_MSG_PREFIX
			+ THAT_WAS_NOT_TEXT
			+ POSITIVE_INC_BLANKS_TEXT
			+ INTEGER_TEXT
			+ INPUT_POSITIV_NUMBER_INC_ZERO
			+ TRY_AGAIN_TEXT
			+ USER_MSG_SUFFIX;
	
	public static final String USER_MSG_NOT_A_POSITIVE_DOUBLE_TRY_AGAIN = USER_MSG_PREFIX
			+ THAT_WAS_NOT_TEXT
			+ POSITIVE_INC_BLANKS_TEXT
			+ DOUBLE_TEXT
			+ INPUT_POSITIV_NUMBER_INC_ZERO
			+ TRY_AGAIN_TEXT
			+ USER_MSG_SUFFIX;
	
	public static final String USER_MSG_NOT_A_NEGATIVE_INTEGER_TRY_AGAIN = USER_MSG_PREFIX
			+ THAT_WAS_NOT_TEXT
			+ NEGATIVE_INC_BLANKS_TEXT
			+ INTEGER_TEXT
			+ INPUT_NEGATIVE_NUMBER
			+ TRY_AGAIN_TEXT
			+ USER_MSG_SUFFIX;
	
	public static final String USER_MSG_NOT_A_NEGATIVE_DOUBLE_TRY_AGAIN = USER_MSG_PREFIX
			+ THAT_WAS_NOT_TEXT
			+ NEGATIVE_INC_BLANKS_TEXT
			+ DOUBLE_TEXT
			+ INPUT_NEGATIVE_NUMBER
			+ TRY_AGAIN_TEXT
			+ USER_MSG_SUFFIX;
	public static final String USER_MSG_MANDATORY_INPUT = "" +
			"\n###########################################################" +
			"\n Dies ist eine Pflichteingabe, diese darf nicht leer sein" +
			"\n und nicht nur aus Leerzeichen bestehen." +
			"\n Bitte versuchen Sie es erneut." +
			"\n###########################################################\n";
	
	public static final String USER_MSG_INPUT_CAN_ONLY_BE_ONE_CHARACTER =
			USER_MSG_PREFIX
			+ "Ihr Eingabe darf nur ein Zeichen sein"
			+ TRY_AGAIN_TEXT
			+ USER_MSG_SUFFIX;
	
	public static final String USER_MSG_INPUT_NEEDS_TO_BE_AN_LETTER =
			USER_MSG_PREFIX
			+ "Ihr Eingabe darf nur ein Buchstabe sein"
			+ TRY_AGAIN_TEXT
			+ USER_MSG_SUFFIX;
	
	public static final String USER_MSG_INPUT_ONLY_SUBMIT_OR_CANCEL_LETTER =
			USER_MSG_PREFIX
			+ "Ihr Eingabe darf nur ein j/J oder ein n/N sein"
			+ TRY_AGAIN_TEXT
			+ USER_MSG_SUFFIX;
	
	public static final String INPUT_INTEGER_TEXT = INPUT_PREFIX + INTEGER_TEXT + INPUT_SUFFIX;
	
	
	public static final String INPUT_POSITIVE_INTEGER_TEXT =
			INPUT_PREFIX + INTEGER_TEXT + INPUT_POSITIV_NUMBER_INC_ZERO + INPUT_SUFFIX;
	
	public static final String INPUT_NEGATIVE_INTEGER_TEXT =
			INPUT_PREFIX + INTEGER_TEXT + INPUT_NEGATIVE_NUMBER + INPUT_SUFFIX;
	
	public static final String INPUT_DOUBLE_TEXT = INPUT_PREFIX + DOUBLE_TEXT + INPUT_SUFFIX;
	
	public static final String INPUT_POSITIVE_DOUBLE_TEXT =
			INPUT_PREFIX + DOUBLE_TEXT + INPUT_POSITIV_NUMBER_INC_ZERO + INPUT_SUFFIX;
	
	public static final String INPUT_NEGATIVE_DOUBLE_TEXT =
			INPUT_PREFIX + DOUBLE_TEXT + INPUT_NEGATIVE_NUMBER + INPUT_SUFFIX;
	
	public static final String INPUT_TEXT_TEXT =
			INPUT_PREFIX + TEXT_TEXT + INPUT_SUFFIX;
	
	public static final String INPUT_NON_EMPTY_TEXT_TEXT =
			INPUT_PREFIX + TEXT_TEXT + INPUT_MANDATORY + INPUT_SUFFIX;
	
		public static final String INPUT_BINARY_QUESTION_TEXT =
			INPUT_PREFIX + BINARY_QUESTION_TEXT + INPUT_MANDATORY + INPUT_SUFFIX;
	//endregion
	
	//region 1. Decl and Init Attribute
	//endregion
	
	//region 2. Konstruktoren
	
	/**
	 * Standardkonstruktor
	 * dieser ist privat da
	 * diese Klasse nur die verwendeten
	 * Texte als Konstanten enthaelt
	 * und von Ihr soll kein Objekt
	 * instanziiert werden koennen.
	 */
	private Texts() {
		//Nichts zun tun ausser privat zu sein
	}
	
	//endregion
	
}

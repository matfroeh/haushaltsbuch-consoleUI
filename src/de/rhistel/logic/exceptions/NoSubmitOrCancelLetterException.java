package de.rhistel.logic.exceptions;

import de.rhistel.settings.Texts;

/**
 * Dediziert Fehlermeldung, sollte
 * der User kein j/J oder n/N eingegeben haben-
 * So mit kann im {@link de.rhistel.logic.ConsoleReader}
 * die Fehler deutlicher und besser abgefangen werden.
 */
public class NoSubmitOrCancelLetterException extends Exception{
	
	//region 0. Konstanten
	//endregion
	
	//region 1. Decl and Init Attribute
	//endregion
	
	//region 2. Konstruktoren
	/**
	 * Generiert eine a {@code Exception} mit der folgenden Nachricht:
	 * {@link Texts#USER_MSG_INPUT_ONLY_SUBMIT_OR_CANCEL_LETTER}
	 */
	public NoSubmitOrCancelLetterException() {
		super(Texts.USER_MSG_INPUT_ONLY_SUBMIT_OR_CANCEL_LETTER);
	}
	//endregion
	
	//region 3. Getter und Setter
	//endregion
	
	//region 4. Methoden und Funktionen
	//endregion
}

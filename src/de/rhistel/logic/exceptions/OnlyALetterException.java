package de.rhistel.logic.exceptions;

import de.rhistel.settings.Texts;

/**
 * Dediziert Fehlermeldung, sollte
 * der User mehr kein Buchstabe eingegeben haben
 * So mit kann im {@link de.rhistel.logic.ConsoleReader}
 * die Fehler deutlicher und besser abgefangen werden.
 */
public class OnlyALetterException extends Exception {
	
	//region 0. Konstanten
	//endregion
	
	//region 1. Decl and Init Attribute
	//endregion
	
	//region 2. Konstruktoren
	/**
	 * Generiert eine a {@code Exception} mit der folgenden Nachricht:
	 * {@link Texts#USER_MSG_INPUT_NEEDS_TO_BE_AN_LETTER}
	 */
	public OnlyALetterException() {
		super(Texts.USER_MSG_INPUT_NEEDS_TO_BE_AN_LETTER);
	}
	//endregion
	
	//region 3. Getter und Setter
	//endregion
	
	//region 4. Methoden und Funktionen
	//endregion
}

package de.rhistel.logic.exceptions;

import de.rhistel.settings.Texts;

/**
 * Dediziert Fehlermeldung, sollte
 * der User keine negative Ganzzahl (die Zahl 0 ist exkludiert)
 * eingeben haben.
 * So mit kann im {@link de.rhistel.logic.ConsoleReader}
 * die Fehler deutlicher und besser abgefangen werden.
 */
public class NoNegativeIntegerException extends Exception{
	
	//region 0. Konstanten
	//endregion
	
	//region 1. Decl and Init Attribute
	//endregion
	
	//region 2. Konstruktoren
	
	/**
	 * Generiert eine a {@code Exception} mit der folgenden Nachricht:
	 * {@link Texts#USER_MSG_NOT_A_NEGATIVE_INTEGER_TRY_AGAIN}
	 */
	public NoNegativeIntegerException() {
		super(Texts.USER_MSG_NOT_A_NEGATIVE_INTEGER_TRY_AGAIN);
	}
	//endregion
	
	//region 3. Getter und Setter
	//endregion
	
	//region 4. Methoden und Funktionen
	//endregion
}

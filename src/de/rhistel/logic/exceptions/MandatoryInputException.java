package de.rhistel.logic.exceptions;

import de.rhistel.settings.Texts;

/**
 * Dediziert Fehlermeldung, sollte
 * der User die Pflichteingabe nicht getaetigt haben
 * So mit kann im {@link de.rhistel.logic.ConsoleReader}
 * die Fehler deutlicher und besser abgefangen werden.
 */
public class MandatoryInputException extends Exception{
	
	//region 0. Konstanten
	//endregion
	
	//region 1. Decl and Init Attribute
	//endregion
	
	//region 2. Konstruktoren
	
	/**
	 * Generiert eine a {@code Exception} mit der folgenden Nachricht:
	 * {@link Texts#USER_MSG_MANDATORY_INPUT}
	 */
	public MandatoryInputException() {
		super(Texts.USER_MSG_MANDATORY_INPUT);
	}
	//endregion
	
	//region 3. Getter und Setter
	//endregion
	
	//region 4. Methoden und Funktionen
	//endregion
}

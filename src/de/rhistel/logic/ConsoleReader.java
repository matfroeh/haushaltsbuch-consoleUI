package de.rhistel.logic;

import de.rhistel.logic.exceptions.*;
import de.rhistel.settings.Texts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static de.rhistel.settings.Texts.INPUT_NON_EMPTY_TEXT_TEXT;

/**
 * Diese Klasse dient dazu das
 * einlesen von Werter aus der
 * Konsole zu erleichtern.
 * Sie kapselt {@link System#in}
 * in verschieden Streamorientierten Objekten
 * um einen komfortablen Zugriff auf den eigentlichen
 * Datenstrom zu erhalten. So wird das Byteweise
 * auslesen von Daten umgangen.
 * <p>
 * Von dieser Klasse kann bewusst kein Objekt
 * angelegt werden. Um die Befehle zum Aufrufen
 * der passenden einlesenden Funktion zu erleichter,
 * ist das einzige Objekt eine oeffentliche Konstante.
 * Damit der Zugriff jedoch
 * Threadsicher erfolgt sind alle Funktionen synchronized
 * <p>
 * Alle Eingaben erfolgen immer als {@link String}.
 * Die Eingabe wird dann passend zu der einlesenden
 * Funktion interpretiert und je nach Bedarf gecastet.
 * Sollte die Eingabe in der Funktion selbst nicht passend
 * sein wird Rekursion genutzt um den Benutzer dazu zu zwingen
 * die richtige Eingabe zu treffen.
 */
public class ConsoleReader {
	
	//region 0. Konstanten
	private static final int    DEF_VALUE_INT = 0;
	private static final double DEF_VALUE_DBL = 0D;
	//endregion
	
	//region 1. Decl and Init Attribute
	
	/**
	 * Um den eigentliche aufruf zu verkuerzen
	 * ist diese konstante Objektattribut oeffentlich.
	 * Dabei lehnt sich diese Verwendung an die Verwendung
	 * von  {@link System#in} an. Dies ist einer der wenigen
	 * Aussnahmefaelle wo Attribute oeffentlich sein koennen
	 * und gleichzeitig auch eigentlich noch eine Konstante sind.
	 */
	public static final ConsoleReader in = new ConsoleReader();
	
	private final BufferedReader consoleInput;
	
	//endregion
	
	//region 2. Konstruktoren
	
	/**
	 * Dieser Konstruktor wird nur
	 * einmal von dieser Klasse selbst
	 * bei der Generierung des oeffentlichen konstanten
	 * Attributs {@link ConsoleReader#in} aufgerufen.
	 * Fuer aufrufende Klass ist Konstruktor bewusst nicht
	 * zu erreichen.
	 */
	private ConsoleReader() {
		this.consoleInput = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
	}
	
	//endregion
	
	//region 3. Einlesen von Ganzzahlen
	
	/**
	 * Liest eine Zeile von der Konsole.
	 * Prueft durch die {@link Integer#parseInt(String)}
	 * Funktion ob der Wert eine Ganzzahlige Zahl ist.
	 * Positiv oder negative Zahlen werden beide akzeptiert.
	 * Ist das Parsen erfolgreich wird der daraus resultierende
	 * Ganzzahlige Wert zurueck geliefert.
	 * Ist das parsen nicht erfolgreich wird die geworfene
	 * {@link NumberFormatException} abgefangen, ignoriert und
	 * die Funktion ruft sich solange selber auf (Rekursion) bis das
	 * parsen klappt. Danach wird der passende Wert zurueck geliefert.
	 *
	 * @return iValue : int : Eingegebene Ganzzahl
	 */
	public synchronized int readInt() {
		//Decl. and Init
		int iValue = DEF_VALUE_INT;
		
		//Ausgabe damit der User auch weis das es jetzt etwas einzugeben gilt.
		System.out.print(Texts.INPUT_INTEGER_TEXT);
		
		try {
			
			//Eingegebene Zeile auslesen
			String strReadLine = this.consoleInput.readLine();
			
			/*
			 * Versuchen eingegebene Zeile zu parsen.
			 * Kann geparst werden so ist es eine gueltige Ganzzahl.
			 * Wird eine NumberFormatException geworfen so wird diese
			 * bewusst ignoriert und diese Funktion solange rekursiv
			 * aufgerufen bis der Benutzer es schafft eine passende
			 * Ganzzahl einzugeben. Die Richtige Eingabe ist zu gleich
			 * die Abbruchbedingung.
			 */
			iValue = Integer.parseInt(strReadLine);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException nfEx) {
			//Ausgabe der Fehlermeldung
			System.out.println(Texts.USER_MSG_NOT_AN_INTEGER_TRY_AGAIN);
			
			//Rekursiver Aufruf um die Eingabe einer gueltigen Ganzzahl zu erzwingen.
			iValue = this.readInt();
		}
		
		return iValue;
	}
	
	/**
	 * Liest eine Zeile von der Konsole.
	 * Prueft durch die {@link Integer#parseInt(String)}
	 * Funktion ob der Wert eine positive Ganzzahl ist. Die Zahl
	 * 0 welche weder positiv noch negativ ist, ist inkludiert.
	 * Ist das Parsen erfolgreich wird der daraus resultierende
	 * Ganzzahlige Wert zurueck geliefert.
	 * Ist das parsen nicht erfolgreich wird die geworfene
	 * {@link NumberFormatException} abgefangen, ignoriert und
	 * die Funktion ruft sich solange selber auf (Rekursion) bis das
	 * parsen klappt. Danach wird der passende Wert zurueck geliefert.
	 *
	 * @return iPositiveValue : int : Eingegebene positive Ganzzahl
	 */
	public synchronized int readPositivInt() {
		//Decl. and Init
		int iPositiveValue = DEF_VALUE_INT;
		
		//Ausgabe damit der User auch weis das es jetzt etwas einzugeben gilt.
		System.out.print(Texts.INPUT_POSITIVE_INTEGER_TEXT);
		
		try {
			
			//Eingegebene Zeile auslesen
			String strReadLine = this.consoleInput.readLine();
			
			/*
			 * Versuchen eingegebene Zeile zu parsen.
			 * Kann geparst werden so ist es eine gueltige Ganzzahl.
			 * Wird eine NumberFormatException geworfen so wird diese
			 * bewusst ignoriert und diese Funktion solange rekursiv
			 * aufgerufen bis der Benutzer es schafft eine passende
			 * Ganzzahl einzugeben. Die Richtige Eingabe ist zu gleich
			 * die Abbruchbedingung.
			 */
			iPositiveValue = Integer.parseInt(strReadLine);
			
			//Ist die Zahl nicht groesser-gleich 0 so loesen wir auch die Rekursion aus
			if (iPositiveValue < DEF_VALUE_INT) {
				throw new NoPositiveIntegerException();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException nfEx) {
			//Ausgabe der Fehlermeldung
			System.out.println(Texts.USER_MSG_NOT_AN_INTEGER_TRY_AGAIN);
			
			//Rekursiver Aufruf um die Eingabe einer gueltigen Ganzzahl zu erzwingen.
			iPositiveValue = this.readPositivInt();
			
		} catch (NoPositiveIntegerException noPositiveIntegerException) {
			//Ausgabe der Fehlermeldung
			System.out.println(noPositiveIntegerException.getMessage());
			
			//Rekursiver Aufruf um die Eingabe einer gueltigen positive Ganzzahl zu erzwingen.
			iPositiveValue = this.readPositivInt();
		}
		
		return iPositiveValue;
	}
	
	/**
	 * Liest eine Zeile von der Konsole.
	 * Prueft durch die {@link Integer#parseInt(String)}
	 * Funktion ob der Wert eine negative Ganzzahl ist. Die Zahl
	 * 0 welche weder positiv noch negativ ist, ist inkludiert.
	 * Ist das Parsen erfolgreich wird der daraus resultierende
	 * Ganzzahlige Wert zurueck geliefert.
	 * Ist das parsen nicht erfolgreich wird die geworfene
	 * {@link NumberFormatException} abgefangen, ignoriert und
	 * die Funktion ruft sich solange selber auf (Rekursion) bis das
	 * parsen klappt. Danach wird der passende Wert zurueck geliefert.
	 *
	 * @return iNegativeValue : int : Eingegebene negative Ganzzahl
	 */
	public synchronized int readNegativInt() {
		//Decl. and Init
		int iNegativeValue = DEF_VALUE_INT;
		
		//Ausgabe damit der User auch weis das es jetzt etwas einzugeben gilt.
		System.out.print(Texts.INPUT_NEGATIVE_INTEGER_TEXT);
		
		try {
			
			//Eingegebene Zeile auslesen
			String strReadLine = this.consoleInput.readLine();
			
			/*
			 * Versuchen eingegebene Zeile zu parsen.
			 * Kann geparst werden so ist es eine gueltige Ganzzahl.
			 * Wird eine NumberFormatException geworfen so wird diese
			 * bewusst ignoriert und diese Funktion solange rekursiv
			 * aufgerufen bis der Benutzer es schafft eine passende
			 * Ganzzahl einzugeben. Die Richtige Eingabe ist zu gleich
			 * die Abbruchbedingung.
			 */
			iNegativeValue = Integer.parseInt(strReadLine);
			
			//Ist die Zahl nicht groesser-gleich 0 so loesen wir auch die Rekursion aus
			if (iNegativeValue >= DEF_VALUE_INT) {
				throw new NoNegativeIntegerException();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException nfEx) {
			//Ausgabe der Fehlermeldung
			System.out.println(Texts.USER_MSG_NOT_AN_INTEGER_TRY_AGAIN);
			
			//Rekursiver Aufruf um die Eingabe einer gueltigen Ganzzahl zu erzwingen.
			iNegativeValue = this.readNegativInt();
			
		} catch (NoNegativeIntegerException noNegativeIntegerException) {
			//Ausgabe der Fehlermeldung
			System.out.println(noNegativeIntegerException.getMessage());
			
			//Rekursiver Aufruf um die Eingabe einer gueltigen negative Ganzzahl zu erzwingen.
			iNegativeValue = this.readNegativInt();
		}
		
		return iNegativeValue;
	}
	
	
	//endregion
	
	//region 4. Einlesen von Gleitkommazahlen
	
	/**
	 * Liest eine Zeile von der Konsole.
	 * Prueft durch die {@link Double#parseDouble(String)}
	 * Funktion ob der Wert eine Gleitkommazahl ist.
	 * Positiv oder negative Zahlen werden beide akzeptiert.
	 * Eingegebene Ganzzahlen des Users werden automatisch in Ganzzahlen
	 * konvertiert.
	 * Ist das Parsen erfolgreich wird der daraus resultierende
	 * Ganzzahlige Wert zurueck geliefert.
	 * Ist das parsen nicht erfolgreich wird die geworfene
	 * {@link NumberFormatException} abgefangen, ignoriert und
	 * die Funktion ruft sich solange selber auf (Rekursion) bis das
	 * parsen klappt. Danach wird der passende Wert zurueck geliefert.
	 *
	 * @return dblValue : double : Eingegebene Gleitkommazahl
	 */
	public synchronized double readDouble() {
		//Decl. and Init
		double dblValue = DEF_VALUE_DBL;
		
		//Ausgabe damit der User auch weis das es jetzt etwas einzugeben gilt.
		System.out.print(Texts.INPUT_DOUBLE_TEXT);
		
		try {
			
			//Eingegebene Zeile auslesen
			String strReadLine = this.consoleInput.readLine();
			
			/*
			 * Versuchen eingegebene Zeile zu parsen.
			 * Kann geparst werden so ist es eine gueltige Gleitkommazahl.
			 * Wird eine NumberFormatException geworfen so wird diese
			 * bewusst ignoriert und diese Funktion solange rekursiv
			 * aufgerufen bis der Benutzer es schafft eine passende
			 * Ganzzahl einzugeben. Die Richtige Eingabe ist zu gleich
			 * die Abbruchbedingung.
			 */
			dblValue = Double.parseDouble(strReadLine);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException nfEx) {
			//Ausgabe der Fehlermeldung
			System.out.println(Texts.USER_MSG_NOT_DOUBLE_TRY_AGAIN);
			
			//Rekursiver Aufruf um die Eingabe einer gueltigen Gleitkommazahl zu erzwingen.
			dblValue = readDouble();
		}
		
		return dblValue;
	}
	
	/**
	 * Liest eine Zeile von der Konsole.
	 * Prueft durch die {@link Double#parseDouble(String)}
	 * Funktion ob der Wert eine positive Gleitkommazahl ist. Die Zahl
	 * 0 welche weder positiv noch negativ ist, ist inkludiert.
	 * Ist das Parsen erfolgreich wird die daraus resultierende
	 * Gleitkommazahl zurueck geliefert.
	 * Eingegebene Ganzzahlen des Users werden automatisch in Ganzzahlen
	 * konvertiert.
	 * Ist das parsen nicht erfolgreich wird die geworfene
	 * {@link NumberFormatException} abgefangen, ignoriert und
	 * die Funktion ruft sich solange selber auf (Rekursion) bis das
	 * parsen klappt. Danach wird der passende Wert zurueck geliefert.
	 *
	 * @return dblPositiveValue :  double : Eingegebene positive Gleitkommazahl
	 */
	public synchronized double readPositivDouble() {
		//Decl. and Init
		double dblPositiveValue = DEF_VALUE_DBL;
		
		//Ausgabe damit der User auch weis das es jetzt etwas einzugeben gilt.
		System.out.print(Texts.INPUT_POSITIVE_DOUBLE_TEXT);
		
		try {
			
			//Eingegebene Zeile auslesen
			String strReadLine = this.consoleInput.readLine();
			
			/*
			 * Versuchen eingegebene Zeile zu parsen.
			 * Kann geparst werden so ist es eine gueltige Gleitkommazahl.
			 * Wird eine NumberFormatException geworfen so wird diese
			 * bewusst ignoriert und diese Funktion solange rekursiv
			 * aufgerufen bis der Benutzer es schafft eine passende
			 * Ganzzahl einzugeben. Die Richtige Eingabe ist zu gleich
			 * die Abbruchbedingung.
			 */
			dblPositiveValue = Double.parseDouble(strReadLine);
			
			//Ist die Zahl nicht groesser-gleich 0 so loesen wir auch die Rekursion aus
			if (dblPositiveValue < DEF_VALUE_DBL) {
				throw new NoPositiveDoubleException();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException nfEx) {
			//Ausgabe der Fehlermeldung
			System.out.println(Texts.USER_MSG_NOT_DOUBLE_TRY_AGAIN);
			
			//Rekursiver Aufruf um die Eingabe einer gueltigen Gleitkommazahl zu erzwingen.
			dblPositiveValue = this.readPositivDouble();
			
		} catch (NoPositiveDoubleException e) {
			//Ausgabe der Fehlermeldung
			System.out.println(e.getMessage());
			
			//Rekursiver Aufruf um die Eingabe einer gueltigen positive Gleitkommazahl zu erzwingen.
			dblPositiveValue = this.readPositivDouble();
		}
		
		return dblPositiveValue;
	}
	
	/**
	 * Liest eine Zeile von der Konsole.
	 * Prueft durch die {@link Double#parseDouble(String)}
	 * Funktion ob der Wert eine negative Gleitkommazahl ist ist. Die Zahl
	 * 0 welche weder positiv noch negativ ist, ist inkludiert.
	 * Eingegebene Ganzzahlen des Users werden automatisch in Ganzzahlen
	 * konvertiert.
	 * Ist das Parsen erfolgreich wird der daraus resultierende
	 * Ganzzahlige Wert zurueck geliefert.
	 * Ist das parsen nicht erfolgreich wird die geworfene
	 * {@link NumberFormatException} abgefangen, ignoriert und
	 * die Funktion ruft sich solange selber auf (Rekursion) bis das
	 * parsen klappt. Danach wird der passende Wert zurueck geliefert.
	 *
	 * @return dblNegativeValue : double : Eingegebene negative Gleitkommazahl
	 */
	public synchronized double readNegativDouble() {
		//Decl. and Init
		double dblNegativeValue = DEF_VALUE_INT;
		
		//Ausgabe damit der User auch weis das es jetzt etwas einzugeben gilt.
		System.out.print(Texts.INPUT_NEGATIVE_DOUBLE_TEXT);
		
		try {
			
			//Eingegebene Zeile auslesen
			String strReadLine = this.consoleInput.readLine();
			
			/*
			 * Versuchen eingegebene Zeile zu parsen.
			 * Kann geparst werden so ist es eine gueltige Gleitkommazahl.
			 * Wird eine NumberFormatException geworfen so wird diese
			 * bewusst ignoriert und diese Funktion solange rekursiv
			 * aufgerufen bis der Benutzer es schafft eine passende
			 * Ganzzahl einzugeben. Die Richtige Eingabe ist zu gleich
			 * die Abbruchbedingung.
			 */
			dblNegativeValue = Double.parseDouble(strReadLine);
			
			//Ist die Zahl nicht groesser-gleich 0 so loesen wir auch die Rekursion aus
			if (dblNegativeValue >= DEF_VALUE_INT) {
				throw new NoNegativeDoubleException();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException nfEx) {
			//Rekursiver Aufruf um die Eingabe einer gueltigen Gleitkommazahl zu erzwingen.
			System.out.println(Texts.USER_MSG_NOT_DOUBLE_TRY_AGAIN);
			
			dblNegativeValue = this.readNegativDouble();
			
		} catch (NoNegativeDoubleException e) {
			//Gleitkommazahl
			System.out.println(e.getMessage());
			
			//Rekursiver Aufruf um die Eingabe einer gueltigen negativen Gleitkommazahl zu erzwingen.
			dblNegativeValue = this.readNegativDouble();
		}
		
		return dblNegativeValue;
	}
	//endregion
	
	//region 5. Einlesen von Texten
	
	/**
	 * Diese Funktion liest die Benutzereingabe ein und
	 * liefert diese Eins zu Eins zurueck. Dabei gibt es keine
	 * Vorgaben und keine Umwandlungen. Der String kann alles
	 * und / oder nichts enthalten. Das heisst taetigt der User keine
	 * Eingabe wird automatisch ein leerer {@link String} zurueck geliefert.
	 *
	 * @return strReadLine : {@link String} Gelesene Zeile
	 */
	public synchronized String readString() {
		
		//Decl. and Init
		String strReadLine = null;
		
		//Ausgabe damit der User auch weis das es jetzt etwas einzugeben gilt.
		System.out.print(Texts.INPUT_TEXT_TEXT);
		
		try {
			//Eingegebene Zeile auslesen
			strReadLine = this.consoleInput.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return strReadLine;
	}
	
	/**
	 * Diese Funktion liest die Benutzereingabe ein und
	 * liefert diese Eins zu Eins zurueck. Dabei gibt es keine keine Umwandlungen.
	 * Der String kann alles enthalten, darf aber nur nicht leer sein oder nur aus
	 * Leerzeichen bestehen.
	 * Somit wird der Benutzer auch hier dazu gezwungen etwas einzugeben
	 *
	 * @return strReadLine : {@link String} Gelesene Zeile
	 */
	public synchronized String readMandatoryString() {
		
		//Decl. and Init
		String strReadLine = null;
		
		//Ausgabe damit der User auch weis das es jetzt etwas einzugeben gilt.
		System.out.print(INPUT_NON_EMPTY_TEXT_TEXT);
		
		try {
			//Eingegebene Zeile auslesen
			strReadLine = this.consoleInput.readLine();
			
			if (strReadLine.isEmpty() || strReadLine.isBlank()) {
				throw new MandatoryInputException();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MandatoryInputException e) {
			//Ausgabe Fehlermeldung
			System.out.println(e.getMessage());
			
			//Rekursiver aufruf fuer erneute Eingabe
			strReadLine = this.readMandatoryString();
		}
		
		return strReadLine;
	}
	//endregion
	
	//region 6. Antworten auf Fragen beantworten
	
	/**
	 * Diese Funktion dient dazu auszuwerten ob der User eine Frage
	 * bestaetigt oder ablehnt(zum Beispiel: Moechten Sie folgendes tun (j/n).)
	 * Zugelassen sind nur die Zeichen j/J oder n/N.
	 * Es wird auch nur das erste Zeichen untersucht. Sollte die Eingabe mehr
	 * als ein Zeichen sein. Wird der Benutzer durch erneutes Aufrufen dieser Funktion
	 * (Rekursion) dazu aufgefordert die richtige Antwort einzugeben.
	 *
	 * @return isPositiveAnswer : boolean : true User hat die Frage bestaetigt - false User hat
	 * Frage abgelehnt.
	 */
	public synchronized boolean readMandatoryAnswerToBinaryQuestion() {
		//Decl. and Init
		boolean isPositiveAnswer = false;
		
		//Ausgabe damit der User auch weis das es jetzt etwas einzugeben gilt.
		System.out.print(Texts.INPUT_BINARY_QUESTION_TEXT);
		
		try {
			String strReadLine = this.consoleInput.readLine();
			
			//Darf nicht leer sein
			if (strReadLine.isEmpty() || strReadLine.isBlank()) {
				throw new MandatoryInputException();
			}
			
			//Darf nur ein Zeichen lang sein
			if (strReadLine.length() > 1) {
				throw new OnlyOneCharacterException();
			}
			
			char firstCharOfInput = strReadLine.charAt(0);
			
			//Darf nur ein Buchstabe und kleine Zahl sein.
			if (!Character.isLetter(firstCharOfInput)) {
				throw new OnlyALetterException();
			}
			
			//Darf nur ein j/J oder ein n/N sein.
			if (firstCharOfInput == 'j' | firstCharOfInput == 'J') {
				//User hat die Frage bestaetigt
				isPositiveAnswer = true;
				
			} else if (firstCharOfInput == 'n' | firstCharOfInput == 'N') {
				//User hat die Frage abgelehnt
				isPositiveAnswer = false;
				
			} else {
				//User hat wender ein j/J noch ein n/N eingegeben
				throw new NoSubmitOrCancelLetterException();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MandatoryInputException e) {
			//Ausgabe der Fehlermeldung
			System.out.println(e.getMessage());
			
			//Erneute Eingabe weil die vorherige leer war
			isPositiveAnswer = this.readMandatoryAnswerToBinaryQuestion();
			
		} catch (OnlyOneCharacterException e) {
			//Ausgabe der Fehlermeldung
			System.out.println(e.getMessage());
			
			//Erneute Eingabe weil die vorherige laenger als Eins war.
			isPositiveAnswer = this.readMandatoryAnswerToBinaryQuestion();
			
		} catch (OnlyALetterException e) {
			//Ausgabe der Fehlermeldung
			System.out.println(e.getMessage());
			
			//Erneute Eingabe weil die vorherige kein Buchstabe war
			isPositiveAnswer = this.readMandatoryAnswerToBinaryQuestion();
			
		} catch (NoSubmitOrCancelLetterException e) {
			//Ausgabe der Fehlermeldung
			System.out.println(e.getMessage());
			
			//Erneute Eingabe weil die vorherige kein j/J oder n/N  war
			isPositiveAnswer = this.readMandatoryAnswerToBinaryQuestion();
			
		}
		
		return isPositiveAnswer;
	}
	//endregion
}

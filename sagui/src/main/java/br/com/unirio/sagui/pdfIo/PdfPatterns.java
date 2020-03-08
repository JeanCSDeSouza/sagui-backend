package br.com.unirio.sagui.pdfIo;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * This enum has a string representation of each regex pattern applied to the pdf lines with numbers to 
 * each string 
 * 1 - Discipline
 * 2 - General locking
 * 3 - Student code
 * 4 - Student Name
 * 5 - Institution header
 * @author Jean carlos
 *
 */
@AllArgsConstructor
public enum PdfPatterns {
	/**
     * Groups:
     * #1 - Code
     * #2 - Name
     * #3 - CR
     * #4 - CH
     * #5 - Grade
     * #6 - Attendance
     * #7 - Status
     * #8 - Status description
     */
	itemRegex("([A-Z]{3}[0-9]{4})\\s(.*?)\\s([0-9])\\s([0-9]{2})\\s([0-9]{1,2}\\,[0-9]{2})?\\s?([0-9]{1,3}\\,[0-9]{2})\\s([A-Z]{3})\\s?-\\s?(.*)", 1),
	/**
     * Groups:
     * #1 - Code
     * #2 - Name
     * #3 - CR
     * #4 - CH
     * #5 - Status 
     */
	trtGeralRegex("(TRT[0-9]{4})\\s(.*?)\\s([0-9])\\s([0-9])\\s\\s\\s([A-Z]{3})", 2),
	studentCodeRegex("Matrícula:\\s([0-9]{11})",3),
	studentNameRegex("Nome Aluno:\\s([a-zA-Z\\s]*)", 4),
	cabecalhoCursoRegex("Curso:\\s([0-9]{1,3})\\s-\\s(.*)\\s-\\s(.*)\\s-\\s(.*)\\s-\\s(.*)",5);
	
	@Getter private String pattern;
	@Getter private int value;
	
}

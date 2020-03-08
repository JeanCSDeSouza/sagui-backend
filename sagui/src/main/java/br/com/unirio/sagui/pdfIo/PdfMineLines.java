package br.com.unirio.sagui.pdfIo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;

public class PdfMineLines {    
    @Setter @Getter private PdfPatterns pdfPattern;
    StudentMap students;
    private String matricula;
    private String name;
    
    public PdfMineLines() {
		students = new StudentMap();
	}
    
    public void mineLines(List<String> lines) {
    	//TODO
    	for( int i = 0; i < lines.size(); i++){
    		Matcher matcher = validatePattern(lines.get(i));
    		lineTypeMethodInvocation(matcher, lines.get(i + 1));
    	}
    }
    
    /**
     * Returns the matcher with the valdated patter over the passed pdf line. 
     * @param line A line from the historic pdf.
     * @return
     */
    public Matcher validatePattern(String line) {
    	Matcher matcher = null;
    	for ( PdfPatterns pdfPattern: PdfPatterns.values() ) {
			//System.out.println(pdfPattern.getPattern());
    		matcher = Pattern.compile( pdfPattern.getPattern() ).matcher(line);
    		if( matcher.find()) {
    			setPdfPattern(pdfPattern);
    		}
		}
		return matcher;
    }
    /**
     * Invokes the desired method based on the line beeing processed at the time.
     * @param pdfPattern the pattern used create the matcher to a line.
     */
    public void lineTypeMethodInvocation(Matcher matcher, String nextLine) {
    	switch(this.pdfPattern.getValue()){
    		case 1: processDisciplineRegex(matcher);
    		case 2: processTrtRegex(matcher);
    		case 3: ProcessStudentCodeRegex(matcher, nextLine);
    		case 4: ProcessStudentNameRegex(matcher);
    		case 5: ProcessInstitutionHeaderRegex(matcher);
    	}
    }
	private void processDisciplineRegex(Matcher matcher) {
		// TODO Auto-generated method stub
		
	}
	private void processTrtRegex(Matcher matcher) {
		// TODO Auto-generated method stub
		
	}
	private void ProcessStudentCodeRegex(Matcher matcher, String nextLine) {
		// TODO Auto-generated method stub
		this.matricula = matcher.group(1);
		matcher =  Pattern.compile( PdfPatterns.studentNameRegex.getPattern() ).matcher(nextLine);
		if( matcher.find() ) {
			this.name = matcher.group(1);
		}
	}
	private void ProcessStudentNameRegex(Matcher matcher) {
		// TODO Auto-generated method stub
		
	}
	private void ProcessInstitutionHeaderRegex(Matcher matcher) {
		// TODO Auto-generated method stub
		
	}
    
}

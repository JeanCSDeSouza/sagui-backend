package br.com.unirio.sagui.pdfIo;

public class Teste3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//historioclob 
		PDFIO pdf = new PDFIO("C://historioclob.pdf");
		System.out.println(pdf.getText());
		PdfMineLines ml = new PdfMineLines();
		StudentMap sm = ml.mineLines( pdf.getLinesList() );
		/**
		sm.getStudents().forEach( (k,v) -> {
			System.out.println("Key value: " + k);
			System.out.println("Matricula Aluno: " + v.getMatricula());
			System.out.println("Nome Aluno:      " + v.getNome());
			System.out.println("====== Disciplinas Cursadas ========");
			v.getDisciplines().getDisciplines().forEach( (k1, v1) -> {
				System.out.println(v1.toString());
			});
		});**/
	}

}

package br.com.unirio.sagui.pdfIo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFIO {

	PDDocument document;

	/**
	 * Create an instance of PDFIO by the given string that represents the path
	 * where the PDF file is.
	 * 
	 * @param filePath
	 *            String that represents the path of the PDF file
	 * @exception IllegalArgumentException
	 *                if the path is invalid
	 * @throws PDFIsEncryptedException
	 *             if the PDF is encrypted
	 */
	public PDFIO(String filePath) {
		File file = new File(filePath);
		if (file.canRead())
			document = loadDocument(file);
		else
			throw new IllegalArgumentException("Could not read pdf: InvalidPath");
	}

	/**
	 * Opens a PDFBox document using the java.io.File passed as parameter
	 * 
	 * @param file,
	 *            a java.io.File needed by PDFBox PDDocument
	 * @return a PDDocument that encapsulates the PDF document
	 */
	private PDDocument loadDocument(File file) {
		PDDocument document = null;
		try {
			document = PDDocument.load(file);
			if (document.isEncrypted()) {
				this.document = null;
				throw new PDFIsEncryptedException("Could not read pdf: Is encrypted");
			}
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * Closes the document opened in the constructor
	 * 
	 * @throws IOException
	 */
	public void closeDocument() throws IOException {
		try {
			document.close();
		} catch (IOException ioe) {
			ioe.getStackTrace();
		}
	}

	/**
	 * Gets the text of a PDDocument
	 * 
	 * @return String containing the text of the given PDFDocument
	 */
	public String getText() {
		if (document.equals(null))
			return null;
		else {
			PDFTextStripper stripper = null;
			String pdfText = null;
			try {
				stripper = new PDFTextStripper();
				stripper.setSortByPosition(true);
				stripper.setStartPage(0);
				stripper.setEndPage(document.getNumberOfPages());
				pdfText = stripper.getText(document);

			} catch (IOException e) {
				e.printStackTrace();
			}
			return pdfText;
		}
	}

	/**
	 * Gets the instance value of PDDocument
	 */
	public PDDocument getDocument() {
		return document;
	}

	/**
	 * Split the getText() String by line
	 * 
	 * @return the List containing the lines of a PDF document
	 */
	public List<String> getLinesList() {
		List<String> list = new ArrayList<String>();
		Collections.addAll(list, getText().split(System.lineSeparator()));
		return list;
	}
}

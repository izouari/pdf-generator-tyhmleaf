package sg.pdfgen.PDFGenerator.services;

import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by izouari on 14/10/2018.
 */
public interface Html2PdfService {

    ByteArrayOutputStream generateInvoice (Map<String, Object> data);
}

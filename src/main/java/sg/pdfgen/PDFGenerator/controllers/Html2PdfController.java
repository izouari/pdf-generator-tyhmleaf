package sg.pdfgen.PDFGenerator.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sg.pdfgen.PDFGenerator.services.Html2PdfService;
import sg.pdfgen.PDFGenerator.services.Html2PdfServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by izouari on 14/10/2018.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class Html2PdfController {

    private final Html2PdfService html2PdfService;

    @RequestMapping(value = "/html2pdf", method = RequestMethod.GET)
    public ResponseEntity<byte[]> html2Pdf(HttpServletResponse response) {
        Map<String, Object> data = new HashMap<>();
        data.put("company_name", "compppppp");
        ByteArrayOutputStream bos = html2PdfService.generateInvoice(data);
        byte[] dataByte = bos.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            // Here you have to set the actual filename of your pdf
            String filename = "output.pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> responseResult = new ResponseEntity<>(dataByte, headers, HttpStatus.OK);
            return responseResult;
    }
}

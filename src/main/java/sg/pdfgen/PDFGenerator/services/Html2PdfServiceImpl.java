package sg.pdfgen.PDFGenerator.services;

import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by izouari on 14/10/2018.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class Html2PdfServiceImpl implements Html2PdfService {

    private final TemplateEngine templateEngine;

    @Override
    public ByteArrayOutputStream generateInvoice(Map<String, Object> data) {

        Context context = new Context();
        context.setVariables(data);

        String html = templateEngine.process("invoice", context);
        log.info(html);
        try {

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            os.writeBytes(html);
            HtmlConverter.convertToPdf(html, os);
            return bos;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}

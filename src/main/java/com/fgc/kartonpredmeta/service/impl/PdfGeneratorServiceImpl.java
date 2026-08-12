package com.fgc.kartonpredmeta.service.impl;

import com.fgc.kartonpredmeta.dto.PredmetResponseDTO;
import com.fgc.kartonpredmeta.service.PdfGeneratorService;
import com.fgc.kartonpredmeta.service.PredmetService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    private final TemplateEngine templateEngine;
    private final PredmetService predmetService;

    @Override
    public byte[] generisiKartonPredmetaPdf(Long predmetId) throws IOException {
        PredmetResponseDTO predmetResponseDTO = predmetService.findById(predmetId);

        Context context = new Context();
        context.setVariable("predmet", predmetResponseDTO);

        String htmlContent = templateEngine.process("karton_predmeta", context);

        try(ByteArrayOutputStream os=new ByteArrayOutputStream()){
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();

            builder.useFont(
                    ()->getClass().getResourceAsStream("/fonts/Roboto-Regular.ttf"),
                    "Roboto"
            );

            builder.withHtmlContent(htmlContent, "/");
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        }
    }
}

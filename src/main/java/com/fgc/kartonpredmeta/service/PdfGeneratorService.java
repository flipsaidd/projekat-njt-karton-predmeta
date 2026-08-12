package com.fgc.kartonpredmeta.service;

import java.io.IOException;

public interface PdfGeneratorService {

    byte[] generisiKartonPredmetaPdf(Long predmetId) throws IOException;
}

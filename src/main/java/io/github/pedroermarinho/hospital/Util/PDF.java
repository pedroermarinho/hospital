/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Util;
import org.apache.pdfbox.pdmodel.PDDocument;
//import com.qoppa.pdfWriter.PDFDocument;

import io.github.pedroermarinho.hospital.Model.Client.Client.ClientModel;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class PDF {

    public static void main(String[] args) throws IOException {
        ClientModel clientModel = new ClientModel();
        createPDF(clientModel);
    }

    public static void createPDF(ClientModel clientModel)throws IOException {

        try (PDDocument doc = new PDDocument()){

            PDPage page = new PDPage();
            doc.addPage(page);
            try (PDPageContentStream contents = new PDPageContentStream(doc, page)){

                contents.beginText();
                contents.setFont (PDType1Font.TIMES_ROMAN, 12);

                contents.newLineAtOffset(20, 700);
                contents.setLeading(14.5f);
                contents.showText("FICHA DE ATENDIMENTO");
                contents.endText();



            }
            doc.save(clientModel.getCartaoSUS()+".pdf");
        }
    }
}

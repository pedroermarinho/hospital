/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import io.github.pedroermarinho.hospital.Model.Client.Client.ClientModel;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class PDF {

    public static void main(String[] args) throws IOException {
        ClientModel clientModel = new ClientModel();
        createPDF(clientModel);
    }

    public static void createPDF(ClientModel clientModel) throws IOException {
// criação do documento
        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream(clientModel.getNome()+"-"+clientModel.getCartaoSUS()+".pdf"));
            document.open();

            PdfPTable table = new PdfPTable(new float[]{
                    1f
            });


            PdfPCell pacienteCell = new PdfPCell(new Phrase("PACIENTE:"+clientModel.getNome()));
            table.addCell(pacienteCell);

            PdfPCell sexoCell = new PdfPCell(new Phrase("SEXO:"+clientModel.getSexo()));
            table.addCell(sexoCell);

            PdfPCell dataNascimentoCell = new PdfPCell(new Phrase("DATA NASCIEMNTO:"+clientModel.getSexo()));
            table.addCell(dataNascimentoCell);

            PdfPCell idadeCell = new PdfPCell(new Phrase("IDADE:"+clientModel.getSexo()));
            table.addCell(idadeCell);

            PdfPCell identidadeCell = new PdfPCell(new Phrase("IDENTIDADE:"+clientModel.getSexo()));
            table.addCell(identidadeCell);

            PdfPCell cpfCell = new PdfPCell(new Phrase("CPF:"+clientModel.getSexo()));
            table.addCell(cpfCell);

            PdfPCell maeCell = new PdfPCell(new Phrase("MÃE:"+clientModel.getSexo()));
            table.addCell(maeCell);

            PdfPCell enderecoCell = new PdfPCell(new Phrase("ENDEREÇO:"+clientModel.getSexo()));
            table.addCell(enderecoCell);

            PdfPCell bairroCell = new PdfPCell(new Phrase("BAIRRO:"+clientModel.getSexo()));
            table.addCell(bairroCell);

            PdfPCell cidadeCell = new PdfPCell(new Phrase("CIDADE:"+clientModel.getSexo()));
            table.addCell(cidadeCell);

            PdfPCell estadoCell = new PdfPCell(new Phrase("ESTADO:"+clientModel.getSexo()));
            table.addCell(estadoCell);

            PdfPCell cartaoSUSCell = new PdfPCell(new Phrase("CARTÃO SUS:"+clientModel.getSexo()));
            table.addCell(cartaoSUSCell);

            PdfPCell foneCell = new PdfPCell(new Phrase("FONE:"+clientModel.getSexo()));
            table.addCell(foneCell);

            PdfPCell recpcaoCell = new PdfPCell(new Phrase("RESPONSÁVEL PELO PREENCHIMENTO (RECEPÇÃO):"+clientModel.getSexo()));
            table.addCell(recpcaoCell);

            PdfPCell especialidadeCell = new PdfPCell(new Phrase("ESPECIALIDADE/ATENDIMENTO:"+clientModel.getSexo()));
            table.addCell(especialidadeCell);




            document.add(table);

            // adicionando um parágrafo no documento
            document.add(new Paragraph("Gerando PDF - Java"));
        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    }
}

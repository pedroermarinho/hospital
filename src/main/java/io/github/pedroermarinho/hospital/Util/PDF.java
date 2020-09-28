/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.pedroermarinho.hospital.Util;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.*;
import io.github.pedroermarinho.hospital.Model.Client.Address.AddressClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Client.ClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Contact.ContactClientModel;
import io.github.pedroermarinho.hospital.Model.Client.Reception.ReceptionClientModel;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class PDF {

    public static void main(String[] args) throws IOException {


        ClientModel clientModel = new ClientModel();
        createPDF(new File(""), clientModel);


    }

    public static void createPDF(File file, ClientModel clientModel) throws IOException {

        final var contactClientModel = ContactClientModel.find(clientModel.getIdClient());
        final var receptionClientModel = ReceptionClientModel.find(clientModel.getIdClient());
        final var addressClientModel = AddressClientModel.find(clientModel.getIdClient());


        String htmlData = "";

        String inicio;
        String client = null;
        String address = null;
        String contact = null;
        String reception = null;
        String fim;


        inicio = "<html>\n" +
                "\n" +
                "<head>\n" +
                "<meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">\n" +
                "<meta name=Generator content=\"Microsoft Word 15 (filtered)\">\n" +
                "<title>PACIENTE</title>\n" +
                "<style>\n" +
                "<!--\n" +
                " /* Font Definitions */\n" +
                " @font-face\n" +
                "\t{font-family:\"Cambria Math\";\n" +
                "\tpanose-1:2 4 5 3 5 4 6 3 2 4;}\n" +
                "@font-face\n" +
                "\t{font-family:Tahoma;\n" +
                "\tpanose-1:2 11 6 4 3 5 4 4 2 4;}\n" +
                "@font-face\n" +
                "\t{font-family:\"Arial Rounded MT Bold\";\n" +
                "\tpanose-1:2 15 7 4 3 5 4 3 2 4;}\n" +
                " /* Style Definitions */\n" +
                " p.MsoNormal, li.MsoNormal, div.MsoNormal\n" +
                "\t{margin:0cm;\n" +
                "\tfont-size:12.0pt;\n" +
                "\tfont-family:\"Times New Roman\",serif;}\n" +
                ".MsoChpDefault\n" +
                "\t{font-size:10.0pt;}\n" +
                "@page WordSection1\n" +
                "\t{size:595.3pt 841.9pt;\n" +
                "\tmargin:1.0cm 1.0cm 1.0cm 1.0cm;}\n" +
                "div.WordSection1\n" +
                "\t{page:WordSection1;}\n" +
                "-->\n" +
                "</style>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body lang=PT-BR>\n" +
                "\n" +
                "<div class=WordSection1>\n" +
                "\n" +
                "<p class=MsoNormal>                                                                                                                                                                                                                                                               \n" +
                "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           <span\n" +
                "style='font-size:16.0pt;font-family:\"Arial Rounded MT Bold\",sans-serif'>FICHA\n" +
                "DE ATENDIMENTO</span></p>\n" +
                "\n" +
                "<p class=MsoNormal><u><span style='font-size:6.0pt'><span style='text-decoration:\n" +
                " none'>&nbsp;</span></span></u></p>\n" +
                "\n" +
                "<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 width=725\n" +
                " style='width:544.1pt;border-collapse:collapse;border:none'>\n";


        ///cliente
        if (contactClientModel!=null) {


            client = " <tr style='height:1.0cm'>\n" +
                    "  <td width=472 colspan=5 valign=top style='width:353.9pt;border:solid windowtext 1.0pt;\n" +
                    "  padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>PACIENTE:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + clientModel.getNome() + "</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=161 colspan=3 valign=top style='width:120.6pt;border:solid windowtext 1.0pt;\n" +
                    "  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>DATA NASCIMENTO:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + clientModel.getDataNascimento() + "</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=93 valign=top style='width:69.6pt;border:solid windowtext 1.0pt;\n" +
                    "  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>IDADE:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + clientModel.getNome() + "</span></p>\n" +
                    "  </td>\n" +
                    " </tr>\n" +
                    " <tr style='height:25.95pt'>\n" +
                    "  <td width=604 colspan=7 valign=top style='width:453.2pt;border:solid windowtext 1.0pt;\n" +
                    "  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:25.95pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>MÃE:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + clientModel.getMae() + "</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=121 colspan=2 valign=top style='width:90.9pt;border-top:none;\n" +
                    "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                    "  padding:0cm 5.4pt 0cm 5.4pt;height:25.95pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>SEXO</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + clientModel.getSexo() + "</span></p>\n" +
                    "  </td>\n" +
                    " </tr>\n" +
                    " <tr style='height:25.95pt'>\n" +
                    "  <td width=160 valign=top style='width:120.1pt;border:solid windowtext 1.0pt;\n" +
                    "  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:25.95pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>IDENTIDADE:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + clientModel.getIdentidade() + "</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=228 colspan=3 valign=top style='width:171.15pt;border-top:none;\n" +
                    "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                    "  padding:0cm 5.4pt 0cm 5.4pt;height:25.95pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>C.P.F.:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + clientModel.getCpf() + "</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=337 colspan=5 valign=top style='width:252.85pt;border-top:none;\n" +
                    "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                    "  padding:0cm 5.4pt 0cm 5.4pt;height:25.95pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>CARTÃO SUS:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + clientModel.getCartaoSUS() + "</span></p>\n" +
                    "  </td>\n" +
                    " </tr>\n";

        }
        if (addressClientModel!=null) {
            ///endereço
            address = " <tr style='height:23.95pt'>\n" +
                    "  <td width=302 colspan=2 valign=top style='width:226.4pt;border:solid windowtext 1.0pt;\n" +
                    "  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:23.95pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>ENDEREÇO:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + addressClientModel.getRua() +","+addressClientModel.getNumeroCasa() +"</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=180 colspan=4 valign=top style='width:134.75pt;border-top:none;\n" +
                    "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                    "  padding:0cm 5.4pt 0cm 5.4pt;height:23.95pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>BAIRRO:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + addressClientModel.getBairro() + "</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=123 valign=top style='width:92.05pt;border-top:none;border-left:\n" +
                    "  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                    "  padding:0cm 5.4pt 0cm 5.4pt;height:23.95pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>CIDADE:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + addressClientModel.getCidade() + "</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=121 colspan=2 valign=top style='width:90.9pt;border-top:none;\n" +
                    "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                    "  padding:0cm 5.4pt 0cm 5.4pt;height:23.95pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>ESTADO:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + addressClientModel.getEstado() + "</span></p>\n" +
                    "  </td>\n" +
                    " </tr>\n";

        }
        ////contado
        if (contactClientModel !=null) {


            contact = " <tr style='height:24.65pt'>\n" +
                    "  <td width=388 colspan=4 valign=top style='width:291.25pt;border:solid windowtext 1.0pt;\n" +
                    "  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:24.65pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>E-MAIL:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + contactClientModel.getEmail() + "</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=337 colspan=5 valign=top style='width:252.85pt;border-top:none;\n" +
                    "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                    "  padding:0cm 5.4pt 0cm 5.4pt;height:24.65pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>FONE:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + contactClientModel.getTelefone() + "</span></p>\n" +
                    "  </td>\n" +
                    " </tr>\n";

        }
        if (receptionClientModel != null) {
            //recepção
            reception = " <tr style='height:28.6pt'>\n" +
                    "  <td width=472 colspan=5 valign=top style='width:353.9pt;border:solid windowtext 1.0pt;\n" +
                    "  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:28.6pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>RESPONSÁVEL PELO\n" +
                    "  PREECHIMENTO (RECEPÇÃO):</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + receptionClientModel.getRecepcao() + "</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=254 colspan=4 valign=top style='width:190.2pt;border-top:none;\n" +
                    "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                    "  padding:0cm 5.4pt 0cm 5.4pt;height:28.6pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>ESPECIALIDADE/ATENDIMENTO:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + receptionClientModel.getEspecialidade() + "</span></p>\n" +
                    "  </td>\n" +
                    " </tr>\n" +
                    " <tr style='height:28.6pt'>\n" +
                    "  <td width=160 valign=top style='width:120.1pt;border:solid windowtext 1.0pt;\n" +
                    "  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:28.6pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>DATA ATENDIMENTO:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + receptionClientModel.getModificationDate() + "</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>&nbsp;</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=155 colspan=2 valign=top style='width:116.55pt;border-top:none;\n" +
                    "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                    "  padding:0cm 5.4pt 0cm 5.4pt;height:28.6pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>HORA ATENDIMENTO:</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>" + receptionClientModel.getModificationDate() + "</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>&nbsp;</span></p>\n" +
                    "  </td>\n" +
                    "  <td width=410 colspan=6 valign=top style='width:307.45pt;border-top:none;\n" +
                    "  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;\n" +
                    "  padding:0cm 5.4pt 0cm 5.4pt;height:28.6pt'>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>&nbsp;</span></p>\n" +
                    "  <p class=MsoNormal><span style='font-size:9.0pt'>&nbsp;</span></p>\n" +
                    "  </td>\n" +
                    " </tr>\n";

        }
        fim = " <tr height=0>\n" +
                "  <td width=160 style='border:none'></td>\n" +
                "  <td width=142 style='border:none'></td>\n" +
                "  <td width=14 style='border:none'></td>\n" +
                "  <td width=73 style='border:none'></td>\n" +
                "  <td width=84 style='border:none'></td>\n" +
                "  <td width=10 style='border:none'></td>\n" +
                "  <td width=123 style='border:none'></td>\n" +
                "  <td width=28 style='border:none'></td>\n" +
                "  <td width=93 style='border:none'></td>\n" +
                " </tr>\n" +
                "</table>\n" +
                "\n" +
                "<p class=MsoNormal style='text-align:justify'><span style='font-size:10.0pt'>&nbsp;</span></p>\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";


        htmlData += inicio;
        if (client != null) {
            htmlData += client;
        }
        if (contact != null) {
            htmlData += contact;
        }
        if (address != null) {
            htmlData += address;
        }
        if (reception != null) {
            htmlData += reception;
        }
        htmlData += fim;

        HtmlConverter.convertToPdf(htmlData, new FileOutputStream(Paths.get(file.getPath(), clientModel.getNome() + "-" + clientModel.getCartaoSUS() + "-" + LocalDate.now().toString() + ".pdf").toString()));

        System.out.println("PDF Created!");

    }


}

package com.meli.projetointegradormelifrescos.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class JavaMailApp {

    // Java program to send simple email using apache commons email
    // Uses the Gmail SMTP servers
    private static final String HOST = "smtp.gmail.com";
    private static final String USERNAME = "fernanda.desouza@mercadolivre.com";
    private static final String PASSWORD = "xhvzqwncgpxqyjjy";
    private static final int PORT = 465;
    private static final boolean SSL_FLAG = true;

    public static void welcome() {
        String message = "Bem vindo ao mercado livre, parabéns pela sua conta";
        String titulo = "Boas Vindas ao Mercado Livre!!";
        sendEmail(USERNAME, titulo, message);
    }

    public static void compraFinalizada() {
        String message = "Parabens pela sua compra no Mercado Livre!!";
        String titulo = "Compra Mercado Livre!!";
        sendEmail(USERNAME, titulo, message);
    }

    public static void sendEmail(String destinatario, String tituloMensagem, String mensagem) {
        try {
            Email email = new SimpleEmail();
            email.setDebug(true);
            email.setHostName(HOST);
            email.setSmtpPort(PORT);
            email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
            email.setSSLOnConnect(SSL_FLAG);
            email.setFrom(USERNAME);
            email.setSubject(tituloMensagem);
            email.setMsg(mensagem);
            email.addTo(destinatario);
            email.send();
        } catch (Exception ex) {
            System.out.println("Unable to send email");
            System.out.println(ex);
        }
    }
}
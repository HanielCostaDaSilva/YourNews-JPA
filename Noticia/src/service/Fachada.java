package service;

import java.util.List;

import database.DAOAssunto;
import database.DAONoticia;
import database.DAOUsuario;
import model.Assunto;
import model.Noticia;
import model.Usuario;
import util.Util;

public class Fachada {
    private static DAONoticia daoNoticia = new DAONoticia();
    private static DAOAssunto daoAssunto = new DAOAssunto();
    private static DAOUsuario daoUsuario = new DAOUsuario();
    public static Usuario logado;

    public static Noticia adicionarNoticia(String titulo, String dataPublicacao, String link) {

        Noticia noticia = new Noticia(titulo, dataPublicacao, link);
        daoNoticia.create(noticia);
        return noticia;
    }

    public static Assunto adicionarAssunto(String nome) {

        Assunto assunto = new Assunto(nome);
        daoAssunto.create(assunto);
        return assunto;
    }

    public static Usuario adicionarUsuario(String nickname, String password) {

        Usuario usuario = new Usuario(nickname, password);
        daoUsuario.create(usuario);
        return usuario;
    }

    public static List<Noticia> listarNoticias() {
        List<Noticia> listaNoticia = daoNoticia.readAll();
        return listaNoticia;

    }

    public static List<Assunto> listarAssuntos() {
        List<Assunto> listaAssunto = daoAssunto.readAll();
        return listaAssunto;
    }

    public static List<Usuario> listarUsuarios() {
        List<Usuario> listaUsuario = daoUsuario.readAll();
        return listaUsuario;
    }

    public static Noticia removerNoticia(int id) {
        Noticia noticia = daoNoticia.read(id);
        daoNoticia.delete(noticia);
        return noticia;

    }

    public static Assunto removerAssunto(int id) {
        Assunto assunto = daoAssunto.read(id);
        daoAssunto.delete(assunto);
        return assunto;

    }

    public static Usuario removerUsuario(int id) {
        Usuario usuario = daoUsuario.read(id);
        daoUsuario.delete(usuario);
        return usuario;

    }

    public static Noticia atualizarNoticia(int id, String titulo, String dataPublicacao, String link) {
        Noticia noticia = daoNoticia.read(id);
        noticia.setTitulo(titulo);
        noticia.setDataPublicacao(dataPublicacao);
        noticia.setLink(link);
        daoNoticia.create(noticia);

        return noticia;
    }

    public static Assunto atualizarAssunto(int id, String nome) {
        Assunto assunto = daoAssunto.read(id);
        assunto.setNome(nome);
        daoAssunto.create(assunto);

        return assunto;
    }

    public static Usuario atualizarUsuario(int id, String nickname, String password) {
        Usuario usuario = daoUsuario.read(id);
        usuario.setNickName(nickname);
        usuario.setPassword(password);
        daoUsuario.create(usuario);
        return usuario;
    }

    public static void inicializar() {
    }

    public static void finalizar() {
    }

    public static Usuario localizarUsuario(String nome, String senha) {
        return null;
    }

}

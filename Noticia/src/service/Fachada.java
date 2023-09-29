package service;

import java.util.List;

import database.DAOAssunto;
import database.DAONoticia;
import database.DAOUsuario;
import database.DAO;
import model.Assunto;
import model.Noticia;
import model.Usuario;

public class Fachada {
    private static DAONoticia daoNoticia = new DAONoticia();
    private static DAOAssunto daoAssunto = new DAOAssunto();
    private static DAOUsuario daoUsuario = new DAOUsuario();
    public static Usuario logado;

    public static Noticia adicionarNoticia(String titulo String dataPublicacao, String link)  throws Exception{
        DAO.begin();
        Noticia resultado= daoNoticia.read(titulo);
        if (resultado!=null) throw new Exception("Noticia:" + titulo +"já cadastrado!") ;

        Noticia noticia = new Noticia(titulo, dataPublicacao, link);
        daoNoticia.create(noticia);
        DAO.commit();

        return noticia;
    }

    public static Assunto adicionarAssunto(String nome) throws Exception{
        DAO.begin();
        Assunto resultado= daoAssunto.read(nome);
        if (resultado!=null) throw new Exception("Assunto:" + nome +"já cadastrado!") ;

        Assunto assunto = new Assunto(nome);
        daoAssunto.create(assunto);
        DAO.commit();

        return assunto;
    }

    public static Usuario adicionarUsuario(String nickname, String password) throws Exception {

        DAO.begin();
		Usuario usu = daoUsuario.read(nickname);
		if (usu!=null)
			throw new Exception("Usuario ja cadastrado:" + nickname);
		usu = new Usuario(nickname, password);

		daoUsuario.create(usu);
		DAO.commit();
		return usu;

    }

    public static List<Noticia> listarNoticias() {
        DAO.begin();
        List<Noticia> listaNoticia = daoNoticia.readAll();
        DAO.commit();
        return listaNoticia;

    }

    public static List<Assunto> listarAssuntos() {
        DAO.begin();
        List<Assunto> listaAssunto = daoAssunto.readAll();
        DAO.commit();
        return listaAssunto;
    }

    public static List<Usuario> listarUsuarios() {
        DAO.begin();
        List<Usuario> listaUsuario = daoUsuario.readAll();
        DAO.commit();
        return listaUsuario;
    }

    public static Noticia removerNoticia(int id) throws  Exception {
        DAO.begin();
        Noticia noticia = daoNoticia.read(id);
        if(noticia== null) throw new Exception("Noticia não encontrado!");

        //if (noticia.)


        daoNoticia.delete(noticia);
        DAO.commit();
        return noticia;

    }

    public static Assunto removerAssunto(int id) throws  Exception {
        DAO.begin();
        Assunto assunto = daoAssunto.read(id);
        if(assunto== null) throw new Exception("Assunto não encontrado!");
        daoAssunto.delete(assunto);
        DAO.commit();
        return assunto;

    }

    public static Usuario removerUsuario(int id) throws  Exception {
        DAO.begin();
        Usuario usuario = daoUsuario.read(id);
        if(usuario== null) throw new Exception("Usuario não encontrado!");
        daoUsuario.delete(usuario);
        DAO.commit();
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
        usuario.setNickname(nickname);
        usuario.setPassword(password);
        daoUsuario.create(usuario);
        return usuario;
    }

	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}

	public static Usuario localizarUsuario(String nickname, String password) {
		Usuario usu = daoUsuario.read(nickname);
		if (usu==null)
			return null;
		if (! usu.getPassword().equals(password))
			return null;
		return usu;
	}
}

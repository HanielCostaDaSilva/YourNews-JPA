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
    public static int limiteAssunto = 3;

    /**
     * Método utilizado para adicionar uma notícia no banco de dados.
     * 
     * @param titulo
     * @param dataPublicacao
     * @param link
     * @return Noticia
     * @throws Exception
     */
    public static Noticia adicionarNoticia(String titulo, String dataPublicacao, String link) throws Exception {
        DAO.begin();
        Noticia resultado = daoNoticia.read(titulo);
        if (resultado != null)
            throw new Exception("Noticia:" + titulo + "já cadastrado!");

        Noticia noticia = new Noticia(titulo, dataPublicacao, link);
        daoNoticia.create(noticia);
        DAO.commit();

        return noticia;
    }

    public static Assunto adicionarAssunto(String nome) throws Exception {
        DAO.begin();
        Assunto resultado = daoAssunto.read(nome);
        if (resultado != null)
            throw new Exception("Assunto:" + nome + "já cadastrado!");

        Assunto assunto = new Assunto(nome);
        daoAssunto.create(assunto);
        DAO.commit();

        return assunto;
    }

    public static Usuario adicionarUsuario(String nickname, String password) throws Exception {

        DAO.begin();
        Usuario usu = daoUsuario.read(nickname);
        if (usu != null)
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

    public static Noticia removerNoticia(int id) throws Exception {
        DAO.begin();
        Noticia noticia = daoNoticia.read(id);
        if (noticia == null)
            throw new Exception("Noticia não encontrada!");

        // if (noticia.)

        daoNoticia.delete(noticia);
        DAO.commit();
        return noticia;
    }

    public static Assunto removerAssunto(int id) throws Exception {
        DAO.begin();
        Assunto assunto = daoAssunto.read(id);
        if (assunto == null)
            throw new Exception("Assunto não encontrado!");
        daoAssunto.delete(assunto);
        DAO.commit();
        return assunto;

    }

    public static Usuario removerUsuario(int id) throws Exception {
        DAO.begin();
        Usuario usuario = daoUsuario.read(id);
        if (usuario == null)
            throw new Exception("Usuario não encontrado!");
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
        usuario.setNickName(nickname);
        usuario.setPassword(password);
        daoUsuario.create(usuario);
        return usuario;
    }

    public static void inicializar() {
        DAO.open();
    }

    public static void finalizar() {
        DAO.close();
    }

    public static Usuario localizarUsuario(String nickname, String password) {
        Usuario usu = daoUsuario.read(nickname);
        if (usu == null)
            return null;
        if (!usu.getPassword().equals(password))
            return null;
        return usu;
    }

    public static void desassociarNoticiaAssunto(int idNoticia, int idAssunto) throws Exception {
        DAO.begin();

        // Primeiro, verifique se a notícia e o assunto existem
        Noticia noticia = daoNoticia.read(idNoticia);
        Assunto assunto = daoAssunto.read(idAssunto);

        if (noticia == null || assunto == null) {
            DAO.rollback(); // Se algum deles não existe, desfaça a transação
            throw new Exception("Notícia ou Assunto não encontrado!");
        }

        if (noticia.localizar(assunto.getNome()) == null) {
            throw new Exception("A Notícia não está associada ao Assunto!");
        }

        noticia.remover(assunto);
        assunto.remover(noticia);
        daoNoticia.update(noticia);

        DAO.commit();
    }

    public static void associarAssuntoNoticia(int idNoticia, int idAssunto) throws Exception {
        DAO.begin();

        // Primeiro, verifique se a notícia e o assunto existem
        Noticia noticia = daoNoticia.read(idNoticia);
        Assunto assunto = daoAssunto.read(idAssunto);

        if (noticia == null || assunto == null) {
            throw new Exception("Notícia ou Assunto não encontrados!");
        }

        else if (noticia.localizar(assunto.getNome()) != null){
            throw new Exception("Assunto:" +assunto.getNome() + " já inserido");
        }
        else if(checarQuantidadeAssunto(noticia)){
            throw new Exception("Noticia: " +noticia.getTitulo().substring(0, 10) + " já atingiu o limite de "+ limiteAssunto+ " assuntos");
        }
        // Associe o assunto à notícia
        noticia.adicionar(assunto);
        assunto.adicionar(noticia);
        daoNoticia.update(noticia);

        DAO.commit();
    }

    /**
     * Realiza uma pesquisa para localizar notícias com base em uma data específica
     * de publicação.
     *
     * @param data A data de publicação das notícias a serem localizadas.
     * @return Uma lista de notícias que correspondem à data de publicação
     *         especificada.
     *         Se ocorrer algum erro durante a pesquisa.
     */
    public static List<Noticia> localizarNoticiasData(String data) {

        List<Noticia> noticiasPorData = daoNoticia.getNoticiasPorDataPublicacao(data);
        return noticiasPorData;
    }

    /**
     * Realiza uma pesquisa para localizar notícias associadas a um assunto
     * específico.
     *
     * @param idAssunto O ID do assunto para o qual deseja localizar notícias.
     * @return Uma lista de notícias associadas ao assunto com o ID especificado.
     *         Se ocorrer algum erro durante a pesquisa.
     */
    public static List<Noticia> localizarNoticiasPorAssunto(String assunto) {

        List<Noticia> noticiasPorAssunto = daoAssunto.getnoticiasPorAssunto(assunto);
        return noticiasPorAssunto;
    }

    /**
     * Realiza uma pesquisa para localizar assuntos que possuam pelo menos uma
     * notícia associada.
     *
     * @return Uma lista de assuntos que têm pelo menos uma notícia associada.
     *         Se ocorrer algum erro durante a pesquisa.
     */
    public static List<Assunto> localizarAssuntosPorQuantidadeNoticia() {

        List<Assunto> noticiasPorAssunto = daoAssunto.getAssuntosPorQuantidadeNoticia(1);
        return noticiasPorAssunto;
    }

    /**
     * Realiza uma pesquisa para localizar assuntos que possuam uma quantidade
     * específica de notícias associadas.
     *
     * @param quantidade A quantidade desejada de notícias que os assuntos devem ter.
     * @return Uma lista de assuntos que atendem ao critério de quantidade denotícias. Se ocorrer algum erro durante a pesquisa.
     */
    public static List<Assunto> localizarAssuntosPorQuantidadeNoticia(int quantidade) {

        List<Assunto> noticiasPorAssunto = daoAssunto.getAssuntosPorQuantidadeNoticia(quantidade);
        return noticiasPorAssunto;
    }

    /**
     * Regra de negócio 1: As notícias não podem ter mais que o limiteAssuntos
     * permitidos
     * 
     * @return noticia > limiteAssunto
     */
    public static boolean checarQuantidadeAssunto(Noticia noticia) {
        return noticia.getListaAssuntos().size() >= limiteAssunto;
    }

}

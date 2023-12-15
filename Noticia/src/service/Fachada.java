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

    public static Noticia localizarNoticia(int id) throws Exception {

        DAO.begin();

        Noticia notLocal = daoNoticia.read(id);

        if (notLocal == null) {
            throw new Exception("Noticia " + id + "Não encontrada!");

        }
        return notLocal;
    }

    public static Assunto localizarAssunto(int id) throws Exception {

        DAO.begin();

        Assunto assLocal = daoAssunto.read(id);

        if (assLocal == null) {
            throw new Exception("Assunto " + id + "Não encontrada!");

        }
        return assLocal;
    }

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

        if (daoNoticia.readByTitle(titulo) != null)
            throw new Exception("Noticia: " + titulo + "já cadastrado!");

        Noticia noticia = new Noticia(titulo, dataPublicacao, link);
        daoNoticia.create(noticia);
        DAO.commit();
        return noticia;
    }

    public static Noticia adicionarNoticia(Noticia noticia) throws Exception {
        DAO.begin();

        if (daoNoticia.readByTitle(noticia.getTitulo()) != null)
            throw new Exception("Noticia: " + noticia.getTitulo() + " já cadastrado!");

        daoNoticia.create(noticia);
        DAO.commit();
        return noticia;
    }

    public static Assunto adicionarAssunto(String nome) throws Exception {
        DAO.begin();
        if (daoAssunto.read(nome) != null)
            throw new Exception("Assunto: " + nome + " já cadastrado!");

        Assunto assunto = new Assunto(nome);
        daoAssunto.create(assunto);
        DAO.commit();

        return assunto;
    }

    public static Assunto adicionarAssunto(Assunto assunto) throws Exception {

        DAO.begin();
        if (daoAssunto.read(assunto.getNome()) != null)
            throw new Exception("Assunto: " + assunto.getNome() + "já cadastrado!");

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

    public static Noticia removerNoticia(String titulo) throws Exception {
        DAO.begin();
        Noticia noticia = daoNoticia.read(titulo);
        if (noticia == null)
            throw new Exception("Noticia não encontrada!");
        
            List<Assunto> assuntos = noticia.getListaAssuntos();
        for (Assunto assunto : assuntos) {
            assunto.remover(noticia);
        }

        daoNoticia.delete(noticia);
        DAO.commit();
        return noticia;
    }

    public static Assunto removerAssunto(String nome) throws Exception {
        DAO.begin();

        Assunto assunto = daoAssunto.read(nome);
        if (assunto == null)
            throw new Exception("Assunto não encontrado!");

        List<Noticia> noticias = assunto.getListaNoticia();
        for (Noticia noticia : noticias) {
            noticia.remover(assunto);
        }

        // Remover o assunto
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

        if (dataPublicacao != "") {
            noticia.setDataPublicacao(dataPublicacao);
        }

        if (link != "") {

            noticia.setLink(link);
        }

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

    /**
     * Associa um assunto a uma notícia.
     *
     * @param idNoticia O ID da notícia à qual o assunto será associado.
     * @param idAssunto O ID do assunto a ser associado à notícia.
     * @throws Exception Lançada se ocorrerem erros durante o processo de
     *                   associação.
     */
    public static void associarAssuntoNoticia(int idNoticia, int idAssunto) throws Exception {
        DAO.begin();

        // Primeiro, verifique se a notícia e o assunto existem
        Noticia noticia = daoNoticia.read(idNoticia);
        Assunto assunto = daoAssunto.read(idAssunto);

        if (noticia == null) {
            throw new Exception("Notícia não encontrada!");
        }

        else if (assunto == null) {
            throw new Exception("Assunto não encontrad!");
        }

        String noticiaTitulo = (noticia.getTitulo().length()>15 ? 
        noticia.getTitulo().substring(0,15)+"..." : noticia.getTitulo()); 
        
        if (noticia.localizar(assunto.getNome()) != null) {
            throw new Exception("Noticia: " + noticiaTitulo + " já possui o assunto: "+ assunto.getNome());
        } else if (checarQuantidadeAssunto(noticia)) {
            throw new Exception("Noticia: " + noticiaTitulo + " já atingiu o limite de "
                    + limiteAssunto + " assuntos");
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
     *         especificada. Se ocorrer algum erro durante a pesquisa.
     */
    public static List<Noticia> pesqNoticiaByData(String data) {
        DAO.begin();
        List<Noticia> noticiasPorData = daoNoticia.getNoticiasPorDataPublicacao(data);
        return noticiasPorData;
    }

    /**
     * Realiza uma pesquisa para localizar notícias associadas a um assunto
     * específico.
     *
     * @param assunto O nome do assunto para o qual deseja localizar notícias.
     * @return Uma lista de notícias associadas ao assunto com o ID especificado. Se
     *         ocorrer algum erro durante a pesquisa.
     */
    public static List<Noticia> pesqNoticiaByAssunto(String assunto) {

        DAO.begin();
        List<Noticia> noticiasPorAssunto = daoAssunto.getnoticiasPorAssunto(assunto);
        return noticiasPorAssunto;
    }

    /**
     * Realiza uma pesquisa para localizar assuntos que possuam pelo menos uma
     * notícia associada.
     *
     * @return Uma lista de assuntos que têm pelo menos uma notícia associada. Se
     *         ocorrer algum erro durante a pesquisa.
     */
    public static List<Assunto> pesqNoticiaByQuantNot() {
        DAO.begin();

        List<Assunto> noticiasPorAssunto = daoAssunto.getAssuntosPorQuantidadeNoticia(1);
        return noticiasPorAssunto;
    }

    /**
     * Realiza uma pesquisa para localizar assuntos que possuam uma quantidade
     * específica de notícias associadas.
     *
     * @param quantidade A quantidade desejada de notícias que os assuntos devem
     *                   ter.
     * @return Uma lista de assuntos que atendem ao critério de quantidade
     *         denotícias. Se ocorrer algum erro durante a pesquisa.
     */
    public static List<Assunto> pesqNoticiaByQuantNot(int quantidade) {
        DAO.begin();

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

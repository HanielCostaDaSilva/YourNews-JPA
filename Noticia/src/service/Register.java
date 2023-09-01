package service;

import com.db4o.ObjectContainer;

import model.Assunto;
import model.Noticia;

import util.Util;

public class Register {

    private static ObjectContainer manager = Util.conectarBanco();

    public static void sendToDatabase(Noticia n) {
      manager.store(n);
      manager.commit();
    }

    public static void sendToDatabase(Noticia[] ns) {
      for (Noticia n : ns) {
        manager.store(n);
      }
      manager.commit();
    }

    public static void sendToDatabase(Assunto a) {
      manager.store(a);
      manager.commit();
    }

    public static void sendToDatabase(Assunto[] as) {
      for (Assunto a : as) {
        manager.store(a);
      }
      manager.commit();
    }

    public static void sendToDatabase(Noticia n, Assunto a) {
      manager.store(n);
      manager.store(a);
      manager.commit();
    }
  }
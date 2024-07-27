package service;

import model.Historique;
import model.User;
import model.Book;

import java.util.List;

public interface HistoriqueService {
    void logAction(User user, Book book, String action);
    List<Historique> getHistoriqueByUser(User user);
}

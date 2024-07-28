package service;

import model.Historique;
import model.User;
import model.Book;
import java.util.List;

public interface HistoriqueService {
    void logAction(User user, Book book, String action);
    List<Historique> getHistoryByUser(User user);
    List<Historique> getAllHistory();  // Add this method
}

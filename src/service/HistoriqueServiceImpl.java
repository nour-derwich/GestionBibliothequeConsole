package service;

import model.Historique;
import model.User;
import model.Book;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HistoriqueServiceImpl implements HistoriqueService {
    private List<Historique> historiques = new ArrayList<>();
    private long historiqueIdCounter = 1;

    @Override
    public void logAction(User user, Book book, String action) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Historique historique = new Historique(historiqueIdCounter++, user, book, action, timestamp);
        historiques.add(historique);
    }

    @Override
    public List<Historique> getHistoriqueByUser(User user) {
        return historiques.stream()
                .filter(historique -> historique.getUser().equals(user))
                .collect(Collectors.toList());
    }
}

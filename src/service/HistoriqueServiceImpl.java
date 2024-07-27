package service;

import model.Historique;
import model.User;
import model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoriqueServiceImpl implements HistoriqueService {
    private List<Historique> historiqueList = new ArrayList<>();

    @Override
    public void logAction(User user, Book book, String action) {
        Historique historique = new Historique(System.currentTimeMillis(), user, book, action);
        historiqueList.add(historique);
    }

    @Override
    public List<Historique> getHistoryByUser(User user) {
        return historiqueList.stream()
                .filter(h -> h.getUser().equals(user))
                .collect(Collectors.toList());
    }
}

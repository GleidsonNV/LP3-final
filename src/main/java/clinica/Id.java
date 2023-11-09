package clinica;

import java.util.HashMap;

public class Id {
    private static Id instancia;
    private HashMap<String, Integer> idPorClasse;

    private Id() {
        idPorClasse = new HashMap<>();
    }

    public static Id getInstance() {
        if (instancia == null) {
            instancia = new Id();
        }
        return instancia;
    }

    public int gerarId(String classe) {
        if (!idPorClasse.containsKey(classe)) {
            idPorClasse.put(classe, 0);
        }
        int id = idPorClasse.get(classe);
        id++;
        idPorClasse.put(classe, id);
        return id;
    }
}
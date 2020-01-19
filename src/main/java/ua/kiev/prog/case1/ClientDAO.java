package ua.kiev.prog.case1;

import ua.kiev.prog.shared.Client;

import java.util.List;

public interface ClientDAO {
    void init();
    void addClient(String name, int age);
    void deleteClient(int id);
    List<Client> getAll();
    long count();
}

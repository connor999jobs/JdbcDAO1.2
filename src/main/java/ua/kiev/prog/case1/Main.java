package ua.kiev.prog.case1;

import ua.kiev.prog.shared.Client;
import ua.kiev.prog.shared.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Vadym280576";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        ConnectionFactory factory = new ConnectionFactory(
                DB_CONNECTION, DB_USER, DB_PASSWORD
        );

        Connection conn = factory.getConnection();
        try {
            ua.kiev.prog.case1.ClientDAO dao = (ua.kiev.prog.case1.ClientDAO) new ua.kiev.prog.case1.ClientDAOImpl(conn);
            dao.init();

            while (true) {
                System.out.println("1: add client");
                System.out.println("2: view clients");
                System.out.println("3: view count");
                System.out.print("-> ");

                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        System.out.print("Enter client name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter client age: ");
                        String sAge = sc.nextLine();
                        int age = Integer.parseInt(sAge);

                        dao.addClient(name, age);
                        break;
                    case "2":
                        List<Client> list = dao.getAll();
                        for (Client client : list) {
                            System.out.println(client);
                        }
                        break;
                    case "3":
                        System.out.println(dao.count());
                        break;
                    default:
                        return;
                }
            }
        } finally {
            sc.close();
            if (conn != null) conn.close();
        }
    }
}

import java.io.*;
import java.util.Scanner;

public class CompanyApp {

    private static final String FILE_NAME = "employees.info";
    private static final int READ_FROM_USER = 1;
    private static final int READ_FROM_FILE =2;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Wprowadzenie danych pracowników wybierz:" + READ_FROM_USER);
        System.out.println("Wprowadzenie danych pracowników z pliku wybiesz:" + READ_FROM_FILE);

        int option = sc.nextInt();
        sc.nextLine();
        if (option == READ_FROM_USER){
            Company company = createCompany();
            writeFile(company);
        } else if (option == READ_FROM_FILE) {
            try {
                Company company = readFile();
                System.out.println(company);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Błąd odczytu danych");
            }
        }
        sc.close();
    }

    private static Company readFile() throws IOException, ClassNotFoundException {
        try (
                var fis = new FileInputStream(FILE_NAME);
                var ois = new ObjectInputStream(fis);
        ) {
            return (Company) ois.readObject();
        }
    }

    private static void writeFile(Company company){
        try (
            var fis = new FileOutputStream(FILE_NAME);
            var oos = new ObjectOutputStream(fis);
        ){
            oos.writeObject(company);
            System.out.println("Zapisano dane do pliku" + company.toString());
        } catch (IOException e){
            System.err.println("Błąd zapisu pliku");
        }
    }

    private static Company createCompany(){
        Company company = new Company();
        for (int i = 0; i < Company.MAX_EMPLOYEES; i++) {
            System.out.println("Wprowadź imię:");
            String firstName = sc.nextLine();
            System.out.println("Wprowadź nazwisko:");
            String lastname = sc.nextLine();
            System.out.println("Wprowadź wypłatę:");
            double salary = sc.nextDouble();
            sc.nextLine();
            company.addEmployee(new Employee(firstName,lastname,salary));
        }
        return company;
    }
}
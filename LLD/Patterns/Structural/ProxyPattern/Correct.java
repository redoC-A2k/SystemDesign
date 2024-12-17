package LLD.Patterns.Structural.ProxyPattern;

// Reference if S-> shreyansh jain (concept and coding yt channel) and if R -> refactoring.guru 

// Proxy is a structural design pattern that lets you provide a substitute or placeholder for another object. A proxy controls access to the original object, allowing you to perform something either before or after the request gets through to the original object. (Ref: R)

// Real world usage
// Access restriction -> Proxy server, Regulates which requests to allow to pass through and which not (college internet server)(Ref: S)
// Caching -> Cache will decide whether to fetch the data from server or not (Ref: S)
// Preprocess & postprocessing -> logging , publishing events or notifying
// Spring boot uses bean proxy to manage the beans

// How it works? (https://refactoring.guru/images/patterns/diagrams/proxy/structure-indexed.png?id=e56d420f31925b3d41348c5036d3cd64)
// 1. Create a service interface
// 2. Create a service class implementing service interface
// 3. Create a proxy class implementing service interface , also having a reference to service class . Upon finishing the pre processing , it will call the service class method and then post process the result
// 4. Client should work with service interface , so that it can work with both service class and proxy class


// Example: 
class Employee {}; // Entity class

interface EmployeeDao { // Service interface
    public void create(String client, Employee employee) throws Exception;  // Create employee
    public void delete(String client, int employeeId) throws Exception; // update
    public Employee get(String client, int employeeId) throws Exception; // delete
}

class EmployeeDaoImpl implements EmployeeDao { // Service class
    @Override
    public void create(String client, Employee employee) throws Exception {
        // Creates a new row
        System.out.println("Employee created");
    }
    @Override
    public void delete(String client, int employeeId) throws Exception {
        // Deletes a row
        System.out.println("Employee deleted");
    }
    @Override
    public Employee get(String client, int employeeId) throws Exception {
        // fetches a row
        System.out.println("Employee fetched");
        return null;
    }
}

class EmployeeDaoProxy implements EmployeeDao { // Proxy class
    private EmployeeDao employeeDao;

    public EmployeeDaoProxy() {
        this.employeeDao = new EmployeeDaoImpl();
    }

    public void create (String client, Employee employee) throws Exception {
        if (client.equals("ADMIN")) {
            employeeDao.create(client, employee);
        } else throw new Exception("Access denied");
    }

    @Override
    public void delete(String client, int employeeId) throws Exception {
        if (client.equals("ADMIN")) {
            employeeDao.delete(client, employeeId);
        } else throw new Exception("Access denied");
    }

    @Override
    public Employee get(String client, int employeeId) throws Exception {
        if (client.equals("ADMIN") || client.equals("USER")) {
            return employeeDao.get(client, employeeId);
        } else throw new Exception("Access denied");
    }
}

class Main {
    public static void main(String[] args) {
        try {
            EmployeeDao employeeDao = new EmployeeDaoProxy();
            employeeDao.create("ADMIN", new Employee());
            System.out.println("Operation successful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
package gogl.foo.bar.practice;

import java.util.*;

public class EmployeeHierarchy {

    class Employee {
        int id;
        int managerId;
        String name;
        List<Employee> subordinates;

        public Employee(int id, int managerId, String name) {
            this.id = id;
            this.managerId = managerId;
            this.name = name;
            this.subordinates = new ArrayList<>();
        }

        public void addSubs(List<Employee> employees) {
            this.subordinates.addAll(employees);
        }
    }

    Map<Integer, List<Employee>> employeeMap;

    public static void main(String[] args) {
        EmployeeHierarchy eh = new EmployeeHierarchy();
        eh.solution();

    }

    public void solution() {
        employeeMap = new HashMap<>();
        List<Employee> list = new ArrayList<Employee>() {{
            add(new Employee(1, 4, "A"));
            add(new Employee(2, 4, "B"));
            add(new Employee(3, 4, "C"));
            add(new Employee(4, 5, "D"));
            add(new Employee(5, 7, "E"));
            add(new Employee(6, 8, "F"));
            add(new Employee(7, 8, "G"));
            add(new Employee(8, 9, "H"));
            add(new Employee(9, 9, "I"));
        }};
        Employee root = null;
        for (Employee e : list) {
            if (e.id == e.managerId) {
                root = e;
                continue;
            }
            List<Employee> currSubs = employeeMap.getOrDefault(e.managerId, new ArrayList<>());
            currSubs.add(e);
            employeeMap.put(e.managerId, currSubs);
        }

        setEmployeeTree(root);
        printHierarchy(root);
    }

    public void setEmployeeTree(Employee root) {
        root.addSubs(employeeMap.getOrDefault(root.id, new ArrayList<>()));
        for (Employee e : root.subordinates) {
            setEmployeeTree(e);
        }
    }

    public void printHierarchy(Employee root) {
        List<Employee> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Employee curr = queue.remove(0);
                System.out.print(curr.id + " ");
                queue.addAll(curr.subordinates);
            }
            System.out.println();
        }
    }


}

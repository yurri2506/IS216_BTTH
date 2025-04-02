package ThucHanhJava.Lab02.ThucHanhTaiLop;

public class BTTL1_P2 {
    private class Person {
        private final String name;
        private final int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
        public String Show() {
            return "Person [name=" + name + ",age=" + age + "]";
        }
    }
    
    private class Employee extends Person {
        private double salary;
        public Employee(String name, int age, double salary) {
            super(name, age);
            this.salary = salary;
        }
        @Override
        public String Show() {
            return "Employee [name=" + getName() + ",age=" + getAge() + ",salary=" + salary + "]";
        }
        
        public void addSalary() {
            salary += salary * 0.1;
        }
        public void addSalary(double salary) {
            this.salary += salary;
        }
    }
    public static void main(String[] args) {
        BTTL1_P2 b1 = new BTTL1_P2();
        Person p = b1.new Person("Nguyen Van A", 20);
        Employee e = b1.new Employee("Nguyen Van B", 25, 1000);
        System.out.println(p.Show());
        System.out.println(e.Show());
        e.addSalary();
        System.out.println(e.Show());
        e.addSalary(1000);
        System.out.println(e.Show());
    }
}

/*
 *
 * College Management
 *
 * Student data
 *
 * Student Details
 *
 */
package college;

import jdk.dynalink.NamedOperation;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CollegeManagement {

    public static void main(String[] args) throws InvalidInputException {
        final StudentView studentView = new StudentView();
        final Student student = studentView.getStudentDetails();

        System.out.println("Student details");
        System.out.println(student.getName());
        System.out.println(student.getDateOfBirth());
        System.out.println(student.getPhone());
        System.out.println(student.getDepartment());
        System.out.println(student.getGender());
    }
}

class StudentView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Student getStudentDetails() throws InvalidInputException {
        final String name = getName();
        final String dateOfBirth = getDateOfBirth();
        final Long mobile = getPhone();
        final Student.Department department = getDepartment();
        final Student.Gender gender = getGender();

        return new Student(name, dateOfBirth, mobile, department, gender);
    }

    private final Student.Gender getGender() {
        try {
            System.out.println("Enter a gender");
            String chooseGender = SCANNER.nextLine();

            return Student.Gender.getGender(chooseGender);
        } catch (InvalidInputException e){
            System.out.println(e.getMessage());
            return getGender();
        }
    }

    private final Student.Department getDepartment() {
        try {
            System.out.println("Enter a Department");
            String departmentName = SCANNER.nextLine();

            return Student.Department.getDepartment(departmentName);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return getDepartment();
        }
    }

        private final String getName () {
            try {
                System.out.println("Enter your name");
                String name = SCANNER.nextLine();

                if (name.matches("[a-zA-Z\\s]")) {
                    throw new NameNotProvideException("please enter your name");
                }
                return name;
            } catch (NameNotProvideException e) {
                System.out.println(e.getMessage());
                return getName();
            }
        }

        private final String getDateOfBirth () {
        try{
                System.out.println("Enter your date of birth");
                String dob = SCANNER.nextLine();

                if(!dob.matches("[0-9\\-]+$")){
                    throw new NameNotProvideException("please enter your DateOfBirth");
                }
                return dob;
        } catch(NameNotProvideException e){
            System.out.println(e.getMessage());
            return getDateOfBirth();
        }

        }

        private final Long getPhone () {
            try {
                System.out.println("Enter your phone number");
                return SCANNER.nextLong();
            } catch (Exception e) {
                System.out.println("Please enter a phone number");
                return getPhone();
            }
        }
    }

    class Student {
        enum Department {
            BIOMEDICAL("biomedical"), BIOTECH("biotech"), CIVIL("civil"), COUMPUTERSCIENCE("computerscience"), MECHANICAL("mech");

            private final String departmentName;

            Department(final String departmentName) {
                this.departmentName = departmentName;
            }

            public static final Department getDepartment(final String department) throws InvalidInputException {
                switch (department) {
                    case "civil":
                        return Department.CIVIL;
                    case "mechanical":
                        return Department.MECHANICAL;
                    case "biomedical":
                        return Department.BIOMEDICAL;
                    case "biotech":
                        return Department.BIOTECH;
                    case "computer science":
                        return Department.COUMPUTERSCIENCE;
                    default:
                        throw new InvalidInputException("Invalid department. Enter a valid Department");
                }
            }
        }

        enum Gender {
            MALE("male"), FEMALE("female"), OTHERS("others");
             private final String chooseGender;

            Gender(final String chooseGender) {
                this.chooseGender = chooseGender;
            }

            public static final Gender getGender(final String gender) throws InvalidInputException {
                switch (gender) {
                    case "male":
                        return Gender.MALE;
                    case "female":
                        return Gender.FEMALE;
                    case "others":
                        return Gender.OTHERS;
                    default:
                        throw new InvalidInputException("Invalid Gender. Enter a valid Gender");
                }
            }

        }

        private Department department;
        private Gender gender;
        private String name;
        private String dateOfBirth;
        private Long phone;

        Student(final String name, final String dateOfBirth, final Long phone, final Department department, final Gender gender) {
            this.name = name;
            this.dateOfBirth = dateOfBirth;
            this.phone = phone;
            this.department = department;
            this.gender = gender;
        }

        public final String getName() {
            return name;
        }

        public final void setName(final String name) {
            this.name = name;
        }

        public final String getDateOfBirth() {
            return dateOfBirth;
        }

        public final void setDateOfBirth(final String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public final Long getPhone() {
            return phone;
        }

        public final void setPhone(final Long phone) {
            this.phone = phone;
        }

        public final Department getDepartment() {
            return department;
        }

        public final void setDepartment(final Department department) {
            this.department = department;
        }

        public final Gender getGender() {
            return gender;
        }

        public final void setGender(Gender gender) {
            this.gender = gender;
        }
}
class InvalidInputException extends Exception{

    public InvalidInputException(String message){
        super(message);
    }
}

class NameNotProvideException extends InvalidInputException{

    public NameNotProvideException(String message){
        super(message);
    }
}




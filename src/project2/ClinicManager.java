package project2;
import util.Date;
import util.List;
import util.Sort;

import java.text.DecimalFormat;
import java.util.Scanner;

public class ClinicManager {
    private Scanner scanner;
    List<Appointment> appointments = new List<>();
    List<Provider> providers = new List<>();
    List<Timeslot> timeslots = Timeslot.generateTimeslots();
    DecimalFormat df = new DecimalFormat("#,###.00");

    public ClinicManager(){
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Scheduler is running.");
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) System.out.println("");

            else if (input.charAt(0) == 'Q') {
                System.out.println("Scheduler is terminated.");
                break;
            }
            else {
                processProviders(input);
                processCommand(input);
            }
        }
        scanner.close();
    }

    private void processProviders(String input){

    }

    // Direct commands to respective functions
    private void processCommand(String input) {
        String command = getCommand(input);
        String data = getData(input); // Rest of information after first comma
        String[] separated_data = data.split(",");

        switch (command) {
            case "D":
                scheduleAppointment(separated_data);
                break;
            case "C":
                cancelAppointment(separated_data);
                break;
            case "R":
                rescheduleAppointment(separated_data);
                break;
            case "PA":
                Sort.appointment(appointments, 'A');
                printAppointments(appointments);
                break;
            case "PP":
                Sort.appointment(appointments, 'P');
                printAppointments(appointments);
                break;
            case "PL":
                Sort.appointment(appointments, 'L');
                printAppointments(appointments);
                break;
                case "PC "
            case "PS":
                printStatements();
                appointments = new List();
                break;
            default:
                System.out.println("Invalid command!");
                break;
        }
    }

    // Takes user input, returns command portion (S, C, PL, etc)
    private String getCommand(String input) {
        int commaIndex = input.indexOf(',');
        if(commaIndex == -1) return input;
        return input.substring(0, commaIndex);
    }

    // Takes user input, returns all information after command portion
    private String getData(String input) {
        int commaIndex = input.indexOf(',');
        return input.substring(commaIndex + 1);
    }

    // Takes array of Strings containing data after command, adds an appointment to the List if valid
    private void scheduleAppointment(String[] separated_data) {
        String[] dateStrings = separated_data[0].split("/");
        int month = Integer.parseInt(dateStrings[0]);
        int day = Integer.parseInt(dateStrings[1]);
        int year = Integer.parseInt(dateStrings[2]);
        Date appointmentDate = new Date(month, day, year);

        String timeslotString = separated_data[1];
        if(!Character.isDigit(timeslotString.charAt(0))){
            System.out.println(timeslotString + " is not a valid timeslot.");
            return;
        }
        if(Integer.parseInt(timeslotString) < 1 || Integer.parseInt(timeslotString) > Timeslot.values().length){
            System.out.println(timeslotString + " is not a valid timeslot.");
            return;
        }
        Timeslot timeslot = Timeslot.valueOf("SLOT" + Integer.parseInt(timeslotString));

        String fname = separated_data[2];
        String lname = separated_data[3];
        String[] dobStrings = separated_data[4].split("/");
        int dobMonth = Integer.parseInt(dobStrings[0]);
        int dobDay = Integer.parseInt(dobStrings[1]);
        int dobYear = Integer.parseInt(dobStrings[2]);
        Date dobDate = new Date(dobMonth, dobDay, dobYear);
        Profile patient = new Profile(fname, lname, dobDate);

        String providerString = separated_data[5];
        if(!checkProviderExists(providerString.toUpperCase())){
            System.out.println(providerString.substring(0,1).toUpperCase() + providerString.substring(1).toLowerCase() +
                    " - provider doesn't exist.");
            return;
        }
        Provider provider = Provider.valueOf(providerString.toUpperCase());

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, provider);

        if(appointment.appointmentValid(appointment, appointments) != null){
            System.out.println(appointment.appointmentValid(appointment, appointments));
            return;
        }

        if(providerBooked(timeslot, appointment)){
            System.out.println("[" + providerString.toUpperCase() + ", " +
                    provider.getLocation().toString().toUpperCase() + ", " + provider.getLocation().countyString() + " " +
                    provider.getLocation().getZip() + ", " + provider.getSpecialty().toString().toUpperCase() + "] is not available at slot " +
                    timeslotString + ".");
            return;
        }

        appointments.add(appointment);
        addToMedicalRecord(patient, appointment);
        System.out.println(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " +
                fname + " " + lname + " " + dobDate.toString() + " [" + providerString.toUpperCase() + ", " +
                provider.getLocation().toString().toUpperCase() + ", " + provider.getLocation().countyString() + " " +
                provider.getLocation().getZip() + ", " + provider.getSpecialty().toString().toUpperCase() + "] " +
                "booked.");
    }

    // Takes array of Strings containing data after command, removes an appointment on list if it exists
    private void cancelAppointment(String[] separated_data) {
        String[] dateStrings = separated_data[0].split("/");
        int month = Integer.parseInt(dateStrings[0]);
        int day = Integer.parseInt(dateStrings[1]);
        int year = Integer.parseInt(dateStrings[2]);
        Date appointmentDate = new Date(month, day, year);

        String timeslotString = separated_data[1];
        Timeslot timeslot = Timeslot.valueOf("SLOT" + Integer.parseInt(timeslotString));

        String fname = separated_data[2];
        String lname = separated_data[3];
        String[] dobStrings = separated_data[4].split("/");
        int dobMonth = Integer.parseInt(dobStrings[0]);
        int dobDay = Integer.parseInt(dobStrings[1]);
        int dobYear = Integer.parseInt(dobStrings[2]);
        Date dobDate = new Date(dobMonth, dobDay, dobYear);
        Profile patient = new Profile(fname, lname, dobDate);

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, Provider.PATEL);

        if(appointments.contains(appointment)){
            appointments.remove(appointment);
            removePatientVisit(patient, appointment);
            System.out.println(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " +
                    fname + " " + lname + " " + dobDate.toString() + " has been canceled.");
        }
        else System.out.println(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " +
                fname + " " + lname + " " + dobDate.toString() + " does not exist.");
    }

    // Takes array of Strings containing data after command, reschedules an appointment to a different timeslot (same day, same provider)
    private void rescheduleAppointment(String[] separated_data) {
        String[] dateStrings = separated_data[0].split("/");
        int month = Integer.parseInt(dateStrings[0]);
        int day = Integer.parseInt(dateStrings[1]);
        int year = Integer.parseInt(dateStrings[2]);
        Date appointmentDate = new Date(month, day, year);

        String timeslotString = separated_data[1];

        if(!Timeslot.contains("SLOT" + Integer.parseInt(timeslotString))){
            System.out.println(timeslotString + " is not a valid time slot.");
            return;
        }

        Timeslot timeslot = Timeslot.valueOf("SLOT" + Integer.parseInt(timeslotString));


        String fname = separated_data[2];
        String lname = separated_data[3];
        String[] dobStrings = separated_data[4].split("/");
        int dobMonth = Integer.parseInt(dobStrings[0]);
        int dobDay = Integer.parseInt(dobStrings[1]);
        int dobYear = Integer.parseInt(dobStrings[2]);
        Date dobDate = new Date(dobMonth, dobDay, dobYear);
        Profile patient = new Profile(fname, lname, dobDate);

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, Provider.PATEL);
        if(!appointments.contains(appointment)) {
            System.out.println(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " +
                    fname + " " + lname + " " + dobDate.toString() + " does not exist.");
            return;
        }
        Appointment oldAppointment = appointments.getAppointments()[appointments.findIdx(appointment)];

        String newTimeslotString = separated_data[5];
        if(!Timeslot.contains("SLOT" + Integer.parseInt(newTimeslotString))){
            System.out.println(newTimeslotString + " is not a valid time slot.");
            return;
        }
        Timeslot newTimeslot = Timeslot.valueOf("SLOT" + Integer.parseInt(newTimeslotString));

        Appointment newAppointment = new Appointment(appointmentDate, newTimeslot, patient, oldAppointment.getProvider());

        Provider provider = oldAppointment.getProvider();
        String providerString = provider.toString();
        if(providerBooked(newTimeslot, newAppointment)){
            System.out.println("[" + providerString.toUpperCase() + ", " +
                    provider.getLocation().toString().toUpperCase() + ", " + provider.getLocation().countyString() + " " +
                    provider.getLocation().getZip() + ", " + provider.getSpecialty().toString().toUpperCase() + "] is not available at slot " +
                    timeslotString + ".");
            return;
        }

        if(appointments.contains(oldAppointment)){
            appointments.remove(oldAppointment);
            appointments.add(newAppointment);
            System.out.println("Rescheduled to " + newAppointment.getDate().toString() + " " + newAppointment.getTimeslot().toString() + " " +
                    fname + " " + lname + " " + dobDate.toString() + " [" + providerString.toUpperCase() + ", " +
                    provider.getLocation().toString().toUpperCase() + ", " + provider.getLocation().countyString() + " " +
                    provider.getLocation().getZip() + ", " + provider.getSpecialty().toString().toUpperCase() + "]");
        } else System.out.println(newAppointment.getDate().toString() + " " + newAppointment.getTimeslot().toString() + " " +
                fname + " " + lname + " " + dobDate.toString() + " does not exist.");
    }

    // Helper method that checks if a given provider name exists as an enum value in Provider
    private boolean checkProviderExists(String providerString) {
        for (Provider p : Provider.values()) {
            if (p.name().equals(providerString)) {
                return true;
            }
        }
        return false;
    }

    // Takes a given timeslot and appointment, determines if provider of that appointment is busy at that timeslot on that day
    private boolean providerBooked(Timeslot timeslot, Appointment appointment) {
        for(int i = 0; i < appointments.getSize(); i++){
            Appointment pointer = appointments.getAppointments()[i];
            if(pointer.getDate().equals(appointment.getDate()) &&
                    pointer.getTimeslot().equals(appointment.getTimeslot()) &&
                    pointer.getProvider().equals(appointment.getProvider())){
                return true;
            }
        }
        return false;
    }

    // Prints all Patients' billing statements
    private void printStatements(){
        if(record.getPatients()[0] == null){
            System.out.println("No patients found in record.");
            return;
        }

        record.sortRecord(0, record.getSize()-1);

        System.out.println("** Billing statement ordered by patient **");
        for(int i = 0; i < record.getSize(); i++){
            int charge = record.getPatients()[i].charge();
            String fname = record.getPatients()[i].getProfile().getFname();
            String lname = record.getPatients()[i].getProfile().getLname();
            String dobString = record.getPatients()[i].getProfile().getDob().toString();
            System.out.println("(" + (i+1) + ") " + fname + " " + lname + " " + dobString + " [amount due: $" + df.format(charge) + "]");
        }
        System.out.println("** end of list **\n");
    }

    // Helper method to make adding to the record easier
    private void addToMedicalRecord(Profile patient, Appointment appointment){
        Patient patientObj = new Patient(patient);
        int patientIndex = record.patientIdx(patientObj);
        if(patientIndex != -1){
            record.getPatients()[patientIndex].add(appointment);
        } else {
            record.add(patientObj);
            int newPatientIndex = record.patientIdx(patientObj);
            record.getPatients()[newPatientIndex].add(appointment);
        }
    }

    // Helper method to make removing a Visit from a Patient easier (when cancelling an appointment)
    private void removePatientVisit(Profile patient, Appointment appointment){
        Patient patientObj = new Patient(patient);
        int patientIndex = record.patientIdx(patientObj);
        record.getPatients()[patientIndex].remove(appointment);
    }

}

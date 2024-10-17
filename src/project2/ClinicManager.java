package project2;
import util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ClinicManager {
    private Scanner scanner;
    List<Appointment> appointments = new List<>();
    List<Provider> providers = new List<>();
    Circular<Provider> technicians = new Circular<>();
    List<Timeslot> timeslots = Timeslot.generateTimeslots();
    List<Patient> record = new List<>();
    DecimalFormat df = new DecimalFormat("#,###.00");

    public ClinicManager(){
        scanner = new Scanner(System.in);
    }

    public void run() {
        File fp = new File("providers.txt");
        processProviders(fp); //need to find out how to find out ratePerVisit for technicians
        System.out.println("Providers loaded to the list.");
        printProviders();
        System.out.println("\nRotation list for the technicians.");
        printTechnicians();
        System.out.println("\nClinic Manager is running...");
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) System.out.print("");

            else if (input.charAt(0) == 'Q') {
                System.out.println("ClinicManager is terminated.");
                break;
            }
            else {
                processCommand(input);
            }
        }
        scanner.close();
    }

    private void printTechnicians(){
        Technician last = (Technician) technicians.get(technicians.size()-1);
        Technician tempTech = last;
        int idx = technicians.size()-1;
        do{
            System.out.print(tempTech.getProfile().getFname().toUpperCase() + " " + tempTech.getProfile().getLname().toUpperCase() + " (" + tempTech.getLocation().toString() + ")");
            if(idx!=0){
                System.out.print(" --> ");
            }
            idx--;
            tempTech = (Technician)technicians.get(idx);
        } while(tempTech!=null && tempTech!=last);
    }

    private void processProviders(File fp){
        try{
            Scanner scanner = new Scanner(fp);
            while(scanner.hasNextLine()){
                String input = scanner.nextLine().trim();
                providersHelper(input);
            }
        } catch(FileNotFoundException e){
            System.out.println("providers.txt file not found.");
        }
    }

    private void providersHelper(String input){
        String[] separated_data = input.split("\\s+");
        String command = separated_data[0];
        String[] dateStrings;
        int month, day, year;
        Date dob;
        Profile newProfile;
        Location loc;
        Specialty specialty;
        String npi;
        Provider prov;
        switch(command){
            case "D":
                dateStrings = separated_data[3].split("/");
                month = Integer.parseInt(dateStrings[0]);
                day = Integer.parseInt(dateStrings[1]);
                year = Integer.parseInt(dateStrings[2]);
                dob = new Date(month, day, year);
                newProfile = new Profile(separated_data[1], separated_data[2], dob);
                loc = Location.valueOf(separated_data[4]);
                specialty = Specialty.valueOf(separated_data[5]);
                npi = separated_data[6];
                prov = new Doctor(newProfile, loc, specialty, npi);
                providers.add(prov);
                break;
            case "T":
                dateStrings = separated_data[3].split("/");
                month = Integer.parseInt(dateStrings[0]);
                day = Integer.parseInt(dateStrings[1]);
                year = Integer.parseInt(dateStrings[2]);
                dob = new Date(month, day, year);
                newProfile = new Profile(separated_data[1], separated_data[2], dob);
                loc = Location.valueOf(separated_data[4]);
                int ratePerVisit = Integer.parseInt(separated_data[5]);
                prov = new Technician(newProfile, loc, ratePerVisit);
                technicians.add(prov);
                providers.add(prov);
                break;
            default:
                System.out.println("Not valid command.");
                break;
        }
    }

    private void printProviders(){
        Sort.provider(providers);
        for(Provider prov : providers){
            System.out.println(prov.toString()); //Need to reformat
        }
    }

    // Direct commands to respective functions
    private void processCommand(String input) {
        String command = getCommand(input);
        String data = getData(input); // Rest of information after first comma
        String[] separated_data = data.split(",");

        switch (command) {
            case "D":
                //Schedule new office appointment (Doctor)
                scheduleAppointment(separated_data);
                break;
            case "T":
                //Schedule new imaging appointment (Technician)
                scheduleImagingAppointment(separated_data);
                break;
            case "C":
                //Cancel any type of appointment (Should work the same as Project 1)
                cancelAppointment(separated_data);
                break;
            case "R":
                //Should work same as project 1
                //rescheduleAppointment(separated_data);
                break;
            case "PA":
                //Implement printAppointments
                printAppointments(Sort.appointment(appointments, 'A'));
                break;
            case "PP":
                printAppointments(Sort.appointment(appointments, 'P'));
                break;
            case "PL":
                printAppointments(Sort.appointment(appointments, 'L'));
                break;
            case "PC":
                //Need to implement
                break;
            case "PO":
                //Need to implement
                printAppointments(Sort.appointment(appointments, 'O'));
                break;
            case "PI":
                printAppointments(Sort.appointment(appointments, 'i'));
                break;
            case "PS":
                printStatements();
                appointments = new List<>();
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
        if(Integer.parseInt(timeslotString) < 1 || Integer.parseInt(timeslotString) > 12){
            System.out.println(timeslotString + " is not a valid timeslot.");
            return;
        }

        Timeslot timeslot = timeslots.get(Integer.parseInt(timeslotString)-1);

        String fname = separated_data[2];
        String lname = separated_data[3];
        String[] dobStrings = separated_data[4].split("/");
        int dobMonth = Integer.parseInt(dobStrings[0]);
        int dobDay = Integer.parseInt(dobStrings[1]);
        int dobYear = Integer.parseInt(dobStrings[2]);
        Date dobDate = new Date(dobMonth, dobDay, dobYear);
        Profile patient = new Profile(fname, lname, dobDate);


        String npi = separated_data[5];
        if(!checkProviderExists(npi)){
            System.out.println(npi +
                    " - provider doesn't exist.");
            return;
        }

        Provider provider = null;
        for(Provider prov : providers){
            if(prov.getClass()==Doctor.class && ((Doctor) prov).getNpi().equals(npi)){
                provider = prov;
            }
        }

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, provider);

        if(appointment.appointmentValid(appointment, appointments) != null){
            System.out.println(appointment.appointmentValid(appointment, appointments));
            return;
        }

        Doctor doc = (Doctor) provider;

        if(providerBooked(timeslot, appointment)){
            System.out.println("[" + doc.getProfile().getFname().toUpperCase() + " " + doc.getProfile().getLname().toUpperCase() +
                    " " + doc.getProfile().getDob().toString() + ", " +
                    doc.getLocation().toString() + ", " + doc.getLocation().countyString() + " " + doc.getLocation().getZip() + "][" +
                    doc.getSpecialty().toString() + ", #" + doc.getNpi() + "] is not available at slot " + timeslotString + ".");
            return;
        }

        appointments.add(appointment);
        addToMedicalRecord(patient, appointment);
        System.out.println(appointment.getDate().toString() + " " + timeslot.toString() + " " + patient.getFname() + " " + patient.getLname() + patient.getDob().toString() + " " +
                "[" + doc.getProfile().getFname().toUpperCase() + " " + doc.getProfile().getLname().toUpperCase() +
                " " + doc.getProfile().getDob().toString() + ", " +
                doc.getLocation().toString() + ", " + doc.getLocation().countyString() + " " + doc.getLocation().getZip() + "][" +
                doc.getSpecialty().toString() + ", #" + doc.getNpi() + "] booked.");
    }

    // Takes array of Strings containing data after command, removes an appointment on list if it exists
    private void cancelAppointment(String[] separated_data) {
        try {
            String[] dateStrings = separated_data[0].split("/");
            int month = Integer.parseInt(dateStrings[0]);
            int day = Integer.parseInt(dateStrings[1]);
            int year = Integer.parseInt(dateStrings[2]);
            Date appointmentDate = new Date(month, day, year);

            String timeslotString = separated_data[1];
            Timeslot timeslot = timeslots.get(Integer.parseInt(timeslotString) - 1);

            if(timeslot==null){
                System.out.println(timeslotString + " is not a valid timeslot.");
                return;
            }

            String fname = separated_data[2];
            String lname = separated_data[3];
            String[] dobStrings = separated_data[4].split("/");
            int dobMonth = Integer.parseInt(dobStrings[0]);
            int dobDay = Integer.parseInt(dobStrings[1]);
            int dobYear = Integer.parseInt(dobStrings[2]);
            Date dobDate = new Date(dobMonth, dobDay, dobYear);
            Profile patient = new Profile(fname, lname, dobDate);

            Appointment appointment = null;

            for(Appointment app : appointments){
                if(app.getDate().equals(appointmentDate) && app.getTimeslot().equals(timeslot) && app.getProfile().equals(patient)){
                    appointment = app;
                }
            }

            if (appointments.contains(appointment)) {
                appointments.remove(appointment);
                removePatientVisit(patient, appointment);
                System.out.println(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " +
                        fname + " " + lname + " " + dobDate.toString() + " has been canceled.");
            } else
                System.out.println(appointment.getDate().toString() + " " + appointment.getTimeslot().toString() + " " +
                        fname + " " + lname + " " + dobDate.toString() + " does not exist.");
        } catch (Exception e){
            System.out.println("Missing data tokens.");
        }
    }

    //Takes array of Strings containing data after command, reschedules an appointment to a different timeslot (same day, same provider)
    private void rescheduleAppointment(String[] separated_data) {

        String[] dateStrings = separated_data[0].split("/");
        int month = Integer.parseInt(dateStrings[0]);
        int day = Integer.parseInt(dateStrings[1]);
        int year = Integer.parseInt(dateStrings[2]);
        Date appointmentDate = new Date(month, day, year);

        String timeslotString = separated_data[1];

        Timeslot timeslot = timeslots.get(Integer.parseInt(timeslotString) - 1);

        if(timeslot==null){
            System.out.println(timeslotString + " is not a valid timeslot.");
            return;
        }

        String fname = separated_data[2];
        String lname = separated_data[3];
        String[] dobStrings = separated_data[4].split("/");
        int dobMonth = Integer.parseInt(dobStrings[0]);
        int dobDay = Integer.parseInt(dobStrings[1]);
        int dobYear = Integer.parseInt(dobStrings[2]);
        Date dobDate = new Date(dobMonth, dobDay, dobYear);
        Profile patient = new Profile(fname, lname, dobDate);

        Appointment appointment = null;

        for(Appointment app : appointments){
            if(app.getDate().equals(appointmentDate) && app.getTimeslot().equals(timeslot) && app.getProfile().equals(patient)){
                appointment = app;
            }
        }

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
    private boolean checkProviderExists(String npi) {
        for (Provider p : providers) {
            if (p.getClass()==Doctor.class && ((Doctor) p).getNpi().equals(npi)) {
                return true;
            }
        }
        return false;
    }

    // Takes a given timeslot and appointment, determines if provider of that appointment is busy at that timeslot on that day
    private boolean providerBooked(Timeslot timeslot, Appointment appointment) {
        for(int i = 0; i < appointments.size(); i++){
            Appointment pointer = appointments.get(i);
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
        if(record.isEmpty()){
            System.out.println("No patients found in record.");
            return;
        }

        Sort.patient(record);

        System.out.println("** Billing statement ordered by patient **");
        int count = 0;
        for(Patient p : record){
            int charge = p.charge();
            String fname = p.getProfile().getFname();
            String lname = p.getProfile().getLname();
            String dobString = p.getProfile().getDob().toString();
            System.out.println("(" + (count+1) + ") " + fname + " " + lname + " " + dobString + " [amount due: $" + df.format(charge) + "]");
        }
        System.out.println("** end of list **\n");
    }

    // Helper method to make adding to the record easier
    private void addToMedicalRecord(Profile patient, Appointment appointment){
        Patient patientObj = new Patient(patient);
        int patientIndex = record.indexOf(patientObj);
        if(patientIndex != -1){
            record.get(patientIndex).add(appointment);
        } else {
            record.add(patientObj);
            int newPatientIndex = record.indexOf(patientObj);
            record.get(newPatientIndex).add(appointment);
        }
    }

    // Helper method to make removing a Visit from a Patient easier (when cancelling an appointment)
    private void removePatientVisit(Profile patient, Appointment appointment){
        Patient patientObj = new Patient(patient);
        int patientIndex = record.indexOf(patientObj);
        record.get(patientIndex).remove(appointment);
    }

    private void printAppointments(List<Appointment> appointments){
        for(int i = 0; i< appointments.size(); i++){
            String date = appointments.get(i).getDate().toString();
            String time = appointments.get(i).getTimeslot().toString();
            String fname = appointments.get(i).getProfile().getFname();
            String lname = appointments.get(i).getProfile().getLname();
            String dob = appointments.get(i).getProfile().getDob().toString();
            if(appointments.get(i).getProvider().getClass()==Doctor.class){
                System.out.println(date + " " + time + " " + fname + " " + lname + " " + dob +
                        " " + ((Doctor)(appointments.get(i).getProvider())).toString());
            } else {
                System.out.println(date + " " + time + " " + fname + " " + lname + " " + dob +
                        " " + ((Technician)(appointments.get(i).getProvider())).toString());
            }
        }
    }

    private void scheduleImagingAppointment(String[] separated_data){
        // This try/catch is to catch errors where user doesn't include entire command
        try{
            separated_data[0] = separated_data[0];
            separated_data[1] = separated_data[1];
            separated_data[2] = separated_data[2];
            separated_data[3] = separated_data[3];
            separated_data[4] = separated_data[4];
            separated_data[5] = separated_data[5];
        } catch(IndexOutOfBoundsException e){
            System.out.println("Missing data tokens.");
            return;
        }

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
        if(Integer.parseInt(timeslotString) < 1 || Integer.parseInt(timeslotString) > timeslots.size()){
            System.out.println(timeslotString + " is not a valid timeslot.");
            return;
        }
        Timeslot timeslot = timeslots.get(Integer.parseInt(timeslotString) - 1);

        String fname = separated_data[2];
        String lname = separated_data[3];
        String[] dobStrings = separated_data[4].split("/");
        int dobMonth = Integer.parseInt(dobStrings[0]);
        int dobDay = Integer.parseInt(dobStrings[1]);
        int dobYear = Integer.parseInt(dobStrings[2]);
        Date dobDate = new Date(dobMonth, dobDay, dobYear);
        Profile patient = new Profile(fname, lname, dobDate);

        String imagingType = separated_data[5];
        if(!(imagingType.toUpperCase().equals(Radiology.CATSCAN.toString()) ||
                imagingType.toUpperCase().equals(Radiology.ULTRASOUND.toString()) ||
                imagingType.toUpperCase().equals(Radiology.XRAY.toString()))){
            System.out.println(imagingType.toUpperCase() + " is not a valid imaging type.");
            return;
        }

        Provider provider = null;
        for(Provider prov : providers){
            if(prov.getClass()==Technician.class){
                provider = prov;
            }
        }

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, provider);

        if(appointment.appointmentValid(appointment, appointments) != null){
            System.out.println(appointment.appointmentValid(appointment, appointments));
            return;
        }

        Technician tech = (Technician) provider;

        if(providerBooked(timeslot, appointment)){
            System.out.println("[" + tech.getProfile().getFname().toUpperCase() + " " + tech.getProfile().getLname().toUpperCase() +
                    " " + tech.getProfile().getDob().toString() + ", " +
                    tech.getLocation().toString() + ", " + tech.getLocation().countyString() + " " + tech.getLocation().getZip() + "][" +
                    "rate: $" + df.format(tech.rate()) + "] is not available at slot " + timeslotString + ".");
            return;
        }

        appointments.add(appointment);
        addToMedicalRecord(patient, appointment);
        System.out.println(appointment.getDate().toString() + " " + timeslot.toString() + " " + patient.getFname() + " " + patient.getLname() + patient.getDob().toString() + " " +
                "[" + tech.getProfile().getFname().toUpperCase() + " " + tech.getProfile().getLname().toUpperCase() +
                " " + tech.getProfile().getDob().toString() + ", " +
                tech.getLocation().toString() + ", " + tech.getLocation().countyString() + " " + tech.getLocation().getZip() + "][" +
                "rate: $" + df.format(tech.rate()) + "] booked.");
    }
}

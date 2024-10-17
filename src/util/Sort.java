package util;

import project2.*;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class Sort {

    private static int partitionByA(int sIdx, int eIdx, List<Appointment> appointments){
        Appointment piv = appointments.get(eIdx);
        int i = (sIdx - 1);
        for(int j = sIdx; j < eIdx; j++){
            if(appointments.get(j).getDate().compareTo(piv.getDate())<0){
                i++;
                Appointment temp = appointments.get(i);
                appointments.set(i, appointments.get(j));
                appointments.set(j, temp);
            } else if(appointments.get(j).getDate().compareTo(piv.getDate())==0){
                if(appointments.get(j).getTimeslot().compareTo(piv.getTimeslot())<0){
                    i++;
                    Appointment temp = appointments.get(i);
                    appointments.set(i, appointments.get(j));
                    appointments.set(j, temp);
                } else if(appointments.get(j).getTimeslot().compareTo(piv.getTimeslot())==0){
                    if(appointments.get(j).getProvider().compareTo(piv.getProvider())<=0){
                        i++;
                        Appointment temp = appointments.get(i);
                        appointments.set(i, appointments.get(j));
                        appointments.set(j, temp);
                    }
                }
            }
        }
        Appointment temp = appointments.get(i+1);
        appointments.set(i+1, appointments.get(eIdx));
        appointments.set(eIdx, temp);

        return i+1;
    }

    private static void sortByA(int sIdx, int eIdx, List<Appointment> appointments){
        if(sIdx < eIdx){
            int pIdx = partitionByA(sIdx, eIdx, appointments);
            sortByA(sIdx, pIdx-1, appointments);
            sortByA(pIdx+1, eIdx, appointments);
        }
    }

    private static int partitionByP(int sIdx, int eIdx, List<Appointment> appointments){
        Appointment piv = appointments.get(eIdx);
        int i = (sIdx - 1);
        for(int j = sIdx; j < eIdx; j++){
            if(appointments.get(j).getProfile().compareTo(piv.getProfile())<0){
                i++;
                Appointment temp = appointments.get(i);
                appointments.set(i, appointments.get(j));
                appointments.set(j, temp);
            } else if(appointments.get(j).getProfile().compareTo(piv.getProfile())==0){
                if(appointments.get(j).getDate().compareTo(piv.getDate())<0){
                    i++;
                    Appointment temp = appointments.get(i);
                    appointments.set(i, appointments.get(j));
                    appointments.set(j, temp);
                } else if(appointments.get(j).getDate().compareTo(piv.getDate())==0){
                    if(appointments.get(j).getTimeslot().compareTo(piv.getTimeslot())<=0){
                        i++;
                        Appointment temp = appointments.get(i);
                        appointments.set(i, appointments.get(j));
                        appointments.set(j, temp);
                    }
                }
            }
        }
        Appointment temp = appointments.get(i+1);
        appointments.set(i+1, appointments.get(eIdx));
        appointments.set(eIdx, temp);

        return i+1;
    }

    private static void sortByP(int sIdx, int eIdx, List<Appointment> appointments){
        if(sIdx < eIdx){
            int pIdx = partitionByP(sIdx, eIdx, appointments);
            sortByP(sIdx, pIdx-1, appointments);
            sortByP(pIdx+1, eIdx, appointments);
        }
    }

    private static int partitionByL(int sIdx, int eIdx, List<Appointment> appointments){
        Appointment piv = appointments.get(eIdx);
        int i = (sIdx - 1);
        for(int j = sIdx; j < eIdx; j++){
            if(appointments.get(j).getProvider().getLocation().getCounty().compareTo(piv.getProvider().getLocation().getCounty())<0){
                i++;
                Appointment temp = appointments.get(i);
                appointments.set(i, appointments.get(j));
                appointments.set(j, temp);
            } else if(appointments.get(j).getProvider().getLocation().getCounty().compareTo(piv.getProvider().getLocation().getCounty())==0){
                if(appointments.get(j).getDate().compareTo(piv.getDate())<0){
                    i++;
                    Appointment temp = appointments.get(i);
                    appointments.set(i, appointments.get(j));
                    appointments.set(j, temp);
                } else if(appointments.get(j).getDate().compareTo(piv.getDate())==0){
                    if(appointments.get(j).getTimeslot().compareTo(piv.getTimeslot())<=0){
                        i++;
                        Appointment temp = appointments.get(i);
                        appointments.set(i, appointments.get(j));
                        appointments.set(j, temp);
                    }
                }
            }
        }
        Appointment temp = appointments.get(i+1);
        appointments.set(i+1, appointments.get(eIdx));
        appointments.set(eIdx, temp);

        return i+1;
    }

    private static int partitionProviders(int sIdx, int eIdx, List<Provider> providers){
        Provider piv = providers.get(eIdx);
        int i = (sIdx-1);

        for (int j = sIdx; j<eIdx; j++){
            if(providers.get(j).getProfile().compareTo(piv.getProfile())<=0){
                i++;
                Provider temp = providers.get(i);
                providers.set(i, providers.get(j));
                providers.set(j, temp);
            }
        }
        Provider temp = providers.get(i+1);
        providers.set(i+1, providers.get(eIdx));
        providers.set(eIdx, temp);

        return i+1;
    }

    private static void sortByProviders(int sIdx, int eIdx, List<Provider> providers){
        if(sIdx < eIdx){
            int pIdx = partitionProviders(sIdx, eIdx, providers);
            sortByProviders(sIdx, pIdx-1, providers);
            sortByProviders(pIdx+1, eIdx, providers);
        }
    }

    private static int partitionPatients(int sIdx, int eIdx, List<Patient> patients){
        Patient piv = patients.get(eIdx);
        int i = (sIdx-1);

        for (int j = sIdx; j<eIdx; j++){
            if(patients.get(j).getProfile().compareTo(piv.getProfile())<=0){
                i++;
                Patient temp = patients.get(i);
                patients.set(i, patients.get(j));
                patients.set(j, temp);
            }
        }
        Patient temp = patients.get(i+1);
        patients.set(i+1, patients.get(eIdx));
        patients.set(eIdx, temp);

        return i+1;
    }

    private static void sortByPatients(int sIdx, int eIdx, List<Patient> patients){
        if(sIdx < eIdx){
            int pIdx = partitionPatients(sIdx, eIdx, patients);
            sortByPatients(sIdx, pIdx-1, patients);
            sortByPatients(pIdx+1, eIdx, patients);
        }
    }

    private static void sortByL(int sIdx, int eIdx, List<Appointment> appointments){
        if(sIdx < eIdx){
            int pIdx = partitionByL(sIdx, eIdx, appointments);
            sortByL(sIdx, pIdx-1, appointments);
            sortByL(pIdx+1, eIdx, appointments);
        }
    }

    public static void patient(List<Patient> patients){
        sortByPatients(0, patients.size()-1, patients);
    }

    /**
     * @param appointments list to be sorted
     * @param key defines the type of sorting
     * @return a List of type Appointment that is sorted
     */
    public static List<Appointment> appointment(List<Appointment> appointments, char key){
        switch(key){
            case 'A': //Sort by date, time, provider
                sortByA(0, appointments.size()-1, appointments);
                return appointments;
            case 'P': //Sort by patient
                sortByP(0, appointments.size()-1, appointments);
                return appointments;
            case 'L': //Sort by county, date, time
                sortByL(0, appointments.size()-1, appointments);
                return appointments;
            case 'O': //Sort office appointments by county, date, time
                List<Appointment> office = separateOffice(appointments);
                sortByL(0, office.size()-1, office);
                return office;
            case 'I': //Sort imaging appointments by county, date, time
                List<Appointment> imaging = separateImaging(appointments);
                sortByL(0, imaging.size()-1, imaging);
                return imaging;
            default:
                throw new IllegalArgumentException();
        }
    }


    private static List<Appointment> separateImaging(List<Appointment> appointments){
        List<Appointment> imaging = new List<>();
        for(Appointment app : appointments){
            if(app.getClass() == Imaging.class){
                imaging.add(app);
            }
        }
        return imaging;
    }

    private static List<Appointment> separateOffice(List<Appointment> appointments){
        List<Appointment> office = new List<>();
        for(Appointment app : appointments){
            if(app.getClass() != Imaging.class){
                office.add(app);
            }
        }
        return office;
    }


    /**
     * @param providers list to be sorted
     * method that sorts a List of type Provider by profile
     */
    public static void provider(List<Provider> providers){
        sortByProviders(0, providers.size()-1, providers);
    }
}

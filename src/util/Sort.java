package util;

import project2.*;

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

    private static void sortByL(int sIdx, int eIdx, List<Appointment> appointments){
        if(sIdx < eIdx){
            int pIdx = partitionByL(sIdx, eIdx, appointments);
            sortByL(sIdx, pIdx-1, appointments);
            sortByL(pIdx+1, eIdx, appointments);
        }
    }

    public static void appointment(List<Appointment> appointments, char key){
        switch(key){
            case 'A': //Sort by date, time, provider
                sortByA(0, appointments.size()-1, appointments);
                break;
            case 'P': //Sort by patient
                sortByP(0, appointments.size()-1, appointments);
                break;
            case 'L': //Sort by county, date, time
                sortByL(0, appointments.size()-1, appointments);
                break;
            case 'O': //Sort office appointments by county, date, time
                break;
            case 'I': //Sort imaging appointments by county, date, time
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
    public static void provider(List<Provider> providers){}
}

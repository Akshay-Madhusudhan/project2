package project2;

public class Person implements Comparable<Person>{
    protected Profile profile;

    public Person(Profile profile){
        this.profile = profile;
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj.getClass()!=this.getClass()){
            return false;
        }
        Person p = (Person) obj;
        return p.profile.compareTo(this.profile)==0;
    }

    @Override
    public int compareTo(Person o) {
        return o.profile.compareTo(this.profile);
    }

    public Profile getProfile(){
        return this.profile;
    }

}

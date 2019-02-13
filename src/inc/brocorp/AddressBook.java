package inc.brocorp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressBook implements Edit {

    private static final ObservableList<Persons> persons = FXCollections.observableArrayList();

    @Override
    public void add(Persons person) {
        if(!persons.contains(person))
        persons.add(person);
    }

    @Override
    public void update(Persons person) {

    }

    @Override
    public void delete(Persons person) {
        if(persons.contains(person))
            persons.remove(person);
    }
    
    public ObservableList<Persons> getPersons(){
        return persons;
    }

    public void fillTestData(){
        persons.add(new Persons("Alex von Trostoff", "4567432"));
        persons.add(new Persons("Axel Rose", "944567432"));
        persons.add(new Persons("Slash guitargay ", "DoReMiFa"));
        persons.add(new Persons("Konstantin Dragonite", "call to cze"));
        persons.add(new Persons("Vladimir \"Czar\" Put in", "unknown"));
        persons.add(new Persons("Yuri \"The\" Professional", "8915467333"));

    }
}

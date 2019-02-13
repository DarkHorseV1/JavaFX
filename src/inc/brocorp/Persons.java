package inc.brocorp;

import javafx.beans.property.SimpleStringProperty;

public class Persons {

    private SimpleStringProperty fio = new SimpleStringProperty("");
    private SimpleStringProperty phone = new SimpleStringProperty("");

    public String getFio() {
        return fio.get();
    }

    public void setFio(String fio) {
        this.fio.set(fio) ;
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public SimpleStringProperty fioProperty(){
        return fio;
    }

    public SimpleStringProperty phoneProperty(){
        return phone;
    }

    public Persons(){

    }

    public Persons(String fio, String phone){
        this.fio = new SimpleStringProperty(fio);
        this.phone = new SimpleStringProperty(phone);
    }

}

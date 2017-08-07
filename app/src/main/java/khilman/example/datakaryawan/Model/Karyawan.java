package khilman.example.datakaryawan.Model;

/**
 * Created by root on 04/08/17.
 */

public class Karyawan {
    private long id;
    private String name;
    private String email;
    private String develpment;
    private String pt_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDevelpment() {
        return develpment;
    }

    public void setDevelpment(String develpment) {
        this.develpment = develpment;
    }

    public String getPt_name() {
        return pt_name;
    }

    public void setPt_name(String pt_name) {
        this.pt_name = pt_name;
    }

    @Override
    public String toString() {
        return "Data " + name + " " + develpment;
    }
}

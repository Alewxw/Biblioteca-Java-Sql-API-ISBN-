package data;

public class Autor {
    private String nume;
    private int id;

    public Autor ( String nume, int id )
    {
        this.nume = nume;
        this.id = id;
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return nume;
    }
}

package data;

public class Carte {
    private int id;
    private String titlu;
    private int an_publicare;
    private int editura_id;
    private String numeEditura;

    public Carte ( int id, String titlu, int an_publicare, int editura_id )
    {
        this.id = id;
        this.titlu = titlu;
        this.an_publicare = an_publicare;
        this.editura_id = editura_id;
    }

    public Carte(int id, String titlu, int an_publicare, int editura_id, String numeEditura) {
        this.id = id;
        this.titlu = titlu;
        this.an_publicare = an_publicare;
        this.editura_id = editura_id;
        this.numeEditura = numeEditura;
    }

    public String getNumeEditura() { return numeEditura; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getAn_publicare() {
        return an_publicare;
    }

    public void setAn_publicare(int an_publicare) {
        this.an_publicare = an_publicare;
    }

    public int getEditura_id() {
        return editura_id;
    }

    public void setEditura_id(int editura_id) {
        this.editura_id = editura_id;
    }

    @Override
    public String toString()
    {
        return titlu;
    }
}

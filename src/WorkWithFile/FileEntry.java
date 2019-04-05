package WorkWithFile;

class FileEntry {

    private String geoName;
    private String geoSumlevel;
    private String geoID;
    private String county;
    private int pop2013;
    private int pop2014;
    private int pop2015;
    private int pop2016;

    FileEntry(String geoName, String geoSumlevel, String county, String geoID, int pop2013, int pop2014, int pop2015, int pop2016) {
        this.geoName = geoName;
        this.geoSumlevel = geoSumlevel;
        this.geoID = geoID;
        this.county = county;
        this.pop2013 = pop2013;
        this.pop2014 = pop2014;
        this.pop2015 = pop2015;
        this.pop2016 = pop2016;
    }

    String getGeoID() {
        return geoID;
    }

    int getDelta() {
        return pop2016 - pop2013;
    }

    int getPop2013() {
        return pop2013;
    }

    int getPop2014() {
        return pop2014;
    }

    int getPop2015() {
        return pop2015;
    }

    int getPop2016() {
        return pop2016;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%d,%d,%d,%d (delta %d)", geoName, geoSumlevel, county, geoID, pop2013, pop2014, pop2015, pop2016, getDelta());
    }
}

import java.util.Arrays;

import stdlib.StdOut;

public class Location implements Comparable<Location> {
    private final String name; // location name
    private final double lat;  // latitude
    private final double lon;  // longitude

    // Constructs a new location given its name, latitude, and longitude.
    public Location(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    // Returns the great-circle distance between this location and other.
    public double distanceTo(Location other) {
        double rx1 = Math.toRadians(lat);
        double ry1 = Math.toRadians(lon);
        double rx2 = Math.toRadians(other.lat);
        double ry2 = Math.toRadians(other.lon);
        // copy from project1
        // D = 111 acos(sin(x1)sin(x2)+cos(x1)cos(x2)cos(y1 - y2)).
        double a = Math.cos(rx1) * Math.cos(rx2) * Math.cos(ry1 - ry2);
        double b = Math.acos((Math.sin(rx1) * Math.sin(rx2)) + a);
        double c = Math.toDegrees(b);
        return 111 * c;
    }

    // Returns true if this location is the same as other, and false otherwise.
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }

        Location location = (Location) other;
        // type casting of the argument
        return (location.lat == this.lat && location.lon == this.lon);
    }

    // Returns a string representation of this location.
    public String toString() {
        return name + " (" + lat + ", " + lon + ")";
    }

    // Returns a comparison of this location with other based on their respective distances to
    // the origin, Parthenon (Greece) @ 37.971525, 23.726726.
    public int compareTo(Location that) {
        Location origin = new Location("Greece", 37.971525, 23.726726);
        double d1 = origin.distanceTo(this);
        double d2 = origin.distanceTo(that);
        return Double.compare(d1, d2);
    }

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        int rank = Integer.parseInt(args[0]);
        String name = args[1];
        double lat = Double.parseDouble(args[2]);
        double lon = Double.parseDouble(args[3]);
        Location[] wonders = new Location[7];
        wonders[0] = new Location("The Great Wall of China (China)", 40.6769, 117.2319);
        wonders[1] = new Location("Petra (Jordan)", 30.3286, 35.4419);
        wonders[2] = new Location("The Colosseum (Italy)", 41.8902, 12.4923);
        wonders[3] = new Location("Chichen Itza (Mexico)", 20.6829, -88.5686);
        wonders[4] = new Location("Machu Picchu (Peru)", -13.1633, -72.5456);
        wonders[5] = new Location("Taj Mahal (India)", 27.1750, 78.0419);
        wonders[6] = new Location("Christ the Redeemer (Brazil)", 22.9519, -43.2106);
        Arrays.sort(wonders);
        StdOut.println("Seven wonders, in the order of their distance to Parthenon (Greece):");
        for (Location wonder : wonders) {
            StdOut.println("  " + wonder);
        }
        Location loc = new Location(name, lat, lon);
        StdOut.print("wonders[" + rank + "] == " + loc + "? ");
        StdOut.println(wonders[rank].equals(loc));
    }
}

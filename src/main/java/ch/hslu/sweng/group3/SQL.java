package ch.hslu.sweng.group3;

public class SQL {
    public static final String movieTable = "CREATE TABLE Movie (\n" +
            "    MovieID  INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "                     NOT NULL\n" +
            "                     UNIQUE,\n" +
            "    Title    STRING,\n" +
            "    Duration STRING,\n" +
            "    IsActive BOOLEAN\n" +
            ");";
    public static final String customerTable = "CREATE TABLE Customer (\n" +
            "    CustomerID INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "                       UNIQUE\n" +
            "                       NOT NULL,\n" +
            "    Email      STRING\n" +
            ");";
    public static final String roomTable = "CREATE TABLE Room (\n" +
            "    RoomID        INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "                          UNIQUE\n" +
            "                          NOT NULL,\n" +
            "    AmountOfSeats INT\n" +
            ");";
    public static final String showTable = "CREATE TABLE Show (\n" +
            "    ShowID  INTEGER  PRIMARY KEY AUTOINCREMENT\n" +
            "                     UNIQUE\n" +
            "                     NOT NULL,\n" +
            "    Start   DATETIME,\n" +
            "    MovieID INT      REFERENCES Movie (MovieID),\n" +
            "    RoomID  INT      REFERENCES Room (RoomID)\n" +
            ");";
    public static final String reservationTable = "CREATE TABLE Reservation (\n" +
            "    ReservationID INTEGER PRIMARY KEY AUTOINCREMENT\n" +
            "                          UNIQUE\n" +
            "                          NOT NULL,\n" +
            "    NumberOfSeats INT,\n" +
            "    IsCollected   BOOLEAN,\n" +
            "    CustomerID    INT     REFERENCES Customer (CustomerID),\n" +
            "    ShowID        INT     REFERENCES Show (ShowID)\n" +
            ");";

    public static final String insertRoom1 = "INSERT INTO Room(RoomID,AmountOfSeats) \n" +
                                                "Values(1,50);";

    public static final String insertRoom2 = "INSERT INTO Room(RoomID,AmountOfSeats) \n" +
                                                "Values(2,25);";

    public static final String insertRoom3 = "INSERT INTO Room(RoomID,AmountOfSeats) \n" +
                                                "Values(3,100);";
}

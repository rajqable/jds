package Core;

import java.io.IOException;
public class UserDetails {
    public static void main(String[] args) throws IOException {

        AirdDatabase database = new AirdDatabase();
        AirdDatabase database2 = new AirdDatabase();

        database.createDatabase("Kalpesh");
        database2.createDatabase("Kumar");

        database.createCollection("Suresh");
        database.createCollection("Jayesh");
        database2.createCollection("Db2Jayesh");

        database.addRecords("Suresh", "age", "27");
        database.addRecords("Suresh", "mobile", "837376282");

        database.addRecords("Jayesh", "age", "25");
        database.addRecords("Jayesh", "mobile", "78872838723");
        database.addRecords("Jayesh", "usertype", "admin");

    }
}
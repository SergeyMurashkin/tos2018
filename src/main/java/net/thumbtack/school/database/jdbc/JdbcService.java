package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JdbcService {

    public static void insertTrainee(Trainee trainee) throws SQLException {
        String insertQuery = "INSERT INTO trainees VALUES(?,?,?,?,?)";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, Types.INTEGER);
            stmt.setNull(2, Types.INTEGER);
            stmt.setString(3, trainee.getFirstName());
            stmt.setString(4, trainee.getLastName());
            stmt.setInt(5, trainee.getRating());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    trainee.setId(id);
                } else {
                    throw new SQLException("Creating trainee failed, no ID obtained");
                }
            }
        }
    }

    public static void updateTrainee(Trainee trainee) throws SQLException {
        String updateQuery = "UPDATE trainees SET firstName=?, lastName=?, rating=? WHERE id=?";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, trainee.getFirstName());
            stmt.setString(2, trainee.getLastName());
            stmt.setInt(3, trainee.getRating());
            stmt.setInt(4, trainee.getId());
            stmt.executeUpdate();
        }
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        Trainee trainee = null;
        String selectQuery = "SELECT * FROM trainees WHERE id=?";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, traineeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    int rating = rs.getInt("rating");
                    trainee = new Trainee(id, firstName, lastName, rating);
                }
            }
        }
        return trainee;
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        Trainee trainee = null;
        String selectQuery = "SELECT * FROM trainees WHERE id=?";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, traineeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    String firstName = rs.getString(3);
                    String lastName = rs.getString(4);
                    int rating = rs.getInt(5);
                    trainee = new Trainee(id, firstName, lastName, rating);
                }
            }
        }
        return trainee;
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        Map<Integer, Trainee> trainees = new TreeMap<>();
        String selectQuery = "SELECT * FROM trainees";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int rating = rs.getInt("rating");
                trainees.put(id, new Trainee(id, firstName, lastName, rating));
            }
        }
        return new ArrayList<>(trainees.values());
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        Map<Integer, Trainee> trainees = new TreeMap<>();
        String selectQuery = "SELECT * FROM trainees";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                int rating = rs.getInt(5);
                trainees.put(id, new Trainee(id, firstName, lastName, rating));
            }
        }
        return new ArrayList<>(trainees.values());
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        String deleteQuery = "DELETE FROM trainees WHERE id=? AND firstName=? AND lastName=? AND rating=?";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(deleteQuery)) {
            stmt.setInt(1, trainee.getId());
            stmt.setString(2, trainee.getFirstName());
            stmt.setString(3, trainee.getLastName());
            stmt.setInt(4, trainee.getRating());
            stmt.executeUpdate();
        }
    }

    public static void deleteTrainees() throws SQLException {
        String deleteQuery = "DELETE FROM trainees";
        //String deleteQuery = "TRUNCATE TABLE trainees";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(deleteQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
        }
    }

    public static void insertSubject(Subject subject) throws SQLException {
        String insertQuery = "INSERT INTO subjects VALUES(?,?)";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, Types.INTEGER);
            stmt.setString(2, subject.getName());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    subject.setId(id);
                } else {
                    throw new SQLException("Creating subject failed, no ID obtained");
                }
            }
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        Subject subject = null;
        String selectQuery = "SELECT * FROM subjects WHERE id=?";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, subjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    subject = new Subject(id, name);
                }
            }
        }
        return subject;
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        Subject subject = null;
        String selectQuery = "SELECT * FROM subjects WHERE id=?";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, subjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    subject = new Subject(id, name);
                }
            }
        }
        return subject;
    }

    public static void deleteSubjects() throws SQLException {
        String deleteQuery = "DELETE FROM subjects";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(deleteQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
        }
    }

    public static void insertSchool(School school) throws SQLException {
        String insertQuery = "INSERT INTO schools VALUES(?,?,?)";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, Types.INTEGER);
            stmt.setString(2, school.getName());
            stmt.setInt(3, school.getYear());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    school.setId(id);
                } else {
                    throw new SQLException("Creating school failed, no ID obtained");
                }
            }
        }
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        School school = null;
        String selectQuery = "SELECT * FROM schools WHERE id=?";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, schoolId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int year = rs.getInt("year");
                    school = new School(id, name, year);
                }
            }
        }
        return school;
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        School school = null;
        String selectQuery = "SELECT * FROM schools WHERE id=?";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, schoolId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    int year = rs.getInt(3);
                    school = new School(id, name, year);
                }
            }
        }
        return school;
    }

    public static void deleteSchools() throws SQLException {
        String deleteQuery = "DELETE FROM schools";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(deleteQuery)) {
            stmt.executeUpdate();
        }
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        String insertQuery = "INSERT INTO groups VALUES(?,?,?,?)";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setNull(1, Types.INTEGER);
            stmt.setInt(2, school.getId());
            stmt.setString(3, group.getName());
            stmt.setString(4, group.getRoom());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    group.setId(id);
                } else {
                    throw new SQLException("Creating group failed, no ID obtained");
                }
            }
        }
    }

    public static School getSchoolByIdWithGroups(int schoolId) throws SQLException {
        School school = null;
        List<Group> groups = new ArrayList<>();
        String selectQuery = "SELECT * FROM schools LEFT OUTER JOIN groups ON schoolId=?";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(selectQuery)) {
            stmt.setInt(1, schoolId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    if (id == schoolId) {
                        if (school == null) {
                            String name = rs.getString(2);
                            int year = rs.getInt(3);
                            school = new School(id, name, year);

                            int groupId = rs.getInt(4);
                            String groupName = rs.getString(6);
                            String groupRoom = rs.getString(7);
                            if (groupName != null && groupRoom != null) {
                                Group group = new Group(groupId, groupName, groupRoom);
                                groups.add(group);
                            }
                        } else {
                            int groupId2 = rs.getInt(4);
                            String groupName2 = rs.getString(6);
                            String groupRoom2 = rs.getString(7);
                            if (groupName2 != null && groupRoom2 != null) {
                                Group group2 = new Group(groupId2, groupName2, groupRoom2);
                                groups.add(group2);
                            }
                        }
                    }
                }
                if (school != null) {
                    school.setGroups(groups);
                }
            }
        }
        return school;
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        Map<Integer, School> id_school = new TreeMap<>();
        School school;
        String selectQuery = "SELECT * FROM schools LEFT OUTER JOIN groups ON schoolId=schools.id";
        Connection con = JdbcUtils.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt(1);
                if (!id_school.containsKey(id)) {
                    String name = rs.getString(2);
                    int year = rs.getInt(3);
                    school = new School(id, name, year);
                    id_school.put(id, school);
                }
                int groupId = rs.getInt(4);
                int schoolId = rs.getInt(5);
                String groupName = rs.getString(6);
                String groupRoom = rs.getString(7);
                if (groupName == null && groupRoom == null) {
                    continue;
                }
                Group group = new Group(groupId, groupName, groupRoom);
                id_school.get(schoolId).addGroup(group);
            }
        }
        return new ArrayList<>(id_school.values());
    }

}




